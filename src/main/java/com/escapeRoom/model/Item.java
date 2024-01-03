package com.escapeRoom.model;

import com.escapeRoom.entitty.ItemEntity;

public abstract class Item  {

    private ItemEntity itemEntity;

    public Item(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public abstract String use();



}
