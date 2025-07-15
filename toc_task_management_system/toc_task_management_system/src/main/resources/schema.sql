-- =====================================================
-- Task Management System - Database Schema
-- Tables: Users, TOCs, Tasks, Time_Entries
-- =====================================================

-- 1. USERS Table
-- Purpose: Stores user account information and access roles
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('Admin', 'Regular'))
    );

-- 2. TOCS Table (Train Operating Companies)
-- Purpose: Stores information about railway operating companies
CREATE TABLE IF NOT EXISTS tocs (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(100) NOT NULL,
    business_code VARCHAR(10) UNIQUE NOT NULL,
    active BOOLEAN DEFAULT TRUE
    );

-- 3. TASKS Table
-- Purpose: Stores task information and assignments
CREATE TABLE IF NOT EXISTS tasks (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title VARCHAR(200) NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('BACKLOG', 'TODO', 'IN_PROGRESS', 'IN_REVIEW', 'DONE', 'BLOCKED')),
    priority VARCHAR(10) NOT NULL CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH', 'CRITICAL')),
    user_id INTEGER,
    toc_id INTEGER NOT NULL,
    created_date DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    due_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (toc_id) REFERENCES tocs(id)
    );

-- 4. TIME_ENTRIES Table
-- Purpose: Tracks time spent on tasks by users
CREATE TABLE IF NOT EXISTS time_entries (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    task_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    hours_worked DECIMAL(4,2) NOT NULL CHECK (hours_worked > 0),
    entry_date DATE NOT NULL,
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

-- =====================================================
-- Indexes for Performance Optimization
-- =====================================================

-- Users table indexes
CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);
CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);

-- TOCs table indexes
CREATE INDEX IF NOT EXISTS idx_tocs_business_code ON tocs(business_code);
CREATE INDEX IF NOT EXISTS idx_tocs_active ON tocs(active);

-- Tasks table indexes
CREATE INDEX IF NOT EXISTS idx_tasks_status ON tasks(status);
CREATE INDEX IF NOT EXISTS idx_tasks_priority ON tasks(priority);
CREATE INDEX IF NOT EXISTS idx_tasks_user ON tasks(user_id);
CREATE INDEX IF NOT EXISTS idx_tasks_toc ON tasks(toc_id);
CREATE INDEX IF NOT EXISTS idx_tasks_created_date ON tasks(created_date);
CREATE INDEX IF NOT EXISTS idx_tasks_due_date ON tasks(due_date);

-- Time_Entries table indexes
CREATE INDEX IF NOT EXISTS idx_time_entries_task ON time_entries(task_id);
CREATE INDEX IF NOT EXISTS idx_time_entries_user ON time_entries(user_id);
CREATE INDEX IF NOT EXISTS idx_time_entries_date ON time_entries(entry_date);

-- =====================================================
-- Relationships Summary (for reference)
-- =====================================================
-- Users (1) ←→ (0..n) Tasks [user_id]
-- TOCs (1) ←→ (1..n) Tasks [toc_id]
-- Tasks (1) ←→ (0..n) Time_Entries [task_id] - CASCADE DELETE
-- Users (1) ←→ (0..n) Time_Entries [user_id]