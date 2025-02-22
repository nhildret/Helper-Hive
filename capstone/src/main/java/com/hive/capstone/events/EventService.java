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
    public Event getEventById(int id) {
        return eventRepository.findById(id);//.orElse(null);
    }

    // Fetch events by organization_id
    public List<Event> getEventsByOrganizationId(Organization organization) {
        return eventRepository.getEventsByOrganization_Id(organization.getOrganizationId());
    }

    // Fetch events by part of a title
    public List<Event> getEventsByTitleContains(String title) {
        return eventRepository.findByTitleContainingIgnoreCase(title);
    }

    // Add Event
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    // Delete event
    public String deleteEventById(int id) {
        eventRepository.deleteById(id);
        return "Event Removed " + id;
    }

    public void saveEvent(Event event, User user) {
        eventRepository.save(event);
    }

    public List<Event> getEventsByOrganization(Organization organization) {
        return eventRepository.findByOrganization_Id(organization.getOrganizationId());
    }


}
