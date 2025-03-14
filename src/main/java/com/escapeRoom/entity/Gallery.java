package com.escapeRoom.entity;


import jakarta.persistence.*;


import java.util.List;
import java.util.Random;

@Entity
public class Gallery extends Item {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> photoList;

    @ManyToOne
    private CodeForProtectContainer code;

    int photoIndex = 0;

    int truePhotoIndex;

    boolean isFistUse=true;

    public Gallery() {
    }

    public Gallery(List<Item> imageList, CodeForProtectContainer code) {
        super("Galeria z obrazami", ItemType.GALLERY);
        this.photoList = imageList;
        this.code = code;
        truePhotoIndex = truePhoto();

    }

    int truePhoto() {
        Random random = new Random();
        return random.nextInt(4) + 1;
    }

   //todo
    boolean isCorrect(){
        return photoIndex==truePhotoIndex;
    }


    @Override
    public String use(Context context) {
        List<Item> itemList = context.getRoom().getItemList();
        if(photoIndex==truePhotoIndex){
            itemList.add(code);
            return "kod prawidłowy.";
        }
        if (isFistUse) {
            itemList.add(photoList.get(photoIndex));
            photoIndex++;
            isFistUse=false;
            return "Galeria ma cztery zdjęcia. W pokoju pojawiaja zdjęcie nr " + photoIndex+ " z kodem ale czy jest prawdziwy?";
        }else if(photoIndex<photoList.size()){
            itemList.remove(photoList.get(photoIndex-1));
            itemList.add(photoList.get(photoIndex));
            photoIndex++;
            return "zdjęcie nr "+photoIndex;
        } else{
            photoIndex=0;
            return "Nie udało się rowiazac zagadki zaczynasz od nowa!";
        }
    }
}
