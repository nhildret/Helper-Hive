package com.hive.capstone.users;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int user_id) {
        return userRepository.findById(user_id).orElse(null);
    }

    public void saveUser(User user) {
        // Encode password before saving
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteUser(int user_id) {
        userRepository.deleteById(user_id);
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void addNewUser(User user) {
        // Encode password before saving
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Set registration date
        user.setRegisteredAt((java.sql.Date) new Date());
        userRepository.save(user);
    }

    public void updateUser(int user_id, User user) {
        User existing = userRepository.findById(
                user_id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user_id));

        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        existing.setName(user.getName());
        existing.setRole(user.getRole());
        existing.setTotalHours(user.getTotalHours());
        existing.setPassword(user.getPassword());
        // Only update password if a new one is provided
        // if (user.getPassword() != null && !user.getPassword().isEmpty()) {
        //     existing.setPassword(passwordEncoder.encode(user.getPassword()));
        // }
        userRepository.save(existing);
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public User getUser(int user_id) {
        return userRepository.getReferenceById(user_id);
    }

//LeaderBoard implementation to get users:
//    public List<User> getLeaderboard(){
//        return userRepository.findAllByOrderByTotalHoursDesc();

}