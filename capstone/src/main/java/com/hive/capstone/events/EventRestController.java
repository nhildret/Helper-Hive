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

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    EventService eventService;

    //with coords
    @GetMapping("/withCoords") 
    public String[] filterEvents(@RequestParam("comingUp") boolean comingUp, @RequestParam("causes") String causes,
                            @RequestParam("x") double x, @RequestParam("y") double y) {
        String xformula = "(e.x - ("+x+"))";
        String yformula = "(e.y - ("+y+"))";
        String coordsFormula = "SQRT(("+xformula+" * "+xformula+") + ("+yformula+" * "+yformula+"))";
        System.out.println(coordsFormula);
        //build query
        String query = "SELECT * from events e"
                        + " WHERE " + coordsFormula + " < 1";
        if (comingUp) {
         query += " AND e.event_date >= CURRENT_DATE";
        }
        if (causes.length() != 0) {
            String[] cause = causes.split(",");
            query += " AND event_id IN (SELECT event_Id from eventcauses where cause_id IN ('" + cause[0];
            for (int i = 1; i < cause.length; i++) {
                query += "','" + cause[i];
            }
            query += "'))";
        }
        query += " ORDER BY ";
        if (comingUp) {
            query += "event_date, ";
        }
        query += coordsFormula + ";";

        //Get list of Events and create JSON string
        List<Event> eventsList = eventService.getEventsByQuery(query);
        String[] eventsString = new String[eventsList.size()];
        int i = 0;
        for (Event event : eventsList) {
            eventsString[i] = new JSONObject(event).toString();
            i++;
        }

        //return a list of events for events.js to populate the container with
        return eventsString;
    }

    //without coords
    @GetMapping("/withoutCoords") 
    public String[] filterEvents(@RequestParam("comingUp") boolean comingUp, @RequestParam("causes") String causes) {
        //build query
        String query = "SELECT * from events e";
        if (causes.length() > 0) {
            String[] cause = causes.split(",");

            query += " WHERE event_id IN (SELECT event_Id from eventcauses where cause_id IN ('" + cause[0];
            for (int i = 1; i < cause.length; i++) {
                query += "','" + cause[i];
            }
            query += "'))";

            if (comingUp) {
                query += " AND e.event_date >= CURRENT_DATE";
                query += " ORDER BY event_date";
            }
        } else {
            if (comingUp) {
                query += " WHERE e.event_date >= CURRENT_DATE";
                query += " ORDER BY event_date";
            }
        }

        //Get list of Events and create JSON string
        List<Event> eventsList = eventService.getEventsByQuery(query);
        String[] eventsString = new String[eventsList.size()];
        int i = 0;
        for (Event event : eventsList) {
            eventsString[i] = new JSONObject(event).toString();
            i++;
        }

        //return a list of events for events.js to populate the container with
        return eventsString;
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

    // Post Updated Event
    @PutMapping("/edit")
    public void editEvent(@RequestParam("eventId") int eventId, 
                            @RequestParam("title") String title, 
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
        //get existing Event
        Event event = eventService.getEventById(eventId);

        event.setTitle(title);
        event.setLocation(location);
        event.setCoords(x, y);
        event.setVolunteerHours(volunteerHours);
        event.setEventDate(eDate);
        event.setCauses(causes);
        event.setImagePath(imagePath);
        event.setOrganization(organizationRepository.findById(organizationId));

        eventService.saveEvent(event);
    }
}
