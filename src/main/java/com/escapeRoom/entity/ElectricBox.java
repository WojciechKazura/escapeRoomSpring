package com.escapeRoom.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity

public class ElectricBox extends Item {

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "electric_box_id")
    private List<Fuse> fuseList;

    private int answer = 1;

    private boolean firstUse = true;

    @Getter
    private boolean correctFuseCode = false;

    public ElectricBox() {
    }

    public ElectricBox(List<Fuse> fuseList) {
        super("Skrzynka na bezpieczniki", ItemType.ELECTRICBOX);
        this.fuseList = fuseList;
    }

    public String placeFuse(Fuse fuse, Context context) {
        int expectedPosition = fuse.getPosition();
        if (expectedPosition == answer) {
            fuse.setPlacedCorrectly(true);
            context.getRoom().getItemList().remove(fuse);
            answer++;
            return "Bezpiecznik dodany do skrzynki.";
        } else {
            context.getRoom().getItemList().remove(fuse);
            return "Bezpiecznik dodany do skrzynki";
        }
    }

    private boolean checkCode() {
        for (Fuse fuse : fuseList) {
            if (!fuse.isPlacedCorrectly()) {
                correctFuseCode = false;
                break;
            } else {
                correctFuseCode = true;
            }
        }
        return correctFuseCode;
    }

    @Override
    public String use(Context context) {
        List<Item> itemList = context.getRoom().getItemList();
        if (firstUse) {
            itemList.addAll(fuseList);
            firstUse = false;
            return "W skrzynce znajdujesz cztery bezpieczniki.";
        } else if (checkCode()) {
            return "Udało się prąd przywrócony.";
        } else {
           itemList.addAll(fuseList);
            return "Nieprawidlowe rozwiązanie. Próbuj dalej rozwiązać zagadkę z bezpiecznikami.";
        }
    }

}
