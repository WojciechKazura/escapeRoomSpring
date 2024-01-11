package com.escapeRoom.entity;

import jakarta.persistence.Entity;

@Entity
public class Key extends Item {

    private boolean keyPass = true;

    public Key() {
        super("Key", ItemType.KEY);
    }

    @Override
    public String use() {
        if (keyPass) {
            return "Pasuje";
        } else{
            System.out.println("nie pasuje");
        }
        return null;
    }
}

