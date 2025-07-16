package com.se_devops.toc_task_management_system.service;


import com.se_devops.toc_task_management_system.model.Task;
import com.se_devops.toc_task_management_system.model.User;
import com.se_devops.toc_task_management_system.model.enums.TaskPriority;
import com.se_devops.toc_task_management_system.model.enums.TaskStatus;
import com.se_devops.toc_task_management_system.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Integer id) {
        return taskRepository.findById(id);
    }

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setCreatedDate(LocalDateTime.now());
        }
        return taskRepository.save(task);
    }

    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> findByUser(User user) {
        return taskRepository.findByUser(user);
    }

    public List<Task> findUnassignedTasks() {
        return taskRepository.findByUserIsNull();
    }

    public List<Task> findOverdueTasks() {
        return taskRepository.findOverdueTasks(LocalDateTime.now());
    }

    public List<Task> findByUserOrderByPriorityAndDueDate(User user) {
        return taskRepository.findByUserOrderByPriorityAndDueDate(user);
    }

    public List<Task> searchTasks(String keyword) {
        return taskRepository.findByKeyword(keyword);
    }

    public long countByStatus(TaskStatus status) {
        return taskRepository.countByStatus(status);
    }

    public Task assignTask(Integer taskId, User user) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task updateTaskStatus(Integer taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public Task updateTaskPriority(Integer taskId, TaskPriority priority) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setPriority(priority);
        return taskRepository.save(task);
    }
}