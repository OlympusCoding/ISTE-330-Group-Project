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