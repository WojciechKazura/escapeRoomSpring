package com.escapeRoom.dto;

public class PlayerDto {

    private String name;

    private int id;

    PlayerDto(){

    }

    public PlayerDto(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }



    @Override
    public String toString() {
        return "PlayerDto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
