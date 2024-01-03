package com.escapeRoom.entity;

import jakarta.persistence.Entity;

@Entity
public class Window extends Item{

    private boolean open=false;

    public Window() {
        super("Window", ItemType.WINDOW);

    }

    @Override
    public String use() {
        return "Otwarte";
    }
}
