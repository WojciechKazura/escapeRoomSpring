package com.escapeRoom.entity;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue
    private Integer id; //wrapper
    private String name;

    @Setter
    private int hp;

    @Setter
    private int howManyCoins = 0;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();

    public Player(Integer id, String name) {
        this.id = id;
        this.name = name;
        hp=100;
    }

    Player() {
        Coin coin=new Coin();
        itemList.add(coin);
        howManyCoins++;
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

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public Integer getId() { //wrapper
        return id;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public int getHowManyCoins() {
        return howManyCoins;
    }

}
