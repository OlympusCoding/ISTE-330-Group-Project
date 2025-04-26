/*
 * File: GUI Fascade
 * Group: 2
 * Project: Group Project
 * Authors: Charles Coleman, Flavio Medina, Will Jacobs, Sean Guyon, David Kalinowski
 * Class: ISTE-330
 */

package backend;

import java.util.List;

import types.StatsType;
import types.enums.UserType;
import types.user.userTypes.*;
import utility.Encryption;

public class GUIFascade {

    DataLayer dataLayer;

    public GUIFascade(DataLayer dataLayer) {
        this.dataLayer = dataLayer;
    }

    /**
     * Attempt to Login to the application using Username and Password
     * 
     * @param username The inputted username of the login attempt
     * @param password The inputted (unencrypted) password of the login attempt
     * @return Returns True if Login attempt successful, else returns False
     */
    public boolean Login(String username, String password) {
        // Use Datalayer to access database entry of user based on username
        // If User does not exist, return false

        // Encrypt password param to test against stored password
        // If Passwords do not match, return false

        // If all parts match the user, return true

        // Are we encrypting before the function or during?

        if (dataLayer.checkUsername(username)) {
            if (dataLayer.checkPassword(username, Encryption.encrypt(password))) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }    

    public boolean AddFacultyAbstract(int userId, String abstractText) {
        return dataLayer.addAbstract(userId, abstractText);
    }

    public boolean UpdateFacultyAbstract(int userId, String abstractText) {
        return dataLayer.updateAbstract(userId, abstractText);
    }

    public boolean RemoveFacultyAbstract(int userId) {
        dataLayer.removeAbstract(userId);
        return true;
    }

    public String getFacultyAbstract(int userId)
    {
        return dataLayer.getAbstract(userId);
    }

    public List<Faculty> SearchForFacultyByInterest(List<String> interests) {
        // Searches for faculty based on an interest
        // Returns a list of faculty that share that interest
        return dataLayer.searchForFacultyByInterest(interests);
    }

    public List<Student> SearchForStudentByAbstract(String abstractText) {
        return dataLayer.searchForStudentByAbstract(abstractText);
    }

    public int GetUserID(String username) {
        return dataLayer.getUserID(username);
    }

    public UserType GetUserType(int userID) {
        // Returns the type of user based on their ID
        return dataLayer.getUserType(userID);
    }

    public String GetUserEmail(int userID) {
        return dataLayer.getUserEmail(userID);
    }

    public StatsType GetStats() {
        return dataLayer.getStats();
    }

    public void CloseConnection() {
        dataLayer.close();
    }
}
