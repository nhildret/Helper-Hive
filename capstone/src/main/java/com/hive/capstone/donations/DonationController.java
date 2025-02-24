package com.hive.capstone.donations;

import java.io.IOException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.aspectj.weaver.ast.Call;
// import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import com.hive.capstone.scripts.CallScripts;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hive.capstone.donations.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller

//@GetMapping("/leaderboard")
//public String showDonationLeaderboard(Model model){
//    List<Donation> leaderboard = donationService.getTopDonors();
//    model.addAttribute("leaderboard", leaderboard);
//    return "leaderboard";
//}

@RequestMapping("/donate")
public class DonationController extends HttpServlet{

    @Autowired
    private DonationService donationService;

    @GetMapping({"", "/"})
    public String donate(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HttpSession session = request.getSession();
        // String lat, lon;
        // if (session.getAttribute("lat") != null && session.getAttribute("lon") != null) {
        //     lat = session.getAttribute("lat").toString();
        //     lon = session.getAttribute("lon").toString();
        // } else {
        //     String res = CallScripts.callJS();
        //     lat = res.substring(0, res.indexOf(" "));
        //     lon = res.substring(res.indexOf(" "));
        // }

        // return "redirect:/" + lat + "/" + lon;
        return "donation-page";
    }

    // @GetMapping("/{lat}/{lon}")
    // public String donate(@PathVariable String lat, @PathVariable String lon, Model model) {
    //     // filters: local, location (state/country), cause, query
    //     String orgs = CallScripts.getOrgs(1, lat, lon);
    //     // JSONArray orgsList = new JSONArray(orgs);
    //     model.addAttribute("orgs", orgs);
    //     model.addAttribute("lat", lat);
    //     model.addAttribute("lon", lon);

    //     return "donation-page";
    // }

    
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
