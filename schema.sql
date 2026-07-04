CREATE DATABASE IF NOT EXISTS ursa_db;
USE ursa_db;

-- ==========================================
-- 1. COURSES TABLE
-- ==========================================
CREATE TABLE courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_code VARCHAR(50) NOT NULL UNIQUE,
    course_title VARCHAR(150) NOT NULL
);

-- ==========================================
-- 2. STUDENTS TABLE
-- ==========================================
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    gender VARCHAR(20),
    account_status VARCHAR(50) DEFAULT 'Active',
    year_level INT,
    date_of_birth DATE
);

-- ==========================================
-- 3. SCHEDULES TABLE
-- ==========================================
CREATE TABLE schedules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    courses_id INT NOT NULL,
    instructor_name VARCHAR(150) NOT NULL,
    date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    FOREIGN KEY (courses_id) REFERENCES courses(id) ON DELETE RESTRICT
);

-- ==========================================
-- 4. GUARDIANS TABLE
-- ==========================================
CREATE TABLE guardians (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    relationship VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE RESTRICT
);

-- ==========================================
-- 5. STUDENTS_SCHEDULES (Junction Table)
-- ==========================================
CREATE TABLE students_schedules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    schedules_id INT NOT NULL,
    academic_year VARCHAR(20) NOT NULL,
    semester VARCHAR(50) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE RESTRICT,
    FOREIGN KEY (schedules_id) REFERENCES schedules(id) ON DELETE RESTRICT
);

-- ==========================================
-- 6. ATTENDANCE_LOG TABLE
-- ==========================================
CREATE TABLE attendance_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    schedule_id INT NOT NULL,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    action VARCHAR(50) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE RESTRICT,
    FOREIGN KEY (schedule_id) REFERENCES schedules(id) ON DELETE RESTRICT
);