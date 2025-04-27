-- Class Name: ISTE 330
-- Project Title: Faculty Research Database
-- Group Number: 2
-- Authors: Charles Coleman, Flavio Medina, Will Jacobs, Sean Guyon, David Kalinowski
-- Date: 04/19/25
-- Version: 1.0

DROP DATABASE IF EXISTS 330_project_research;

CREATE DATABASE 330_project_research;

USE 330_project_research;

-- Users
CREATE TABLE users (
    userID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NULL DEFAULT NULL,
    userType ENUM("Faculty", "Student", "Community"),
    PRIMARY KEY (userID)
);

ALTER TABLE users AUTO_INCREMENT = 100;

-- Interests
CREATE TABLE interests (
    interestID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    PRIMARY KEY (interestID)
);

-- User Interests
CREATE TABLE user_interests (
    userID INT UNSIGNED NOT NULL,
    interestID INT UNSIGNED NOT NULL,
    PRIMARY KEY (userID, interestID),
    CONSTRAINT user_interests_user_FK FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT user_interests_FK FOREIGN KEY (interestID) REFERENCES interests(interestID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Department
CREATE TABLE departments (
    departmentID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    abbreviation CHAR(10) DEFAULT NULL,
    PRIMARY KEY (departmentID)
);

-- Faculty
CREATE TABLE faculty (
    userID INT UNSIGNED NOT NULL,
    departmentID INT UNSIGNED NOT NULL,
    firstName VARCHAR(50) DEFAULT NULL,
    lastName VARCHAR(50) DEFAULT NULL,
    abstract VARCHAR(200) DEFAULT NULL,
    buildingNum VARCHAR(50) DEFAULT NULL,
    officeNum VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (userID, departmentID),
    CONSTRAINT faculty_userID_FK FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT faculty_departmentID_FK FOREIGN KEY (departmentID) REFERENCES departments(departmentID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Students
CREATE TABLE students (
    userID INT UNSIGNED NOT NULL,
    categoryYear ENUM('freshman', 'sophomore', 'junior', 'senior') NOT NULL,
    departmentID INT UNSIGNED NOT NULL,
    firstName VARCHAR(50) DEFAULT NULL,
    lastName VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (userID),
    CONSTRAINT student_userID_FK FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT student_departmentID_FK FOREIGN KEY (departmentID) REFERENCES departments(departmentID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT chk_categoryYear CHECK (categoryYear IN ('freshman', 'sophomore', 'junior', 'senior'))
);

-- Community Users
CREATE TABLE community_users (
    userID INT UNSIGNED NOT NULL,
    name VARCHAR(50) NOT NULL DEFAULT '',
    PRIMARY KEY (userID),
    CONSTRAINT community_user_FK FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ====================
-- Insert:
-- ====================

-- Users
INSERT INTO 
    users (username, password, email, userType) 
VALUES 
    ('faculty', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'faculty@rit.edu', "Faculty"),  
    ('student', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'student@rit.edu', "Student"),
    ('community', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'community@roc.gov', "Community");


-- Interests
INSERT INTO 
    interests (description) 
VALUES 
    ('Software Engineering'),
    ('Data Science'),
    ('JDBC'),
    ('MySQL'),
    ('Database'),
    ('Data Security'),
    ('SwingUI');


-- User Interests
INSERT INTO 
    user_interests (userID, interestID) 
VALUES 
    (100, 1),
    (100, 2),
    (100, 3),
    (100, 4),
    (100, 5),
    (100, 6),
    (100, 7),
    (101, 3),
    (101, 5),
    (101, 7);


-- Department
INSERT INTO 
    departments (name, abbreviation) 
VALUES 
    ('Golisano College of Computing and Information Sciences','GCCIS');

-- Faculty
INSERT INTO 
    faculty(userID, departmentID, firstName, lastName, abstract, buildingNum, officeNum) 
VALUES
    (100, 1, 'Jim', 'Habermas', 'Database and Data Security Expert, with expertise in JDBC MySQL applications with SwingUI', 'Building 70', "Room 270");
    


-- Students
INSERT INTO 
    students (userID, categoryYear, departmentID, firstName, lastName) 
VALUES 
    (101, 'sophomore', 1, 'Will', 'Jacobs');

-- Community Users
INSERT INTO    
    community_users (userID, name) 
VALUES 
    (102, 'Rochester Public Library');
