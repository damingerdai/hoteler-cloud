package org.daming.hoteler.auth.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.domain.UserToken;
import org.daming.hoteler.auth.service.ITokenService;
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
    public UserToken createToken(@RequestHeader String username, @RequestHeader String password) {
        var userToken = this.tokenService.createToken(username, password);
        return userToken;
    }

    @PutMapping("")
    public UserToken verifyToken(@RequestHeader String accessToken) {
        var userToken = this.tokenService.refreshToken(accessToken);

        return userToken;
    }

    @GetMapping("")
    public User parseToken(@RequestHeader String accessToken) {
        System.out.println(accessToken);
        var user = this.tokenService.verifyToken(accessToken);
        System.out.println(user);
        return user;
    }

    public TokenController(ITokenService tokenService) {
        super();
        this.tokenService = tokenService;
    }
}
