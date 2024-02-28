package com.escapeRoom.entity;

import jakarta.persistence.Entity;

@Entity
public class Coin extends Item{


   public Coin(){
       super("Coin", ItemType.COIN);
    }

    @Override
    public String use(Context context) {
        context.getPlayer().getItemList().add(this);
        int howManyCoins=context.getPlayer().getHowManyCoins();
        context.getPlayer().setHowManyCoins(howManyCoins+1);
        return "Znaleziono monete!";
    }
}
