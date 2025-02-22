package com.hive.capstone.events;

import java.sql.Date;

import com.hive.capstone.organizations.Organization;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String location;
    private Date event_date;
    private int volunteer_hours; // Hours earned
   
    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;
    // Links to an organization

    // Constructors

    public Event(String title, String location, Date event_date, Organization organization, int volunteer_hours) {
        this.title = title;
        this.location = location;
        this.event_date = event_date;
        this.organization = organization;
        this.volunteer_hours = volunteer_hours;
    }

    public Event(int id, String title, String location, Date event_date, Organization organization,
            int volunteer_hours) {
        this.id = id;
        this.title = title;
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
        return id;
    }

    public String getEventName() {
        return title;
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

    public void setEventId(int id) {
        this.id = id;
    }

    public void setEventName(String title) {
        this.title = title;
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
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", event_date=" + event_date +
                ", organization=" + (organization != null ? organization.getOrganizationName() : "null") +
                ", volunteer_hours=" + volunteer_hours +
                '}';
    }
}