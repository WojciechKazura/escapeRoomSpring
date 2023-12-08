package com.escapeRoom.entitty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.awt.*;

@Entity()
@Table(name="Room")
public class Room {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String image;

    public Room(String name, String image) {
        this.name = name;
        this.image = image;
    }

    Room(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }
}
