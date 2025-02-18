package com.escapeRoom.entity;

import jakarta.persistence.Entity;

@Entity
public class Chair extends Item {





    @Override
    public String use(Context context) {
        return "Siadasz wygodnie na krześle.";
    }
}
