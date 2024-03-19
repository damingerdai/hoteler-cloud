package org.daming.hoteler.workflow.service.stat.impl;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.pojo.Room;
import org.daming.hoteler.workflow.pojo.enums.RoomStatus;
import org.daming.hoteler.workflow.pojo.stat.RoomNumsStat;
import org.daming.hoteler.workflow.pojo.stat.RoomStatusStat;
import org.daming.hoteler.workflow.repository.RoomMapper;
import org.daming.hoteler.workflow.service.stat.IRoomStatusStatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  IRoomStatusStatService的实现类
 */
@Service
public class RoomStatusStatServiceImpl implements IRoomStatusStatService {

    private final RoomMapper roomMapper;

    @Override
    public RoomStatusStat countRoomStatusStatistics() throws HotelerException {
        return null;
    }

    @Override
    public RoomNumsStat countRoomNumStatistics() throws HotelerException {
        var rooms = this.roomMapper.list();
        var groups = rooms.stream().collect(Collectors.groupingBy(Room::getStatus));

        return new RoomNumsStat(
                Optional.ofNullable(groups.get(RoomStatus.InUsed)).orElseGet(List::of).size(),
                Optional.ofNullable(groups.get(RoomStatus.NoUse)).orElseGet(List::of).size()
        );
    }

    public RoomStatusStatServiceImpl(RoomMapper roomMapper) {
        super();
        this.roomMapper = roomMapper;
    }
}
