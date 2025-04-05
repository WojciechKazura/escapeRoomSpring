package com.escapeRoom.entity;


public class Context {

    private Game game;

    public Context(Game game) {
        this.game = game;

    }

    public Scene getRoom() {
        return game.getActiveScene();
    }

    public Player getPlayer() {
        return game.getPlayer();
    }

    public Game getGame() {
        return game;
    }


}
