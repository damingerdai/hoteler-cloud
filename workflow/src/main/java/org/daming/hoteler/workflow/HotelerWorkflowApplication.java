package  org.daming.hoteler.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gming001
 * @version 2022-12-21 21:04
 */
@EnableFeignClients
@SpringBootApplication
public class HotelerWorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelerWorkflowApplication.class, args);
    }
}
