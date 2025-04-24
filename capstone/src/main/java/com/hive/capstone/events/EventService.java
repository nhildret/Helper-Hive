package com.hive.capstone.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import com.hive.capstone.users.User;

import lombok.Value;

import com.hive.capstone.organizations.Organization;
import com.hive.capstone.organizations.OrganizationRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.hive.capstone.beans.DBConfig;
import com.hive.capstone.causes.Cause;

@Service
public class EventService {
    
    @Autowired
    EventRepository eventRepository;

    @Autowired
    OrganizationRepository organizationRepository;
    
    @Autowired
    DBConfig dbConfig;

    // Fetch all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getAllEventsAdmin() {

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

    // Fetch events by title
    public List<Event> getEventsByTitle(String title) {
        return eventRepository.getEventsByTitle(title);
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


    public List<Event> getEventsByQuery(String query) {

        try {
            
            JdbcTemplate jdbc = new JdbcTemplate(dbConfig.getDataSource());

            List<Event> returnList = jdbc.query(query, new ResultSetExtractor<List<Event>>() {
                // extractData() is ResultSetExtractor 
                // interface's method
                public List<Event> extractData(ResultSet rs) throws SQLException, DataAccessException {
                    List<Event> eventsList = new ArrayList<Event>();
                    while(rs.next()) {
                        Event event = new Event();
                        // 1, 2 and 3 are the indices of the data present
                        // in the database respectively 
                        event.setEventId(rs.getInt(1));
                        event.setTitle(rs.getString(2));
                        event.setLocation(rs.getString(3));
                        event.setEventDate(rs.getDate(4));
                        event.setOrganization(organizationRepository.findById(rs.getInt(5)));
                        event.setVolunteerHours(rs.getInt(6));
                        event.setImagePath(rs.getString(7));
                        event.setCoords(rs.getDouble(8), rs.getDouble(9));

                        // add causes
                        // Cause[] causes = (Cause[])rs.getArray(10).getArray();
                        // event.setCauses(Arrays.asList(causes));

                        eventsList.add(event);
                    }
                    return eventsList;
                }
            });
            return returnList;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Event>();
        }
    }

}
