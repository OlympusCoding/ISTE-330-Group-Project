package types.user.userTypes;

public class Faculty extends UserTypeInfo {
    
    private String userType = "faculty";
    private int departmentID;
    private String firstName;
    private String lastName;
    private int buildingNum;
    private int officeNum;
    private String facultyAbstract;


    public Faculty(int departmentID, String firstName, String lastName, int buildingNum, int officeNum, String facultyAbstract)
    {
        super();
        this.departmentID = departmentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.facultyAbstract = facultyAbstract;
    }
    public String getUserType() {
        return userType;
    }
    public int getDepartmentID() {
        return departmentID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFacultyAbstract() {
        return facultyAbstract;
    }

    public int getBuildingNum()
    {
        return buildingNum;
    }

    public int getOfficeNum()
    {
        return officeNum;
    }
    
}
