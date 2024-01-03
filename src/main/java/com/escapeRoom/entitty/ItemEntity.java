package com.escapeRoom.entitty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity()
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private ItemType type; //todo

    public ItemEntity(String name) {
        this.name = name;
        type=ItemType.WINDOW;
    }

    public ItemEntity() {

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
