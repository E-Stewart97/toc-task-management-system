package com.se_devops.toc_task_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "time_entries")
public class TimeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    @NotNull(message = "Task is required")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User is required")
    private User user;

    @Column(name = "hours_worked", nullable = false, precision = 4, scale = 2)
    @NotNull(message = "Hours worked is required")
    @DecimalMin(value = "0.01", message = "Hours worked must be greater than 0")
    @DecimalMax(value = "99.99", message = "Hours worked cannot exceed 99.99")
    private BigDecimal hoursWorked;

    @Column(name = "entry_date", nullable = false)
    @NotNull(message = "Entry date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate entryDate;

    // Constructor
    public TimeEntry(Task task, User user, BigDecimal hoursWorked, LocalDate entryDate) {
        this.task = task;
        this.user = user;
        this.hoursWorked = hoursWorked;
        this.entryDate = entryDate;
    }
}