package com.escapeRoom.dto;

import lombok.Data;

@Data
public class AccountDto {
// tu jet playre id do wrappera
    private Integer id; //wrapper
    private String email;
    private String password;
    private Integer gameId;

    public AccountDto(){

    }

    public AccountDto(Integer id, String email, String password, Integer gameId) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.gameId = gameId;

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

    public Integer getGameId() {
        return gameId;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gameId=" + gameId +
                '}';
    }
}
