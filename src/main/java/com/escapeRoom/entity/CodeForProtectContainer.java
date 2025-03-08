package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

import java.util.Random;

@Entity
public class CodeForProtectContainer extends Item {

    private String label = "";
    @Getter
    private int code;

    private boolean haveCode = false;

    public CodeForProtectContainer() {
        super("Kod", ItemType.CODE);
        code = createCode();
    }

    private int createCode() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }

    @Override
    public String use(Context context) {
        if (!haveCode) {
            context.getPlayer().getItemList().add(this);
            haveCode=true;
            return "Znalazłeś kod " + code;
        }else{
            return "Masz juz kod " + code;
        }
    }

    public void setLabel(int nr) {
        label = " " + nr;
    }

    @Override
    public String getName() {
        return super.getName() + label;
    }

    @Override
    public String toString() {
        return "CodeForProtectContainer{" +
                "label='" + label + '\'' +
                ", code=" + code +
                ", haveCode=" + haveCode +
                '}';
    }
}

