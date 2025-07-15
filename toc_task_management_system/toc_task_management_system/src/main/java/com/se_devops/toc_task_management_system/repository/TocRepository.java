package com.se_devops.toc_task_management_system.repository;

import com.se_devops.toc_task_management_system.model.Toc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TocRepository extends JpaRepository<Toc, Integer> {
    
    Optional<Toc> findByBusinessCode(String businessCode);
    
    List<Toc> findByActiveTrue();
    
    List<Toc> findByActiveFalse();
    
    boolean existsByBusinessCode(String businessCode);
    
    List<Toc> findByNameContainingIgnoreCase(String name);
}