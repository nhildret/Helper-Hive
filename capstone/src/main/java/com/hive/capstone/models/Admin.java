package com.hive.capstone.models;

import java.sql.Date;

public class Admin {

    private int adminId;

    private String password;

    private Date createdAt;

    private Date registeredAt;

    // Getters and Setters
    
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    // Constructors
    public Admin(String password, Date createdAt, Date registeredAt) {
        this.password = password;
        this.createdAt = createdAt;
        this.registeredAt = registeredAt;
    }

    public Admin(int adminId, String password, Date createdAt, Date registeredAt) {
        this.adminId = adminId;
        this.password = password;
        this.createdAt = createdAt;
        this.registeredAt = registeredAt;
    }

    public Admin() {
    }

    // toString method
    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", createdAt=" + createdAt +
                ", registeredAt=" + registeredAt +
                '}';
    }
}