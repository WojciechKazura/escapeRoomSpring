package com.escapeRoom.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name="Room")
public class Room {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String image;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itemList;

    public Room(String name, String image, List<Item> itemList) {
        this.name = name;
        this.image = image;
        this.itemList = itemList;
    }

    Room(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }
}
