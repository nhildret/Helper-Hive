package com.hive.capstone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String events() {
        return "event-page"; // Serves event-page.html
    }
}
