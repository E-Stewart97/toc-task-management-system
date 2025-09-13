package com.se_devops.tocsystem.model;

import com.se_devops.tocsystem.model.enums.Role;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TOCTest {

    @Test
    public void testTOCCreation() {
        TOC toc = new TOC();
        toc.setId(1);
        toc.setTitle("Test TOC");
        toc.setBusinessCode("TST");
        toc.setActive(true);

        assertEquals(1, toc.getId());
        assertEquals("Test TOC", toc.getTitle());
        assertEquals("TST", toc.getBusinessCode());
        assertTrue(toc.isActive());
    }

    @Test
    public void testTOCDefaultActiveStatus() {
        TOC toc = new TOC();
        toc.setTitle("New TOC");
        toc.setBusinessCode("NEW");

        assertTrue(toc.isActive()); // Default value should be true
    }

    @Test
    public void testTOCSettersAndGetters() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setRole(Role.REGULAR);

        TOC toc = new TOC();
        toc.setTitle("Project Alpha");
        toc.setBusinessCode("PA01");
        toc.setActive(false);
        toc.setUser(user);

        assertEquals("Project Alpha", toc.getTitle());
        assertEquals("PA01", toc.getBusinessCode());
        assertFalse(toc.isActive());
        assertNotNull(toc.getUser());
        assertEquals("testuser", toc.getUser().getUsername());
        assertNull(toc.getTasks()); // Tasks list should be null initially
    }
}
