package com.hive.capstone;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HiveController {
    @GetMapping("/")
    public String home() {
        return "landing-page"; // Serves landing-page.html
    }

    @GetMapping("/maps")
    public String maps() {
        return "maps-page"; // Serves maps-page.html
    }

    // @GetMapping("/user")
    // public String user() {
    //     return "user-page"; // Serves user-page.html
    // }

    @GetMapping("/about")
    public String aboutus() {
        return "about-us"; // Serves about-us.html
    }

    // @GetMapping("/leaderboard")
    // public String leaderboard() {
    //     return "leaderboard"; // Serves leaderboard.html
    // }

    @GetMapping("/admin")
    public String admin() {
        return "admin-page"; // Serves admin-page.html
    }

    @GetMapping("/login")
    public String login() {
        return "log-in"; // Serves log-in.html
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

}
