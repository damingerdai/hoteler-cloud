package org.daming.hoteler.user.service.impl;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.common.utils.CommonUtils;
import org.daming.hoteler.common.utils.JwtUtil;
import org.daming.hoteler.security.service.IPasswordService;
import org.daming.hoteler.user.config.service.ISecretPropService;
import org.daming.hoteler.user.domain.User;
import org.daming.hoteler.user.domain.UserToken;
import org.daming.hoteler.user.service.ITokenService;
import org.daming.hoteler.user.service.IUserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;

@Service
public class TokenServiceImpl implements ITokenService, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private IUserService userService;

    private ISecretPropService secretPropService;

    @Override
    public UserToken createToken(String username, String password) throws HotelerException {
        var user = userService.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new RuntimeException("no user or password error");
        }
        var passwordType = CommonUtils.isNotEmpty(user.getPasswordType()) ? user.getPasswordType() : "noop";
        var passwordService = this.getPasswordService(passwordType);
        if (!user.getPassword().equals(passwordService.encodePassword(password))) {
            throw new RuntimeException("no user or password error");
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
        return null;
    }

    @Override
    public User verifyToken(String token) throws HotelerException {
        return null;
    }

    private IPasswordService getPasswordService(String passwordType) {
        return Objects.requireNonNull(this.applicationContext).getBean(passwordType + "PasswordService", IPasswordService.class);
    }

    public TokenServiceImpl(IUserService userService, ISecretPropService secretPropService) {
        super();
        this.userService = userService;
        this.secretPropService = secretPropService;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
