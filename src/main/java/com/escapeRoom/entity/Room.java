package com.escapeRoom.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name="Room")
public class Room {

    @Id
    @GeneratedValue
    private int id;//wrapper
    private String name;
    private String image;
    @ManyToOne
    private Key key;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itemList;

    @ManyToOne(cascade =  CascadeType.ALL)
    private Room nextRoom;

    public Room(String name, String image, List<Item> itemList, Key key) {
        this.name = name;
        this.image = image;
        this.itemList = itemList;
        this.key=key;
    }

    Room(){

    }

    public int getId() {//wrapper
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

    public Key getKey() {
        return key;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    public void setNextRoom(Room nextRoom) {
        this.nextRoom = nextRoom;
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
