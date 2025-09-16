package com.se_devops.tocsystem.controller;

import com.se_devops.tocsystem.service.TOCService;
import com.se_devops.tocsystem.service.TaskService;
import com.se_devops.tocsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Implements admin-only CRUD operations.
 * C[R]UD: Reads all user and TOC data for display.
 * CRU[D]: Deletes any TOC from the system.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final TOCService tocService;
    private final TaskService taskService;

    public AdminController(UserService userService, TOCService tocService, TaskService taskService) {
        this.userService = userService;
        this.tocService = tocService;
        this.taskService = taskService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin-dashboard";
    }

    @PostMapping("/tocs/delete/{id}")
    public String deleteTocAsAdmin(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        tocService.deleteByIdAsAdmin(id);
        redirectAttributes.addFlashAttribute("successMessage", "TOC deleted successfully."); // Optional: Add feedback
        return "redirect:/admin/dashboard";
    }

    // ADD THIS NEW METHOD
    @PostMapping("/tasks/delete/{taskId}")
    public String deleteTaskAsAdmin(@PathVariable Integer taskId, RedirectAttributes redirectAttributes) {
        taskService.deleteTaskAsAdmin(taskId);
        redirectAttributes.addFlashAttribute("successMessage", "Task deleted successfully."); // Optional: Add feedback
        return "redirect:/admin/dashboard";
    }
}