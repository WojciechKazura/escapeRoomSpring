package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Lamp extends Item {

    private int valueCode;
    @Setter
    private int howManyUse;
    private String a;
    private boolean isLight = false;
    @Getter
    private boolean code = false;

    public Lamp() {
    }

    public Lamp(String name, int valueCode) {
        super(name, ItemType.LAMP);
        this.valueCode = valueCode;
        a = name;
        howManyUse = 0;
    }

    @Override
    public String use(Context context) {
        if(howManyUse==9){
            howManyUse=0;
            return "Lampa została już  użyta  ponad 10 razy licznik wyzerował się.";
        }
        if(howManyUse+1==valueCode){
            code=true;
        }else{
            code=false;
        }
        if (!isLight) {
            isLight = true;
            howManyUse++;
            return "Zapalasz " + a + ". Lampa użyta " + howManyUse+code+valueCode;
        } else {
            isLight = false;
            howManyUse++;
            return "Gasisz " + a + ". Lampa uzyta " + howManyUse+code+valueCode;
        }


    }


}

