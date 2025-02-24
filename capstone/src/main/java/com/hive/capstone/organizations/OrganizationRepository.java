package com.hive.capstone.organizations;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

    List<Organization> findAll();

    Organization findById(int organizationId);

    
}
