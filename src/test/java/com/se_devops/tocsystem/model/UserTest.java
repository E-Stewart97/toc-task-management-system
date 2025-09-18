package com.se_devops.tocsystem.model;

import com.se_devops.tocsystem.model.enums.Role;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserCreation() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("testpass");
        user.setRole(Role.REGULAR);

        assertEquals(1, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("testpass", user.getPassword());
        assertEquals(Role.REGULAR, user.getRole());
    }

    @Test
    public void testUserSettersAndGetters() {
        User user = new User();
        user.setUsername("admin");

        assertEquals("admin", user.getUsername());

        user.setRole(Role.ADMIN);
        assertEquals(Role.ADMIN, user.getRole());

        assertNull(user.getId());
        assertNull(user.getTocs());
        assertNull(user.getAssignedTasks());
    }

    @Test
    public void testUserRoleEnum() {
        User adminUser = new User();
        User regularUser = new User();

        adminUser.setRole(Role.ADMIN);
        regularUser.setRole(Role.REGULAR);

        assertEquals(Role.ADMIN, adminUser.getRole());
        assertEquals(Role.REGULAR, regularUser.getRole());
        assertNotEquals(adminUser.getRole(), regularUser.getRole());
    }
}

