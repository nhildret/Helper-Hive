package com.hive.capstone;

import com.hive.capstone.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserService userService;

    @GetMapping("/admin/users/registration-data")
    @ResponseBody
    public List<Map<String, Object>> getUserRegistrationData() {
        List<Object[]> rawData = userService.getUserRegistrationStats();
        List<Map<String, Object>> formattedData = new ArrayList<>();

        for (Object[] row : rawData) {
            Map<String, Object> dataPoint = new HashMap<>();
            dataPoint.put("date", row[0].toString());
            dataPoint.put("count", row[1]);
            formattedData.add(dataPoint);
        }

        return formattedData;
    }




}
