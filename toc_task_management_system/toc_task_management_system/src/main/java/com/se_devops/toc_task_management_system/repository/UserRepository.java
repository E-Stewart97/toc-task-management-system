package com.se_devops.toc_task_management_system.repository;

import com.se_devops.toc_task_management_system.model.User;
import com.se_devops.toc_task_management_system.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByUsername(String username);
    
    List<User> findByRole(UserRole role);
    
    boolean existsByUsername(String username);
    
    List<User> findByRoleIn(List<UserRole> roles);
}