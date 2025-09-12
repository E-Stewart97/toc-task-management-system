package com.se_devops.tocsystem.repository;

import com.se_devops.tocsystem.model.TimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for TimeEntry entities.
 * JpaRepository provides the full implementation for all CRUD operations:
 * [C]reate: save(timeEntry)
 * [R]ead: findById(id), findAll()
 * [U]pdate: save(timeEntry)
 * [D]elete: deleteById(id)
 */
public interface TimeEntryRepository extends JpaRepository<TimeEntry, Integer> {
    // No custom methods are needed at this time.
}