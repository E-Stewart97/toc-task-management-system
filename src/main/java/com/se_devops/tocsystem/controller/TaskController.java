package com.se_devops.tocsystem.controller;

import com.se_devops.tocsystem.model.Task;
import com.se_devops.tocsystem.service.TaskService;
import com.se_devops.tocsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * Implements full CRUD (Create, Read, Update, Delete) for Tasks.
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    /**
     * C[R]UD - Reads a single task to show its details.
     */
    @GetMapping("/{taskId}")
    public String viewTask(@PathVariable Integer taskId, Model model, Principal principal) {
        Task task = taskService.findByIdAndUsername(taskId, principal.getName());
        model.addAttribute("task", task);
        return "task-details";
    }

    /**
     * [C]RUD - Creates a new task.
     */
    @PostMapping("/new")
    public String createTask(@Valid @ModelAttribute("newTask") Task task,
                             BindingResult bindingResult,
                             @RequestParam Integer tocId,
                             Principal principal,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            // ERROR message path for validation
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newTask", bindingResult);
            redirectAttributes.addFlashAttribute("newTask", task);
            redirectAttributes.addFlashAttribute("errorInTocId", tocId);
            return "redirect:/dashboard";
        }

        taskService.save(task, tocId, principal.getName());
        // SUCCESS message path
        redirectAttributes.addFlashAttribute("successMessage", "Task '" + task.getTitle() + "' created successfully!");
        return "redirect:/dashboard";
    }
    /**
     * C[R]UD - Reads a task's data to populate the edit form.
     */
    @GetMapping("/edit/{taskId}")
    public String showEditTaskForm(@PathVariable Integer taskId, Model model, Principal principal) {
        Task task = taskService.findByIdAndUsername(taskId, principal.getName());
        model.addAttribute("task", task);
        model.addAttribute("users", userService.findAll());
        return "edit-task";
    }

    /**
     * CR[U]D - Updates an existing task.
     */
    @PostMapping("/update/{taskId}")
    public String updateTask(@PathVariable Integer taskId,
                             @Valid @ModelAttribute("task") Task task,
                             BindingResult bindingResult,
                             Principal principal,
                             RedirectAttributes redirectAttributes,
                             Model model) {

        Task existingTask = taskService.findByIdAndUsername(taskId, principal.getName());

        if (bindingResult.hasErrors()) {
            // ERROR message path for validation
            model.addAttribute("users", userService.findAll());
            return "edit-task";
        }

        task.setId(taskId);
        task.setToc(existingTask.getToc());
        taskService.save(task, existingTask.getToc().getId(), principal.getName());

        // SUCCESS message path
        redirectAttributes.addFlashAttribute("successMessage", "Task updated successfully!");
        return "redirect:/dashboard";
    }

    /**
     * CRU[D] - Deletes a task.
     */
    @PostMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable Integer taskId, Principal principal, RedirectAttributes redirectAttributes) {
        taskService.deleteTask(taskId, principal.getName());
        // SUCCESS message path
        redirectAttributes.addFlashAttribute("successMessage", "Task deleted successfully.");
        return "redirect:/dashboard";
    }
}