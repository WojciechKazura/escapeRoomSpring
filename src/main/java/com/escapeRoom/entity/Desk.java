package com.escapeRoom.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Entity
public class Desk extends Item {


    @ManyToOne
    private Container container;
    @Setter
    @ManyToOne
    private Item item;

    @Transient
    boolean isEmptyContainer = false;

    @Transient
    private int fourNumbersCodeForLamps;

    public Desk() {
    }

    public Desk(Container container, Item item) {
        super("Biurko", ItemType.FURNITURE);
        this.container = container;
        this.fourNumbersCodeForLamps = createCode();
        this.item = item;


    }

    private int createCode() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }

    @Override
    public String use(Context context) {
        if (!isEmptyContainer) {
            isEmptyContainer = true;
            context.getRoom().getItemList().add(container);
            context.getRoom().getItemList().add(item);
            return "Biurko ma ukrytą szuflade!, Na blacie jest przytwierdone małe " + item.getName();
        } else {
            return "W szufladzie nic wiecej nie ma na blacie jest " + item.getName();
        }
    }

}
