package com.escapeRoom.entity;

import jakarta.persistence.Entity;

@Entity
public class Key extends Item {

    private boolean keyPass = false;

    public Key() {
        super("Key", ItemType.KEY);
    }

    @Override
    public String use() {
        if (keyPass) {
            keyPass = false;
            return "Pasuje";
        } else {
            keyPass = true;
            return "Nie pasuje";
        }
    }
}

