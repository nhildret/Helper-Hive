package com.hive.capstone;

import org.springframework.web.bind.annotation.GetMapping;

// import com.hive.capstone.events.EventService;
// import com.hive.capstone.donations.DonationService;

//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

@Controller
public class HiveController {
    @GetMapping("/")
    public String home() {
        return "landing-page"; // This looks for src/main/resources/templates/landing-page.html
    }

    @GetMapping("/maps")
    public String maps() {
        return "maps-page"; // Serves maps-page.html
    }

    @GetMapping("/user")
    public String user() {
        return "user-page"; // Serves user-page.html
    }

    @GetMapping("/events")
    public String events(Model model) {
        //eventsList = EventService.getEvents();
        //model.addAttribute("events", eventsList);
        return "event-page"; // Serves event-page.html
    }

    @GetMapping("/about")
    public String aboutus() {
        return "about-us"; // Serves event-page.html
    }

    @GetMapping("/leaderboard")
    public String leaderboard() {
        return "leaderboard"; // Serves event-page.html
    }

    @GetMapping("/donate")
    public String donate(Model model) {
        // filters: local, location (state/country), cause, query
        //DonationService.getOrgs();
        //model.addAttribute("orgs", orgsList);
        return "donation-page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin-page"; // Serves admin-page.html
    }

    @GetMapping("/login")
    public String login() {
        return "log-in"; // Serves log-in.html
    }
}
