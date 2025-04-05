package com.escapeRoom.dto;

import com.escapeRoom.entity.ItemType;

public class ItemDto {
    //json będą mapowane na ten obiekt i z tego obiektu na jsony
    //dzieje się to automatycznie dzięki bibliotece Jackson w Spring;
    //Wymaga ona geterów wszystkich pól bezparanetrowego konstruktora i zgodnych nazw z jsonem

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
