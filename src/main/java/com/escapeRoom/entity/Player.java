package com.escapeRoom.entity;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Item> itemList;

    @OneToOne(cascade = CascadeType.ALL)
    private Room room;

    public Player(String name, Room room) {
        this.name = name;
        this.room=room;
    }

    Player() {

    }

    public Room getRoom() {
        return room;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    public List<Item> getItemList() {
        return itemList;
    }
}
