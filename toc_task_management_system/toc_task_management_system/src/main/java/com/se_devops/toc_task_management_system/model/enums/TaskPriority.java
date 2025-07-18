package com.se_devops.toc_task_management_system.model.enums;

import lombok.Getter;

@Getter
public enum TaskPriority {
    LOW("Low", 1),
    MEDIUM("Medium", 2),
    HIGH("High", 3),
    CRITICAL("Critical", 4);

    private final String displayName;
    private final int priority;

    TaskPriority(String displayName, int priority) {
        this.displayName = displayName;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return displayName;
    }
}