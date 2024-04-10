package com.escapeRoom.dto;

public class AccountDto {
// tu jet playre id do wrappera
    private Integer id; //wrapper
    private String email;
    private String password;
    private String playerName;
    AccountDto(){

    }
    public AccountDto(Integer id, String email, String password, String playerName) {
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

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", playerName='" + playerName + '\'' +
                '}';
    }
}
