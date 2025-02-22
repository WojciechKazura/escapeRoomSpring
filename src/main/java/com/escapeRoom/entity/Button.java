package com.escapeRoom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
public class Button extends Item {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "button_id")
    private List<Item> itemList;

    @Setter
    @ManyToOne
    private Container container;

    public Button() {
    }

    public Button(List<Item> itemList, Container container) {
        super("Przycisk na ścianie", ItemType.BUTTON);
        this.itemList = itemList;
        this.container = container;
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
        if (checkCode()) {
            context.getRoom().getItemList().add(container);
            return "Udało się w pokoju pojawia sie nowy przedmiot.";
        } else {
            return "Niepoprawny kod";
        }

    }


}
