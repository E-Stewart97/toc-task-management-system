package com.se_devops.toc_task_management_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "hours_worked", nullable = false, precision = 4, scale = 2)
    private BigDecimal hoursWorked;

    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate;

    // Constructor with fields (excluding id)
    public TimeEntry(Task task, User user, BigDecimal hoursWorked, LocalDate entryDate) {
        this.task = task;
        this.user = user;
        this.hoursWorked = hoursWorked;
        this.entryDate = entryDate;
    }

}
