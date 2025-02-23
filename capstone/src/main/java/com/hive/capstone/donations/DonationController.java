package com.hive.capstone.donations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hive.capstone.donations.*;

@Controller

//@GetMapping("/leaderboard")
//public String showDonationLeaderboard(Model model){
//    List<Donation> leaderboard = donationService.getTopDonors();
//    model.addAttribute("leaderboard", leaderboard);
//    return "leaderboard";
//}

@RequestMapping("/donate")
public class DonationController {
    @GetMapping({"", "/"})
    public String donate(Model model) {
        // filters: local, location (state/country), cause, query
        //DonationService.getOrgs();
        //model.addAttribute("orgs", orgsList);
        return "donation-page";
    }
}
