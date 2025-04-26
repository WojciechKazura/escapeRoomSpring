package com.escapeRoom.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@Entity
public class Merchant extends Item {


    @Getter
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itemList;

    private int cost = 1;

    public Merchant() {
    }

    public Merchant(List<Item> itemList) {
        super("Merchant", ItemType.MERCHANT);
        this.itemList = itemList;
    }

    @Override
    public String use(Context context) {
        boolean enoughMoney = context.getPlayer().pay(cost);
        if (enoughMoney) {
            context.getPlayer().getItemList().add(itemList.get(0));

            return "Kupujesz pzedmiot.";
        } else {
            return "Nie masz wystarczajacel ilości monet.";
        }
    }


}
