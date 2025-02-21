package com.hive.capstone.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Create new User
    @PostMapping("/users/new")
    public String addNewUser(@ModelAttribute User user) {
        userService.addNewUser(user);
        return "redirect:/users/all";
    }

    // Show user creation form
    @GetMapping("/users/createForm")
    public String showCreateForm() {
        return "signup";
    }

    // User Self Edit Function
    


    // Admin Functions for User Controller
    
    // Get all users
    @GetMapping("/admin/users/all")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("title", "All Users");
        // where users will be displayed
        return "/admin-page";
    }

    // Get single user by ID
    @GetMapping("/admin/{user_id}")
    public String getUser(@PathVariable int user_id, Model model) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/User/user-details";
    }

    // Show delete confirmation
    @GetMapping("/users/delete/{user_id}")
    public String confirmDelete(@PathVariable int user_id, Model model) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/User/user-delete";
    }

    // Delete user
    @PostMapping("/users/delete/{user_id}")
    public String deleteUser(@PathVariable int user_id) {
        userService.deleteUser(user_id);
        return "redirect:/users/all";
    }

    // Show edit form
    @GetMapping("/users/edit/{user_id}")
    public String showEditForm(@PathVariable int user_id, Model model) {
        User user = userService.getUserById(user_id);
        model.addAttribute("user", user);
        return "/User/user-edit";
    }
    // Update user
    @PostMapping("/update/{user_id}")
    public String updateUser(@PathVariable int user_id, @ModelAttribute User user, Model model) {
        userService.updateUser(user_id, user);
        model.addAttribute("user", userService.getUserById(user_id));
        return "/User/user-details";
    }
}