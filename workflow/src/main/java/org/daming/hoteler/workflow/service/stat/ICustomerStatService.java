package org.daming.hoteler.workflow.service.stat;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.pojo.stat.PastWeekCustomerCountStat;

/**
 * 客户统计服务
 */
public interface ICustomerStatService {

    PastWeekCustomerCountStat countPastWeekCustomerCountStat() throws HotelerException;
}
