package com.hive.capstone.events;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hive.capstone.organizations.Organization;
import com.hive.capstone.organizations.OrganizationRepository;
import com.hive.capstone.users.UserRepository;

@RestController
@RequestMapping("/events")
public class EventRestController {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    //with coords
    @GetMapping("/withCoords") 
    public String filterEvents(@RequestParam("comingUp") boolean comingUp, @RequestParam("causes") String causes,
                            @RequestParam("x") double x, @RequestParam("y") double y) {
        // String query = "SELECT e from Event e";
        // if (nearMe) {
        //     query += " WHERE ";
        // }
        //return a list of events for events.js to populate the container with
        System.out.println("\n\n" + comingUp + "\n" + causes + "\n" + x + " " + y);
        return "/Event/event-page";
    }

    //without coords
    @GetMapping("/withoutCoords") 
    public String filterEvents(@RequestParam("comingUp") boolean comingUp, @RequestParam("causes") String causes) {
        // String query = "SELECT e from Event e";
        // if (nearMe) {
        //     query += " WHERE ";
        // }
        //return a list of events for events.js to populate the container with
        System.out.println("\n\n" + comingUp + "\n" + causes + "\nno coords");
        return "/Event/event-page";
    }

    // Handle form submission (POST request)
    @PostMapping("/new")
    public void createEvent(@RequestParam("title") String title, 
                            @RequestParam("location") String location,
                            @RequestParam("x") double x,
                            @RequestParam("y") double y,
                            @RequestParam("eventDate") String eventDate,
                            @RequestParam("volunteerHours") int volunteerHours, 
                            @RequestParam("causeId") int causeId,
                            @RequestParam("imagePath") String imagePath, 
                            @RequestParam("organizationId") int organizationId) {

        //parse date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date eDate = sdf.parse(eventDate, new ParsePosition(0));

        //create event object to save to DB
        Event event = new Event(title, location, x, y, eDate, organizationRepository.findById(organizationId), volunteerHours, causeId, imagePath);

        eventRepository.save(event);
    }
}
