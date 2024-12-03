package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private int id; //wrapper
    private String email;
    private String password;
    @OneToOne
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
