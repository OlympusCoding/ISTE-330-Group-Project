/*
 * File: GUI Fascade
 * Group: 2
 * Project: Group Project
 * Authors: Charles Coleman, Flavio Medina, Will Jacobs, Sean Guyon, David Kalinowski
 * Class: ISTE-330
 */


package backend;

import java.util.ArrayList;
import java.util.List;

import types.user.UserParams;
import types.user.userTypes.*;
import utility.Encryption;

public class GUIFascade {
    
    DataLayer dataLayer;

    public GUIFascade(DataLayer dataLayer)
    {
        this.dataLayer = dataLayer;
    }

    /**
     * Attempt to Login to the application using Username and Password
     * @param username The inputted username of the login attempt
     * @param password The inputted (unencrypted) password of the login attempt
     * @return Returns True if Login attempt successful, else returns False
     */
    public boolean Login(String username, String password)
    {
        // Use Datalayer to access database entry of user based on username
        // If User does not exist, return false

        // Encrypt password param to test against stored password
        // If Passwords do not match, return false

        // If all parts match the user, return true

        // Are we encrypting before the function or during?

        if(dataLayer.checkUsername(username)){
            if(dataLayer.checkPassword(username, Encryption.encrypt(password))){
                return true;
            }
            else 
            {
                return false;
            }
        }

        return false;
    }

    public boolean Register(UserParams params)
    {
        // Create New User with all sub types etc
        return dataLayer.addUser(params);
    }

    public boolean ChangeUserFullname(int userID, String firstName, String lastName)
    {
        if(ChangeUserFirstName(userID, firstName)){
            if(ChangeUserLastname(userID, lastName)){
                return true;
            }
        }
        return false;
    }

    public boolean ChangeUserFirstName(int userID, String newName)
    {
        return dataLayer.updateFirstName(userID, newName);

    }

    public boolean ChangeUserLastname(int userID, String newName)
    {
        return dataLayer.updateLastName(userID, newName);
    }

    public boolean ChangePassword(int userID, String oldPass, String newPass)
    {
        String username = dataLayer.getUserUsername(userID);
        if(dataLayer.checkPassword(username, oldPass)){
            return dataLayer.updatePassword(userID, newPass);
        }
        return false;
    }

    public boolean AddFacultyAbstract(int userId, String abstractText)
    {
        return dataLayer.addAbstract(userId, abstractText);
    }

    public boolean UpdateFacultyAbstract(int userId, String abstractText)
    {
        return dataLayer.updateAbstract(userId, abstractText);
    }

    public boolean RemoveFacultyAbstract(int userId)
    {
        dataLayer.removeAbstract(userId);
        return true;
    }

    public boolean CreateFacultyInterest(int userID, String interestDescription)
    {
        // Creates a new Faculty Interest
        dataLayer.addInterest(userID, interestDescription);
        return true;
    }

    public boolean UpdateFacultyInterest(int interestID, String description)
    {
        // Updates an existing Faculty Interest

        // Do we need to update interests?
        dataLayer.updateInterest(interestID, description);
        return true;
    }

    public boolean RemoveFacultyInterest(int userID, int interestID)
    {
        // Removes Faculty Interest
        dataLayer.removeInterest(userID, interestID);
        return true;
    }

    public boolean CreateProject(int userId, String projectname, String description)
    {
        return dataLayer.addProject(userId, projectname, description);
    }

    public boolean UpdateProjectName(int userId, String name)
    {
        // Updates a Project

        return dataLayer.updateProjectName(userId, name);
    }

    public boolean DeleteProject(int projectId)
    {
        // Deletes a project
        return dataLayer.removeProject(projectId);
    }

    public List<Faculty> SearchForFacultyByInterest(List<String> interests)
    {
        // Searches for faculty based on an interest
        // Returns a list of faculty that share that interest
        return dataLayer.searchForFacultyByInterest(interests);
    }



    public static void main(String[] args) {
        
        // This is where the application is run prior to the GUI being built.
        // Authors: Charles Coleman, Flavio Medina, Will Jacobs, Sean Guyon, David Kalinowski
        // This is forced code to show you that the backend works, once the GUI is built for the next deliverable, the 
        System.out.println("Authors: Charles Coleman, Flavio Medina, Will Jacobs, Sean Guyon, David Kalinowski");


        DataLayer dataLayer = new DataLayer();
        GUIFascade fascade = new GUIFascade(dataLayer);

        dataLayer.connect("root", "student", "330_project_research");

        if (fascade.Login("willjacobs", "student4"))
        {
            System.out.println("Login Successful for User: willjacobs");
        }
        else
        {
            System.out.println("Login unsuccessful, please re-run the program");
            return;
        }

        System.out.println("Updating Faculty Abstract for userID #100");
        if (fascade.UpdateFacultyAbstract(100, "The leading AI and Machine Learning Book for those in higher educational institutions"))
        {
            System.out.println("New Abstract gotten from DB: " + dataLayer.getFacultyAbstract(100));
        }
        else
        {
            System.out.println("Abstract could not be updated, please try again.");
        }

        System.out.println("Search Functions:");
        System.out.println("-----------------------------");
        System.out.println("Seraching for Faculty Member with interests 'AI' and 'Machine Learning'");
        List<String> testInterests = new ArrayList<String>();
        testInterests.add("AI");
        testInterests.add("Machine Learning");
        List<Faculty> facultyReturned = fascade.SearchForFacultyByInterest(testInterests);
        System.out.println("The faculty members that were returned: ");
        for (Faculty faculty : facultyReturned) {
            System.out.println("Faculty Member: " + faculty.getFirstName() + " " + faculty.getLastName());
            System.out.println("Office: Building " + faculty.getBuildingNum() + " - Office " + faculty.getOfficeNum());
        }

        dataLayer.close();
        System.out.println("Exiting Application");
    }




}
