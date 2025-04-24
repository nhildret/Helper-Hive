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

import com.hive.capstone.causes.CauseRepository;
import com.hive.capstone.causes.CauseService;
import com.hive.capstone.donations.*;
import com.hive.capstone.scripts.CallScripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

@RequestMapping("/donate")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private CauseRepository causeRepository;

    @GetMapping({"", "/"})
    public String donate(Model model) {
        model.addAttribute("causes", causeRepository.findAll());
        return "donation-page";
    }

    @GetMapping("/admin/donations/all")
    public String getAllDonations(Model model) {
        model.addAttribute("donationList", donationService.getAllDonations());
        model.addAttribute("title", "All Donations");
        // where all orgs displayed
        return "admin-donations";
    }


    //Okay kinda the exact same but we're so back

    @GetMapping("/admin/donations/stats")
    @ResponseBody
    public List<Map<String, Object>> getDonationStats() {
        List<Object[]> rawData = donationService.getDonationsByDate();
        List<Map<String, Object>> formattedData = new ArrayList<>();

        for (Object[] row : rawData) {
            Map<String, Object> dataPoint = new HashMap<>();
            dataPoint.put("date", row[0].toString());
            dataPoint.put("amount", row[1]);
            formattedData.add(dataPoint);
        }

        return formattedData;
    }

}
