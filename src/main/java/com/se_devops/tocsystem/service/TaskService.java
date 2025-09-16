package com.se_devops.tocsystem.service;

import com.se_devops.tocsystem.model.TOC;
import com.se_devops.tocsystem.model.Task;
import com.se_devops.tocsystem.model.User;
import com.se_devops.tocsystem.repository.TaskRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implements full CRUD (Create, Read, Update, Delete) for Task entities.
 */
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TOCService tocService;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, TOCService tocService, UserService userService) {
        this.taskRepository = taskRepository;
        this.tocService = tocService;
        this.userService = userService;
    }

    /**
     * C[R]UD - Reads a single task by its ID.
     */
    public Task findById(Integer taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    /**
     * C[R]UD - Reads a single task by ID, ensuring the user has permission via the parent TOC.
     */
    public Task findByIdAndUsername(Integer taskId, String username) {
        Task task = findById(taskId);
        // Security check: Verifies ownership of the parent TOC
        tocService.findByIdAndUsername(task.getToc().getId(), username);
        return task;
    }

    /**
     * [C]R[U]D - Creates a new Task or Updates an existing one.
     */
    @Transactional
    public void save(Task task, Integer tocId, String username) {
        TOC toc = tocService.findByIdAndUsername(tocId, username); // Security check
        task.setToc(toc);

        // Handle assignment of user
        if (task.getAssignedUser() != null && task.getAssignedUser().getId() != null) {
            User assignedUser = userService.findById(task.getAssignedUser().getId())
                    .orElseThrow(() -> new RuntimeException("Assigned user not found"));
            task.setAssignedUser(assignedUser);
        } else {
            task.setAssignedUser(null);
        }

        taskRepository.save(task);
    }

    /**
     * CRU[D] - Deletes a task, ensuring the user has permission.
     */
    @Transactional
    public void deleteTask(Integer taskId, String username) {
        // Security check is implicit via findByIdAndUsername
        Task task = findByIdAndUsername(taskId, username);
        taskRepository.delete(task);
    }

    /**
     * CRU[D] - Admin-only delete operation without ownership checks.
     */
    @Transactional
    public void deleteTaskAsAdmin(Integer taskId) throws RuntimeException {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }
}