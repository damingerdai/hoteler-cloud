package org.daming.hoteler.auth.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.daming.hoteler.auth.service.IPingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gming001
 * @version 2022-12-02 09:21
 */
@Tag(name = "Ping Controller")
@RestController
public class PingController {

    private final IPingService pingService;

    @Operation(summary = "ping接口")
    @RequestMapping(path = "ping")
    public String ping() {
        return this.pingService.ping();
    }

    public PingController(IPingService pingService) {
        super();
        this.pingService = pingService;
    }
}
