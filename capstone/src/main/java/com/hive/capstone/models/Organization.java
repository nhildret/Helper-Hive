package com.hive.capstone.models;

import java.sql.Date;

public class Organization {

    private int organizationId;

    private String organizationName;

    private String password;

    private Date registeredAt;

    // Getters and Setters
    
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

    // Constructors
    public Organization(String organizationName, String password, Date registeredAt) {
        this.organizationName = organizationName;
        this.password = password;
        this.registeredAt = registeredAt;
    }

    public Organization(int organizationId, String organizationName, String password, Date registeredAt) {
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.password = password;
        this.registeredAt = registeredAt;
    }

    public Organization() {
    }

    // toString method
    @Override
    public String toString() {
        return "Organization{" +
                "organizationId=" + organizationId +
                ", organizationName='" + organizationName + '\'' +
                '}';
    }
}
