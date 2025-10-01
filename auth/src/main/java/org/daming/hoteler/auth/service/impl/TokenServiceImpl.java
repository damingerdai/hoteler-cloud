package org.daming.hoteler.auth.service.impl;

import io.jsonwebtoken.ExpiredJwtException;
import org.daming.hoteler.common.domain.UserToken;
import org.daming.hoteler.common.errors.IErrorService;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.common.logger.LoggerManager;
import org.daming.hoteler.common.utils.CommonUtils;
import org.daming.hoteler.common.utils.JwtUtil;
import org.daming.hoteler.security.service.IPasswordService;
import org.daming.hoteler.auth.config.service.ISecretPropService;
import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.service.ITokenService;
import org.daming.hoteler.auth.service.IUserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Service
public class TokenServiceImpl implements ITokenService, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final IUserService userService;

    private final ISecretPropService secretPropService;

    private final IErrorService errorService;

    @Override
    public UserToken createToken(String username, String password) throws HotelerException {
        var user = userService.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw this.errorService.createHotelerException(600005);
        }
        if (this.userService.isAccountLocked(user)) {
            LoggerManager.getCommonLogger().error("user {} is locked", user);
            throw this.errorService.createHotelerException(600014);
        }
        var passwordType = CommonUtils.isNotEmpty(user.getPasswordType()) ? user.getPasswordType() : "noop";
        var passwordService = this.getPasswordService(passwordType);
        if (!user.getPassword().equals(passwordService.encodePassword(password))) {
            this.userService.loginFailed(user);
            throw this.errorService.createHotelerException(600005);
        }
        return doCreateToken(user);
    }

    private UserToken doCreateToken(User user) {
        var id = String.valueOf(user.getId());
        var subject = user.getId() + "@" + user.getUsername();
        var ttlMillis = Duration.ofHours(1L).toMillis();
        var key = JwtUtil.generalKey(this.secretPropService.getKey());
        var claims = new HashMap<String, Object>();

        var accessToken = JwtUtil.createJWT(id, subject, ttlMillis, key, claims);
        var refreshToken = JwtUtil.createJWT(id, subject, ttlMillis + Duration.ofHours(23L).toMillis(), key, claims);
        var exp = Instant.now().toEpochMilli() + ttlMillis;
        return new UserToken().setAccessToken(accessToken).setRefreshToken(refreshToken).setExp(exp);
    }

    @Override
    public UserToken refreshToken(String refreshToken) throws HotelerException {
        try {
            var key = JwtUtil.generalKey(this.secretPropService.getKey());
            var claim = JwtUtil.parseJwt(refreshToken, key);
            var sub = claim.getSubject();
            var subs = sub.split("@");
            var userId = subs[0];
            var username = subs[1];
            var user = Optional.ofNullable(userService.get(Integer.parseInt(userId)))
                    .orElseThrow(() ->  this.errorService.createHotelerException(600005));
            return doCreateToken(user);
        } catch (ExpiredJwtException ex) {
            throw this.errorService.createHotelerException(600009, ex);
        }

    }

    @Override
    public User verifyToken(String token) throws HotelerException {
        try {
            var key = JwtUtil.generalKey(this.secretPropService.getKey());
            var claim = JwtUtil.parseJwt(token, key);
            var sub = claim.getSubject();
            var subs = sub.split("@");
            var userId = subs[0];
            var username = subs[1];
            var user = userService.get(Integer.parseInt(userId));
            if (Objects.isNull(user)) {
                this.errorService.createHotelerException(600005);
            }
            user.setPassword("");
            return user;
        } catch (ExpiredJwtException ex) {
            throw this.errorService.createHotelerException(600010, ex);
        }

    }

    private IPasswordService getPasswordService(String passwordType) {
        return Objects.requireNonNull(this.applicationContext).getBean(passwordType + "PasswordService", IPasswordService.class);
    }

    public TokenServiceImpl(IUserService userService, ISecretPropService secretPropService, IErrorService errorService) {
        super();
        this.userService = userService;
        this.secretPropService = secretPropService;
        this.errorService = errorService;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
