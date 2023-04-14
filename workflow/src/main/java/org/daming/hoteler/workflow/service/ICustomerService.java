package org.daming.hoteler.workflow.service;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.pojo.Customer;

import java.util.List;

/**
 * @author daming
 * @version 2023-04-14 17:45
 **/
public interface ICustomerService {

    String create(Customer customer) throws HotelerException;

    void update(Customer customer) throws HotelerException;

    Customer get(String id) throws HotelerException;

    List<Customer> list() throws HotelerException;

    void delete(String id) throws HotelerException;
}
