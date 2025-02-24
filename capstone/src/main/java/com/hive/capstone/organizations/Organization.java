package com.hive.capstone.organizations;

import java.sql.Date;
import lombok.Getter;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "organizations")
public class Organization {

    @Id
    @Column(name = "organization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "password")
    private String password;

    @Column(name = "registered_at")
    private Date registeredAt;

    @Column(name = "address")
    private String address;

    // Constructors

    public Organization(String organizationName, String password, Date registeredAt, String address) {
        this.organizationName = organizationName;
        this.password = password;
        this.registeredAt = registeredAt;
        this.address = address;
    }

    public Organization(int id, String organizationName, String password, Date registeredAt,
            String address) {
        this.id = id;
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
        return id;
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

    public void setOrganizationId(int id) {
        this.id = id;
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
                "id=" + id +
                ", organizationName='" + organizationName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
