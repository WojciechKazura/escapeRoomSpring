package com.escapeRoom.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
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
    private List<Item> itemList = new ArrayList<>();

    @ManyToMany(cascade =  CascadeType.ALL)
    private List<Scene> nextScenes;

    public Scene(String name, String image, List<Item> itemList) {
        this.name = name;
        this.image = image;
        this.itemList = itemList;
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

    public List<Scene> getNextScenes() {
        return nextScenes;
    }

    public void setNextScenes(List<Scene> scenes) {
        this.nextScenes = scenes;
        for (Scene scene : scenes) {
            itemList.add(new Door(scene));
        }
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
