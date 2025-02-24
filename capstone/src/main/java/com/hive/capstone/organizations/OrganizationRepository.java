package com.hive.capstone.organizations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    
}
