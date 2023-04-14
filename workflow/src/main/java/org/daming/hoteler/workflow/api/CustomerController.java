package org.daming.hoteler.workflow.api;

import org.daming.hoteler.workflow.pojo.Customer;
import org.daming.hoteler.workflow.pojo.request.CreateCustomerRequest;
import org.daming.hoteler.workflow.service.ICustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daming
 * @version 2023-04-14 17:54
 **/
@RestController
public class CustomerController {

    private final ICustomerService customerService;

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


    public CustomerController(ICustomerService customerService) {
        super();
        this.customerService = customerService;
    }
}
