package com.hive.capstone.donations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository <Donation, Integer> {

    List<Donation> findByUserId(int userId);

    List<Donation> findByOrganizationName(String organizationName);

    List<Donation> findByDonationName(String donationName);

    List<Donation> findByAmount(double amount);

    List<Donation> findByDonatedAt(Date donatedAt);

    @Query("SELECT d FROM Donation d ORDER BY d.amount DESC LIMIT 5")
    List<Donation> findAllByOrderByAmountDesc();

    List<Donation> findAll();
}
