package com.hive.capstone.causes;
import java.util.List;
import java.util.Set;

import com.hive.capstone.donations.Donation;
import com.hive.capstone.events.Event;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "causes")
public class Cause {
    @Id()
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "causes")
    List<Donation> donations;

    @ManyToMany(mappedBy = "causes")
    List<Event> events;

    //Getter

    public String getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }


    @Override
    public String toString() {
        return "id = " + id + ", title = " + title;
    }
}
