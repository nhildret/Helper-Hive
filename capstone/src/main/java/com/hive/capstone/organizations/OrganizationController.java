package com.hive.capstone.organizations;

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

    @GetMapping("/admin/organizations/all")
    public String getAllOrganizations(Model model) {
        model.addAttribute("organizationList", organizationService.getAllOrganizations());
        model.addAttribute("title", "All Organizations");
        // where all orgs displayed
        return "admin-organizations";
    }
}
