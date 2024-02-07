package com.escapeRoom.dto;

public class PlayerDto {

    private String name;

    private int id;

    private int roomId;


    PlayerDto(){

    }

    public PlayerDto(String name, int id, int roomId) {
        this.name = name;
        this.id = id;
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", roomId=" + roomId +
                '}';
    }
}
