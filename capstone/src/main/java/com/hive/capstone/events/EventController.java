package com.hive.capstone.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hive.capstone.users.UserRepository;
import com.hive.capstone.users.UserService;

import java.util.List;

@Controller

@RequestMapping("/events")
public class EventController {
    
    @Autowired
    EventService eventService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    // Get All Events
    @GetMapping({"/all", "", "/"})
    public String getAllEvents(Model model) {
        model.addAttribute("event_list", eventService.getAllEvents());
        model.addAttribute("title", "All Events");
        return "/Event/event-page";
    }

    // Event By ID
    @GetMapping("/{id}")
    public String getEventsById(@PathVariable int id, Model model) {
        // getting authentication will be put here

        Event event = eventService.getEventById(id);
        //model.addAttribute("isOwner", event.getOrganizationId().getId() == currentUserId);

        // Fetch Event Details
        model.addAttribute("event", eventService.getEventById(id));

        // Set page title
        model.addAttribute("title", "Event # " + id + " Details");
        return "/Event/event-details";
    }
    
    


}
