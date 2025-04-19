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

INSERT INTO students (userID, categoryYear, departmentID, firstName, lastName) VALUES (0, '', 0, '', ''),;


-- Community Users
CREATE TABLE community_users (
    userID INT UNSIGNED NOT NULL,
    name VARCHAR(50) NOT NULL DEFAULT '',
    PRIMARY KEY (userID),
    CONSTRAINT community_user_userID_FK FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE ON UPDATE CASCADE
);




-- Projects
CREATE TABLE projects (
    projectID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    userID INT UNSIGNED NOT NULL,
    name VARCHAR(50) NOT NULL DEFAULT '',
    description VARCHAR(255) NOT NULL DEFAULT '',
    PRIMARY KEY (projectID),
    CONSTRAINT project_userID_FK FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE ON UPDATE CASCADE
);


/* ====================
        Insert:
=======================*/

-- Users
INSERT INTO 
    users (username, password, email) 
-- Contains 7 students & Auto Increment starts at 100
VALUES 
    -- userID: 100
    ('flaviomedinajr', 'student1', 'fjm8881@rit.edu'),  

    -- userID: 101 
    ('charlescoleman', 'student2', 'cxctis@rit.edu'),   

    -- userID: 102
    ('seanguyon', 'student3', 'spg5070@rit.edu'),    

    -- userID: 103     
    ('willjacobs', 'student4', 'whj3443@rit.edu'),    

    -- userID: 104 
    ('davidkalinowski', 'student5', 'dmk7090@rit.edu'), 

    -- userID: 105
    ('johndoe', 'student6', 'johndoe@rit.edu'),    

    -- userID: 106 
    ('janesmith', 'student7', 'janesmith@rit.edu');     


-- Interests
INSERT INTO 
    interests (description) 
VALUES 
    ('Software Engineering'), -- dse
    ('Data Science'), -- dse
    ('Studio Arts'), -- sa
    ('Chemistry'), -- scms
    ('Biochemistry'), -- scms
    ('Accounting'), -- fad
    ('Finance'), -- fad
    ('Artificial Intelligence'),
    ('Machine Learning'),
    ('Data Science'),
    ('Cybersecurity'),
    ('Human-Computer Interaction'),
    ('Computer Networks'),
    ('Database Systems'),
    ('Web Development'),
    ('Computer Graphics'),
    ('Cloud Computing');
    


-- User Interests
INSERT INTO 
    user_interets (userID, interestID) 
VALUES 
    (100, 1),
    (101, 2),
    (102, 8),
    (103, 9),
    (104, 4),
    (105, 6),
    (105, 3);


-- Department
INSERT INTO 
    departments (name, abbreviation) 
VALUES 
    ('College of Art and Design','CAD'),
    ('College of Engineering Technology','CET'),
    ('College of Health Sciences and Technology','CHST'),
    ('College of Liberal Arts','COLA'),
    ('College of Science','COS'),
    ('Golisano College of Computing and Information Sciences','GCCIS'),
    ('Golisano Institute for Sustainability','GIS'),
    ('Kate Gleason College of Engineering','KGCOE'),
    ('National Technical Institute for the Deaf','NTID'),
    ('Saunders College of Business','SCOB'),
    ('School of Individualized Study','SOIS');


-- Faculty
INSERT INTO 
    faculty(userID, departmentID, firstName, lastName, abstract) 
VALUES
    (100, 6, 'Flavio', 'Medina', 'AI and Cybersecurity expert'),
    (101, 6, 'Charles', 'Coleman', 'Web Development and Software Frameworks'),
    (102, 7, 'Sean', 'Guyon', 'Cloud Computing and IoT'),
    (103, 2, 'Will', 'Jacobs', 'Data Analytics and Machine Learning'),
    (104, 3, 'David', 'Kalinowski', 'Sustainable Biochemistry Processes'),
    (105, 9, 'John', 'Doe', 'Accessibility tech for Deaf students'),
    (106, 8, 'Jane', 'Smith', 'AI and Robotics');

-- Students
INSERT INTO 
    students (userID, categoryYear, departmentID, firstName, lastName) 
VALUES 
    (100, 'Senior', 6, 'Flavio', 'Medina'),
    (101, 'Senior', 6, 'Charles', 'Coleman'),
    (102, 'Freshman', 7, 'Sean', 'Guyon'),
    (103, 'Junior', 2, 'Will', 'Jacobs'),
    (104, 'Junior', 3, 'David', 'Kalinowski'),
    (105, 'Sophomore', 9, 'John', 'Doe'),
    (106, 'Senior', 8, 'Jane', 'Smith');

-- Community Users
INSERT INTO    
    community_users (userID, name) 
VALUES 
    (107, 'Emma Kang'),
    (108, 'Peter Parker'),
    (109, 'Steve Rogers'),
    (110, 'Tony Stark'),
    (111, 'Jim Goods'),
    (112, 'Denis Fire'),
    (113, 'Elon Musk'),
    (114, 'LeBron James'),
    (115, 'Stephen Curry');


-- Projects
INSERT INTO 
    projects (userID, name, description) 
VALUES 
    (100, 'Secure AI', 'Use AI to detect cyber treats'),
    (101, 'Web Tools', 'Compare React and Vue performance'),
    (102, 'Smart Campus', 'Link campus tech using IoT'),
    (103, 'Stock ML', 'ML Model for stock prediction'),
    (104, 'Green Chem', 'Make eco-frendly lab materials');
    (105, 'ASL App', 'App to teach ASL basics'),
    (106, 'Robot Arm', 'AI for object sorting task');