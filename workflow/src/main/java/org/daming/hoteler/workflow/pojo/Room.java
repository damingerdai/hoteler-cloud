package  org.daming.hoteler.workflow.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import  org.daming.hoteler.workflow.pojo.enums.RoomStatus;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author daming
 * @version 2023-04-07 20:13
 **/
public class Room implements Serializable {

    private static final long serialVersionUID = 5964557657365714040L;

    private String id;

    @JsonProperty("roomname")
    private String name;

    private RoomStatus status;

    public double price;

    public String getId() {
        return id;
    }

    public Room setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public Room setStatus(RoomStatus status) {
        this.status = status;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Room setPrice(double price) {
        this.price = price;
        return this;
    }

    public Room(String id, String name, RoomStatus status, double price) {
        super();
        this.id = id;
        this.name = name;
        this.status = status;
        this.price = price;
    }

    public Room() {
        super();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Room.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("status=" + status)
                .add("price=" + price)
                .toString();
    }
}
