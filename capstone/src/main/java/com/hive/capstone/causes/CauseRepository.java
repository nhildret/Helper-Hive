package com.hive.capstone.causes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CauseRepository extends JpaRepository <Cause, Integer> {
    Cause findById(String id);
}
