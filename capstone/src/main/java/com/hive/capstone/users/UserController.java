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

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create new User
/*    @PostMapping("/new")
    public String addNewUser(@ModelAttribute User user) {
        userService.addNewUser(user);
        return "redirect:/users/all";
    }*/

    // Show user creation form
    @GetMapping("/createForm")
    public String showCreateForm() {
        return "signup";
    }

    // Get all users
    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("title", "All Users");
        return "/User/user-list";
    }

    // Get users by role
    // @GetMapping("/byRole")
    // public String findByRole(@RequestParam(name = "role", defaultValue =
    // "Volunteer") String role, Model model) {
    // model.addAttribute("userList", userService.getUsersByRole(role));
    // model.addAttribute("title", "Users with Role: " + role);
    // return "/User/user-list";
    // }
    @GetMapping("/byRole")
    public String findByRole(
            @RequestParam(name = "role", defaultValue = "Volunteer") String role,
            Model model) {
        model.addAttribute("userList", userService.getUsersByRole(role));
        model.addAttribute("title", "Users with Role: " + role);
        return "User/user-list";
    }

    // Get single user by ID
    @GetMapping("/{userId}")
    public String getUser(@PathVariable int userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "/User/user-details";
    }

    // Show delete confirmation
    @GetMapping("/delete/{userId}")
    public String confirmDelete(@PathVariable int userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "/User/user-delete";
    }

    // Delete user
    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return "redirect:/users/all";
    }

    // Show edit form
    @GetMapping("/edit/{userId}")
    public String showEditForm(@PathVariable int userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "/User/user-edit";
    }

    // Update user
    @PostMapping("/update/{userId}")
    public String updateUser(@PathVariable int userId, @ModelAttribute User user, Model model) {
        userService.updateUser(userId, user);
        model.addAttribute("user", userService.getUserById(userId));
        return "/User/user-details";
    }
}