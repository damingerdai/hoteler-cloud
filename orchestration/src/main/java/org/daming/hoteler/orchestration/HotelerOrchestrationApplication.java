package  org.daming.hoteler.orchestration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gming001
 * @version 2022-12-21 21:04
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HotelerOrchestrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelerOrchestrationApplication.class, args);
    }
}
