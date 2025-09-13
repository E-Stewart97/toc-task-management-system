package com.se_devops.tocsystem.model;

import com.se_devops.tocsystem.model.enums.TaskPriority;
import com.se_devops.tocsystem.model.enums.TaskStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        Task task = new Task();
        task.setId(1);
        task.setTitle("New Important Task");
        task.setStatus(TaskStatus.TODO);
        task.setPriority(TaskPriority.HIGH);
        task.setDueDate(LocalDate.now().plusDays(7));

        assertEquals(1, task.getId());
        assertEquals("New Important Task", task.getTitle());
        assertEquals(TaskStatus.TODO, task.getStatus());
        assertEquals(TaskPriority.HIGH, task.getPriority());
        assertNotNull(task.getDueDate());
    }
}