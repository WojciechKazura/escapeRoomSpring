package com.escapeRoom.dto;

import com.escapeRoom.entity.Item;


import java.util.List;

public class RoomDto {


    private int id; //wraper
    private String name;
    private String image;

    private List<Item> itemList;


    public RoomDto(int id, String name, String image, List<Item> itemList) {//wrapper
        this.id = id;
        this.name = name;
        this.image = image;
        this.itemList = itemList;
    }

    public RoomDto(int id){
        this.id=id;
    }

    RoomDto() {

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

    @Override
    public String toString() {
        return "RoomDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
