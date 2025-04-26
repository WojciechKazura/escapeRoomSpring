package com.escapeRoom.entity;

import jakarta.persistence.Entity;


@Entity
public class Figurine extends Item {

    private int weight;

    public Figurine() {
    }

    public Figurine(int weight) {
        super("Figurka", ItemType.FIGURINE);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String use(Context context) {
        return "";
    }
}
