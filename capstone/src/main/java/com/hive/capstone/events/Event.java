package com.hive.capstone.events;

import java.sql.Date;

import com.hive.capstone.organizations.Organization;

//import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "location")
    private String location;

    @Column(name = "event_date")
    private Date eventDate;

    @Column(name = "volunteer_hours")
    private int volunteerHours; // Hours earned

    @Column(name = "cause_id")
    private int causeId;

    @Column(name = "image_path")
    private String imagePath;
   
    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;
    // Links to an organization
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    // Constructors

    public Event(String title, String location, Date eventDate, Organization organization, int volunteerHours, int causeId, String imagePath) {
        this.title = title;
        this.location = location;
        this.eventDate = eventDate;
        this.organization = organization;
        this.volunteerHours = volunteerHours;
        this.causeId = causeId;
        this.imagePath = imagePath;
    }

            
    public Event(int id, String title, String location, Date eventDate, Organization organization,
            int volunteerHours, int causeId, String imagePath) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.eventDate = eventDate;
        this.organization = organization;
        this.volunteerHours = volunteerHours;
        this.causeId = causeId;
        this.imagePath = imagePath;
    }

    public Event() {
    }

    // -----------------------
    // Getters
    // -----------------------

    public int getEventId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public int getOrganizationId() {
        return organization.getOrganizationId();
    }

    public int getVolunteerHours() {
        return volunteerHours;
    }

    public int getCauseId() {
        return causeId;
    }

    public String getImagePath() {
        return imagePath;
    }

    // -----------------------
    // Setters
    // -----------------------

    public void setEventId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setCauseId(int causeId) {
        this.causeId = causeId;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
                ", eventDate=" + eventDate +
                ", organization=" + (organization != null ? organization.getOrganizationName() : "null") +
                ", causeId=" + causeId +
                ", imagePath=" + imagePath +
                ", volunteerHours=" + volunteerHours +
                '}';
    }
}