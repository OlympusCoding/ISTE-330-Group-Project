package backend;

import java.util.List;

import types.user.UserParams;
import types.user.userTypes.*;

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
        return true;
    }

    public boolean Register(UserParams params)
    {
        // Create New User with all sub types etc
        return true;
    }

    public boolean ChangeUserFullname()
    {
        ChangeUserFirstName();
        ChangeUserLastname();
        return true;
    }

    public boolean ChangeUserFirstName()
    {
        return true;
    }

    public boolean ChangeUserLastname()
    {
        return true;
    }

    public boolean ChangePassword()
    {
        // Requires knowing old password to change password
        return true;
    }

    public boolean AddFacultyAbstract()
    {
        // Adds a new abstract to a faculty member
        return true;
    }

    public boolean UpdateFacultyAbstract()
    {
        // Updates an existing abstract of a faculty member(s)
        return true;
    }

    public boolean RemoveFacultyAbstract()
    {
        // Removes a faculty member from an abstract
        return true;
    }

    public boolean CreateFacultyInterest()
    {
        // Creates a new Faculty Interest
        return true;
    }

    public boolean UpdateFacultyInterest()
    {
        // Updates an existing Faculty Interest
        return true;
    }

    public boolean RemoveFacultyInterest()
    {
        // Removes Faculty Interest
        return true;
    }

    public boolean CreateProject()
    {
        // Creates a new project
        return true;
    }

    public boolean UpdateProject()
    {
        // Updates a Project
        return true;
    }

    public boolean DeleteProject()
    {
        // Deletes a project
        return true;
    }

    // Project Interests??

    public List<Student> SearchForStudentByInterest()
    {
        // Searches for students based on an interest
        // Returns a list of students that share that interest
        return null;
    }

    public List<Faculty> SearchForFacultyByInterest()
    {
        // Searches for faculty based on an interest
        // Returns a list of faculty that share that interest
        return null;
    }



}
