package org.daming.hoteler.user.api;

import org.daming.hoteler.user.service.IPingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gming001
 * @version 2022-12-02 09:21
 */
@RestController
public class PingController {

    private final IPingService pingService;

    @RequestMapping(path = "ping")
    public String ping() {
        return this.pingService.ping();
    }

    public PingController(IPingService pingService) {
        super();
        this.pingService = pingService;
    }
}
