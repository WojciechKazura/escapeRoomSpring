package com.escapeRoom.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BlackWall extends Item {

    @ManyToOne
    private CodeForProtectContainer codeForProtectContainer;

    @ManyToOne
    private FlashLight flashLight;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "black_wall_id")
    private List<Item> itemList;


    private boolean isCheck = false;

    public BlackWall() {
    }

    public BlackWall(CodeForProtectContainer code, List<Item> itemList, FlashLight flashLight) {
        super("Czarna ścian", ItemType.BLACKWALL);
        this.codeForProtectContainer = code;
        this.itemList = itemList;
        this.flashLight = flashLight;
    }

    private boolean checkCode() {
        for (Item item : itemList) {
            if (item instanceof Mirror mirror) {
                return mirror.isCode();
            }
        }
        return false;
    }

    private boolean checkFlashLight() {
        return flashLight.isOn();
    }

    @Override
    public String use(Context context) {
        System.out.println(isCheck);
        if (!isCheck && checkCode() && checkFlashLight()) {
            isCheck = true;
            context.getRoom().getItemList().add(codeForProtectContainer);
            return "Odczytujesz ze ściany kod: " + codeForProtectContainer.getCode();
        } else if (isCheck) {
            return "Nic tu wiecej nie ma.";
        } else {
            return "Nic tu nie widzisz";
        }
    }
}
