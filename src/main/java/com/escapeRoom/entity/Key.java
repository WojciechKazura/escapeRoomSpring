package com.escapeRoom.entity;

import jakarta.persistence.Entity;

@Entity
public class Key extends Item {

    private String label = "";

    public Key() {
        super("Key", ItemType.KEY);

    }

   /* @Override
    public String use(Context context) {
        context.getPlayer().getItemList().add(this);
        context.getRoom().getItemList().remove(this);
        return "Klucz zabrany z pokoju.";
    }*/

   @Override
   public String use(Context context) {
       if (!context.getPlayer().getItemList().contains(this)) {
           context.getPlayer().getItemList().add(this); // Tylko dodaj, jeśli nie ma jeszcze klucza w ekwipunku
           context.getRoom().getItemList().remove(this); // Usuwamy klucz z pokoju
           return "Klucz zabrany z pokoju.";
       }
       return "Klucz już w ekwipunku.";
   }


    public String findKey(Context context) {
        context.getPlayer().getItemList().add(this);
        context.getRoom().getItemList().remove(this);
        return "Klucz zabrany z pokoju.";
    }


    public void setLabel(int nr) {
        label = " " + nr;
    }

    @Override
    public String getName() {
        return super.getName() + label;
    }
}

