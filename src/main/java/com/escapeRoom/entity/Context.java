package com.escapeRoom.entity;

import com.escapeRoom.service.GameService;

public class Context {
    private GameService gameService;
    private Scene scene;
    private Player player;

    public Context(GameService gameService, Scene scene, Player player) {
        this.gameService=gameService;
        this.scene = scene;
        this.player = player;
    }

    public Scene getRoom() {
        return scene;
    }

    public Player getPlayer() {
        return player;
    }

    public GameService getGameService() {
        return gameService;
    }
}
