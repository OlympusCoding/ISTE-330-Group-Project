-- Class Name: ISTE 330
-- Project Title: Faculty Research Database
-- Group Number: 2
-- Authors: Charles Coleman, Flavio Medina, Will Jacobs, Sean Guyon, David Kalinowski
-- Date: 04/19/25
-- Version: 1.0

DROP DATABASE IF EXISTS 330_project_research;

CREATE DATABASE 330_project_research;

USE 330_project_research;

CREATE TABLE users (
    userID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(50) NULL DEFAULT NULL,
    PRIMARY KEY (userID)
);

ALTER TABLE users AUTO_INCREMENT = 100;

CREATE TABLE interests (
    interestID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL DEFAULT '',
    PRIMARY KEY (interestID)
);

CREATE TABLE user_interests (
    userID INT UNSIGNED NOT NULL,
    interestID INT UNSIGNED NOT NULL,
    PRIMARY KEY (userID, interestID),
    CONSTRAINT user_interests_user_FK FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT user_interests_FK FOREIGN KEY (interestID) REFERENCES interests(interestID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE departments (
    departmentID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    PRIMARY KEY (departmentID)
);

CREATE TABLE faculty (
    userID INT UNSIGNED NOT NULL,
    departmentID INT UNSIGNED NOT NULL,
    firstName VARCHAR(50) DEFAULT NULL,
    lastName VARCHAR(50) DEFAULT NULL,
    abstract VARCHAR(200) DEFAULT NULL,
    PRIMARY KEY (userID, departmentID),
    CONSTRAINT faculty_userID_FK FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT faculty_departmentID_FK FOREIGN KEY (departmentID) REFERENCES departments(departmentID) ON DELETE CASCADE ON UPDATE CASCADE
);

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

CREATE TABLE community_users (
    userID INT UNSIGNED NOT NULL,
    name VARCHAR(50) NOT NULL DEFAULT '',
    PRIMARY KEY (userID),
    CONSTRAINT community_user_userID_FK FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE projects (
    projectID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    userID INT UNSIGNED NOT NULL,
    name VARCHAR(50)NOT NULL DEFAULT '',
    description VARCHAR(255) NOT NULL DEFAULT '',
    PRIMARY KEY (projectID, userID),
    CONSTRAINT project_userID_FK FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE project_interests (
    projectID INT UNSIGNED NOT NULL,
    interestID INT UNSIGNED NOT NULL,
    PRIMARY KEY (projectID, interestID),
    CONSTRAINT projectID_FK FOREIGN KEY (projectID) REFERENCES projects(projectID),
    CONSTRAINT interestID_FK FOREIGN KEY (interestID) REFERENCES interests(interestID) ON DELETE CASCADE ON UPDATE CASCADE
);
