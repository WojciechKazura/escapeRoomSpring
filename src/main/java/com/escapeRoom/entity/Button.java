package com.escapeRoom.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Button extends Item {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "button_id")
    private List<Item> itemList;

    public Button() {
    }

    public Button(List<Item> itemList) {
        super("Przycisk na ścianie", ItemType.BUTTON);
        this.itemList = itemList;
    }

    Boolean checkCode() {
       int allPartsOfCode = (int) itemList.stream()
                .filter(item -> item instanceof Lamp)
                .map(item -> (Lamp) item)
                .filter(Lamp::isCode)
                .count();

        return allPartsOfCode == itemList.stream().filter(item -> item instanceof Lamp).count();
    }

    @Override
    public String use(Context context) {
        return checkCode() ? "Udało się" : "Niepoprawny kod";
    }

  /*  @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "button_id")
    private List<Item> itemList;

    public Button() {
    }

    public Button(List<Item> itemList) {
        super("Przycisk na ścianie", ItemType.BUTTON);
        this.itemList = itemList;
    }

    Boolean checkCode() {
        int allPartsOfCode = 0;
        for (Item item : itemList) {
            if (item instanceof Lamp) {
                Lamp lamp = (Lamp) item;
                if (lamp.isCode()) {
                    allPartsOfCode++;
                }
            }
        }
        if(allPartsOfCode==itemList.size()){
           return true;
        }else{
            return false;
        }
    }


    @Override
    public String use(Context context) {
        boolean isCorrectCode;
        isCorrectCode=checkCode();
        if(isCorrectCode){
            return "Udało się";
        }
        return "Nie poprawny kod";
    }*/
}
