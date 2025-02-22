package com.hive.capstone.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hive.capstone.organizations.Organization;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    // Returns Event with name
    List<Event> getEventsByName(String event_name);

    // Returns Event with part of name
    List<Event> findByTitleContainingIgnoreCase(String event_name);

    // Finding Events by organization_id
    List<Event> getEventsByOrganizationId(Organization organization);

    // Find Events by organization_id
    List<Event> findByOrganizationId(Organization organization);

    // Finding events by event_id
    List<Event> findByEventId(int event_id);
    
}
