package com.hive.capstone.models;

public class Donation {

    private int donorId;

    private String donationName;

    private String organizationName;

    private double amount;

    // Getters and Setters
    
    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public String getDonationName() {
        return donationName;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Constructors
    public Donation(String donationName, String organizationName, double amount) {
        this.donationName = donationName;
        this.organizationName = organizationName;
        this.amount = amount;
    }

    public Donation(int donorId, String donationName, String organizationName, double amount) {
        this.donorId = donorId;
        this.donationName = donationName;
        this.organizationName = organizationName;
        this.amount = amount;
    }

    public Donation() {
    }

    // toString method
    @Override
    public String toString() {
        return "Donation{" +
                "donorId=" + donorId +
                ", donationName='" + donationName + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", amount=" + amount +
                '}';
    }
}