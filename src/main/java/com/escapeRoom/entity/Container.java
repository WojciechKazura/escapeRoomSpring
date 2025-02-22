package com.escapeRoom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
public class Container extends Item {

    private boolean isChecked = false;
    @Getter
    @Setter
    @ManyToOne
    private Item item;

    public Container() {
    }

    public Container(Item item, String name) {
        super(name, ItemType.CONTAINER);
        this.item = item;
    }

    @Override
    public String use(Context context) {
        if (!isChecked && item.getType().equals(ItemType.KEY)) {
            isChecked = true;
            context.getPlayer().addItem(item);
            return "Wyciagasz " + item.getName() + " z " + getName();
        } else if (!isChecked && !item.getType().equals(ItemType.KEY)) {
            isChecked = true;
            context.getRoom().getItemList().add(item);
            return "Wyciagasz " + item.getName() + " z " + getName();
        } else {
            return "Nic tu nie ma!";
        }
    }


}
