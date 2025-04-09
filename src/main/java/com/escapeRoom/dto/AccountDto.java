package com.escapeRoom.dto;

import lombok.Data;

@Data
public class AccountDto {
// tu jet playre id do wrappera
    private Integer id; //wrapper
    private String login;
    private String password;
    private Integer gameId;

    public AccountDto(){

    }

    public AccountDto(Integer id, String login, String password, Integer gameId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.gameId = gameId;

    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Integer getGameId() {
        return gameId;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", email='" + login + '\'' +
                ", password='" + password + '\'' +
                ", gameId=" + gameId +
                '}';
    }
}
