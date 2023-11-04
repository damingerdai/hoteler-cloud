package org.daming.hoteler.auth.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.service.ITokenService;
import org.daming.hoteler.common.response.UserTokenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Token Controller")
@RequestMapping("token")
public class TokenController {

    private final ITokenService tokenService;

    @PostMapping("")
    public UserTokenResponse createToken(@RequestHeader String username, @RequestHeader String password) {
        var userToken = this.tokenService.createToken(username, password);
        return new UserTokenResponse(userToken);
    }

    @PutMapping("")
    public UserTokenResponse verifyToken(@RequestHeader String accessToken) {
        var userToken = this.tokenService.refreshToken(accessToken);

        return new UserTokenResponse(userToken);
    }

    @GetMapping("")
    public User parseToken(@RequestHeader String accessToken) {
        var user = this.tokenService.verifyToken(accessToken);

        return user;
    }

    public TokenController(ITokenService tokenService) {
        super();
        this.tokenService = tokenService;
    }
}
