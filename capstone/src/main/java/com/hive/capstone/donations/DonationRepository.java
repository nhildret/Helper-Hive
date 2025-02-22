package com.hive.capstone.donations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository <Donation, Integer> {

    List<Donation> findByUserId(int user_id);

    List<Donation> findByOrganizationName(String organizationName);

    List<Donation> findDonationName(String donationName);

    List<Donation> findByAmount(double amount);

    List<Donation> findByDonateAt(Date donatedAt);
}
