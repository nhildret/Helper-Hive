package com.hive.capstone.donations;

import java.sql.Date;

public class Donation {

    private int donorId;
    private int userId; // links to User table
    private String donationName;
    private String organizationName;
    private double amount;
    private Date donatedAt;

    // Constructors

    public Donation(String donationName, String organizationName, double amount, int userId, Date donatedAt) {
        this.donationName = donationName;
        this.organizationName = organizationName;
        this.amount = amount;
        this.userId = userId;
        this.donatedAt = donatedAt;
    }

    public Donation(int donorId, String donationName, String organizationName, double amount, int userId,
            Date donatedAt) {
        this.donorId = donorId;
        this.donationName = donationName;
        this.organizationName = organizationName;
        this.amount = amount;
        this.userId = userId;
        this.donatedAt = donatedAt;
    }

    public Donation() {
    }

    // -----------------------
    // Getters
    // -----------------------

    public int getDonorId() {
        return donorId;
    }

    public int getUserId() {
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
                ", amount=" + amount +
                ", donatedAt=" + donatedAt +
                '}';
    }
}