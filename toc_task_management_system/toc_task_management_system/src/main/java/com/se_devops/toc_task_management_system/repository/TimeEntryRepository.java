package com.se_devops.toc_task_management_system.repository;

import com.se_devops.toc_task_management_system.model.TimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TimeEntryRepository extends JpaRepository<TimeEntry, Integer> {
    
    List<TimeEntry> findByTaskId(Integer taskId);
    
    List<TimeEntry> findByUserId(Integer userId);
    
    List<TimeEntry> findByTaskIdAndUserId(Integer taskId, Integer userId);
}