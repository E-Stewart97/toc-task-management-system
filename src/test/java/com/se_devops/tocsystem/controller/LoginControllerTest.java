package com.se_devops.tocsystem.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        loginController = new LoginController();
    }

    @Test
    public void testLoginEndpoint() {
        String result = loginController.login();

        assertEquals("login", result);
    }

    @Test
    public void testAccessDeniedEndpoint() {
        String result = loginController.accessDenied();

        assertEquals("access-denied", result);
    }

    @Test
    public void testControllerInstantiation() {
        LoginController controller = new LoginController();

        assertNotNull(controller);
        assertEquals("login", controller.login());
        assertEquals("access-denied", controller.accessDenied());
    }
}

