package com.se_devops.tocsystem.repository;

import com.se_devops.tocsystem.model.TOC;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository for TOC entities.
 * JpaRepository provides the full implementation for:
 * [C]reate: save(toc)
 * [R]ead: findById(id), findAll()
 * [U]pdate: save(toc)
 * [D]elete: deleteById(id)
 */
public interface TOCRepository extends JpaRepository<TOC, Integer> {
    /**
     * Custom [R]ead operation to find all TOCs owned by a specific user.
     *
     * @param username The username of the user who owns the TOCs.
     * @return A list of TOCs.
     */
    List<TOC> findByUserUsername(String username);
}