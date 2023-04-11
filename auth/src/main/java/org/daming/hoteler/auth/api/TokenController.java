package org.daming.hoteler.auth.api;

import org.daming.hoteler.auth.domain.UserToken;
import org.daming.hoteler.auth.service.ITokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
public class TokenController {

    private ITokenService tokenService;

    @PostMapping("")
    public UserToken createToken(@RequestHeader String username, @RequestHeader String password) {
        var userToken = tokenService.createToken(username, password);
        return userToken;

    }

    public TokenController(ITokenService tokenService) {
        super();
        this.tokenService = tokenService;
    }
}
