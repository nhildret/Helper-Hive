package com.hive.capstone.users;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hive.capstone.security.SecurityConfig;

import org.springframework.util.StringUtils;
import java.util.regex.Pattern;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Getting User Profile
    @GetMapping("/user")
    public String getAccount(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        model.addAttribute("user", user);
        return "user-page";
    }

    @PostMapping("/user/update")
    public String updateAccount(
            @ModelAttribute User updatedUser,
            @RequestParam(required = false) String currentPassword,
            @RequestParam(required = false) String newPassword,
            @RequestParam(required = false) String confirmPassword,
            RedirectAttributes redirectAttributes) {

        User existingUser = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id"));

        // Update basic info
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

        // Handle password change if fields are filled
        if (StringUtils.hasText(newPassword)) {
            if (!passwordEncoder.matches(currentPassword, existingUser.getPassword())) {
                redirectAttributes.addFlashAttribute("error", "Current password is incorrect");
                return "redirect:/user";
            }

            if (!newPassword.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("error", "New passwords do not match");
                return "redirect:/user";
            }

            existingUser.setPassword(passwordEncoder.encode(newPassword));
        }

        userRepository.save(existingUser);
        redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
        return "redirect:/user";
    }

    // @PostMapping("/user/update")
    // public String updateAccount(@ModelAttribute User updatedUser) {
    // User existingUser = userRepository.findById(updatedUser.getId())
    // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id"));
    // // Update allowed fields
    // existingUser.setUsername(updatedUser.getUsername());
    // existingUser.setName(updatedUser.getName());
    // existingUser.setEmail(updatedUser.getEmail());

    // userRepository.save(existingUser);
    // return "redirect:/user";
    // }

    // Create new User
    @PostMapping("/users/new")
    public String addNewUser(@ModelAttribute User user, Model model) {
        userService.addNewUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/home";
        // returns home-page.html
    }

    // Show user creation form
    @GetMapping("/users/createForm")
    public String showCreateForm() {
        return "signup";
    }

    // Admin Functions for User Controller

    // Get all users
    @GetMapping("/admin/users/all")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("title", "All Users");
        // where users will be displayed
        return "admin-users";
    }

    // Get single user by ID
    @GetMapping("/admin/users/{id}")
    public String getUser(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin-users";
    }

    // Show delete confirmation
    @GetMapping("/users/delete/{id}")
    public String confirmDelete(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin-users";
    }

    // Delete user
    @PostMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users/all";
    }

    // Show edit form
    @GetMapping("/admin/users/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin-users";
    }

    // Update user
    @PostMapping("/admin/update/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        // model.addAttribute("user", userService.getUserById(id));
        return "redirect:/admin/users/all";
    }

    // Leaderboard controller mappings

    @GetMapping("/leaderboard/hours")
    public String showLeaderboardHours(Model model) {
        List<User> leaderboard = userService.getLeaderboard();
        model.addAttribute("leaderboard", leaderboard);
        return "leaderboard-hours";
    }

    @GetMapping("/leaderboard/donations")
    public String showLeaderboardDonations(Model model) {
        // List<User> leaderboard = userService.getLeaderboard();
        // model.addAttribute("leaderboard", leaderboard);
        return "leaderboard-donations";
    }
}