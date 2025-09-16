package com.se_devops.tocsystem.service;

import com.se_devops.tocsystem.model.Task;
import com.se_devops.tocsystem.model.TimeEntry;
import com.se_devops.tocsystem.repository.TimeEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
public class TimeEntryService {
    private final TimeEntryRepository timeEntryRepository;
    private final TaskService taskService;

    public TimeEntryService(TimeEntryRepository timeEntryRepository, TaskService taskService) {
        this.timeEntryRepository = timeEntryRepository;
        this.taskService = taskService;
    }

    @Transactional
    public void saveTimeEntry(TimeEntry timeEntry, Integer taskId, Principal principal) {
        // Security Check: Ensure the user owns the parent task before adding a time entry
        Task task = taskService.findByIdAndUsername(taskId, principal.getName());
        timeEntry.setTask(task);
        timeEntryRepository.save(timeEntry);
    }

    @Transactional
    public void deleteTimeEntry(Integer timeEntryId, Principal principal) {
        TimeEntry timeEntry = timeEntryRepository.findById(timeEntryId)
                .orElseThrow(() -> new RuntimeException("Time Entry not found"));
        // Security Check: Ensure the user owns the parent task before deleting
        taskService.findByIdAndUsername(timeEntry.getTask().getId(), principal.getName());
        timeEntryRepository.delete(timeEntry);
    }

}
