package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;



@Entity
public class FlashLight extends Item {

    @Getter
    private boolean isOn = false;

    @ManyToOne
    private ElectricBox electricBox;

    public FlashLight() {
    }

    public FlashLight(ElectricBox electricBox) {
        super("Latarka", ItemType.FLASHLIGHT);
        this.electricBox = electricBox;
    }

    @Override
    public String use(Context context) {
        if (!isOn && electricBox.isCorrectFuseCode()) {
            isOn = true;
            return "Latarna włączona.";
        } else if (!electricBox.isCorrectFuseCode()) {
            return "Brak prądu.";
        } else {
            isOn = false;
            return "Latarka wyłączona";
        }
    }
}
