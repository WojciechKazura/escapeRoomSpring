package com.escapeRoom.entity;


import jakarta.persistence.*;


import java.util.List;

@Entity
public class Gallery extends Item {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> picturesList;

    @ManyToOne
    private Picture picture;

    private boolean isFistUse = true;

    public Gallery() {
    }

    public Gallery(List<Item> imageList, Picture truePicture) {
        super("Galeria z obrazami", ItemType.GALLERY);
        this.picturesList = imageList;
        this.picture = truePicture;
    }


    @Override
    public String use(Context context) {
        List<Item> itemList = context.getRoom().getItemList();
        if (isFistUse) {
            itemList.addAll(picturesList);
            isFistUse = false;
            return "W pokoju pojawiaja sie cztery obrazy z kodem ale który jest prawdziwy?";
        } else if (!isFistUse&&picturesList.getFirst().equals(picture)) {
            context.getRoom().getItemList().add(picture.getSecretCode());
            return "znalazłeś prawidłowy kod!";
        } else {
            return "Nieprawidłowe rozwiązanie próbój dalej";
        }
    }
}
