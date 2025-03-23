package com.hive.capstone.donations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/leaderboard")
public class DonationLeaderboardController {
    @Autowired
    private DonationService donationService;

    @GetMapping("/donations")
    public String showDonationLeaderboard(Model model) {
        List<Donation> leaderboard = donationService.getTopDonors();
        model.addAttribute("leaderboard", leaderboard);
        return "leaderboard-donations";
    }
}
