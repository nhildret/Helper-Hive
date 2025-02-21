package com.hive.capstone.users;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();
    
    List<User> findByUsername(String username);

    Optional<User> findById(int user_id);

    List<User> findByRole(String role);

    List<User> getUsersByRole(String role);

}
