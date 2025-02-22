package com.escapeRoom.entity;

import jakarta.persistence.Entity;

@Entity
public class PieceOfPaper extends Item {

    private String message;

    public PieceOfPaper() {
    }

    public PieceOfPaper(String name,String message ) {
        super(name, ItemType.MESSAGE);
        this.message=message;
    }

    @Override
    public String use(Context context) {
        context.getPlayer().getItemList().add(this);
        context.getRoom().getItemList().remove(this);
        return "Na kartce papieru widnieje "+message;
    }

    public String getMessage() {
        return message;
    }
}
