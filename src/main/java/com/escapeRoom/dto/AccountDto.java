package com.escapeRoom.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class AccountDto {
    @Getter
    private Integer id;
    @Getter
    private String login;
    @Getter
    private String password;
    @Getter
    private Integer gameId;

    public AccountDto(){

    }

    public AccountDto(Integer id, String login, String password, Integer gameId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.gameId = gameId;

    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", gameId=" + gameId +
                '}';
    }
}
