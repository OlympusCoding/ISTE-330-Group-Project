package backend;

import java.util.List;

import types.enums.UserType;
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

        // Are we encrypting before the function or during?

        if(dataLayer.checkUsername(username)){
            if(dataLayer.checkPassword(username, password)){
                return true;
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

    public boolean CreateFacultyInterest()
    {
        // Creates a new Faculty Interest
        return true;
    }

    public boolean UpdateFacultyInterest()
    {
        // Updates an existing Faculty Interest

        // Do we need to update interests?
        return true;
    }

    public boolean RemoveFacultyInterest()
    {
        // Removes Faculty Interest
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
