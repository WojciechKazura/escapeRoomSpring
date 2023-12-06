package com.escapeRoom.entitty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity()
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public Item(){

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
