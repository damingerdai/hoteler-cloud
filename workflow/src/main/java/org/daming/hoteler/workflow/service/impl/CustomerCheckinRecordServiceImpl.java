package org.daming.hoteler.workflow.service.impl;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.common.logger.LoggerManager;
import org.daming.hoteler.common.utils.DateUtils;
import org.daming.hoteler.workflow.pojo.CustomerCheckinRecord;
import org.daming.hoteler.workflow.pojo.enums.RoomStatus;
import org.daming.hoteler.workflow.repository.CustomerCheckinRecordMapper;
import org.daming.hoteler.workflow.service.ICustomerCheckinRecordService;
import org.daming.hoteler.workflow.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * @author gming001
 * @version 2024-01-18 17:30
 */
@Service
public class CustomerCheckinRecordServiceImpl implements ICustomerCheckinRecordService {

    private final CustomerCheckinRecordMapper customerCheckinRecordMapper;

    private IRoomService roomService;

    @Override
    public String create(CustomerCheckinRecord customerCheckinRecord) throws HotelerException {
        var id = "";
        var beginDate = customerCheckinRecord.getBeginDate().toLocalDate();
        var endDate =  customerCheckinRecord.getEndDate().toLocalDate();
        var durations = DateUtils.getDates(beginDate, endDate);
        for (LocalDate begin : durations) {
            var end = begin.plusDays(1L);
            var beginDateTime = begin.atTime(12, 0, 0);
            var endDateTime = end.atTime(12, 0, 0);
            var record = new CustomerCheckinRecord();
            record.setBeginDate(beginDateTime);
            record.setEndDate(endDateTime);
            record.setRoomId(customerCheckinRecord.getRoomId());
            record.setCustomerId(customerCheckinRecord.getCustomerId());

            if (DateUtils.isToday(begin)) {
                id = this.doCreate(record);
            } else {
                LoggerManager.getLogger(ICustomerCheckinRecordService.class.getName()).warn("skip", record);
                // this.dispatchCustomerCheckinRecord(record);
            }
        }

        return id;
    }


    private String doCreate(CustomerCheckinRecord customerCheckinRecord) throws HotelerException {
        var id = this.customerCheckinRecordMapper.create(customerCheckinRecord);
        customerCheckinRecord.setId(id);
        this.processBeginDateAndEndDate(customerCheckinRecord);
        this.roomService.updateStatus(customerCheckinRecord.getRoomId(), RoomStatus.InUsed);
        return id;
    }

    private void processBeginDateAndEndDate(CustomerCheckinRecord customerCheckinRecord) {
        var beginDate = customerCheckinRecord.getBeginDate();
        customerCheckinRecord.setBeginDate(LocalDateTime.of(beginDate.toLocalDate(), LocalTime.of(12, 0)));
        var endDate = customerCheckinRecord.getEndDate();
        customerCheckinRecord.setEndDate(LocalDateTime.of(endDate.toLocalDate(), LocalTime.of(12, 0)));
    }

    @Override
    public void update(CustomerCheckinRecord customerCheckinRecord) throws HotelerException {
        this.processBeginDateAndEndDate(customerCheckinRecord);
        this.customerCheckinRecordMapper.update(customerCheckinRecord);
        this.roomService.updateStatus(customerCheckinRecord.getRoomId(), RoomStatus.InUsed);
    }

    @Override
    public CustomerCheckinRecord get(String id) throws HotelerException {
        return this.customerCheckinRecordMapper.get(id);
    }

    @Override
    public void delete(String id) throws HotelerException {
        var record = this.customerCheckinRecordMapper.get(id);
        if (Objects.nonNull(record)) {
            this.roomService.updateStatus(record.getRoomId(), RoomStatus.InUsed);
            this.customerCheckinRecordMapper.delete(id);
        }
    }

    @Override
    public List<CustomerCheckinRecord> list() throws HotelerException {
        return this.customerCheckinRecordMapper.list();
    }

    @Override
    public List<CustomerCheckinRecord> listCurrentDate() throws HotelerException {
        return null;
    }

    @Override
    public List<CustomerCheckinRecord> listByRoomIdAndDate(long roomId, LocalDate date) {
        return null;
    }

    @Autowired
    public void setRoomService(IRoomService roomService) {
        this.roomService = roomService;
    }

    public CustomerCheckinRecordServiceImpl(CustomerCheckinRecordMapper customerCheckinRecordMapper) {
        super();
        this.customerCheckinRecordMapper = customerCheckinRecordMapper;
    }
}
