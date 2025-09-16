package com.se_devops.tocsystem.controller;

import com.se_devops.tocsystem.model.TimeEntry;
import com.se_devops.tocsystem.service.TimeEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/time-entries")
public class TimeEntryController {
    private final TimeEntryService timeEntryService;

    public TimeEntryController(TimeEntryService timeEntryService) {
        this.timeEntryService = timeEntryService;
    }

    @PostMapping("/new")
    public String addTimeEntry(@ModelAttribute("newTimeEntry") TimeEntry newTimeEntry,
                               @RequestParam("taskId") Integer taskId,
                               Principal principal,
                               RedirectAttributes redirectAttributes) {

        timeEntryService.saveTimeEntry(newTimeEntry, taskId, principal);
        redirectAttributes.addFlashAttribute("successMessage", "Time entry added successfully!");
        return "redirect:/tasks/" + taskId;
    }

    @PostMapping("/delete/{timeEntryId}")
    public String deleteTimeEntry(@PathVariable("timeEntryId") Integer timeEntryId,
                                  @RequestParam("taskId") Integer taskId,
                                  Principal principal,
                                  RedirectAttributes redirectAttributes) {

        timeEntryService.deleteTimeEntry(timeEntryId, principal);
        redirectAttributes.addFlashAttribute("successMessage", "Time entry deleted successfully.");
        return "redirect:/tasks/" + taskId;
    }

}
