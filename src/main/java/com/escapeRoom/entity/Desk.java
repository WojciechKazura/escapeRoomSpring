package com.escapeRoom.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class Desk extends Item {

    private boolean isChecked = false;
    @OneToOne(cascade = CascadeType.ALL)
    private Item itemInDesk;


    public Desk() {
    }

    public Desk(Item item) {
        super("Desk", ItemType.DESK);
        this.itemInDesk=item;
    }

    @Override
    public String use(Context context) {
        if (!isChecked) {
            isChecked = true;
            context.getPlayer().getItemList().add(itemInDesk);
            return "Znajdujesz przedmiot!";
        } else {
            return "Nic tu więcej nie ma.";
        }
    }
}
