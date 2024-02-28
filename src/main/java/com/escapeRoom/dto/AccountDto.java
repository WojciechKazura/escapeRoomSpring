package com.escapeRoom.dto;

public class AccountDto {

    private int id;
    private String email;
    private String password;

    private String playerName;
    AccountDto(){

    }
    public AccountDto(int id, String email, String password, String playerName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.playerName = playerName;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPlayerName() {
        return playerName;
    }
}
