package types.user;

import types.enums.UserType;
import types.user.userTypes.UserTypeInfo;

public class UserParams 
{
    private int userID;
    private String username;
    private String password;
    private String email;
    private UserType userType;
    private UserTypeInfo userTypeInfo;

    public UserParams(int userID, String username, String password, String email, UserType userType, UserTypeInfo userTypeInfo)
    {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.userTypeInfo = userTypeInfo;
    }
}


