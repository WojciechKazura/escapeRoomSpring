package com.escapeRoom.entitty;

public class Item {

    private String name;

    public Item(String name) {
        this.name = name;
    }

    public Item(){

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                '}';
    }
}
