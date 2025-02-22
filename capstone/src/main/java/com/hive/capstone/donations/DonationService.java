package com.hive.capstone.donations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hive.capstone.users.User;
import com.hive.capstone.organizations.Organization;

import java.util.List;

@Service
public class DonationService {

    @Autowired
    DonationRepository donationRepository;

    //Get all donations
    public List<Donation> getAllDonations(){
        return donationRepository.findAll();
    }
    //Get donations by user_id


}
