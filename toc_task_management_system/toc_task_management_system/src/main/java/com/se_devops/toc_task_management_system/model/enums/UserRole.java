package com.se_devops.toc_task_management_system.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("Admin"),
    REGULAR("Regular");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public boolean hasAdminPrivileges() {
        return this == ADMIN;
    }

    public boolean canManageTasks() {
        return this == ADMIN;
    }

    public boolean canViewAllTasks() {
        return this == ADMIN;
    }

    @Override
    public String toString() {
        return displayName;
    }

}