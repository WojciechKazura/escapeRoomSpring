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

    boolean isEmptyContainer=false;

    private int fourNumbersCodeForLamps;

    public Desk() {
    }

    public Desk(String name,Container container,Item item) {
        super(name, ItemType.FURNITURE);
        this.container=container;
        this.fourNumbersCodeForLamps=createCode();
        this.item=item;


    }

    private int createCode() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }

    @Override
    public String use(Context context) {
        if(!isEmptyContainer){
            isEmptyContainer=true;
            context.getRoom().getItemList().add(container);
            return "Biutko ma ukryta szuflade!";
        }else{
            return "Nic tu więcej nie ma.";
        }
    }

}
