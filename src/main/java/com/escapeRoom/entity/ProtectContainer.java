package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ProtectContainer extends Item {

    private boolean isChecked = false;

    @ManyToOne
    private CodeForProtectContainer codeForProtectContainer;
    @ManyToOne
    private Item item;

    public ProtectContainer() {

    }

    public ProtectContainer(String name, Item item, CodeForProtectContainer code) {
        super(name, ItemType.PROTECTCONTAINER);
        this.item = item;
        this.codeForProtectContainer = code;
    }

    @Override
    public String use(Context context) {
        if (!isChecked && context.getPlayer().getItemList().contains(codeForProtectContainer)) {
            isChecked = true;
            context.getPlayer().addItem(item);
            context.getRoom().getItemList().remove(codeForProtectContainer);
            return "Wyciagasz " + item.getName() + " z " + getName();
        } else if(isChecked) {
            return "Nic tu więcej nie ma.";
        }else{
            return "Sejf zamknięty brak czterocyfrowego kodu";
        }
    }
}

