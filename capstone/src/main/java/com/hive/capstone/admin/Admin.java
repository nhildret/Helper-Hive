/*
package com.hive.capstone.admin;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="admin")

public class Admin {

        @Id
        @Column(name = "admin_id")
        @GeneratedValue(strategy= GenerationType.AUTO)
        private int id;

        private String username;
        private String password;
        private String role; // For Volunteers, Admins, and OrganizationRepresentatives
        private String name;
        private String email;

        @Column(name = "registered_at")
        private Date registeredAt;

        @Column(name = "total_hours")
        private int totalHours;

        // Constructors

        public User(int id, String username, String name, String role, String email, Date registeredAt, String password,
                    int totalHours) {
            this.id = id;
            this.username = username;
            this.name = name;
            this.role = role;
            this.email = email;
            this.registeredAt = registeredAt;
            this.password = password;
            this.totalHours = totalHours;
        }

        public User(String username, String name, String role, String email, Date registeredAt, String password,
                    int totalHours) {
            this.username = username;
            this.name = name;
            this.role = role;
            this.email = email;
            this.registeredAt = registeredAt;
            this.password = password;
            this.totalHours = totalHours;
        }

        // Default constructor
        public User() {
        }
        // toString Method
        public String toString() {
            return "User{" +
                    "id=" + id + '}';
        }

        // -----------------------
        // Getters
        // -----------------------

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public Date getRegisteredAt() {
            return registeredAt;
        }

        public int getTotalHours() {
            return totalHours;
        }

        // -----------------------
        // Setters
        // -----------------------

        public void setId(int id) {
            this.id = id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setRegisteredAt(Date registeredAt) {
            this.registeredAt = registeredAt;
        }

        public void setTotalHours(int totalHours) {
            this.totalHours = totalHours;
        }

    }
}
*/
