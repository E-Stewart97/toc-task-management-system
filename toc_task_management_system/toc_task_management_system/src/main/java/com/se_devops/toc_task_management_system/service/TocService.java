package com.se_devops.toc_task_management_system.service;

import com.se_devops.toc_task_management_system.model.Toc;
import com.se_devops.toc_task_management_system.repository.TocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TocService {

    private final TocRepository tocRepository;

    public List<Toc> findAll() {
        return tocRepository.findAll();
    }

    public List<Toc> findAllActive() {
        return tocRepository.findAllActiveTocs();
    }

    public Optional<Toc> findById(Integer id) {
        return tocRepository.findById(id);
    }

    public Optional<Toc> findByBusinessCode(String businessCode) {
        return tocRepository.findByBusinessCode(businessCode);
    }

    public Toc save(Toc toc) {
        return tocRepository.save(toc);
    }

    public void deleteById(Integer id) {
        tocRepository.deleteById(id);
    }

    public boolean existsByBusinessCode(String businessCode) {
        return tocRepository.existsByBusinessCode(businessCode);
    }

    public long countTasksByToc(Integer tocId) {
        return tocRepository.countTasksByTocId(tocId);
    }

    public Toc toggleActive(Integer id) {
        Toc toc = tocRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TOC not found"));
        toc.setActive(!toc.getActive());
        return tocRepository.save(toc);
    }
}