package com.se_devops.tocsystem.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TimeEntryTest {

    @Test
    public void testTimeEntryCreation() {
        // Arrange
        TimeEntry entry = new TimeEntry();
        entry.setId(1);
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(2);

        Task task = new Task();
        task.setId(99);

        entry.setTask(task);
        entry.setStartTime(startTime);
        entry.setEndTime(endTime);

        assertEquals(1, entry.getId());
        assertEquals(99, entry.getTask().getId());
        assertEquals(startTime, entry.getStartTime());
        assertEquals(endTime, entry.getEndTime());
    }
}