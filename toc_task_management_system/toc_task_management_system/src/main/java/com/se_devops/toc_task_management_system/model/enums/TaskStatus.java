package com.se_devops.toc_task_management_system.model.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {
    PENDING("BACKLOG"),
    IN_PROGRESS("TODO"),
    COMPLETED("IN_PROGRESS"),
    IN_REVIEW("IN_REVIEW"),
    DONE("DONE"),
    BLOCKED("BLOCKED");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
