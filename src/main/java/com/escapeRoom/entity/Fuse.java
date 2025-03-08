package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Fuse extends Item {

    @Getter
    @Setter
    @ManyToOne
    private ElectricBox electricBox;

    @Getter
    private int position;

    @Getter
    @Setter
    private boolean placedCorrectly = false;

    @Getter
    private String power;

    public Fuse() {
    }

    public Fuse(String power, int position) {
        super("Bezpiecznik "+power, ItemType.FUSE);
        this.power = power;
        this.position = position;

    }


    @Override
    public String use(Context context) {
        if (electricBox != null) {
            return  electricBox.placeFuse(this, context);
        }
        return "Nie możesz teraz użyć tego bezpiecznika.";
    }


}

