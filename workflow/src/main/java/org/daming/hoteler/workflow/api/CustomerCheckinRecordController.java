package org.daming.hoteler.workflow.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.daming.hoteler.common.response.DataResponse;
import org.daming.hoteler.workflow.pojo.CustomerCheckinRecord;
import org.daming.hoteler.workflow.pojo.request.CreateCustomerCheckinRecordRequest;
import org.daming.hoteler.workflow.service.ICustomerCheckinRecordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gming001
 * @version 2024-01-18 19:58
 */
@RestController
public class CustomerCheckinRecordController {

    private final ICustomerCheckinRecordService customerCheckinRecordService;

    @Operation(summary = "创建用户入住记录", security = { @SecurityRequirement(name = "bearer-key") })
    @PostMapping("customer-checkin-record")
    public DataResponse<String> createCustomerCheckinRecord(@RequestBody CreateCustomerCheckinRecordRequest request) {
        var ur = new CustomerCheckinRecord()
                .setCustomerId(request.getCustomerId())
                .setRoomId(request.getRoomId())
                .setBeginDate(request.getBeginDate())
                .setEndDate(request.getEndDate());
        this.customerCheckinRecordService.create(ur);
        var id = ur.getId();
        return new DataResponse<>(id);
    }

    public CustomerCheckinRecordController(ICustomerCheckinRecordService customerCheckinRecordService) {
        this.customerCheckinRecordService = customerCheckinRecordService;
    }
}
