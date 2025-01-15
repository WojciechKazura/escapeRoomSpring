package com.escapeRoom.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private Player player;
    @OneToOne(cascade = CascadeType.ALL)
    private Scene firstScene;
    @OneToOne
    private Scene activeScene;
    @Transient
    private double chanceForSplit= 1.0;

    public Game() {
        player = new Player();
        firstScene = new Scene();
        activeScene = firstScene;
        createMap(firstScene);
    }

    public void createMap(Scene scene){
        List<Scene> roomList= createConnections();
        scene.setNextScenes(roomList);
        for(Scene next : roomList){
            createMap(next);
        }
    }

    public List<Scene> createConnections() {
        Random random = new Random();
        double splitChance = random.nextDouble();
        List<Scene> roomList = new ArrayList<>();
        if (splitChance < chanceForSplit) {
            for (int i = 0; i < 2; i++) {
                Scene nextRoom = new Scene();
                roomList.add(nextRoom);
            }
            chanceForSplit=chanceForSplit-0.05;
            return roomList;
        }
        double soloChance = random.nextDouble();
        if (soloChance < 0.5) {
            Scene nextRoom = new Scene();
            roomList.add(nextRoom);
        }
        return roomList;
    }

    public void setFirstScene(Scene scene){
        firstScene =scene;
        activeScene = scene;

    }

    public int getId() {
        return id;
    }

    public Scene getActiveScene() {
        return activeScene;
    }

    public Player getPlayer() {
        return player;
    }

    public void setActiveScene(Scene targetScene) {
        this.activeScene = targetScene;
    }
}
