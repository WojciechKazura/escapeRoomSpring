package com.escapeRoom.entity;

import jakarta.persistence.Entity;

@Entity
public class Key extends Item {


    public Key() {
        super("Key", ItemType.KEY);
    }

    @Override
    public String use(Context context) {
        context.getPlayer().getItemList().add(this);
        context.getRoom().getItemList().remove(this);
        return "Klucz zabrany z pokoju";
    }
}

