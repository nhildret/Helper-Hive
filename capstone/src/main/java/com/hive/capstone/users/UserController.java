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

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // User Functions for User Controller

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

    // User Self Edit Function that gets Current User Logged In
    


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
        //model.addAttribute("user", userService.getUserById(id));
        return "redirect:/admin/users/all";
    }

    // Leaderboard controller mappings

   @GetMapping("/leaderboard/hours")
   public String showLeaderboardHours(Model model){
       List<User> leaderboard = userService.getLeaderboard();
       model.addAttribute("leaderboard", leaderboard);
       return "leaderboard-hours";
   }

   @GetMapping("/leaderboard/donations")
   public String showLeaderboardDonations(Model model){
    //    List<User> leaderboard = userService.getLeaderboard();
    //    model.addAttribute("leaderboard", leaderboard);
       return "leaderboard-donations";
   }
}