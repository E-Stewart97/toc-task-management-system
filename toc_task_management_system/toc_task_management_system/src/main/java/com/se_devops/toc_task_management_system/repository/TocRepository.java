package com.se_devops.toc_task_management_system.repository;


import com.se_devops.toc_task_management_system.model.Toc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TocRepository extends JpaRepository<Toc, Integer> {

    Optional<Toc> findByBusinessCode(String businessCode);

    List<Toc> findByActive(Boolean active);

    boolean existsByBusinessCode(String businessCode);

    @Query("SELECT t FROM Toc t WHERE t.active = true ORDER BY t.name")
    List<Toc> findAllActiveTocs();

    @Query("SELECT COUNT(t.tasks) FROM Toc t WHERE t.id = :tocId")
    long countTasksByTocId(Integer tocId);
}