package com.escapeRoom.model;

import com.escapeRoom.entitty.ItemEntity;

public class Window extends Item{

    public Window(ItemEntity itemEntity) {
        super(itemEntity);
    }

    @Override
    public String use() {
        return "Otwarte";
    }
}
