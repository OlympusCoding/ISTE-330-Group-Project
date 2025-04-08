-- Class Name: ISTE 330
-- Project Title: Faculty Research Database
-- Group Number: 2
-- Authors: Flavio Medina, Charles Coleman, Will Jacobs, Sean Guyon, David Kalinowski
-- Date: 04/19/25
-- Version: 1.0


-- Users
INSERT INTO users (username, password, email) VALUES ('', '', '');


-- Interests
INSERT INTO interests (description) VALUES ('');


-- User Interests
INSERT INTO user_interets (userID, interestID) VALUES (0);


-- Department
INSERT INTO departments (name) VALUES ('');


-- Faculty
INSERT INTO faculty (userID, departmentID, firstName, lastName, abstract) VALUES(0, 0, '');


-- Students
INSERT INTO students (userID, categoryYear, departmentID, firstName, lastName) VALUES (0, '', 0, '', ''),;


-- Community Users
INSERT INTO community_users (userID, name) VALUES (0, '');


-- Projects
INSERT INTO projects (userID, name, description) VALUES (0, '', '');


-- Project Interests
INSERT INTO project_interests (projectID, interestID) VALUES (0, 0);