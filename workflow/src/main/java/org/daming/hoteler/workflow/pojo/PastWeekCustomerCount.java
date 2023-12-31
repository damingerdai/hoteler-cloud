package org.daming.hoteler.workflow.pojo;

import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * @author gming001
 * @version 2023-12-31 18:37
 */
public class PastWeekCustomerCount {

    private LocalDate checkInDate;

    private int customerCount;

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public PastWeekCustomerCount setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
        return this;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public PastWeekCustomerCount setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
        return this;
    }

    public PastWeekCustomerCount(LocalDate checkInDate, int customerCount) {
        super();
        this.checkInDate = checkInDate;
        this.customerCount = customerCount;
    }

    public PastWeekCustomerCount() {
        super();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PastWeekCustomerCount.class.getSimpleName() + "[", "]")
                .add("checkInDate=" + checkInDate)
                .add("customerCount=" + customerCount)
                .toString();
    }
}
