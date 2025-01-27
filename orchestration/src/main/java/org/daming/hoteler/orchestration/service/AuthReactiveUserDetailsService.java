package org.daming.hoteler.orchestration.service;

import org.daming.hoteler.common.domain.User;
import org.daming.hoteler.common.response.DataResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthReactiveUserDetailsService implements ReactiveUserDetailsService {


    private final RestTemplate restTemplate;

    private final WebClient webClient;

    public AuthReactiveUserDetailsService(RestTemplate restTemplate, WebClient webClient) {
        super();
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    // @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var response = this.restTemplate.getForEntity("http://auth-service/user?username=" + username, DataResponse.class).getBody();
        var user = (User) response.getData();
        System.out.println(user);
        return user;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        System.out.println("username" + username);
        var response = this.webClient.get()
                .uri("http://auth-service/user?username=" + username)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<DataResponse<User>>() {
                });
        //.bodyToMono(Map.class);
        var user = response.map(res -> (UserDetails) res.getData());
        return user;
    }


}
