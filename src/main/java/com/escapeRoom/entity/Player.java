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
    private int result;
    private boolean active;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Item> itemList;

    @OneToOne(cascade = CascadeType.ALL)
    private Room room;

    public Player(String name, int result,Room room) {
        this.name = name;
        this.result = result;
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

    public int getResult() {
        return result;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public List<Item> getItemList() {
        return itemList;
    }
}
