package com.hive.capstone.organizations;

import com.hive.capstone.users.User;
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
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

//    @PostMapping("/organizations/new")
//    public String addNewOrganization(@ModelAttribute User user, Model model) {
//        organizationService.addNewOrganization(user);
//        model.addAttribute("users", userService.getAllUsers());
//        return "create-organization.html";
//    }

    @GetMapping("/admin/organizations/all")
    public String getAllOrganizations(Model model) {
        model.addAttribute("organizationList", organizationService.getAllOrganizations());
        model.addAttribute("title", "All Organizations");
        // where all orgs displayed
        return "admin-organizations";
    }
    @GetMapping("/organizations/new")
    public String showCreateOrganizationForm(Model model){
        model.addAttribute("organization", new Organization());
        model.addAttribute("title", "Create New Organization");
        return "create-organization";
    }

    @GetMapping("/organizations/view/{organizationId}")
    public String viewOrganization(@PathVariable int organizationId, Model model) {
        // Fetch Organization Details
        Organization organization = organizationService.getOrganizationById(organizationId);
        model.addAttribute("organization", organization);

        // Set page title
        model.addAttribute("title", "Organization # " + organizationId + " Details");
        return "Organization/organization-details"; // Thymeleaf template for organization details
    }

    @GetMapping("/organizations/all")
    public String viewAllOrganizations(Model model) {
        // Fetch all organizations
        List<Organization> organizationList = organizationService.getAllOrganizations();
        model.addAttribute("organization_list", organizationList); // Pass the list to the template

        // Set page title
        model.addAttribute("title", "All Organizations");

        // Return the template for displaying all organizations
        return "Organization/organization-page"; // Thymeleaf template for all organizations
    }
//    @PostMapping("/organizations/new")
//    public String addNewOrganization(@ModelAttribute("organization") Organization organization, Model model)
//        organizationService.addNewOrganization(organization);
//        return "redirect:/organizations/all";
//    }
}
