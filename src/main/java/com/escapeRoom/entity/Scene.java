package com.escapeRoom.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name="Scene")
public class Scene {

    @Id
    @GeneratedValue
    private int id;//wrapper
    private String name;
    private String image;
    @ManyToOne
    private Key key;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itemList;

    @ManyToOne(cascade =  CascadeType.ALL)
    private Scene nextScene;

    public Scene(String name, String image, List<Item> itemList, Key key) {
        this.name = name;
        this.image = image;
        this.itemList = itemList;
        this.key=key;
    }

    Scene(){

    }

    public int getId() {//wrapper
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public Key getKey() {
        return key;
    }

    public Scene getNextRoom() {
        return nextScene;
    }

    public void setNextRoom(Scene nextScene) {
        this.nextScene = nextScene;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }
}
