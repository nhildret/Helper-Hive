package com.hive.capstone.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hive.capstone.organizations.Organization;
import com.hive.capstone.organizations.OrganizationRepository;
import com.hive.capstone.users.UserRepository;

@RestController
@RequestMapping("/events")
public class EventRestController {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrganizationRepository organizationRepository;


    // Handle form submission (POST request)
    @PostMapping("/new")
    public void createEvent(@ModelAttribute Event event, @RequestParam("organizationId") int organizationId) {
        // // Fetch the Organization object based on the submitted organizationId
        // Organization organization = organizationRepository.findById(organizationId);
        //         //.orElseThrow(() -> new IllegalArgumentException("Invalid organization ID: " + organizationId));
        // // Set the Organization object in the Event
        // event.setOrganization(organization);
        System.out.println(event.toString());

        // eventRepository.save(event);

        // return "redirect:/events/all";
    }
}
