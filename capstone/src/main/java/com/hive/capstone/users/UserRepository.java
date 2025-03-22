package com.hive.capstone.users;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    Optional<User> findByUsername(String username);

    Optional<User> findById(int user_id);

    List<User> findByRole(String role);

    List<User> getUsersByRole(String role);

    @Query("SELECT u FROM User u ORDER BY u.totalHours DESC LIMIT 5")
    List<User> findAllByOrderByTotalHoursDesc();

}
