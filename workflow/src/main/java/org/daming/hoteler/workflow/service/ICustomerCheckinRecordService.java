package org.daming.hoteler.workflow.service;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.pojo.CustomerCheckinRecord;

import java.time.LocalDate;
import java.util.List;

/**
 * @author gming001
 * @version 2024-01-18 17:30
 */
public interface ICustomerCheckinRecordService {

    String create(CustomerCheckinRecord customerCheckinRecord) throws HotelerException;

    void update(CustomerCheckinRecord customerCheckinRecord) throws HotelerException;

    CustomerCheckinRecord get(String id) throws HotelerException;

    void delete(String id) throws HotelerException;

    List<CustomerCheckinRecord> list() throws HotelerException;

    List<CustomerCheckinRecord> listCurrentDate() throws HotelerException;

    List<CustomerCheckinRecord> listByRoomIdAndDate(long roomId, LocalDate date);
}
