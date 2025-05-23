package com.hive.capstone.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hive.capstone.causes.Cause;
import com.hive.capstone.causes.CauseRepository;
import com.hive.capstone.organizations.Organization;
import com.hive.capstone.organizations.OrganizationRepository;
import com.hive.capstone.organizations.OrganizationService;
import com.hive.capstone.users.UserRepository;
import com.hive.capstone.users.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hive.capstone.users.User;
import com.hive.capstone.users.User;

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
    OrganizationService organizationService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Autowired
    CauseRepository causeRepository;

    // Get All Events
    @GetMapping({"/all", "", "/"})
    public String getAllEvents(Model model) {
        List<Event> eventList = eventRepository.findAll();
        model.addAttribute("event_list", eventList);
        List<Cause> causes = causeRepository.findAll();
        model.addAttribute("causes", causes);
        //model.addAttribute("event_list", eventService.getAllEvents());
        //model.addAttribute("title", "All Events");

        return "Event/event-page";
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName()).orElse(null);
        model.addAttribute("user", user);

        // Event event = eventService.getEventById(id);
        //model.addAttribute("isOwner", event.getOrganizationId().getId() == currentUserId);

        // Fetch Event Details
        Event event = eventService.getEventById(eventId);
        model.addAttribute("event", event);

        //Fetch Causes for edit form
        List<Cause> causes = causeRepository.findAll();
        model.addAttribute("causes", causes);

        // Fetch All Events for the "All Event Cards" section
        List<Event> eventList = eventService.getAllEvents();
        model.addAttribute("event_list", eventList);

        // Fetch All Organizations for the dropdown
        List<Organization> organizations = organizationService.getAllOrganizations();
        model.addAttribute("organizations", organizations);

        // Set page title
        model.addAttribute("title", "Event # " + eventId + " Details");
        return "Event/event-details";
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
        model.addAttribute("causes", causeRepository.findAll());

        return "Event/event-page";
    }

    // Update Event
    @GetMapping("/update/{eventId}")
    public String showUpdateForm(@PathVariable int eventId, Model model) {
        model.addAttribute("event", eventService.getEventById(eventId));
        return "Event/event-update";
    }

    // Delete Event
    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int eventId) {
        eventRepository.deleteById(eventId);
        return ResponseEntity.ok().build();
    }

    // Creating an Event:
    // Display the "Create Event" form (GET request)
    @GetMapping("/new")
    public String showCreateEventForm(Model model) {
        model.addAttribute("event", new Event());
        List<Cause> causes = causeRepository.findAll();
        model.addAttribute("causes", causes);
        // Fetch all organizations and pass them to the template
        List<Organization> organizations = organizationRepository.findAll();
        model.addAttribute("organizations", organizations);
        return "Event/add-event";
    }
    
    @PostMapping("/signup/{eventId}")
    public String signUpForEvent(@PathVariable int eventId, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Event event = eventService.getEventById(eventId);

        if (user.getEvents().contains(event)) {
            redirectAttributes.addFlashAttribute("error", "You're already signed up for this event!");
            return "redirect:/events/view/" + eventId;
        }

        user.getEvents().add(event);
        user.setTotalHours(user.getTotalHours() + event.getVolunteerHours());
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("success", "Successfully signed up!");
        return "redirect:/events/view/" + eventId;
    }


}
