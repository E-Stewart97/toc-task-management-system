package com.se_devops.tocsystem.controller;

import com.se_devops.tocsystem.model.TOC;
import com.se_devops.tocsystem.model.Task;
import com.se_devops.tocsystem.service.TOCService;
import com.se_devops.tocsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * C[R]UD Operation: Reads all data required for the main user dashboard.
 */
@Controller
@RequestMapping({"/", "/dashboard"})
public class DashboardController {

    private final TOCService tocService;
    private final UserService userService;

    public DashboardController(TOCService tocService, UserService userService) {
        this.tocService = tocService;
        this.userService = userService;
    }

    @GetMapping
    public String dashboard(Model model, Principal principal) {
        // Add empty objects to back the forms on the dashboard
        if (!model.containsAttribute("newToc")) {
            model.addAttribute("newToc", new TOC());
        }
        if (!model.containsAttribute("newTask")) {
            model.addAttribute("newTask", new Task());
        }

        model.addAttribute("tocs", tocService.findTocsForUser(principal.getName()));
        model.addAttribute("users", userService.findAll());
        return "dashboard";
    }
}