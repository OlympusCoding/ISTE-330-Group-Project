package types.user.userTypes;

public class Faculty extends UserTypeInfo {

    private String userType = "faculty";
    private int departmentID;
    private String firstName;
    private String lastName;
    private String buildingNum;
    private String officeNum;
    private String facultyAbstract;
    private String email;

    public Faculty(int departmentID, String firstName, String lastName, String buildingNum, String officeNum,
            String facultyAbstract) {
        super();
        this.departmentID = departmentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.facultyAbstract = facultyAbstract;
        this.buildingNum = buildingNum;
        this.officeNum = officeNum;
    }

    public Faculty(String email, String firstName, String lastName, String buildingNum, String officeNum) {
        super();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.buildingNum = buildingNum;
        this.officeNum = officeNum;
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

    public String getBuildingNum() {
        return buildingNum;
    }

    public String getOfficeNum() {
        return officeNum;
    }

    public String getEmail() {
        return email;
    }

}
