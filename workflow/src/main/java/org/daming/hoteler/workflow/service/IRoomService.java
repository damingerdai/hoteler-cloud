package  org.daming.hoteler.workflow.service;

import  org.daming.hoteler.common.exceptions.HotelerException;
import  org.daming.hoteler.workflow.pojo.Room;
import  org.daming.hoteler.workflow.pojo.enums.RoomStatus;

import java.util.List;

/**
 * @author daming
 * @version 2023-04-07 23:17
 **/
public interface IRoomService {

    String create(Room room) throws HotelerException;

    Room get(String id) throws HotelerException;

    void update(Room room) throws HotelerException;

    List<Room> list(Room room) throws HotelerException;

    List<Room> list() throws HotelerException;

    void delete(String id) throws HotelerException;

    void updateStatus(String id, RoomStatus status)  throws HotelerException;
}
