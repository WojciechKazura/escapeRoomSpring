package com.escapeRoom.entity;

import jakarta.persistence.Entity;

import java.util.List;


@Entity
public class SmallMirror extends Item {


    private String code;

    public SmallMirror() {
    }

    public SmallMirror(String name, String code) {
        super(name, ItemType.MIRROR);
        this.code = code;
    }

    @Override
    public String use(Context context) {
        List<Item> itemList=context.getRoom().getItemList();
        boolean hasPieceOfPaper = itemList.stream().anyMatch(item -> item instanceof PieceOfPaper);
        if (hasPieceOfPaper) {
            String trueCode = decipherCode(code);
            return "Prawdziwy kod to" + trueCode;
        } else{
            return"Nie masz w pokoju nic co mugłbyś rozszyfrować.";
        }
    }

    private String decipherCode(String code) {
        return new StringBuilder(code).reverse().toString();
    }

    private int safeParse(String code) {
        try {
            return Integer.parseInt(code);
        } catch (NumberFormatException e) {
            return 0;
        }
    }


}





