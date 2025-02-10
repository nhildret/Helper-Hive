package com.hive.capstone.event;

import java.sql.Date;

public class Event {

    private int eventId;
    private String eventName;
    private String location;
    private Date eventDate;
    private int organizationId; // Links to an organization
    private int volunteerHours; // Hours earned

    // Constructors

    public Event(String eventName, String location, Date eventDate, int organizationId, int volunteerHours) {
        this.eventName = eventName;
        this.location = location;
        this.eventDate = eventDate;
        this.organizationId = organizationId;
        this.volunteerHours = volunteerHours;
    }

    public Event(int eventId, String eventName, String location, Date eventDate, int organizationId,
            int volunteerHours) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.location = location;
        this.eventDate = eventDate;
        this.organizationId = organizationId;
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

    public int getOrganizationId() {
        return organizationId;
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

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
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
                ", organizationId=" + organizationId +
                ", volunteerHours=" + volunteerHours +
                '}';
    }
}