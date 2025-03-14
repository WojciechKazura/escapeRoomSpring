package com.escapeRoom.entity;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class RightSideWeightMechanism extends Item {


    @Setter
    @Getter
    private int mass;

    @Setter
    @Getter
    private boolean isFigurine = false;


    public RightSideWeightMechanism() {
    }

    public RightSideWeightMechanism(int mass) {
        super("Prawa szalka wagi", ItemType.RIGHTSIDE);
        this.mass = mass;
    }

    private boolean isWeightForScaleInPlayerList(Context context) {
        List<Item> playersItems = context.getPlayer().getItemList();
        for (Item item : playersItems) {
            if (item instanceof WeightForScale) {
                mass = mass + ((WeightForScale) item).getWeight();
                context.getPlayer().getItemList().remove(item);
                return true;
            }
        }
        return false;
    }

    private boolean isFigurineInPlayerList(Context context) {
        List<Item> playersItems = context.getPlayer().getItemList();
        for (Item item : playersItems) {
            if (item instanceof Figurine) {
                mass = mass + ((Figurine) item).getWeight();
                context.getPlayer().getItemList().remove(item);
                isFigurine = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public String use(Context context) {
        if (isWeightForScaleInPlayerList(context) && !isFigurine) {
            return "Prawa szalka wagi " + mass + " kg.";
        } else if (isFigurineInPlayerList(context)) {
            return "Figurka na prawej szali wagi" + mass;
        } else if (isFigurine) {
            return "Na szalce jest figurka nie mozna juz dokładać ciężarków";
        }
        return "Nie masz w reku żadnego ciężarka.";
    }
}
