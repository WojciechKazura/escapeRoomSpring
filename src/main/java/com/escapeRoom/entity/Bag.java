package com.escapeRoom.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Bag extends Item{

    private boolean isChecked = false;
    @OneToOne(cascade = CascadeType.ALL)
    private Item itemInBag;


    public Bag() {
    }

    public Bag(Item item) {
        super("Bag", ItemType.DESK);
        this.itemInBag =item;
    }

    @Override
    public String use(Context context) {
        if (!isChecked) {
            isChecked = true;
            context.getPlayer().getItemList().add(itemInBag);
            return "Znajdujesz przedmiot!";
        } else {
            return "Nic tu więcej nie ma.";
        }
    }




}
