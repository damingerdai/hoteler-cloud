package org.daming.hoteler.workflow.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.daming.hoteler.common.response.CommonResponse;
import org.daming.hoteler.common.response.ListResponse;
import org.daming.hoteler.workflow.pojo.Customer;
import org.daming.hoteler.workflow.pojo.request.CreateCustomerRequest;
import org.daming.hoteler.workflow.pojo.request.UpdateCustomerRequest;
import org.daming.hoteler.workflow.service.ICustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daming
 * @version 2023-04-14 17:54
 **/
@Tag(name = "客户", description = "客户 API")
@RestController
public class CustomerController {

    private final ICustomerService customerService;

    @Operation(
            summary = "创建客户信息", security = { @SecurityRequirement(name = "bearer-key") },
            parameters = {
                    @Parameter(name = "body", description = "创建用户信息的请求体")
            }
    )
    @PostMapping("customer")
    public Customer create(@RequestBody CreateCustomerRequest request) {
        var customer = new Customer()
                .setName(request.getName())
                .setGender(request.getGender())
                .setCardId(request.getCardId())
                .setPhone(request.getPhone());
        var id = this.customerService.create(customer);
        customer.setId(id);

        return customer;
    }

    @GetMapping("customer/{id}")
    public Customer get(@PathVariable String id) {
        var customer = this.customerService.get(id);

        return customer;
    }

    @Operation(summary = "更新客户信息", security = { @SecurityRequirement(name = "bearer-key") })
    @PutMapping("customer")
    public CommonResponse update(@RequestBody UpdateCustomerRequest request) {
        var customer = new Customer()
                .setId(request.getId()).
                setName(request.getName())
                .setGender(request.getGender())
                .setCardId(request.getCardId())
                .setPhone(request.getPhone());
        this.customerService.update(customer);
        return new CommonResponse();
    }

    @Operation(summary = "获取所有的客户信息", security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping("customers")
    public CommonResponse list() {
        var list = this.customerService.list();
        return new ListResponse<>(list);
    }



    public CustomerController(ICustomerService customerService) {
        super();
        this.customerService = customerService;
    }
}
