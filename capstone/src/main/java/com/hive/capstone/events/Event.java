package com.hive.capstone.events;

import java.sql.Date;

import com.hive.capstone.organizations.Organization;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int event_id;

    private String event_name;
    private String location;
    private Date event_date;
    private int volunteer_hours; // Hours earned
   
    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;
    // Links to an organization

    // Constructors

    public Event(String event_name, String location, Date event_date, Organization organization, int volunteer_hours) {
        this.event_name = event_name;
        this.location = location;
        this.event_date = event_date;
        this.organization = organization;
        this.volunteer_hours = volunteer_hours;
    }

    public Event(int event_id, String event_name, String location, Date event_date, Organization organization,
            int volunteer_hours) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.location = location;
        this.event_date = event_date;
        this.organization = organization;
        this.volunteer_hours = volunteer_hours;
    }

    public Event() {
    }

    // -----------------------
    // Getters
    // -----------------------

    public int getEventId() {
        return event_id;
    }

    public String getEventName() {
        return event_name;
    }

    public String getLocation() {
        return location;
    }

    public Date getEventDate() {
        return event_date;
    }

    public Organization getOrganizationId() {
        return organization;
    }

    public int getVolunteerHours() {
        return volunteer_hours;
    }

    // -----------------------
    // Setters
    // -----------------------

    public void setEventId(int event_id) {
        this.event_id = event_id;
    }

    public void setEventName(String event_name) {
        this.event_name = event_name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventDate(Date event_date) {
        this.event_date = event_date;
    }

    public void setOrganizationId(Organization organization) {
        this.organization = organization;
    }

    public void setVolunteerHours(int volunteer_hours) {
        this.volunteer_hours = volunteer_hours;
    }

    // -----------------------
    // toString Method
    // -----------------------

    @Override
    public String toString() {
        return "Event{" +
                "event_id=" + event_id +
                ", event_name='" + event_name + '\'' +
                ", location='" + location + '\'' +
                ", event_date=" + event_date +
                ", organization=" + (organization != null ? organization.getOrganizationName() : "null") +
                ", volunteer_hours=" + volunteer_hours +
                '}';
    }
}