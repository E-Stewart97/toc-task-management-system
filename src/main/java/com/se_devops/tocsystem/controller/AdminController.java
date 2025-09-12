package com.se_devops.tocsystem.controller;

import com.se_devops.tocsystem.service.TOCService;
import com.se_devops.tocsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    public AdminController(UserService userService, TOCService tocService) {
        this.userService = userService;
        this.tocService = tocService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin-dashboard";
    }

    @PostMapping("/tocs/delete/{id}")
    public String deleteTocAsAdmin(@PathVariable Integer id) {
        tocService.deleteByIdAsAdmin(id);
        return "redirect:/admin/dashboard";
    }
}