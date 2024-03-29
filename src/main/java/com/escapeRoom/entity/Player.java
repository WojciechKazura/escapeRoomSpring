package com.escapeRoom.entity;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {

    @Id
    private int id;
    private String name;

    private int howManyCoins = 0;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itemList;

    @OneToOne(cascade = CascadeType.ALL)
    private Room room;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;

    }

    Player() {

    }

    boolean pay(int price) {
        if (howManyCoins > 0 && howManyCoins >= price) {
            howManyCoins = howManyCoins - price;
            return true;
        } else {
            return false;
        }
    }

    public void addItem(Item item) {
        if(item.getType().equals(ItemType.COIN)){
            takeCoin();
        }else{itemList.add(item);}
    }

   private void takeCoin() {
        howManyCoins++;
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

    public int getHowManyCoins() {
        return howManyCoins;
    }

    public void setHowManyCoins(int howManyCoins) {
        this.howManyCoins = howManyCoins;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
