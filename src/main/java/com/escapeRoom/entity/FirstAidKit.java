package com.escapeRoom.entity;

import jakarta.persistence.Entity;

@Entity
public class FirstAidKit extends Item{

    public FirstAidKit(){
        super("FirstAidKit", ItemType.FIRSTAIDKIT);
    }

    @Override
    public String use(Context context) {
        context.getPlayer().getItemList().add(this);
        return "Znaleziono apteczkę!";
    }
}
