package org.daming.hoteler.orchestration.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Component
//public class TokenRelayFilter implements GlobalFilter, Ordered {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        // TODO: 1. Retrieve the Authentication object from ReactiveSecurityContextHolder
//        return ReactiveSecurityContextHolder.getContext()
//                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
//                .flatMap(authentication -> {
//                    // TODO: 2. Ensure the principal is of type TokenService.UserClaims
//                    // TODO: 3. Extract claims and credentials (raw token)
//                    // TODO: 4. Use exchange.getRequest().mutate().header(...) to forward the info to downstream Headers
//                    // Suggested Header names: X-Auth-User-Id, X-Auth-User-Role, X-Auth-Token
//
//                    return chain.filter(exchange.mutate().request(/* mutated request */).build()); // Forward the request
//                })
//                .switchIfEmpty(chain.filter(exchange)); // Not authenticated, continue (will be intercepted by SecurityConfig)
//    }
//
//    @Override
//    public int getOrder() {
//        // Ensure execution happens AFTER the AUTHENTICATION process
//        return Ordered.LOWEST_PRECEDENCE;
//    }
//}
