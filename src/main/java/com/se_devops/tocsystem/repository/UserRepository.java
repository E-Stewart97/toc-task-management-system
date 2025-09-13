package com.se_devops.tocsystem.repository;

import com.se_devops.tocsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository for User entities.
 * JpaRepository provides the full implementation for:
 * [C]reate: save(user)
 * [R]ead: findById(id), findAll()
 * [U]pdate: save(user)
 * [D]elete: deleteById(id)
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Custom [R]ead operation to find a user by their unique username.
     * This is essential for the security layer to authenticate users.
     *
     * @param username The username to search for.
     * @return An Optional containing the user if found.
     */
    Optional<User> findByUsername(String username);
}