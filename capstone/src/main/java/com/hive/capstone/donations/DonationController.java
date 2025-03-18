package com.hive.capstone.donations;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hive.capstone.donations.*;
import com.hive.capstone.scripts.CallScripts;

@Controller

//@GetMapping("/leaderboard-donations")
//public String showDonationLeaderboard(Model model){
//    List<Donation> leaderboard = donationService.getTopDonors();
//    model.addAttribute("leaderboard", leaderboard);
//    return "leaderboard-donations";
//}

@RequestMapping("/donate")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @GetMapping({"", "/"})
    public String donate() {
        return "coords"; //middle-man method to get coords and pass to "/{lat}/{lon}" method
    }

    @GetMapping("/{lat}/{lon}")
    public String donate(@PathVariable String lat, @PathVariable String lon, Model model) {
        JSONArray orgs = CallScripts.getOrgs(0);
        model.addAttribute("orgs",orgs.toList());
        model.addAttribute("lat", lat);
        model.addAttribute("lon", lon);

        return "donation-page";
    }

    @GetMapping("/admin/donations/all")
    public String getAllDonations(Model model) {
        model.addAttribute("donationList", donationService.getAllDonations());
        model.addAttribute("title", "All Donations");
        // where all orgs displayed
        return "admin-donations";
    }

    /*@GetMapping("/admin/users/all")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("title", "All Users");
        // where users will be displayed
        return "admin-users";
    }*/
}
