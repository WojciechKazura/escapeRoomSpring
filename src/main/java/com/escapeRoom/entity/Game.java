package com.escapeRoom.entity;

import jakarta.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Player player;
    @OneToOne(cascade = CascadeType.ALL)
    private Scene firstRoom;
    @OneToOne
    private Scene activeRoom;
    @Transient
    private double chanceForSplit= 1.0;

    public Game() {

    }

    public void setFirstRoom(Scene scene){
        firstRoom=scene;
        activeRoom= scene;

    }

    public int getId() {
        return id;
    }
}
