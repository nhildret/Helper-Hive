package com.hive.capstone.users;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String role; // For Volunteers, Admins, and OrganizationRepresentatives
    
    private String name;

    private String email;

    private Date registered_at;
    
    private int total_hours;

    // -----------------------
    // Constructors
    // -----------------------

    public User(int id, String username, String name, String role, String email, Date registered_at, String password,
            int total_hours) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
        this.email = email;
        this.registered_at = registered_at;
        this.password = password;
        this.total_hours = total_hours;
    }

    public User(String username, String name, String role, String email, Date registered_at, String password,
            int total_hours) {
        this.username = username;
        this.name = name;
        this.role = role;
        this.email = email;
        this.registered_at = registered_at;
        this.password = password;
        this.total_hours = total_hours;
    }

    // Default constructor
    public User() {
    }
    // toString Method
    @Override
    public String toString() {
        return "User{" +
                "id=" + id + '}';
    }

    // -----------------------
    // Getters
    // -----------------------

    public int getId() {
        return id;
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
        return registered_at;
    }

    public int getTotalHours() {
        return total_hours;
    }

    // -----------------------
    // Setters
    // -----------------------

    public void setId(int id) {
        this.id = id;
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

    public void setRegisteredAt(Date registered_at) {
        this.registered_at = registered_at;
    }

    public void setTotalHours(int total_hours) {
        this.total_hours = total_hours;
    }

}