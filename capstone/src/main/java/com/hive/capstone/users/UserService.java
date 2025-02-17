package com.hive.capstone.users;

import com.hive.capstone.users.User;
import com.hive.capstone.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(int id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setRole(userDetails.getRole());
            user.setTotalHours(userDetails.getTotalHours());
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}