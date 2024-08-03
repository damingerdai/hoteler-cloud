package org.daming.hoteler.common.domain;

import java.util.StringJoiner;

public class Permission {
    private long id;

    private String name;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Permission(long id, String name, String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Permission() {
        super();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Permission.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .toString();
    }
}
