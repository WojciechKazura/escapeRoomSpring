package com.escapeRoom.dto;

import com.escapeRoom.entity.Item;

import java.util.List;

public class PlayerDto {

    private String name;

    private int id; //wrapper

    private int howManyCoins;

    private List<ItemDto> itemList;

    PlayerDto() {

    }

    public PlayerDto(String name, int id, int howManyCoins, List<ItemDto> itemList) { //wrapper
        this.name = name;
        this.id = id;
        this.howManyCoins = howManyCoins;
        this.itemList = itemList;
    }

    public List<ItemDto> getItemList() {
        return itemList;
    }

    public String getName() {
        return name;
    }

    public int getId() { //wrapper
        return id;
    }

    public int getHowManyCoins() {
        return howManyCoins;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
