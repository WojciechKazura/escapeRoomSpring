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
        this.key = key;
    }

    @Override
    public String use(Context context) {
        if (!open && context.getPlayer().getItemList().contains(key)) {
            open = true;
            return "Otworzyłeś dzwi kluczem";
        } else if (open) {
            open = false;
            return "Drzwi zanknięte";
        }else{
            return "Dzwi zamknięte brak klucza.";
        }
    }
}
