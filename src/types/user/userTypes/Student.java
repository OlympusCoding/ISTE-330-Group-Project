package types.user.userTypes;

import types.enums.CategoryYear;

public class Student extends UserTypeInfo {
    
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
}
