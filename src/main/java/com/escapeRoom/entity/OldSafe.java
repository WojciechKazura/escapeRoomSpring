package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class OldSafe extends Item{

    @ManyToOne
    private CodeForProtectContainer code;


    public OldSafe() {
    }

    public OldSafe(CodeForProtectContainer code) {
        super("Satry sejf", ItemType.OLDSAFE);
        this.code=code;
    }

    @Override
    public String use(Context context) {
        return "";
    }
}
