package org.daming.hoteler.workflow.pojo.request;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * @author gming001
 * @version 2024-01-18 19:56
 */
public class CreateCustomerCheckinRecordRequest {

    private String customerId;

    private String roomId;

    private LocalDateTime beginDate;

    private LocalDateTime endDate;

    public String getCustomerId() {
        return customerId;
    }

    public CreateCustomerCheckinRecordRequest setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getRoomId() {
        return roomId;
    }

    public CreateCustomerCheckinRecordRequest setRoomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public CreateCustomerCheckinRecordRequest setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public CreateCustomerCheckinRecordRequest setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public CreateCustomerCheckinRecordRequest() {
        super();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CreateCustomerCheckinRecordRequest.class.getSimpleName() + "[", "]")
                .add("customerId='" + customerId + "'")
                .add("roomId='" + roomId + "'")
                .add("beginDate=" + beginDate)
                .add("endDate=" + endDate)
                .toString();
    }
}
