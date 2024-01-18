package org.daming.hoteler.workflow.pojo;

import java.time.LocalDateTime;

/**
 * @author gming001
 * @version 2024-01-04 20:35
 */
public class CustomerCheckinRecord {

    private String id;

    private String customerId;

    private String roomId;

    private LocalDateTime beginDate;

    private LocalDateTime endDate;

    public String getId() {
        return id;
    }

    public CustomerCheckinRecord setId(String id) {
        this.id = id;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public CustomerCheckinRecord setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getRoomId() {
        return roomId;
    }

    public CustomerCheckinRecord setRoomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public CustomerCheckinRecord setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public CustomerCheckinRecord setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public CustomerCheckinRecord() {
        super();
    }
}
