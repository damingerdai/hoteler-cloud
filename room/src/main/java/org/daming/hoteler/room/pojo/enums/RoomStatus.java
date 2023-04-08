package org.daming.hoteler.room.pojo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.StringJoiner;

/**
 * @author daming
 * @version 2023-04-07 20:15
 **/
@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
public enum RoomStatus implements Enumerator {
    NoUse(1, "no used"),
    InUsed(2, "in used");

    private int id;

    private String value;

    public int getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    RoomStatus(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static RoomStatus getInstance(int id) {
        for (RoomStatus rs : values()) {
            if (rs.id ==  id) {
                return rs;
            }
        }
        throw new RuntimeException("no room status with " + id);
    }

    public static RoomStatus getInstance(String name) {
        for (RoomStatus rs : values()) {
            if (name.equals(rs.getValue())) {
                return rs;
            }
        }
        throw new RuntimeException("no room status with name " + name);
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static RoomStatus forValues(int id) {
        for (RoomStatus rs : values()) {
            if (rs.id() == id) {
                return rs;
            }
        }
        throw new RuntimeException("no room status with id " + id);
    }

    @Override
    @JsonValue
    public int id() {
        return id;
    }

    @Override
    //@JsonValue
    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RoomStatus.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("value='" + value + "'")
                .toString();
    }
}
