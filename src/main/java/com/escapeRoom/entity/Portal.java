package com.escapeRoom.entity;

public class Portal extends Item {
    private Scene scene;

    public Portal(Scene scene,String name) {
        super(name,ItemType.PORTAL);
        this.scene = scene;
    }

    @Override
    public String use(Context context) {
        return null;
    }






}
