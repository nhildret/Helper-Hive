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

    // Get a list of Events based on name
    @GetMapping("/search")
    public String getEventsByName(@RequestParam(name = "title", defaultValue = "event") String title, Model model) {
        model.addAttribute("eventList", eventService.getEventsByTitle(title));
        model.addAttribute("title", "Event Name: " + title);
        return "/Event/event-page";
    }

    @GetMapping("")
    public String getEventsByTitleContains(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("classList", eventService.getEventsByTitleContains(title));
        model.addAttribute("title", "Event Name: " + title);
        return "/Event/event-page";
    }

    // Update Event
    @GetMapping("/update/{eventId}")
    public String showUpdateForm(@PathVariable int eventId, Model model) {
        model.addAttribute("event", eventService.getEventById(eventId));
        return "/Event/event-update";
    }
    // Post Updated Event
    @PostMapping("/event/update")
    public String updateClass(Event event) {
        eventService.saveEvent(event);
        return "redirect:/event/" + event.getEventId();
    }

    // Delete Event
    @GetMapping("/delete/{eventId}")
    public String deleteEventById(@PathVariable int eventId) {
        eventService.deleteEventById(eventId);
        return "redirect:/event/all";
    }

    // Create New Event
    @PostMapping("/new")
    public String addNewEvent(Event event) {
        eventService.saveEvent(event);
        return "redirect:/event/all";
    }

}
