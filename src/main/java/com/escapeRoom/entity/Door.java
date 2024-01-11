package com.escapeRoom.entity;

import jakarta.persistence.Entity;

@Entity
public class Door extends Item {

    private boolean open = false;

    public Door() {
        super("Door", ItemType.DOOR);
    }

    @Override
    public String use() {
        if (open) {
            open = false;
            return "Drzwi zanknięte";
        } else {
            open = true;
            return "Drzwi otwarte";
        }
    }
}
