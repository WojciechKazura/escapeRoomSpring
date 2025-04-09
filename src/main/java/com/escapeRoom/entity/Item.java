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
        this.type = itemType;
    }

    public Item() {

    }

    public abstract String use(Context context);

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
