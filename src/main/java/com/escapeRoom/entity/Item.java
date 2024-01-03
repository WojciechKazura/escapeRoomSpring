package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity()
@Table(name = "items")
public abstract class Item {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private ItemType type; //todo
  

    public Item(String name, ItemType itemType) {
        this.name = name;
        this.type=itemType;
    }

    public Item() {

    }

    public abstract String use();

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
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
