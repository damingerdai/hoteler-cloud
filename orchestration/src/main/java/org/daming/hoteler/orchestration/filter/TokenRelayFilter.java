package org.daming.hoteler.orchestration.filter;

import org.daming.hoteler.common.domain.User;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TokenRelayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // TODO: 1. Retrieve the Authentication object from ReactiveSecurityContextHolder
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .flatMap(authentication -> {
                    Object details = authentication.getDetails();
                    if (details instanceof User user) {
                        String userId = String.valueOf(user.getId());

                        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                                .header("X-Auth-User-Id", userId)
                                .header("X-Auth-User-Name", user.getUsername())
                                .build();
                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
                    }

                    return chain.filter(exchange);
                })
                .switchIfEmpty(Mono.defer(() -> chain.filter(exchange))); // Not authenticated, continue (will be intercepted by SecurityConfig)
    }

    @Override
    public int getOrder() {
        // Ensure execution happens AFTER the AUTHENTICATION process
        return Ordered.LOWEST_PRECEDENCE;
    }
}
