package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class WeightForScale extends Item {

    @Getter
    @Setter
    @ManyToOne
    private WeightMechanism weightMechanism;

    @Getter
    private int weight;

    public WeightForScale() {
    }

    public WeightForScale(int weight) {
        super("Ciężarek " + weight + " kg.", ItemType.WEIGHTFORSCALE);
        this.weight = weight;
    }

    private boolean isWeightForScaleInPlayerList(Context context) {
        List<Item> playersItems = context.getPlayer().getItemList();
        for (Item item : playersItems) {
            if (item instanceof WeightForScale) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String use(Context context) {
        if (isWeightForScaleInPlayerList(context)) {
            return "Masz już ciężarek w ręku, możesz podnieść tylko jeden, wybierz szalke wagi.";
        } else {
            context.getPlayer().getItemList().add(this);
            context.getRoom().getItemList().remove(this);
            return "Podnisisz ciężarek " + weight + " kg";
        }
    }


}




