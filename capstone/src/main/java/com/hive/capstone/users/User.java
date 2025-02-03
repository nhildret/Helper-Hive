package com.hive.capstone.users;

import java.sql.Date;

public class User {

    private int userId;
    private String username;
    private String password;
    private String role; // For Volunteers, Admins, and OrganizationRepresentatives
    private String name;
    private String email;
    private Date registeredAt;
    private int totalHours;

    // Constructors

    public User(int userId, String username, String name, String role, String email, Date registeredAt, String password,
            int totalHours) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.role = role;
        this.email = email;
        this.registeredAt = registeredAt;
        this.password = password;
        this.totalHours = totalHours;
    }

    public User(String username, String name, String role, String email, Date registeredAt, String password,
            int totalHours) {
        this.username = username;
        this.name = name;
        this.role = role;
        this.email = email;
        this.registeredAt = registeredAt;
        this.password = password;
        this.totalHours = totalHours;
    }

    // Default constructor
    public User() {
    }
    // toString Method
    public String toString() {
        return "User{" +
                "userId=" + userId + '}';
    }

    // -----------------------
    // Getters
    // -----------------------

    public int getId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public int getTotalHours() {
        return totalHours;
    }

    // -----------------------
    // Setters
    // -----------------------

    public void setId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

}