package com.hive.capstone.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hive.capstone.users.User;
import com.hive.capstone.organizations.Organization;

import java.util.List;

@Service
public class EventService {
    
    @Autowired
    EventRepository eventRepository;

    // Fetch all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Fetch an event by id
    public Event getEventById(int event_id) {
        return eventRepository.findById(event_id).orElse(null);
    }

    // Fetch events by organization_id
    public List<Event> getEventsByOrganizationId(Organization organization) {
        return eventRepository.getEventsByOrganizationId(organization);
    }

    // Fetch events by part of an event_name
    public List<Event> getEventsByNameContains(String event_name) {
        return eventRepository.findByTitleContainingIgnoreCase(event_name);
    }

    // Add Event
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    // Delete event
    public String deleteEventById(int event_id) {
        eventRepository.deleteById(event_id);
        return "Event Removed " + event_id;
    }

    public void saveEvent(Event event, User user) {
        eventRepository.save(event);
    }

    public List<Event> getEventsByOrganization(Organization organization) {
        return eventRepository.findByOrganizationId(organization);
    }


}
