package org.daming.hoteler.room.api;

import org.daming.hoteler.room.pojo.Room;
import org.daming.hoteler.room.pojo.enums.RoomStatus;
import org.daming.hoteler.room.pojo.request.CreateRoomRequest;
import org.daming.hoteler.room.service.IRoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println(id);
        var room = this.roomService.get(id);

        return room;
    }

    public RoomController(IRoomService roomService) {
        super();
        this.roomService = roomService;
    }
}
