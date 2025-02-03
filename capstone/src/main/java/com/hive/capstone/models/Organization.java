package com.hive.capstone.models;

import java.sql.Date;

public class Organization {

    private int organizationId;
    private String organizationName;
    private String password;
    private Date registeredAt;
    private String address;

    // Constructors

    public Organization(String organizationName, String password, Date registeredAt, String address) {
        this.organizationName = organizationName;
        this.password = password;
        this.registeredAt = registeredAt;
        this.address = address;
    }

    public Organization(int organizationId, String organizationName, String password, Date registeredAt,
            String address) {
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.password = password;
        this.registeredAt = registeredAt;
        this.address = address;
    }

    public Organization() {
    }

    // -----------------------
    // Getters
    // -----------------------

    public int getOrganizationId() {
        return organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getPassword() {
        return password;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public String getAddress() {
        return address;
    }

    // -----------------------
    // Setters
    // -----------------------

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // -----------------------
    // toString Method
    // -----------------------

    @Override
    public String toString() {
        return "Organization{" +
                "organizationId=" + organizationId +
                ", organizationName='" + organizationName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
