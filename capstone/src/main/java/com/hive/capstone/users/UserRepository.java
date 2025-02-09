package com.hive.capstone.users;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByRole(String role);

    Optional<User> findByUsername(String username);
}