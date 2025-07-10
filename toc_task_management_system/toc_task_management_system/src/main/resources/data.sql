-- =====================================================
-- Task Management System - Clean Fresh Data Insert
-- Database will be rebuilt from scratch
-- Date Range: May 1, 2025 - June 25, 2025
-- =====================================================

-- =====================================================
-- 1. USERS Table Data (6 records)
-- =====================================================

INSERT OR IGNORE INTO users (id, username, password_hash, role) VALUES
       (1, 'admin', '$2a$10$N.zmdr9k7uOCQb3VGdMJOOxF8ZoQs0PSfVmgpPRSDKmN7t4J9cPU6', 'Admin'),
       (2, 'john_doe', '$2a$10$8K7fE9xF2DGKj3vRm8zXYeHcFd0Z2oqW1pVkGt5BmEr9sNhJuCzLm', 'Regular'),
       (3, 'alice_smith', '$2a$10$7M2nG8hJ3EgTp9wSl6yZDefKc1A3rqzV2oVjHu6CnGt8rOkLvDwN', 'Regular'),
       (4, 'bob_wilson', '$2a$10$9N3oH9iK4FhUp0xTm7zAGfgLd2B4sqzW3pWkGt5BmEr9sNhJuCzLm', 'Regular'),
       (5, 'sarah_jones', '$2a$10$5K1mF7gH2ChRo8vQk5xYHeJiE3C5tuzX4qXlJw8EpJv0tMnNyFzP', 'Regular'),
       (6, 'mike_brown', '$2a$10$6L2nG8hI3DhSp9wRl6yZCfgKd1A3rqvW2oUjHu6CmFt8sOkLvCxM', 'Regular');

-- =====================================================
-- 2. TOCS Table Data (10 records - 2 inactive)
-- =====================================================

INSERT OR IGNORE INTO tocs (id, name, business_code, active) VALUES
       (1, 'Great Western Railway', 'GWR', 1),
       (2, 'Virgin Trains West Coast', 'VT', 0),
       (3, 'Southern Railway', 'SN', 1),
       (4, 'South Western Railway', 'SWR', 1),
       (5, 'CrossCountry', 'XC', 1),
       (6, 'Chiltern Railways', 'CH', 1),
       (7, 'East Midlands Railway', 'EMR', 1),
       (8, 'Northern Rail', 'NR', 0),
       (9, 'ScotRail', 'SR', 1),
       (10, 'Transport for Wales', 'TFW', 1);

-- =====================================================
-- 3. TASKS Table Data (10 records) - CORRECTED ENUM VALUES
-- =====================================================

INSERT OR IGNORE INTO tasks (id, title, status, priority, user_id, toc_id, created_date, due_date) VALUES
        (1, 'Platform Safety Inspection - Reading Station', 'DONE', 'HIGH', 2, 1, '2025-05-01 09:00:00', '2025-05-15'),
        (2, 'Signal System Maintenance - Cardiff', 'DONE', 'HIGH', 3, 10, '2025-05-02 10:30:00', '2025-05-20'),
        (3, 'Staff Training - Customer Service Module', 'IN_PROGRESS', 'MEDIUM', 4, 3, '2025-05-05 14:00:00', '2025-06-01'),
        (4, 'Rolling Stock Inspection - Class 800', 'TODO', 'HIGH', NULL, 1, '2025-05-08 08:15:00', '2025-05-25'),
        (5, 'Ticketing System Upgrade - Phase 1', 'IN_PROGRESS', 'MEDIUM', 5, 4, '2025-05-10 11:00:00', '2025-06-15'),
        (6, 'Bridge Engineering Assessment', 'DONE', 'HIGH', 6, 5, '2025-05-12 13:45:00', '2025-05-30'),
        (7, 'Emergency Response Drill Coordination', 'IN_PROGRESS', 'HIGH', 3, 7, '2025-05-18 09:30:00', '2025-05-28'),
        (8, 'Station Accessibility Audit', 'TODO', 'MEDIUM', 2, 6, '2025-06-01 10:00:00', '2025-06-25'),
        (9, 'Cybersecurity Infrastructure Review', 'TODO', 'HIGH', NULL, 9, '2025-06-03 12:00:00', '2025-06-30'),
        (10, 'Fleet Performance Analysis Report', 'DONE', 'LOW', 4, 10, '2025-06-05 14:30:00', '2025-06-18');

-- =====================================================
-- 4. TIME_ENTRIES Table Data (10 records)
-- =====================================================

INSERT OR IGNORE INTO time_entries (id, task_id, user_id, hours_worked, entry_date) VALUES
       (1, 1, 2, 4.50, '2025-05-01'),
       (2, 1, 2, 3.25, '2025-05-02'),
       (3, 2, 3, 6.00, '2025-05-02'),
       (4, 2, 3, 5.50, '2025-05-03'),
       (5, 3, 4, 3.50, '2025-05-06'),
       (6, 5, 5, 7.25, '2025-05-11'),
       (7, 6, 6, 8.00, '2025-05-12'),
       (8, 7, 3, 4.75, '2025-05-19'),
       (9, 8, 2, 2.50, '2025-06-02'),
       (10, 10, 4, 3.00, '2025-06-06');

-- =====================================================
-- Data Summary
-- =====================================================
-- Users: 6 (1 Admin, 5 Regular)
-- TOCs: 10 (8 Active, 2 Inactive)
-- Tasks: 10 (3 TODO, 3 IN_PROGRESS, 4 DONE)
-- Time Entries: 10 (spanning May-June 2025)
-- Total Hours Logged: 47.25 hours
-- Fresh database rebuild with correct enum values
-- =====================================================