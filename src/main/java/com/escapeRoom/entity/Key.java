package com.escapeRoom.entity;

import jakarta.persistence.Entity;

@Entity
public class Key extends Item {

    private int keyCost;


    public Key() {
        super("Key", ItemType.KEY);
        keyCost=1;
    }

    @Override
    public String use(Context context) {
        context.getPlayer().getItemList().add(this);
        context.getRoom().getItemList().remove(this);
        return "Klucz zabrany z pokoju.";
    }


    public String findKey(Context context) {
        context.getPlayer().getItemList().add(this);
        context.getRoom().getItemList().remove(this);
        return "Klucz zabrany z pokoju.";
    }

    public String buyKey(Context context){
        if(context.getPlayer().getHowManyCoins()>=keyCost){
            context.getPlayer().getItemList().add(this);
            context.getRoom().getItemList().remove(this);
            return "Kupujesz klucz do drzwi.";
        }else{
            return"Nie masz wystarczajacel ilości monet.";
        }
    }
}

