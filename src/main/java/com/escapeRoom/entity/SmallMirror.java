package com.escapeRoom.entity;

import jakarta.persistence.Entity;


@Entity
public class SmallMirror extends Item {

    private boolean isDecipher = false;
    private int code;

    public SmallMirror() {
    }

    public SmallMirror(String name, int code) {
        super(name, ItemType.MIRROR);
        this.code = code;
    }

    @Override
    public String use(Context context) {
        String trueCode = "";
        if (!isDecipher) {
            for( int i = 0;
            i < 4;
            i++){
                int lastNumber = code % 10;
                trueCode = trueCode + code;
            }
            isDecipher=true;
            return "Po odczytaniu nowy kod to " + trueCode;
        }else{
            return "Już rozszyfrowałeś kod jeśli go nie pamiętasz spójż na ...";
        }

    }


}
