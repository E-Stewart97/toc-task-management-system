package com.se_devops.toc_task_management_system.service;

import com.se_devops.toc_task_management_system.model.Task;
import com.se_devops.toc_task_management_system.model.TimeEntry;
import com.se_devops.toc_task_management_system.model.User;
import com.se_devops.toc_task_management_system.repository.TimeEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TimeEntryService {

    private final TimeEntryRepository timeEntryRepository;

    public List<TimeEntry> findAll() {
        return timeEntryRepository.findAll();
    }

    public Optional<TimeEntry> findById(Integer id) {
        return timeEntryRepository.findById(id);
    }

    public TimeEntry save(TimeEntry timeEntry) {
        return timeEntryRepository.save(timeEntry);
    }

    public void deleteById(Integer id) {
        timeEntryRepository.deleteById(id);
    }

    public List<TimeEntry> findByTask(Task task) {
        return timeEntryRepository.findByTask(task);
    }

    public List<TimeEntry> findByUser(User user) {
        return timeEntryRepository.findByUserOrderByEntryDateDesc(user);
    }

    public List<TimeEntry> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return timeEntryRepository.findByEntryDateBetween(startDate, endDate);
    }

    public List<TimeEntry> findByUserAndDateRange(User user, LocalDate startDate, LocalDate endDate) {
        return timeEntryRepository.findByUserAndEntryDateBetween(user, startDate, endDate);
    }

    public BigDecimal getTotalHoursByTask(Task task) {
        BigDecimal total = timeEntryRepository.getTotalHoursByTask(task);
        return total != null ? total : BigDecimal.ZERO;
    }

    public BigDecimal getTotalHoursByUserAndDateRange(User user, LocalDate startDate, LocalDate endDate) {
        BigDecimal total = timeEntryRepository.getTotalHoursByUserAndDateRange(user, startDate, endDate);
        return total != null ? total : BigDecimal.ZERO;
    }
}