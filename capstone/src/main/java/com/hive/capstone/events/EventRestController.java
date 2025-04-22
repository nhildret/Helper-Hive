package com.hive.capstone.events;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hive.capstone.causes.Cause;
import com.hive.capstone.causes.CauseRepository;
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
    @Autowired
    CauseRepository causeRepository;

    @Autowired
    EventService service;

    //with coords
    @GetMapping("/withCoords") 
    public String filterEvents(@RequestParam("comingUp") boolean comingUp, @RequestParam("causes") String causes,
                            @RequestParam("x") double x, @RequestParam("y") double y) {
        //build query
        String query = "SELECT e, ec.cause_id from events e"
                        + " JOIN eventcauses ec on ec.event_id = e.event_id"
                        + " WHERE ABS(e.x - ("+x+")) < 10 AND ABS(e.y - ("+y+")) < 10";

        if (comingUp) {
         query += " AND e.eventDate >= CURRENT_DATE";
        }
        if (causes.length() != 0) {
            String[] cause = causes.split(",");
            query += " AND ec.cause_id IN ('" + cause[0];
            for (int i = 1; i < cause.length; i++) {
                query += "','" + cause[i];
            }
            query += "')";
        }
        //sort
        query += " ORDER BY";
        if (comingUp) {
            query += " eventDate,";
        }
        query += " ABS(e.x - ("+x+")), ABS(e.y - ("+y+"))";

        service.getEventsByQuery(query);
        //return a list of events for events.js to populate the container with
        return "/Event/event-page";
    }

    //without coords
    @GetMapping("/withoutCoords") 
    public String filterEvents(@RequestParam("comingUp") boolean comingUp, @RequestParam("causes") String causes) {
        //build query
        String query = "SELECT e.*";
        if (causes.length() > 0) {
            String[] cause = causes.split(",");

            query += ", ec.cause_id from events e";
            query += " JOIN eventcauses ec on ec.event_id = e.event_id";
            query += " WHERE ec.cause_id IN ('" + cause[0];
            for (int i = 1; i < cause.length; i++) {
                query += "','" + cause[i];
            }
            query += "')";

            if (comingUp) {
                query += " AND e.event_date >= CURRENT_DATE";
                query += " ORDER BY event_date";
            }
        } else {
            query += " from events e";
            if (comingUp) {
                query += " WHERE e.event_date >= CURRENT_DATE";
                query += " ORDER BY event_date";
            }
        }

        service.getEventsByQuery(query);
        
        //return a list of events.toString() for events.js to populate the container with
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
                            @RequestParam("causeId") String[] causeId,
                            @RequestParam("imagePath") String imagePath, 
                            @RequestParam("organizationId") int organizationId) {

        //parse date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date eDate = sdf.parse(eventDate, new ParsePosition(0));

        //get causes from id, put them into List
        List<Cause> causes = new ArrayList<Cause>();
        for (int i = 0; i < causeId.length; i++) {
            causes.add(causeRepository.findById(causeId[i]));
        }

        //create event object to save to DB
        Event event = new Event(title, location, x, y, eDate, organizationRepository.findById(organizationId), volunteerHours, imagePath);
        event.setCauses(causes);

        eventRepository.save(event);
    }
}
