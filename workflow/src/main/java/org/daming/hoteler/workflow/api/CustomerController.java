package org.daming.hoteler.workflow.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.daming.hoteler.common.domain.Role;
import org.daming.hoteler.common.response.CommonResponse;
import org.daming.hoteler.common.response.DataResponse;
import org.daming.hoteler.common.response.ListResponse;
import org.daming.hoteler.workflow.pojo.Customer;
import org.daming.hoteler.workflow.pojo.request.CreateCustomerRequest;
import org.daming.hoteler.workflow.pojo.request.UpdateCustomerRequest;
import org.daming.hoteler.workflow.service.ICustomerService;
import org.daming.hoteler.workflow.service.feign.IUserFeignService;
import org.springframework.web.bind.annotation.*;

/**
 * @author daming
 * @version 2023-04-14 17:54
 **/
@Tag(name = "客户", description = "客户 API")
@RestController
public class CustomerController {

    private final ICustomerService customerService;
    private final IUserFeignService userFeignService;

    @Operation(
            summary = "创建客户信息", security = { @SecurityRequirement(name = "bearer-key") },
            parameters = {
                    @Parameter(name = "body", description = "创建用户信息的请求体")
            }
    )
    @PostMapping("customer")
    public DataResponse<Customer> create(@RequestBody CreateCustomerRequest request) {
        var customer = new Customer()
                .setName(request.getName())
                .setGender(request.getGender())
                .setCardId(request.getCardId())
                .setPhone(request.getPhone());
        var id = this.customerService.create(customer);
        customer.setId(id);
        var response = new DataResponse<>(customer);

        return response;
    }

    @GetMapping("customer/{id}")
    public DataResponse<Customer> get(@PathVariable String id) {
        var customer = this.customerService.get(id);
        var response = new DataResponse<>(customer);

        return response;
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


    @DeleteMapping("customer/{id}")
    public CommonResponse delete(@PathVariable String id, @RequestHeader(value = "Authorization") String accessToken) {
        var userResponse = this.userFeignService.getUserByAccessToken(accessToken);
        System.out.println("userResponse");
        System.out.println(userResponse);
        if (userResponse == null || userResponse.getStatus() != CommonResponse.SUCCESS_STATUS) {
           throw new RuntimeException("非法操作");
        }
        var user = userResponse.getData();
        if (user == null) {
            throw new RuntimeException("非法操作");
        }
        if (user.getRoles().stream().map(Role::getName).noneMatch(r -> r.equalsIgnoreCase("admin"))) {
            throw new RuntimeException("非法操作，只有管理员才有权限");
        }
        this.customerService.delete(id);
        return new CommonResponse();
    }


    public CustomerController(ICustomerService customerService, IUserFeignService userFeignService) {
        super();
        this.customerService = customerService;
        this.userFeignService = userFeignService;
    }

}
