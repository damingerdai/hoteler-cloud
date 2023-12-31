package org.daming.hoteler.workflow.service.impl;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.pojo.Customer;
import org.daming.hoteler.workflow.repository.CustomerMapper;
import org.daming.hoteler.workflow.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author daming
 * @version 2023-04-14 17:46
 **/
@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerMapper customerMapper;

    @Override
    public String create(Customer customer) throws HotelerException {
        return this.customerMapper.create(customer);
    }

    @Override
    public void update(Customer customer) throws HotelerException {
        this.customerMapper.update(customer);
    }

    @Override
    public Customer get(String id) throws HotelerException {
        return this.customerMapper.get(id);
    }

    @Override
    public List<Customer> list() throws HotelerException {
        return null;
    }

    @Override
    public void delete(String id) throws HotelerException {

    }

    public CustomerServiceImpl(CustomerMapper customerMapper) {
        super();
        this.customerMapper = customerMapper;
    }
}
