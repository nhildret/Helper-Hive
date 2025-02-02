package com.hive.capstone.models;

import java.sql.Date;

public class User {

    private int userId;

    private String username;

    private String name;

    private String password;

    private Date registeredAt;

    // getters and setters

    public int getId() {
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public User(String username, String name, Date registeredAt, String password) {
        this.username = username;
        this.name = name;
        this.registeredAt = registeredAt;
        this.password = password;
    }

    public User(int userId, String username, String name, Date registeredAt, String password) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.registeredAt = registeredAt;
        this.password = password;
    }

    public User() {
    }
    public String toString() {
        return "User{" +
                "userId=" + userId  +'}';
    }

}