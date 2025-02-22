package com.escapeRoom.entity;

import jakarta.persistence.Entity;

import java.util.Random;

@Entity
public class CodeGenerator extends Item {

    private int code;

    public CodeGenerator( ) {
        super("Generator kodu", ItemType.CODE);
        this.code=createCode();
    }

    private int createCode() {
        Random random = new Random();
        int code;
        do {
            code = random.nextInt(9000) + 1000;
        } while (String.valueOf(code).contains("0"));
        return code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String use(Context context) {
        return "Kod: "+code;
    }
}
