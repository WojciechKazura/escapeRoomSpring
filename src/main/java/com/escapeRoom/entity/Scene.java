package com.escapeRoom.entity;

import com.escapeRoom.dto.ConnectionDTO;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity()
@Table(name = "Scene")
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
    @ManyToOne
    private Game game;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Scene> nextScenes;

    public Scene(String name, String image, List<Item> itemList) {
        this.name = name;
        this.image = image;
        this.itemList = itemList;
    }

    Scene(Game game) {
        this.game = game;
    }

    public Scene() {
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

    public void lockRandomDoor(Key key) {
        Collections.shuffle(itemList);
        for (Item item : itemList) {
            if (item.getType() == ItemType.DOOR) {
                Door door = (Door) item;
                door.lock(key);
                return;
            }
        }
    }

    public void setNextScenes(List<Scene> scenes) {
        this.nextScenes = scenes;
        for (Scene scene : scenes) {
            itemList.add(new Door(scene));
            scene.itemList.add(new Door(this));
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

    public List<ConnectionDTO> getConnectionsDts() {
        List<ConnectionDTO> connectionViews = new ArrayList<>();
        for (Scene scene : nextScenes) {
            ConnectionDTO connectionDTO = new ConnectionDTO(id, scene.id);
            connectionViews.add(connectionDTO);
        }
        return connectionViews;
    }

    public void addItems(List<Item> newItems) {
        itemList.addAll(newItems);
    }
}
