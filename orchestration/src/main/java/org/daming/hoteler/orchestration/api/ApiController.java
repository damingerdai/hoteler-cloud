package  org.daming.hoteler.orchestration.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path ="api/v1")
public class ApiController {

    @GetMapping(path = "ping")
    public Mono<String> get() {
        return Mono.just("pong");
    }
}