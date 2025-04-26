package com.escapeRoom.dto;


import java.util.List;

public class SceneDto {


    private int id;
    private String name;
    private String image;

    private List<ItemDto> itemList;

    public SceneDto() {

    }


    public SceneDto(int id, String name, String image, List<ItemDto> itemList) {//wrapper
        this.id = id;
        this.name = name;
        this.image = image;
        this.itemList = itemList;
    }

    public SceneDto(int id) {
        this.id = id;
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

    public List<ItemDto> getItemList() {
        return itemList;
    }

    @Override
    public String toString() {
        return "SceneDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
