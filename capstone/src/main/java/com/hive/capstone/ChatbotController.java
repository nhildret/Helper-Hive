package com.hive.capstone;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

    @GetMapping("/reply")
    public String getReply(@RequestParam String message) {
        if (containsProfanity(message)) {
            return "Please keep the conversation respectful or I'll sting you.";
        }

        message = message.toLowerCase();

        if (message.contains("hello") || message.contains("hi") || message.contains("hiya") || message.contains("ciao") || message.contains("who are you")) {
            return "Hi! My name is Barry 🐝 and I'm a helper bee! How can I assist you?";
        }
        if (message.contains("honey")) {
            return "Making honey is my job, raising money is yours!";
        }
        if (message.contains("love you") || message.contains("you are cute") || message.contains("you are so cute") || message.contains("you're cute") || message.contains("you're so cute") || message.contains("you are adorable") || message.contains("you're adorable")) {
            return "Thank you! I love you too 💐";
        }
        if (message.contains("bee fact") || message.contains("about bees") || message.contains("bees")) {
            return "Fun fact! Did you know bees dance to communicate?";
        }
        if (message.contains("bye") || message.contains("goodbye") || message.contains("farewell")) {
            return "Goodbye! Have a great day!";
        }
        if (message.contains("why bee") || message.contains("why")) {
            return "Because bees are cute and like to help their peers. Like you!";
        }
        if (message.contains("thanks") || message.contains("thank you")) {
            return "You're welcome!";
        }



        if (message.contains("help")) {
            return "Sure, I'm here to help! What do you need?";
        }
        if (message.contains("donate") || message.contains("donation")) {
            return "If you wish to donate, please navigate to the dropdown under the 'services' tab.";
        }
        if (message.contains("volunteer") || message.contains("volunteering")) {
            return "If you wish to find a way to volunteer, please navigate to the dropdown under the 'services' tab.";
        }
        if (message.contains("map") || message.contains("maps") || message.contains("locate") || message.contains("location")) {
            return "To find and use the map, navigate to the 'services' tab and click on the dropdown!";
        }
        if (message.contains("find")) {
            return "To locate volunteer opportunities, please use the header to navigate our site.";
        }
        if (message.contains("account") || message.contains("profile")) {
            return "To check and change your profile, use the 'account' tab!";
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (message.contains("options") || message.contains("opportunities") || message.contains("choices")) {
            return "The types of outreach events we have here are: All of the ones provided by Pledge";
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        return "I'm not sure how to respond to that.";
    }

    private boolean containsProfanity(String message) {
        try {
            String apiUrl = "https://www.purgomalum.com/service/containsprofanity?text=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
            RestTemplate restTemplate = new RestTemplate();
            return Boolean.parseBoolean(restTemplate.getForObject(apiUrl, String.class));
        } catch (Exception e) {
            return false; // If the API fails, assume the message is clean
        }
    }
}
