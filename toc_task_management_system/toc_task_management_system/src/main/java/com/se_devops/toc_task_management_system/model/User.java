package com.se_devops.toc_task_management_system.model;

import com.se_devops.toc_task_management_system.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Convert(converter = com.se_devops.toc_task_management_system.model.converter.UserRoleConverter.class)
    @Column(nullable = false)
    private UserRole role;

    // Constructor with fields (excluding id)
    public User(String username, String passwordHash, UserRole role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // Helper method to check if user has admin privileges
    public boolean hasAdminPrivileges() {
        return role != null && role.hasAdminPrivileges();
    }

    // Helper method to check if user can manage tasks
    public boolean canManageTasks() {
        return role != null && role.canManageTasks();
    }

}
