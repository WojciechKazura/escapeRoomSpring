package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

import java.util.List;
import java.util.Random;

@Entity
public class Figurine extends Item{

    @Getter
    private int weight;

    public Figurine() {
        super("Figurka", ItemType.FIGURINE);
        this.weight= prepareWeight();
    }

    private int prepareWeight(){
        Random random = new Random();
        return random.nextInt(45)+1;
    }

    private boolean isFigurinePlayerList(Context context) {
        List<Item> playersItems = context.getPlayer().getItemList();
        for (Item item : playersItems) {
            if (item instanceof Figurine) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String use(Context context) {
        if (isFigurinePlayerList(context)) {
            return "Masz już figurkę w ręku, możesz jej uzyć jak ciężarka, wybierz szalke wagi.";
        } else {
            context.getPlayer().getItemList().add(this);
            context.getRoom().getItemList().remove(this);
            return "Podnosisz figurke ciekawe ile waży. "+weight;
        }
    }
}
