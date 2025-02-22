package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Picture extends Item {

    @ManyToOne
    private CodeForProtectContainer secretCode;

    public Picture() {
    }

    public Picture(CodeForProtectContainer codeForProtectContainer) {
        super("Obraz", ItemType.PICTURE);
        this.secretCode = codeForProtectContainer;
    }

    @Override
    public String use(Context context) {
        secretCode.use(context);
        return "Przyglądasz się zdjęciu wiszącemu na ścianie. Odkrywasz ukryty w nim kod."+secretCode.getCode();

    }

    public CodeForProtectContainer getSecretCode() {
        return secretCode;
    }

}
