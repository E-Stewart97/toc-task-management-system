package com.se_devops.tocsystem.service;

import com.se_devops.tocsystem.model.User;
import com.se_devops.tocsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Finds all users.
     * @return A list of all users in the system.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Finds a single user by their ID.
     * @param id The ID of the user to find.
     * @return An Optional containing the user if found.
     */
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
}