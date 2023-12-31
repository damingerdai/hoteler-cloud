package org.daming.hoteler.workflow.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.daming.hoteler.common.response.DataResponse;
import org.daming.hoteler.workflow.pojo.stat.PastWeekCustomerCountStat;
import org.daming.hoteler.workflow.pojo.stat.RoomNumsStat;
import org.daming.hoteler.workflow.pojo.stat.RoomStatusStat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gming001
 * @version 2023-12-31 18:32
 */
@Tag(name = "统计服务接口")
@RestController
@RequestMapping("stat")
public class StatController {

    @Operation(summary = "获取房间状态", security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping(path = "rooms")
    public DataResponse<RoomStatusStat> getRoomStatusStat() {
        var roomStatusStat = new RoomStatusStat().setCurrentWeekInUsedRoomNum(10).setLastWeekInUsedRoomNum(12);
        return new DataResponse(roomStatusStat);
    }

    @Operation(summary = "获取房间数量统计", security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping(path = "rooms/nums")
    public DataResponse<RoomNumsStat> getRoomNumStat() {
        var roomNumsStat = new RoomNumsStat().setTotalNums(10).setNotUsedNums(4).setInUseNums(6);
        return new DataResponse(roomNumsStat);
    }

    @Operation(summary = "获取客户数量统计", security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping(path = "customers/counts")
    public DataResponse<PastWeekCustomerCountStat> getPastWeekCustomersCounts() {
        var pastWeekCustomerCountStat = new PastWeekCustomerCountStat();
        return new DataResponse(pastWeekCustomerCountStat);
    }

}
