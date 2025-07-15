package com.se_devops.toc_task_management_system.model;

import com.se_devops.toc_task_management_system.model.enums.TaskPriority;
import com.se_devops.toc_task_management_system.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String title;

    // what is the relationship between task to user @?To?
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toc_id", nullable = false)
    private Toc toc;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority = TaskPriority.MEDIUM;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

//    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<TimeEntry> timeEntries;

    // Constructor with fields (excluding id, timestamps, and collections)
    public Task(String title, Toc toc, User user, LocalDateTime createdDate,
                TaskStatus status, TaskPriority priority, LocalDateTime dueDate) {
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.user = user;
        this.toc = toc;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
    }

//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//    }

    // Helper methods
    public boolean isOverdue() {
        return dueDate != null && dueDate.isBefore(LocalDateTime.now()) && !isCompleted();
    }

    public boolean isCompleted() {
        return status == TaskStatus.COMPLETED;
    }

    public boolean isInProgress() {
        return status == TaskStatus.IN_PROGRESS;
    }

}