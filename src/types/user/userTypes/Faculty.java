package types.user.userTypes;

public class Faculty extends UserTypeInfo {
    
    private String userType = "faculty";
    private int departmentID;
    private String firstName;
    private String lastName;
    private String facultyAbstract;


    public Faculty(int departmentID, String firstName, String lastName, String facultyAbstract)
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
    
}
