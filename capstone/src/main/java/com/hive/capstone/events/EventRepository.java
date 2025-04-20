package com.hive.capstone.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hive.capstone.organizations.Organization;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    //Returns all events
    List<Event> findAll();

    @Query("SELECT e FROM Event e WHERE eventDate >= CURRENT_DATE")
    List<Event> findComingUp();

    // Returns Event with name
    List<Event> getEventsByTitle(String title);

    // Returns Event with part of name

    List<Event> findByTitleContainingIgnoreCase(String title);

    // Finding Events by organization_id
    List<Event> getEventsByOrganization_Id(int organizationId);

    // Find Events by organization_id
    List<Event> findByOrganization_Id(int organizationId);

    // Finding events by id
    Event findById(int id);

    @Query("SELECT e FROM Event e WHERE ABS(e.x - ?1) < 10 AND ABS(e.y - ?2) < 10 ORDER BY ABS(e.x - ?1), ABS(e.y - ?2)")
    List<Event> getEventsByCoords(double userX, double userY);
    
}
