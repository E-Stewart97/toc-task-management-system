package com.se_devops.toc_task_management_system.model;

import com.se_devops.toc_task_management_system.model.enums.TaskPriority;
import com.se_devops.toc_task_management_system.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "toc_id", nullable = false)
    private Toc toc;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    // Constructor with fields (excluding id)
    public Task(String title, TaskStatus status, TaskPriority priority, User user,
                Toc toc, LocalDateTime createdDate, LocalDate dueDate) {
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.user = user;
        this.toc = toc;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
    }
}
