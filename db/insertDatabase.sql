-- Class Name: ISTE 330
-- Project Title: Faculty Research Database
-- Group Number: 2
-- Authors: Flavio Medina, Charles Coleman, Will Jacobs, Sean Guyon, David Kalinowski
-- Date: 04/19/25
-- Version: 1.0


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
    ('tigersrit', 'student7', 'tigersrit@rit.edu');     




-- Interests
INSERT INTO 
    interests (description) 
VALUES 
    ('Software Engineering'), -- dse
    ('Data Science'), -- dse
    ('Studio Arts'), -- sa
    ('Chemistry',) -- scms
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
    (50, 1);


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
INSERT INTO faculty (userID, departmentID, firstName, lastName, abstract) VALUES(0, 0, '');


-- Students
INSERT INTO students (userID, categoryYear, departmentID, firstName, lastName) VALUES (0, '', 0, '', ''),;


-- Community Users
INSERT INTO community_users (userID, name) VALUES (0, '');


-- Projects
INSERT INTO projects (userID, name, description) VALUES (0, '', '');


-- Project Interests
INSERT INTO project_interests (projectID, interestID) VALUES (0, 0);
