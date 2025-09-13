package com.se_devops.tocsystem.controller;

//import com.example.tocsystem.service.TOCService;
//import com.example.tocsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
class AdminControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private TOCService tocService;
//
//    @Test
//    @WithMockUser(roles = "ADMIN") // Simulate a logged-in admin user
//    void adminDashboard_ReturnsOk_ForAdminUser() throws Exception {
//        mockMvc.perform(get("/admin/dashboard"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("admin-dashboard"))
//                .andExpect(model().attributeExists("users"));
//    }
//
//    @Test
//    @WithMockUser(roles = "REGULAR") // Simulate a logged-in regular user
//    void adminDashboard_ReturnsForbidden_ForRegularUser() throws Exception {
//        mockMvc.perform(get("/admin/dashboard"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void deleteTocAsAdmin_RedirectsToDashboard() throws Exception {
//        int tocId = 1;
//
//        mockMvc.perform(post("/admin/tocs/delete/" + tocId).with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/admin/dashboard"));
//
//        // Verify that the service method was called
//        verify(tocService).deleteByIdAsAdmin(tocId);
//    }
}