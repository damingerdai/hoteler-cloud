package org.daming.hoteler.orchestration.service;

import org.daming.hoteler.common.domain.User;
import org.daming.hoteler.common.response.DataResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TokenService {

    private final WebClient webClient;

    public TokenService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<User> validateToken(String token) {
        var response = this.webClient.get()
                .uri("http://auth-service/token")
                .header("accessToken", token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<User>() {
                });

        return response;
    }
}
