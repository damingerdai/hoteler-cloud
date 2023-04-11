package  org.daming.hoteler.workflow.service.impl;

import org.daming.hoteler.common.exceptions.HotelerException;
import  org.daming.hoteler.workflow.pojo.Room;
import  org.daming.hoteler.workflow.pojo.enums.RoomStatus;
import  org.daming.hoteler.workflow.repository.RoomMapper;
import  org.daming.hoteler.workflow.service.IRoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author daming
 * @version 2023-04-07 23:17
 **/
@Service
public class RoomServiceImpl implements IRoomService {

    private final RoomMapper roomMapper;

    @Transactional
    @Override
    public String create(Room room) throws HotelerException {
        var id = this.roomMapper.create(room);
        room.setId(id);

        return id;
    }

    @Override
    public Room get(String id) throws HotelerException {
        return this.roomMapper.get(id);
    }

    @Override
    public void update(Room room) throws HotelerException {
        this.roomMapper.update(room);
    }

    @Override
    public List<Room> list(Room room) throws HotelerException {
        //return this.roomMapper.get();
        throw new RuntimeException("尚未实现");
    }

    @Override
    public List<Room> list() throws HotelerException {
        //
        throw new RuntimeException("尚未实现");
    }

    @Override
    public void delete(String id) throws HotelerException {
        this.roomMapper.delete(id);
    }

    @Override
    public void updateStatus(long id, RoomStatus status) throws HotelerException {

    }

    public RoomServiceImpl(RoomMapper roomMapper) {
        super();
        this.roomMapper = roomMapper;
    }
}
