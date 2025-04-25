package com.hive.capstone.donations;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.hive.capstone.causes.Cause;

@Entity
@Getter
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    public int donationId;

    @Column(name = "user_id")
    private int userId; // links to User table

    @Column(name = "donation_name")
    private String donationName;

    @Column(name = "organization_name")
    private String organizationName;

    @ManyToMany(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    @JoinTable(name = "donationcauses", joinColumns = @JoinColumn(name = "donation_id"), 
                                        inverseJoinColumns = @JoinColumn(name = "cause_id"))
    private List<Cause> causes = new ArrayList<>();

    @Column(name = "amount")
    private double amount;

    @Column(name = "donated_at")
    private Date donatedAt;

    // Constructors

    public Donation(String donationName, String organizationName, double amount, int userId, Date donatedAt) {
        this.donationName = donationName;
        this.organizationName = organizationName;
        this.amount = amount;
        this.userId = userId;
        this.donatedAt = donatedAt;
    }

    public Donation(String organizationName, double amount, int userId, Date donatedAt) {
        this.organizationName = organizationName;
        this.amount = amount;
        this.userId = userId;
        this.donatedAt = donatedAt;
    }

    public Donation(int donationId, String donationName, String organizationName, double amount, int userId,
            Date donatedAt, List<Cause> causes) {
        this.donationId = donationId;
        this.donationName = donationName;
        this.organizationName = organizationName;
        this.amount = amount;
        this.userId = userId;
        this.donatedAt = donatedAt;
        this.causes = causes;
    }

    public Donation() {
    }

    // -----------------------
    // Getters
    // -----------------------

    public int getDonationId() {
        return donationId;
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

    public List<Cause> getCauses() {
        return causes;
    }

    // -----------------------
    // Setters
    // -----------------------

    public void setDonationId(int donationId) {
        this.donationId = donationId;
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

    public void setCauses(List<Cause> causes) {
        this.causes = causes;
    }

    // -----------------------
    // toString Method
    // -----------------------

    @Override
    public String toString() {
        return "Donation{" +
                "donationId=" + donationId +
                ", userId=" + userId +
                ", donationName='" + donationName + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", causes=" + causes +
                ", amount=" + amount +
                ", donatedAt=" + donatedAt +
                '}';
    }
}