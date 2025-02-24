package com.hive.capstone.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hive.capstone.organizations.Organization;
import com.hive.capstone.organizations.OrganizationRepository;
import com.hive.capstone.users.UserRepository;
import com.hive.capstone.users.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller

@RequestMapping("/events")
public class EventController {
    
    @Autowired
    EventService eventService;
    @Autowired
    EventRepository eventRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    // Get All Events
    @GetMapping({"/all", "", "/"})
    public String getAllEvents(Model model) {
        List<Event> eventList = eventRepository.findAll();
        model.addAttribute("event_list", eventList);
        
        //model.addAttribute("event_list", eventService.getAllEvents());
        //model.addAttribute("title", "All Events");

        return "/Event/event-page";
    }

    @GetMapping("/admin/events/all")
    public String getAllEventsAdmin(Model model) {
        model.addAttribute("eventList", eventService.getAllEventsAdmin());
        model.addAttribute("title", "All Events");
        return "admin-events";
    }

    // Event By ID
    @GetMapping("/view/{eventId}")
    public String getEventsById(@PathVariable int eventId, Model model) {
        // getting authentication will be put here

        // Event event = eventService.getEventById(id);
        //model.addAttribute("isOwner", event.getOrganizationId().getId() == currentUserId);

        // Fetch Event Details
        model.addAttribute("event", eventService.getEventById(eventId));

        // Set page title
        model.addAttribute("title", "Event # " + eventId + " Details");
        return "/Event/event-details";
    }

    // Get a list of Events based on name
    // @GetMapping("/search")
    // public String getEventsByName(@RequestParam(name = "title", defaultValue = "event") String title, Model model) {
    //     model.addAttribute("eventList", eventService.getEventsByTitle(title));
    //     model.addAttribute("title", "Event Name: " + title);
    //     return "Event/event-page";
    // }

    @GetMapping("/search")
    public String getEventsByTitleContains(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("eventList", eventService.getEventsByTitleContains(title));
        model.addAttribute("title", "Event Name: " + title);
        return "Event/event-page";
    }

    // Update Event
    @GetMapping("/update/{eventId}")
    public String showUpdateForm(@PathVariable int eventId, Model model) {
        model.addAttribute("event", eventService.getEventById(eventId));
        return "Event/event-update";
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

    // Creating an Event:
    // Display the "Create Event" form (GET request)
    @GetMapping("/new")
    public String showCreateEventForm(Model model) {
        model.addAttribute("event", new Event());
        // Fetch all organizations and pass them to the template
        List<Organization> organizations = organizationRepository.findAll();
        model.addAttribute("organizations", organizations);
        return "Event/add-event";
    }
    // Handle form submission (POST request)
    @PostMapping("/new")
    public String createEvent(@ModelAttribute Event event, @RequestParam("organizationId") int organizationId) {
        // Fetch the Organization object based on the submitted organizationId
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid organization ID: " + organizationId));
        // Set the Organization object in the Event
        event.setOrganization(organization);

        eventRepository.save(event);
        return "redirect:/events/all";
    }

}
