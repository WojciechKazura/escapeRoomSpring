package com.escapeRoom.entity;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private int id; //wrapper
    private String login;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Game game;

    Account() {

    }

    public Account(String login, String password, Game game) {
        this.login = login;
        this.password = password;
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public int getId() {//wrapper
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
