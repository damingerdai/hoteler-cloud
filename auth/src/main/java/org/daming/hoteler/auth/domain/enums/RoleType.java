package org.daming.hoteler.auth.domain.enums;

import java.util.StringJoiner;

/**
 * @author gming001
 * @version 2023-11-04 19:21
 */
public enum RoleType {

    Admin(1, "admin", "Admin"),
    Manager(2, "Manager", "Manager"),
    User(3, "user", "Standard User");

    private int id;

    private String name;

    private String description;

    RoleType(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static RoleType getInstance(String name) {
        for (RoleType rt : values()) {
            if (name.equals(rt.name)) {
                return rt;
            }
        }
        throw new RuntimeException("no role type with name " + name);
    }

    public static RoleType forValues(int id) {
        for (RoleType rt : values()) {
            if (rt.id == id) {
                return rt;
            }
        }
        throw new RuntimeException("no role tyoe with id " + id);
    }

    public int id() {
        return this.id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RoleType.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .toString();
    }
}
