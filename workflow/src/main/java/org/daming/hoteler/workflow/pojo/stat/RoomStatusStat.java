package org.daming.hoteler.workflow.pojo.stat;

import java.util.StringJoiner;

/**
 * @author gming001
 * @version 2023-12-31 18:32
 */
public class RoomStatusStat {

    private int lastWeekInUsedRoomNum;

    private int currentWeekInUsedRoomNum;

    public int getLastWeekInUsedRoomNum() {
        return lastWeekInUsedRoomNum;
    }

    public RoomStatusStat setLastWeekInUsedRoomNum(int lastWeekInUsedRoomNum) {
        this.lastWeekInUsedRoomNum = lastWeekInUsedRoomNum;
        return this;
    }

    public int getCurrentWeekInUsedRoomNum() {
        return currentWeekInUsedRoomNum;
    }

    public RoomStatusStat setCurrentWeekInUsedRoomNum(int currentWeekInUsedRoomNum) {
        this.currentWeekInUsedRoomNum = currentWeekInUsedRoomNum;
        return this;
    }

    public RoomStatusStat(int lastWeekInUsedRoomNum, int currentWeekInUsedRoomNum) {
        super();
        this.lastWeekInUsedRoomNum = lastWeekInUsedRoomNum;
        this.currentWeekInUsedRoomNum = currentWeekInUsedRoomNum;
    }

    public RoomStatusStat() {
        super();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RoomStatusStat.class.getSimpleName() + "[", "]")
                .add("lastWeekInUsedRoomNum=" + lastWeekInUsedRoomNum)
                .add("currentWeekInUsedRoomNum=" + currentWeekInUsedRoomNum)
                .toString();
    }
}
