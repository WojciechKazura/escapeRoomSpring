package com.escapeRoom.dto;

import com.escapeRoom.entity.ItemType;

public class ItemDto {
    private int id;
    private String name;
    private ItemType itemType;

    public ItemDto() {

    }

    public ItemDto( int id, String name, ItemType itemType) {
        this.id = id;
        this.name = name;
        this.itemType = itemType;
    }

    public ItemType getItemType() {
        return itemType;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
