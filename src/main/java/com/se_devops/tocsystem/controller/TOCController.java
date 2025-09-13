package com.se_devops.tocsystem.controller;

import com.se_devops.tocsystem.model.TOC;
import com.se_devops.tocsystem.service.TOCService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/tocs")
public class TOCController {

    private final TOCService tocService;

    public TOCController(TOCService tocService) {
        this.tocService = tocService;
    }

    @PostMapping("/new")
    public String createToc(@Valid @ModelAttribute("newToc") TOC newToc,
                            BindingResult bindingResult,
                            Principal principal,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            // This is an ERROR message path
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newToc", bindingResult);
            redirectAttributes.addFlashAttribute("newToc", newToc);
            return "redirect:/dashboard";
        }

        tocService.save(newToc, principal.getName());
        // This is a SUCCESS message path
        redirectAttributes.addFlashAttribute("successMessage", "TOC '" + newToc.getTitle() + "' created successfully!");
        return "redirect:/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteToc(@PathVariable Integer id, Principal principal, RedirectAttributes redirectAttributes) {
        tocService.deleteById(id, principal.getName());
        // This is a SUCCESS message path
        redirectAttributes.addFlashAttribute("successMessage", "TOC deleted successfully.");
        return "redirect:/dashboard";
    }
}