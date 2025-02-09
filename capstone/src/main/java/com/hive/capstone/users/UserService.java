package com.hive.capstone.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(int userId, User user) {
        User existing = getUserById(userId);
        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        existing.setName(user.getName());
        existing.setRole(user.getRole());
        existing.setTotalHours(user.getTotalHours());
        userRepository.save(existing);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }
}