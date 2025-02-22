package com.hive.capstone.events;

import java.sql.Date;

import com.hive.capstone.organizations.Organization;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "location")
    private String location;

    @Column(name = "event_date")
    private Date eventDate;

    @Column(name = "volunteer_hours")
    private int volunteerHours;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;
    // Links to an organization

    // Constructors

    public Event(String eventName, String location, Date eventDate, Organization organization, int volunteerHours) {
        this.eventName = eventName;
        this.location = location;
        this.eventDate = eventDate;
        this.organization = organization;
        this.volunteerHours = volunteerHours;
    }

    public Event(int eventId, String eventName, String location, Date eventDate, Organization organization,
            int volunteerHours) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.location = location;
        this.eventDate = eventDate;
        this.organization = organization;
        this.volunteerHours = volunteerHours;
    }

    public Event() {
    }

    // -----------------------
    // Getters
    // -----------------------

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getLocation() {
        return location;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Organization getOrganizationId() {
        return organization;
    }

    public int getVolunteerHours() {
        return volunteerHours;
    }

    // -----------------------
    // Setters
    // -----------------------

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setOrganizationId(Organization organization) {
        this.organization = organization;
    }

    public void setVolunteerHours(int volunteerHours) {
        this.volunteerHours = volunteerHours;
    }

    // -----------------------
    // toString Method
    // -----------------------

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", location='" + location + '\'' +
                ", eventDate=" + eventDate +
                ", organization=" + (organization != null ? organization.getOrganizationName() : "null") +
                ", volunteerHours=" + volunteerHours +
                '}';
    }
}