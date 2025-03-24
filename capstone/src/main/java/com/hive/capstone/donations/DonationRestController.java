package com.hive.capstone.donations;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hive.capstone.scripts.CallScripts;
import com.hive.capstone.users.User;

@RestController
@RequestMapping("/donate")
public class DonationRestController {
    @GetMapping("/get")
    public String[] donate(   @RequestParam(name = "lat", required = false) String lat, @RequestParam(name = "lon", required = false) String lon, 
                            @RequestParam(name = "state", required = false) String state, @RequestParam(name = "country", required = false) String country,
                            @RequestParam(name = "zip", required = false) String zip, @RequestParam(name = "q", required = false) String q,
                            @RequestParam(name = "pagenum", required = true) String pageNum, Model model) {
        String argString = "?page=" + pageNum;
        
        //location
        if (zip != null) {
            argString += "&postal_code=" + zip;
        }else if (state != null) {
            argString += "&region=" + state;
        }else if (country != null) {
            argString += "&country=" + country;
        }else if (lat != null && lon != null) {
            argString += "&lat=" + lat + "&lon=" + lon;
        }

        if (q != null) {
            argString += "&q=" + q;
        }

        JSONArray orgsArray = new JSONArray(CallScripts.getOrgs(argString));
        String[] orgs = new String[orgsArray.length()];

        for (int i = 0; i < orgsArray.length(); i++) {
            orgs[i] = orgsArray.getJSONObject(i).toString();
        }
        System.out.println("\n\n" + orgs[0] + "\n\n");
        return orgs;
    }

    @GetMapping("/getById")
    public String org(@RequestParam(name="id") String id){
        String argString = "/" + id;
        JSONObject org = CallScripts.getOrgDetails(argString);
        return org.toString();
    }

    @PostMapping("/new")
    public void newDonation(@ModelAttribute User user, @RequestParam(name="amount") double amt) {

    }
}
