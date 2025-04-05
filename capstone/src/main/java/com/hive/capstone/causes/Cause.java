package com.hive.capstone.causes;
import java.util.List;
import java.util.Set;

import com.hive.capstone.donations.Donation;

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
}
