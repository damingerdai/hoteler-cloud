package  org.daming.hoteler.workflow.api;

import org.daming.hoteler.workflow.service.IPingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gming001
 * @version 2022-12-21 21:23
 */
@RestController
public class PingController {

    private IPingService pingService;

    @RequestMapping(path = "ping")
    public String ping() {
        return this.pingService.ping();
    }

    public PingController(IPingService pingService) {
        this.pingService = pingService;
    }
}
