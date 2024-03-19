package org.daming.hoteler.workflow.service.stat;


import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.pojo.stat.RoomNumsStat;
import org.daming.hoteler.workflow.pojo.stat.RoomStatusStat;

/**
 * 房间状态统计处理服务
 */
public interface IRoomStatusStatService {
    RoomStatusStat countRoomStatusStatistics() throws HotelerException;

    RoomNumsStat countRoomNumStatistics() throws HotelerException;
}
