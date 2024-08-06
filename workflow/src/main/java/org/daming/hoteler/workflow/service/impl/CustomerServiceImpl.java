package org.daming.hoteler.workflow.service.impl;

import org.daming.hoteler.common.constants.ErrorCodeConstants;
import org.daming.hoteler.common.errors.IErrorService;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.pojo.Customer;
import org.daming.hoteler.workflow.repository.CustomerMapper;
import org.daming.hoteler.workflow.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author daming
 * @version 2023-04-14 17:46
 **/
@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerMapper customerMapper;

    private IErrorService errorService;

    @Override
    public String create(Customer customer) throws HotelerException {
        try {
            return this.customerMapper.create(customer);
        } catch (DataAccessException dae) {
            throw this.errorService.createHotelerException(ErrorCodeConstants.SQL_ERROR_CODE, new Object[]{dae.getMessage()}, dae.getRootCause());
        }
    }

    @Override
    public void update(Customer customer) throws HotelerException {
        try {
            this.customerMapper.update(customer);
        } catch (DataAccessException dae) {
            throw this.errorService.createHotelerException(ErrorCodeConstants.SQL_ERROR_CODE, new Object[]{dae.getMessage()}, dae.getRootCause());
        }
    }

    @Override
    public Customer get(String id) throws HotelerException {
        try {
            return this.customerMapper.get(id);
        } catch (DataAccessException dae) {
            throw this.errorService.createHotelerException(ErrorCodeConstants.SQL_ERROR_CODE, new Object[]{dae.getMessage()}, dae.getRootCause());
        }
    }

    @Override
    public List<Customer> list() throws HotelerException {
        try {
            return this.customerMapper.list();
        } catch (DataAccessException dae) {
            throw this.errorService.createHotelerException(ErrorCodeConstants.SQL_ERROR_CODE, new Object[]{dae.getMessage()}, dae.getRootCause());
        }
    }

    @Override
    public void delete(String id) throws HotelerException {
        try {
            this.customerMapper.delete(id);
        } catch (DataAccessException dae) {
            throw this.errorService.createHotelerException(ErrorCodeConstants.SQL_ERROR_CODE, new Object[]{dae.getMessage()}, dae.getRootCause());
        }
    }

    @Autowired
    public void setErrorService(IErrorService errorService) {
        this.errorService = errorService;
    }

    public CustomerServiceImpl(CustomerMapper customerMapper) {
        super();
        this.customerMapper = customerMapper;
    }
}
