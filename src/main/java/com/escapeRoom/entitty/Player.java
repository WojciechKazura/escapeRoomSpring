package com.escapeRoom.entitty;

import jakarta.persistence.*;

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
    @OneToMany
    private List<ItemEntity> itemEntityList;

    public Player(String name, int result) {
        this.name = name;
        this.result = result;
    }

    Player() {

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

    public List<ItemEntity> getItemList() {
        return itemEntityList;
    }
}
