package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Photo extends Item{

    @Setter
    @ManyToOne
    private Gallery gallery;

    @Getter
    @ManyToOne
    private CodeForProtectContainer code;

    public Photo() {
    }

    public Photo(CodeForProtectContainer code) {
        super("Zdjęcie", ItemType.PHOTO);
        this.code=code;
    }

    @Override
    public String use(Context context) {
        return "znalazłeś kod! " +code.getCode()+" Ale czy jest prawdziwy?";
    }
}
