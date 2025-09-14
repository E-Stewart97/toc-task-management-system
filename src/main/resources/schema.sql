DROP TABLE IF EXISTS time_entries;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS tocs;
DROP TABLE IF EXISTS users;

-- Create the users table
CREATE TABLE users (
                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) NOT NULL CHECK (role IN ('ADMIN', 'REGULAR'))
);

-- Create the tocs table
CREATE TABLE tocs (
                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                      title VARCHAR(100) NOT NULL,
                      business_code VARCHAR(4) NOT NULL,
                      user_id INTEGER NOT NULL,
                      active BOOLEAN NOT NULL DEFAULT TRUE,
                      FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- Create the tasks table
CREATE TABLE tasks (
                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                       title VARCHAR(100) NOT NULL,
                       status VARCHAR(20) NOT NULL CHECK (status IN ('BACKLOG', 'TODO', 'IN_PROGRESS', 'IN_REVIEW', 'DONE', 'BLOCKED')),
                       priority VARCHAR(10) NOT NULL CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH', 'CRITICAL')),
                       due_date DATETIME,
                       toc_id INTEGER NOT NULL,
                       user_id INTEGER, -- The user this task is assigned to
                       FOREIGN KEY (toc_id) REFERENCES tocs (id) ON DELETE CASCADE,
                       FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL
);

-- Create the time_entries table
CREATE TABLE time_entries (
                              id INTEGER PRIMARY KEY AUTOINCREMENT,
                              start_time DATETIME NOT NULL,
                              end_time DATETIME,
                              task_id INTEGER NOT NULL,
                              FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE
);