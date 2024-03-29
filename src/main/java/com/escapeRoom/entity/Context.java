package com.escapeRoom.entity;

public class Context {

    private Room room;
    private Player player;

    public Context(Room room, Player player) {
        this.room = room;
        this.player = player;
    }

    public Room getRoom() {
        return room;
    }

    public Player getPlayer() {
        return player;
    }
}
