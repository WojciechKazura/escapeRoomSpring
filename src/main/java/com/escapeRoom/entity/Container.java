package com.escapeRoom.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;


@Entity
public class Container extends Item {


    private boolean isChecked = false;
    @OneToOne(cascade = CascadeType.ALL)
    private Item item;


    public Container() {
    }

    public Container(Item item, String name) {
        super(name, ItemType.CONTAINER);
        this.item =item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String use(Context context) {
        if (!isChecked) {
            isChecked = true;
            context.getPlayer().addItem(item);
            return "Wyciagasz "+item.getName()+" z "+getName();
        } else {
            return "Nic tu więcej nie ma.";
        }
    }
}
