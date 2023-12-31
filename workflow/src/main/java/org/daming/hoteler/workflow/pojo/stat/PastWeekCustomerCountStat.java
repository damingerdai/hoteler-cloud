package org.daming.hoteler.workflow.pojo.stat;

import org.daming.hoteler.workflow.pojo.PastWeekCustomerCount;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author gming001
 * @version 2023-12-31 18:36
 */
public class PastWeekCustomerCountStat {

    private List<PastWeekCustomerCount> pastWeekCustomerCounts;

    public List<PastWeekCustomerCount> getPastWeekCustomerCounts() {
        return pastWeekCustomerCounts;
    }

    public PastWeekCustomerCountStat setPastWeekCustomerCounts(List<PastWeekCustomerCount> pastWeekCustomerCounts) {
        this.pastWeekCustomerCounts = pastWeekCustomerCounts;
        return this;
    }

    public PastWeekCustomerCountStat(List<PastWeekCustomerCount> pastWeekCustomerCounts) {
        super();
        this.pastWeekCustomerCounts = pastWeekCustomerCounts;
    }

    public PastWeekCustomerCountStat() {
        super();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PastWeekCustomerCountStat.class.getSimpleName() + "[", "]")
                .add("pastWeekCustomerCounts=" + pastWeekCustomerCounts)
                .toString();
    }
}
