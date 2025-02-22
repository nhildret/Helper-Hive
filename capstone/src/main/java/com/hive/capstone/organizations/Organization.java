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
    private int organization_id;

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

    public Organization(int organization_id, String organization_name, String password, Date registered_at,
            String address) {
        this.organization_id = organization_id;
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
        return organization_id;
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

    public void setOrganizationId(int organization_id) {
        this.organization_id = organization_id;
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
                "organization_id=" + organization_id +
                ", organization_name='" + organization_name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
