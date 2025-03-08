package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class Chair extends Item {

    @Transient
    private boolean haveAdvice = false;


    public Chair() {
        super("Chair", ItemType.FURNITURE);

    }

    @Override
    public String use(Context context) {
        return "Siadasz wygodnie na krześle.";
    }
}
