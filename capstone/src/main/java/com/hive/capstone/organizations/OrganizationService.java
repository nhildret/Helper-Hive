package com.hive.capstone.organizations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    // Fetch an organization by id
    public Organization getOrganizationById(int organization_id) {
        return organizationRepository.findById(organization_id);// .orElse(null);
    }

    // public Organization getOrganizationById(int organizationId) {
    //     return organizationRepository.findById(organizationId)
    //             .orElseThrow(() -> new IllegalArgumentException("Invalid organization ID: " + organizationId));
    // }
}
