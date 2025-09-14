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
-- admin password: 'adminpass'
-- user password: 'userpass'
-- Other users password: 'password'
INSERT INTO users (username, password, role) VALUES
                                                 ('admin', '$2a$12$U/J36Zj3LlfnHigM90ea8.R.s.2Y5fEDO2SWH0BLwdl1xXLnJNbUK', 'ADMIN'),
                                                 ('user', '$2a$12$hpGXBoshFVqPDqh/0DVpqe2pZGf5j1KVftJraz6Jpy1DOYdAVwB0m', 'REGULAR'),
                                                 ('j.doe', '$2a$12$GcLtmFEMLtz.TwxypCd36uR.pbGcP5ceErgmL4HHP5vuolI5jv9SG', 'REGULAR'),
                                                 ('s.smith', '$2a$12$GcLtmFEMLtz.TwxypCd36uR.pbGcP5ceErgmL4HHP5vuolI5jv9SG', 'REGULAR'),
                                                 ('m.jones', '$2a$12$GcLtmFEMLtz.TwxypCd36uR.pbGcP5ceErgmL4HHP5vuolI5jv9SG', 'REGULAR');

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
