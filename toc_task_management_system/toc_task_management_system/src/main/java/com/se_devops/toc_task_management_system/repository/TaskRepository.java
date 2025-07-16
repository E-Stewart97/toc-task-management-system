package com.se_devops.toc_task_management_system.repository;

import com.se_devops.toc_task_management_system.model.Task;
import com.se_devops.toc_task_management_system.model.Toc;
import com.se_devops.toc_task_management_system.model.User;
import com.se_devops.toc_task_management_system.model.enums.TaskPriority;
import com.se_devops.toc_task_management_system.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(TaskPriority priority);

    List<Task> findByUser(User user);

    List<Task> findByUserIsNull();

    List<Task> findByToc(Toc toc);

    List<Task> findByStatusAndUser(TaskStatus status, User user);

    @Query("SELECT t FROM Task t WHERE t.dueDate IS NOT NULL AND t.dueDate < :date AND t.status != 'DONE'")
    List<Task> findOverdueTasks(@Param("date") LocalDateTime date);

    @Query("SELECT t FROM Task t WHERE t.user = :user ORDER BY t.priority DESC, t.dueDate ASC")
    List<Task> findByUserOrderByPriorityAndDueDate(@Param("user") User user);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.status = :status")
    long countByStatus(@Param("status") TaskStatus status);

    @Query("SELECT t FROM Task t WHERE t.title LIKE %:keyword% OR t.toc.name LIKE %:keyword%")
    List<Task> findByKeyword(@Param("keyword") String keyword);
}