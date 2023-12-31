package org.daming.hoteler.workflow.pojo.stat;

import java.util.StringJoiner;

/**
 * @author gming001
 * @version 2023-12-31 18:34
 */
public class RoomNumsStat {

    /**
     * 总数
     */
    private int totalNums;

    /**
     * 正在使用的数量
     */
    private int inUseNums;

    /**
     * 空闲的数量
     */
    private int notUsedNums;

    public int getTotalNums() {
        return totalNums;
    }

    public RoomNumsStat setTotalNums(int totalNums) {
        this.totalNums = totalNums;
        return this;
    }

    public int getInUseNums() {
        return inUseNums;
    }

    public RoomNumsStat setInUseNums(int inUseNums) {
        this.inUseNums = inUseNums;
        return this;
    }

    public int getNotUsedNums() {
        return this.notUsedNums;
    }

    public RoomNumsStat setNotUsedNums(int notUsedNums) {
        this.notUsedNums = notUsedNums;
        return this;
    }

    public RoomNumsStat(int totalNums, int inUseNums, int notUsedNums) {
        super();
        this.totalNums = totalNums;
        this.inUseNums = inUseNums;
        this.notUsedNums = notUsedNums;
    }

    public RoomNumsStat(int inUseNums, int notUsedNums) {
        super();
        this.totalNums = inUseNums + notUsedNums;
        this.inUseNums = inUseNums;
        this.notUsedNums = notUsedNums;
    }

    public RoomNumsStat() {
        super();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RoomNumsStat.class.getSimpleName() + "[", "]")
                .add("totalNums=" + totalNums)
                .add("inUseNums=" + inUseNums)
                .add("notUsedNums=" + notUsedNums)
                .toString();
    }
}
