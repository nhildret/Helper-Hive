package com.hive.capstone.events;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import com.hive.capstone.causes.Cause;
import com.hive.capstone.organizations.Organization;
import com.hive.capstone.users.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "title")
    private String title;

    @Column(name = "location")
    private String location;

    private double x, y;

    @Column(name = "event_date")
    private Date eventDate;

    @Column(name = "volunteer_hours")
    private int volunteerHours; // Hours earned

    @ManyToMany(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    @JoinTable(name = "eventcauses", joinColumns = @JoinColumn(name = "event_id"), 
                                        inverseJoinColumns = @JoinColumn(name = "cause_id"))
    private List<Cause> causes = new ArrayList<>();

    @Column(name = "image_path")
    private String imagePath;

    @ManyToMany(mappedBy = "events")
    private Set<User> users = new HashSet<>();
   
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

    public void setCoords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Constructors

    public Event(String title, String location, double x, double y, Date eventDate, Organization organization, int volunteerHours, String imagePath) {
        this.title = title;
        this.location = location;
        this.x = x;
        this.y = y;
        this.eventDate = eventDate;
        this.organization = organization;
        this.volunteerHours = volunteerHours;
        this.imagePath = imagePath;
    }

            
    public Event(int id, String title, String location, Date eventDate, Organization organization,
            int volunteerHours, String imagePath) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.eventDate = eventDate;
        this.organization = organization;
        this.volunteerHours = volunteerHours;
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

    public List<Cause> getCauses() {
        return causes;
    }

    public String getImagePath() {
        return imagePath;
    }
    
    public Set<User> getUsers() {
        return users;
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

    public void setCauses(List<Cause> causes) {
        this.causes = causes;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    // -----------------------
    // toString Method
    // -----------------------

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", title:'" + title + "'" +
                ", location:'" + location + "'" +
                ", coords:{x:" + x + ",y:" + y + "}" +
                ", eventDate:" + eventDate +
                ", organization:" + (organization != null ? organization.toString() : "null") +
                ", causes:" + causes +
                ", imagePath:'" + imagePath + "'" +
                ", volunteerHours:" + volunteerHours +
                '}';
    }
}