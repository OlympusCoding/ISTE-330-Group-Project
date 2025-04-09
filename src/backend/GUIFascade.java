package backend;

import types.user.UserParams;

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
        return true;
    }




}
