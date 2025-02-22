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
    public Event getEventById(int eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    // Fetch events by organizationNd
    public List<Event> getEventsByOrganizationId(int organizationId) {
        return eventRepository.findByOrganizationId(organizationId);
    }

    // Fetch events by part of an eventName
    public List<Event> getEventsByNameContains(String eventName) {
        return eventRepository.findByTitleContainingIgnoreCase(eventName);
    }

    // Add Event
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    // Delete event
    public String deleteEventById(int eventId) {
        eventRepository.deleteById(eventId);
        return "Event Removed " + eventId;
    }

    public void saveEvent(Event event, User user) {
        eventRepository.save(event);
    }

    public List<Event> getEventsByOrganization(int organizationId) {
        return eventRepository.findByOrganizationId(organizationId);
    }


}
