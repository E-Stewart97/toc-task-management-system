package com.se_devops.toc_task_management_system.model.enums;

public enum UserRole {
    ADMIN("Administrator"),
    PROJECT_MANAGER("Project Manager"),
    TEAM_LEAD("Team Lead"),
    DEVELOPER("Developer"),
    TESTER("Tester"),
    VIEWER("Viewer");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean hasAdminPrivileges() {
        return this == ADMIN || this == PROJECT_MANAGER;
    }

    public boolean canManageTasks() {
        return this == ADMIN || this == PROJECT_MANAGER || this == TEAM_LEAD;
    }
}
