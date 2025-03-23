package com.hive.capstone.donations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.hive.capstone.users.User;
import com.hive.capstone.organizations.Organization;

import java.util.Date;
import java.util.List;

@Service
public class DonationService {

    @Autowired
    DonationRepository donationRepository;

    //Get all donations
    public List<Donation> getAllDonations(){
        return donationRepository.findAll();
    }
    //Get donations by userId
    public List<Donation> getDonationsByUserId(int userId){
        return donationRepository.findByUserId(userId);
    }
    //Get donations by Organization Name
    public List<Donation> getDonationsByOrganizationName(String organizationName){
        return donationRepository.findByOrganizationName(organizationName);
    }
    //Get donations by donation name
    public List<Donation> getDonationsByDonationName(String donationName){
        return donationRepository.findByDonationName(donationName);
    }
    public List<Donation> getTopDonors(){
        return donationRepository.findAllByOrderByAmountDesc();
    }
//    //Get donations by amount
//    public List<Donation> getDonationsByAmount(double amount){
//        return donationRepository.findByAmount(amount);
//    }
//    //Get donations by data
//    public List<Donation> getDonationsByDate(Date donatedAt){
//        return donationRepository.findByDonatedAt(donatedAt);
//    }

}
