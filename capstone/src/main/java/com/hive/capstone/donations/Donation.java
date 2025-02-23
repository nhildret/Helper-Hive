package com.hive.capstone.donations;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donorId;

    @Column(name = "user_id")
    private int userId; // links to User table

    @Column(name = "donation_name")
    private String donationName;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "cause_id")
    private int causeId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "donated_at")
    private Date donatedAt;

    // Constructors

    public Donation(String donationName, String organizationName, double amount, int userId, Date donatedAt, int causeId) {
        this.donationName = donationName;
        this.organizationName = organizationName;
        this.amount = amount;
        this.userId = userId;
        this.donatedAt = donatedAt;
        this.causeId = causeId;
    }

    public Donation(int donorId, String donationName, String organizationName, double amount, int userId,
            Date donatedAt, int causeId) {
        this.donorId = donorId;
        this.donationName = donationName;
        this.organizationName = organizationName;
        this.amount = amount;
        this.userId = userId;
        this.donatedAt = donatedAt;
        this.causeId = causeId;
    }

    public Donation() {
    }

    // -----------------------
    // Getters
    // -----------------------

    public int getDonorId() {
        return donorId;
    }

    public int getuserId() {
        return userId;
    }

    public String getDonationName() {
        return donationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDonatedAt() {
        return donatedAt;
    }

    public int getCauseId() {
        return causeId;
    }

    // -----------------------
    // Setters
    // -----------------------

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDonatedAt(Date donatedAt) {
        this.donatedAt = donatedAt;
    }

    public void setCauseId(int causeId) {
        this.causeId = causeId;
    }

    // -----------------------
    // toString Method
    // -----------------------

    @Override
    public String toString() {
        return "Donation{" +
                "donorId=" + donorId +
                ", userId=" + userId +
                ", donationName='" + donationName + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", causeId=" + causeId +
                ", amount=" + amount +
                ", donatedAt=" + donatedAt +
                '}';
    }
}