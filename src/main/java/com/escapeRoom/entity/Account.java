package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private int id; //wrapper
    private String email;
    private String password;

    Account() {

    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
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
