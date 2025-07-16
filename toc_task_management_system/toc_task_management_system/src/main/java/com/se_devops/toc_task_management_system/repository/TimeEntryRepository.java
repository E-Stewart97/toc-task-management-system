package com.se_devops.toc_task_management_system.repository;

import com.se_devops.toc_task_management_system.model.Task;
import com.se_devops.toc_task_management_system.model.TimeEntry;
import com.se_devops.toc_task_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeEntryRepository extends JpaRepository<TimeEntry, Integer> {

    List<TimeEntry> findByTask(Task task);

    List<TimeEntry> findByUser(User user);

    List<TimeEntry> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);

    List<TimeEntry> findByUserAndEntryDateBetween(User user, LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(te.hoursWorked) FROM TimeEntry te WHERE te.task = :task")
    BigDecimal getTotalHoursByTask(@Param("task") Task task);

    @Query("SELECT SUM(te.hoursWorked) FROM TimeEntry te WHERE te.user = :user AND te.entryDate BETWEEN :startDate AND :endDate")
    BigDecimal getTotalHoursByUserAndDateRange(@Param("user") User user, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT te FROM TimeEntry te WHERE te.user = :user ORDER BY te.entryDate DESC")
    List<TimeEntry> findByUserOrderByEntryDateDesc(@Param("user") User user);
}