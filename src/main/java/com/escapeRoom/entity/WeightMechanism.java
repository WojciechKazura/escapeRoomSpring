package com.escapeRoom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class WeightMechanism extends Item {

    @Getter
    @Setter
    @ManyToOne
    private Item item;

    @OneToMany(cascade = CascadeType.ALL)
    private List<WeightForScale> weightForScales;

    @Transient
    private boolean firstUse = false;

    @Transient
    private List<Item> itemsOnScaleLeft = new ArrayList<>();

    @Transient
    private List<Item> itemsOnScaleRight = new ArrayList<>();

    @Transient
    private final Map<Integer, Integer> itemWeights = new HashMap<>();

    public WeightMechanism() {

    }

    public WeightMechanism(Item item, List<WeightForScale> weightForScales) {
        super("Waga", ItemType.WEIGHTMECHANISM);
        this.item = item;
        this.weightForScales = weightForScales;
    }

    public String placeItemOnScale(Item item, boolean isLeftSide) {
        if (itemWeights.containsKey(item.getId())) {
            if (isLeftSide) {
                itemsOnScaleLeft.add(item);
            } else {
                itemsOnScaleRight.add(item);
            }
            return "Przedmiot " + item.getName() + " został dodany.";
        } else {
            return "Ten przedmiot nie ma przypisanej wagi.";
        }
    }

    public boolean checkBalance() {
        int leftWeight = calculateWeight(itemsOnScaleLeft);
        int rightWeight = calculateWeight(itemsOnScaleRight);
        return leftWeight == rightWeight;
    }

    private int calculateWeight(List<Item> items) {
        int totalWeight = 0;
        for (Item item : items) {
            Integer weight = itemWeights.get(item.getId());
            if (weight != null) {
                totalWeight += weight;
            }
        }
        return totalWeight;
    }

    @Override
    public String use(Context context) {
        if (!firstUse) {
            context.getRoom().getItemList().addAll(weightForScales);
            return"wyciągasz z wagi ciężarki";
        }
        return "waga!!!!!!!!!!!!";
    }




}


