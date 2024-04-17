package com.escapeRoom.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Merchant extends Item {


    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itemList;

    private int cost = 1;

    public Merchant() {
    }

   public Merchant(List<Item> itemList) {
        super("Merchant",ItemType.MERCHANT);
        this.itemList = itemList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    @Override
    public String use(Context context) {// dokończyć zakup apteczki
        boolean enoughMoney = context.getPlayer().pay(cost);
        if (enoughMoney) {
            context.getPlayer().getItemList().add(itemList.remove(0));//tutaj
            return "Kupujesz pzedmiot.";
        } else {
            return "Nie masz wystarczajacel ilości monet.";
        }
    }





}
