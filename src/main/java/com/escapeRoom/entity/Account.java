package com.escapeRoom.entity;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private int id; //wrapper
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Game game;

    Account() {

    }

    public Account(String email, String password, Game game) {
        this.email = email;
        this.password = password;
        this.game = game;
    }

    public int getId() {//wrapper
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
