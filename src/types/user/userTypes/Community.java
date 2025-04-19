package types.user.userTypes;

public class Community extends UserTypeInfo{

    private String userType = "community";
    private String name;

    public Community(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUserType() {
        return userType;
    }
    
}
