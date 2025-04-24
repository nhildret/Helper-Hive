package com.hive.capstone.organizations;

import java.sql.Date;
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

    public Organization createOrganization(Organization organization) {
        organization.setRegisteredAt(new java.sql.Date(System.currentTimeMillis()));
        return organizationRepository.save(organization);
    }
}
