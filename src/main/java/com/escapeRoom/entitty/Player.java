package com.escapeRoom.entitty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    private List<Item> itemList;

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

    public List<Item> getItemList() {
        return itemList;
    }
}
