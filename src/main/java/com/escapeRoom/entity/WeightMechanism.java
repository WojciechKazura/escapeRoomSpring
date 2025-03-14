package com.escapeRoom.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class WeightMechanism extends Item {

    @ManyToOne
    private Item item;

    @ManyToOne
    private Figurine figurine;

    @OneToMany(cascade = CascadeType.ALL)
    private List<WeightForScale> weightForScales;


    @ManyToOne
    private RightSideWeightMechanism right;


    @ManyToOne
    private LeftSideWeightMechanism left;

    private boolean firstUse = true;

    private boolean correctAnswer = false;

    public WeightMechanism() {

    }

    public WeightMechanism(Item item, List<WeightForScale> weightForScales, RightSideWeightMechanism right,
                           LeftSideWeightMechanism left, Figurine figurine) {
        super("Waga", ItemType.WEIGHTMECHANISM);
        this.item = item;
        this.weightForScales = weightForScales;
        this.right = right;
        this.left = left;
        this.figurine = figurine;
    }

    private String isCorrect() {
        if (right.isFigurine() && right.getMass() == left.getMass()) {
            int m = right.getMass() - figurine.getWeight();
            correctAnswer = true;
            return "Udałosię figurka waży " + (right.getMass() - m);
        } else if (left.isFigurine() && left.getMass() == right.getMass()) {
            int m = left.getMass() - figurine.getWeight();
            correctAnswer = true;
            return "Udało się figurka waży " + (right.getMass() - m);
        } else {
            return "Na wadze są tylko ciężarki brak figurki ciężarki po obu stronach ważą tyle samo.";
        }
    }

    private boolean checkWeight() {
        return right.getMass() != 0 && left.getMass() != 0 && right.getMass() == left.getMass();
    }

    private boolean leftMoreMass() {
        return left.getMass() > right.getMass();
    }


    private int possibleWeights(Context context) {
        int weights = 0;
        List<Item> items = new ArrayList<>();
        items.addAll(context.getPlayer().getItemList());
        items.addAll(context.getRoom().getItemList());
        for (Item item : items) {
            if (item instanceof WeightForScale) {
                weights += ((WeightForScale) item).getWeight();
            }
        }
        return weights;
    }

    private boolean canSolve(Context context) {
        int figWeight = figurine.getWeight();
        int weights = possibleWeights(context);
        if (right.getMass() > 45 || left.getMass() > 45) {
            return false;
        } else if (weights + figWeight < left.getMass() || weights + figWeight < right.getMass()) {
            return false;
        } else if (left.isFigurine() && left.getMass() > weights || right.isFigurine() && right.getMass() > weights) {
            return false;
        } else if (left.isFigurine() && left.getMass() < right.getMass() || right.isFigurine() && right.getMass() < left.getMass()) {
            return false;
        } else if (!checkWeight() && weights == 0) {
            return false;
        } else if(left.getMass() == right.getMass() || left.isFigurine() || right.isFigurine()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String use(Context context) {
        if (firstUse) {
            context.getRoom().getItemList().addAll(weightForScales);
            context.getRoom().getItemList().add(figurine);
            firstUse = false;
            return "Wyciągasz z wagi ciężarki";
        } else if (checkWeight()) {
            String result = isCorrect();
            if (correctAnswer) {
                context.getPlayer().addItem(item);
            }
            return result;
        } else if (!canSolve(context)) {
            resetGame(context);
            return "Zagadki nie da się juz rozwiazać restart";
        } else if (leftMoreMass()) {
            return "Lewa szalka wagi jest cięższa waży " + left.getMass() + " kg";
        } else if (!leftMoreMass()) {
            return "Prawa szalka wagi jest cięższa waży " + right.getMass() + " kg";
        } else {
            return "Zagadka z waga nierozwiążana";
        }

    }

    private void resetGame(Context context) {
        context.getRoom().getItemList().removeAll(weightForScales);
        context.getRoom().getItemList().remove(figurine);
        context.getPlayer().getItemList().removeAll(weightForScales);
        context.getPlayer().getItemList().remove(figurine);

        right.setMass(0);
        left.setMass(0);
        right.setFigurine(false);
        left.setFigurine(false);

        context.getRoom().getItemList().addAll(weightForScales);
        context.getRoom().getItemList().add(figurine);
    }


}


