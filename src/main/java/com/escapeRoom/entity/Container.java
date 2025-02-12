package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Container extends Item {


    private boolean isChecked = false;
    @ManyToOne
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
