package com.escapeRoom.entity;

import jakarta.persistence.Entity;

import java.util.Random;

@Entity
public class CodeForProtectContainer extends Item {

    private String label = "";
    private int code;

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
        context.getPlayer().getItemList().add(this);
        return "Znalazłeś kod "+code;
    }

    public String getLabel() {
        return label;
    }

    public int getCode() {
        return code;
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
                "code=" + code +
                '}';
    }
}

