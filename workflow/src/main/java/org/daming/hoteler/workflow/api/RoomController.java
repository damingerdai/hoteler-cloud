package  org.daming.hoteler.workflow.api;

import  org.daming.hoteler.workflow.pojo.Room;
import  org.daming.hoteler.workflow.pojo.enums.RoomStatus;
import  org.daming.hoteler.workflow.pojo.request.CreateRoomRequest;
import  org.daming.hoteler.workflow.service.IRoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author daming
 * @version 2023-04-08 14:05
 **/
@RestController
public class RoomController {

    private final IRoomService roomService;

    @PostMapping(path = "room")
    public Room createRoom(@RequestBody  CreateRoomRequest request) {
        var room = new Room()
                .setName(request.getName())
                .setPrice(request.getPrice())
                .setStatus(RoomStatus.NoUse);
        var id = this.roomService.create(room);
        room.setId(id);
        return room;
    }

    @GetMapping(path = "room/{id}")
    public Room getRoom(@PathVariable String id) {
        var room = this.roomService.get(id);

        return room;
    }

    @GetMapping(path = "rooms")
    public List<Room> listRoom() {
        var rooms = this.roomService.list();
        return rooms;
    }

    public RoomController(IRoomService roomService) {
        super();
        this.roomService = roomService;
    }
}
