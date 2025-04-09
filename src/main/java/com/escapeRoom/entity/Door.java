package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Door extends Item {

    private boolean open = true;
    @ManyToOne
    private Key key;
    @ManyToOne
    private Scene targetScene;

    public Door() {
        super("Door", ItemType.DOOR);
    }

    public Door(Scene scene) {
        super("Door", ItemType.DOOR);
       this.targetScene = scene;
    }

    public Door(Key key) {
        super("Door", ItemType.DOOR);
        this.key = key;
    }

    public boolean isOpen() {
        return open;
    }

    public void setTargetScene(Scene targetScene) {
        this.targetScene = targetScene;
    }

    @Override
    public String use(Context context) {
        if(key == null){
            open = true;
            context.getGame().setActiveScene(targetScene);
            return "Otworzyłeś dzwi i przechodzisz do kolejnego pokoju";
        }
        if (!open && context.getPlayer().getItemList().contains(key)) {
            open = true;
            context.getGame().setActiveScene(targetScene);
            return "Otworzyłeś dzwi kluczem i przechodzisz do kolejnego pokoju";
        } else if (open) {
            open = false;
            return "Drzwi zanknięte";
        }else{
            return "Dzwi zamknięte brak klucza.";
        }
    }

    @Override
    public String getName(){
        String closeLabel = "";
        if (!open) {
            closeLabel = " zamknięte";
        }
        return super.getName()+" "+targetScene.getId() + closeLabel;
    }

    public void lock(Key key) {
        this.key = key;
        key.setLabel(targetScene.getId());
        open = false;
    }


}
