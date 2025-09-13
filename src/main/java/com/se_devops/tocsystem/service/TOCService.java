package com.se_devops.tocsystem.service;

import com.se_devops.tocsystem.model.TOC;
import com.se_devops.tocsystem.model.User;
import com.se_devops.tocsystem.repository.TOCRepository;
import com.se_devops.tocsystem.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implements full CRUD (Create, Read, Update, Delete) for TOC entities.
 */
@Service
public class TOCService {

    private final TOCRepository tocRepository;
    private final UserRepository userRepository;

    public TOCService(TOCRepository tocRepository, UserRepository userRepository) {
        this.tocRepository = tocRepository;
        this.userRepository = userRepository;
    }

    /**
     * C[R]UD - Reads all TOCs for a specific user.
     */
    public List<TOC> findTocsForUser(String username) {
        return tocRepository.findByUserUsername(username);
    }

    /**
     * C[R]UD - Reads a single TOC by ID, ensuring the user has permission.
     */
    public TOC findByIdAndUsername(Integer id, String username) {
        TOC toc = tocRepository.findById(id).orElseThrow(() -> new RuntimeException("TOC not found"));
        if (!toc.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("You do not have permission to view this TOC");
        }
        return toc;
    }

    /**
     * [C]R[U]D - Creates a new TOC or Updates an existing one.
     */
    @Transactional
    public void save(TOC toc, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        toc.setUser(user);
        tocRepository.save(toc);
    }

    /**
     * CRU[D] - Deletes a TOC, ensuring the user has permission.
     */
    @Transactional
    public void deleteById(Integer id, String username) {
        TOC toc = findByIdAndUsername(id, username); // Security check is implicit
        tocRepository.deleteById(toc.getId());
    }

    /**
     * CRU[D] - Admin-only delete operation without ownership checks.
     */
    @Transactional
    public void deleteByIdAsAdmin(Integer id) {
        if (!tocRepository.existsById(id)) {
            throw new RuntimeException("TOC not found with id: " + id);
        }
        tocRepository.deleteById(id);
    }
}