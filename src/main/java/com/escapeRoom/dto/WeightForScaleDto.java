package com.escapeRoom.dto;

public class WeightForScaleDto {

    private int itemId; // Identyfikator przedmiotu (ciężarka)
    private int gameId; // Identyfikator gry
    private String side; // Strona wagi: "left" lub "right"


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }


}
