package org.daming.hoteler.orchestration.config;

import org.daming.hoteler.orchestration.auth.JwtAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.security.config.Customizer.withDefaults;


@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
@Configuration
public class WebFluxSecurityConfig {

    private JwtAuthenticationManager jwtAuthenticationManager;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        var authenticationWebFilter = new AuthenticationWebFilter(jwtAuthenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(this.createEnhancedAuthenticationConverter());
        // authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges ->
                        exchanges.pathMatchers("/login").permitAll()
                                .pathMatchers("/api/v1/token").permitAll()
                                .pathMatchers("swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .pathMatchers( "/*/v3/api-docs/**").permitAll()
                                .pathMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
                        .anyExchange().authenticated()
                )
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .addFilterAt(authenticationWebFilter,  SecurityWebFiltersOrder.AUTHENTICATION)
                .exceptionHandling(exceptionHandlingSpec ->
                        exceptionHandlingSpec.authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED))
                );
        return http.build();
    }

    private ServerAuthenticationConverter createEnhancedAuthenticationConverter() {
        return exchange -> {
            // 优先级1：从Authorization头获取
            String tokenFromHeader = extractTokenFromHeader(exchange);
            if (tokenFromHeader != null) {
                return Mono.just(new UsernamePasswordAuthenticationToken(tokenFromHeader, tokenFromHeader));
            }

            // 优先级2：从查询参数获取（可选）
            String tokenFromQuery = extractTokenFromQuery(exchange);
            if (tokenFromQuery != null) {
                return Mono.just(new UsernamePasswordAuthenticationToken(tokenFromQuery, tokenFromQuery));
            }

            // 没有找到Token
            return Mono.empty();
        };
    }

    private String extractTokenFromHeader(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7).trim();
        }
        return extractTokenFromCustomHeader(exchange);
    }

    private String extractTokenFromCustomHeader(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("accessToken");
        return (authHeader != null && !authHeader.trim().isEmpty()) ? authHeader.trim() : null;
    }

    private String extractTokenFromQuery(ServerWebExchange exchange) {
        // 从access_token查询参数获取
        String tokenParam = exchange.getRequest().getQueryParams().getFirst("access_token");
        return (tokenParam != null && !tokenParam.trim().isEmpty()) ? tokenParam.trim() : null;
    }


    @Autowired
    public void setJwtAuthenticationManager(JwtAuthenticationManager jwtAuthenticationManager) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
