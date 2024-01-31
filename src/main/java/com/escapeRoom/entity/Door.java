package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Door extends Item {

    private boolean open = false;
    @OneToOne
    private Key key;

    public Door() {

    }

    public Door(Key key) {
        super("Door", ItemType.DOOR);
        this.key=key;
    }

    @Override
    public String use(Context context) {
        if (open) {
            open = false;
            return "Drzwi zanknięte";
        } else {
            open = true;
            return "Drzwi otwarte";
        }
    }
}
