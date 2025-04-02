package com.escapeRoom.dto;

public class ActionDto {

    private int itemId;
    private int gameId;
  ;

    public ActionDto() {

    }

    public int getItemId() {
        return itemId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "ActionDto{" +
                "itemId=" + itemId +
                ", gameId=" + gameId +
                '}';
    }
}
