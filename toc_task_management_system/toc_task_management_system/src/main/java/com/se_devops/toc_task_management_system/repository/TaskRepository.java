package com.se_devops.toc_task_management_system.repository;

import com.se_devops.toc_task_management_system.model.Task;
import com.se_devops.toc_task_management_system.model.enums.TaskPriority;
import com.se_devops.toc_task_management_system.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    
    List<Task> findByUserId(Integer userId);

    List<Task> findByTocId(Integer tocId);
    
    List<Task> findByStatus(TaskStatus status);
    
    List<Task> findByPriority(TaskPriority priority);
    
//    @Query("SELECT t FROM Task t WHERE t.assignedTo.id = :userId AND t.status = :status")
//    List<Task> findByAssignedToIdAndStatus(@Param("userId") Integer userId, @Param("status") TaskStatus status);
//
//    @Query("SELECT t FROM Task t WHERE t.dueDate < :date AND t.status != 'COMPLETED'")
//    List<Task> findOverdueTasks(@Param("date") LocalDateTime date);
//
//    @Query("SELECT t FROM Task t WHERE t.dueDate BETWEEN :start AND :end")
//    List<Task> findTasksDueBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}