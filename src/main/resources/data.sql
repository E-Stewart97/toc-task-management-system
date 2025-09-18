-- Clear existing data from tables in the correct order (child tables first)
DELETE FROM time_entries;
DELETE FROM tasks;
DELETE FROM tocs;
DELETE FROM users;

-- Reset the autoincrement counters for all tables in SQLite
DELETE FROM sqlite_sequence WHERE name IN ('users', 'tocs', 'tasks', 'time_entries');

-- ===================================================================
-- INSERT SAMPLE USERS WITH UPDATED BCRYPT PASSWORDS
-- ===================================================================
INSERT INTO users (username, password, role) VALUES
                                                 ('admin', '$2y$10$Hl8MW30cz9cIEUf2gaZwFOOmpin35s/9.Gd1dda1JaCZJJoh11DFO', 'ADMIN'),
                                                 ('user', '$2y$10$cy.bFpE5spy9nhkD0FwQOegmtsYz6gso.h.bDMhIK6WBPcFdlO1zG', 'REGULAR'),
                                                 ('j.doe', '$2y$10$hQlbGgNJd0UZt75iIW0MEeQJ1iJUupqLuN.3/p1W.cmlEcl8KNdHe', 'REGULAR'),
                                                 ('s.smith', '$2y$10$2BKXEVyYq1XboBK8EjSjZep7pEiex2TrvdWIzYONazHFYgC6P8gq6', 'REGULAR'),
                                                 ('m.jones', '$2y$10$.u0UG7bRUwXb6Nkl/0RN6exAugguvHBQoJ5k1vwnM42GpETwa0hYS', 'REGULAR');

-- ===================================================================
-- INSERT SAMPLE TOCs (TABLE OF CONTENTS)
-- ===================================================================
INSERT INTO tocs (title, business_code, user_id, active) VALUES
                                                             ('Heathrow Express', 'HX', 1, TRUE),
                                                             ('Grand Central', 'GC', 2, TRUE),
                                                             ('Hull Trains', 'HT', 1, TRUE),
                                                             ('Lumo', 'LM', 3, FALSE),
                                                             ('Eurostar', 'ES', 3, TRUE),
                                                             ('c2c', 'CC', 4, TRUE),
                                                             ('Chiltern Railways', 'CH', 5, TRUE);

-- ===================================================================
-- INSERT SAMPLE TASKS
-- ===================================================================
INSERT INTO tasks (title, status, priority, due_date, toc_id, user_id) VALUES
                                                                           ('Track Maintenance Schedule', 'IN_PROGRESS', 'HIGH', '2025-07-15 00:00:00', 1, 3),
                                                                           ('Driver Rota Planning', 'TODO', 'MEDIUM', '2025-07-10 00:00:00', 1, 4),
                                                                           ('On-board Catering Stocktake', 'DONE', 'LOW', '2025-06-20 00:00:00', 2, 2),
                                                                           ('Customer Feedback Analysis', 'IN_REVIEW', 'MEDIUM', '2025-07-01 00:00:00', 2, 2),
                                                                           ('Signal Failure Investigation', 'BLOCKED', 'CRITICAL', '2025-07-05 00:00:00', 3, 1),
                                                                           ('New Ticket Machine Rollout', 'IN_PROGRESS', 'HIGH', '2025-07-18 00:00:00', 4, 3),
                                                                           ('Passport Checkpoint Staffing', 'DONE', 'MEDIUM', '2025-06-30 00:00:00', 5, 3),
                                                                           ('Platform Screen Door Install', 'BACKLOG', 'HIGH', '2025-07-18 00:00:00', 6, 4),
                                                                           ('Update Timetable Displays', 'TODO', 'LOW', '2025-07-12 00:00:00', 6, 4),
                                                                           ('Station WiFi Upgrade', 'IN_PROGRESS', 'MEDIUM', '2025-07-16 00:00:00', 7, 5);

-- ===================================================================
-- INSERT SAMPLE TIME ENTRIES
-- ===================================================================
INSERT INTO time_entries (start_time, end_time, task_id) VALUES
                                                             ('2025-07-10 09:00:00', '2025-07-10 12:30:00', 1),
                                                             ('2025-07-11 14:00:00', NULL, 1),
                                                             ('2025-06-18 10:00:00', '2025-06-20 16:00:00', 3),
                                                             ('2025-06-28 09:00:00', '2025-07-01 11:00:00', 4),
                                                             ('2025-07-02 13:00:00', NULL, 4),
                                                             ('2025-07-15 10:00:00', '2025-07-15 17:00:00', 6),
                                                             ('2025-06-25 09:30:00', '2025-06-30 17:30:00', 7),
                                                             ('2025-07-01 10:00:00', '2025-07-01 12:00:00', 8),
                                                             ('2025-07-14 11:00:00', '2025-07-14 15:00:00', 10),
                                                             ('2025-07-15 09:00:00', NULL, 10);
