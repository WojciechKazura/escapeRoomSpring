package com.escapeRoom.entity;

import com.escapeRoom.service.GameService;

public class Context {
    private GameService gameService;
    private Room room;
    private Player player;

    public Context(GameService gameService,Room room, Player player) {
        this.gameService=gameService;
        this.room = room;
        this.player = player;
    }

    public Room getRoom() {
        return room;
    }

    public Player getPlayer() {
        return player;
    }

    public GameService getGameService() {
        return gameService;
    }
}
