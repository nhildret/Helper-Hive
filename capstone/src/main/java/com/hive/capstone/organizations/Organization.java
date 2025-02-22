package com.hive.capstone.organizations;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String organization_name;
    private String password;
    private Date registered_at;
    private String address;

    // Constructors

    public Organization(String organization_name, String password, Date registered_at, String address) {
        this.organization_name = organization_name;
        this.password = password;
        this.registered_at = registered_at;
        this.address = address;
    }

    public Organization(int id, String organization_name, String password, Date registered_at,
            String address) {
        this.id = id;
        this.organization_name = organization_name;
        this.password = password;
        this.registered_at = registered_at;
        this.address = address;
    }

    public Organization() {
    }

    // -----------------------
    // Getters
    // -----------------------

    public int getOrganizationId() {
        return id;
    }

    public String getOrganizationName() {
        return organization_name;
    }

    public String getPassword() {
        return password;
    }

    public Date getRegisteredAt() {
        return registered_at;
    }

    public String getAddress() {
        return address;
    }

    // -----------------------
    // Setters
    // -----------------------

    public void setOrganizationId(int id) {
        this.id = id;
    }

    public void setOrganizationName(String organization_name) {
        this.organization_name = organization_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegisteredAt(Date registered_at) {
        this.registered_at = registered_at;
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
                "id=" + id +
                ", organization_name='" + organization_name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
