package org.daming.hoteler.orchestration.auth;

import org.daming.hoteler.orchestration.service.TokenService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final TokenService tokenService;

    public JwtAuthenticationManager(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        try {
            var token = (String) authentication.getCredentials();
            return this.tokenService.validateToken(token).map((user) -> {
               var authorities = user.getAuthorities();

                var authenticatedToken = new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        authorities
                );
                authenticatedToken.setDetails(user);

                return authenticatedToken;
            });
        } catch(Exception ex) {
            return Mono.error(new BadCredentialsException("Invalid token"));
        }

    }
}
