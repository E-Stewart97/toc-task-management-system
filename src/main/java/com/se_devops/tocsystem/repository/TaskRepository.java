package com.se_devops.tocsystem.repository;

import com.se_devops.tocsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Task entities.
 * JpaRepository provides the full implementation for all CRUD operations:
 * [C]reate: save(task)
 * [R]ead: findById(id), findAll()
 * [U]pdate: save(task)
 * [D]elete: deleteById(id)
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {
    // No custom methods are needed at this time, as the inherited
    // methods from JpaRepository cover all required functionality.
}