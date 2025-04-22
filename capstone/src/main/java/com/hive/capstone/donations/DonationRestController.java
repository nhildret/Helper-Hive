package com.hive.capstone.donations;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.RequestEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hive.capstone.causes.Cause;
import com.hive.capstone.causes.CauseService;
import com.hive.capstone.scripts.CallScripts;
import com.hive.capstone.users.User;
import com.hive.capstone.users.UserService;

@RestController
@RequestMapping("/donate")
public class DonationRestController {
    @Autowired
    DonationService service;

    @Autowired
    UserService userService;

    @Autowired
    CauseService causeService;

    @PostMapping("/new")
    public void newDonation(RequestEntity<String> donationString) {
        // parse JSON
        JSONObject donation = new JSONObject(donationString.getBody());

        // get organization details
        String orgId = donation.getJSONObject("toNonprofit").getString("slug");
        JSONObject organization = service.getOrgDetails(orgId).getJSONObject("data");

        // get organization name
        String orgName = organization.getJSONObject("nonprofit").getString("name");

        // get list of causes for the organization
        JSONArray causesJSON = organization.getJSONArray("nonprofitTags");
        List<Cause> causes = new ArrayList<>();
        for(int i = 0; i < causesJSON.length(); i++) {
            causes.add(causeService.getById(causesJSON.getJSONObject(i).getString("tagName")));
        }

        System.out.println(causes);

        // get donation Date
        String donationDate = donation.getString("donationDate");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(donationDate, dtf);
        Date donated_at = Date.valueOf(date);

        double amount = Double.parseDouble(donation.getString("amount"));

        // get user info
        Optional<User> userByEmail = userService.findByEmail(donation.get("email").toString());
        if (userByEmail.isPresent()) {
            User user = userByEmail.get();
            System.out.println(user.getName());


            //create Donation object
            Donation d = new Donation(donation.getString("publicTestimony"), orgName, amount, user.getId(), donated_at);
            d.setCauses(causes);
            service.save(d);

        } else {
            System.out.println("User does not exist with email: " + donation.get("email").toString());
        }
    }
}
