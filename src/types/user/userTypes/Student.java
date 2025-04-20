package types.user.userTypes;

import types.enums.CategoryYear;

public class Student extends UserTypeInfo {
    
    private String userType = "student";
    private CategoryYear categoryYear;
    private int departmentID;
    private String firstName;
    private String lastName;

    public Student(CategoryYear categoryYear, int departmentID, String firstName, String lastName)
    {
        this.categoryYear = categoryYear;
        this.departmentID = departmentID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserType() {
        return userType;
    }

    public CategoryYear getCategoryYear() {
        return categoryYear;
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
    
}
