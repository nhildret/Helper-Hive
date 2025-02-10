package com.hive.capstone.user;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String password;
    private String role; // For Volunteers, Admins, and OrganizationRepresentatives
    private String name;
    private String email;
    private Date registeredAt;
    private int totalHours;

    public User(int userId, String username, String name, String role, String email, Date registeredAt, String password,
            int totalHours) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.role = role;
        this.email = email;
        this.registeredAt = registeredAt;
        this.password = password;
        this.totalHours = totalHours;
    }

}
