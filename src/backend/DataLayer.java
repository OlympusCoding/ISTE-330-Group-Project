/*
 * File: Data Layer
 * Group: 2
 * Project: Group Project
 * Class: ISTE-330
 */

package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import types.user.UserParams;
import types.user.userTypes.Community;
import types.user.userTypes.Faculty;
import types.user.userTypes.Student;

public class DataLayer {
    private Connection conn;
    public boolean connect(String username, String password, String url) {
        try {
            conn = DriverManager.getConnection(username, password, url);
            System.out.println("DB Connected - Group 2");
            return true;
        } catch (SQLException e) {
            try {
            url = "jdbc:mysql://localhost:3307/"+url; 
            conn = DriverManager.getConnection(username, password, url);
            System.out.println("DB Connected - Group 2");
            return true;
            } catch (SQLException e2) {
               e2.printStackTrace();
               return false;
            }
        }
    }
    public boolean close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean checkUsername(String username){
        String sql = "SELECT userID FROM users WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean checkPassword(String username, String password){
        String sql = "SELECT userID FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Add functions
    public Boolean addUser(UserParams params){
        String username = params.getUsername();
        String password = params.getPassword();
        String email = params.getEmail();
        String type = params.getUserTypeInfo().getUserType();

        String sql = "INSERT into users(username, password, email, userType)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, type);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if(type.equals("student")){
                    addStudent(params);
                }else if(type.equals("faculty")){
                    addFaculty(params);
                }else{
                    addCommunity(params);
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addStudent(UserParams params) {
    
        Student studentInfo = (Student) params.getUserTypeInfo();
    
        String sql = "INSERT INTO students (userID, categoryYear, departmentID, firstName, lastName) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, params.getUserID());
            stmt.setString(2, studentInfo.getCategoryYear().toString());
            stmt.setInt(3, studentInfo.getDepartmentID());
            stmt.setString(4, studentInfo.getFirstName());
            stmt.setString(5, studentInfo.getLastName());
    
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean addFaculty(UserParams params) {
        String username = params.getUsername();
        String password = params.getPassword();
        int userID = Integer.parseInt(getUserID(username, password));

        Faculty faculty = (Faculty) params.getUserTypeInfo();
        int deptID = faculty.getDepartmentID();
        String firstName = faculty.getFirstName();
        String lastName = faculty.getLastName();
        String facultyAbstract = faculty.getFacultyAbstract();

        String sql = "INSERT INTO faculty (userID, departmentID, firstName, lastName, abstract) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, deptID);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(4, facultyAbstract);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean addCommunity(UserParams params) {
        String username = params.getUsername();
        String password = params.getPassword();
        int userID = Integer.parseInt(getUserID(username, password));

        Community community = (Community) params.getUserTypeInfo();
        String name = community.getName();

        String sql = "INSERT INTO community_users (userID, name) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.setString(2, name);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addProject(int userID, String projectName, String description) {
        String sql = "INSERT INTO projects (userID, name, description) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.setString(2, projectName);
            stmt.setString(3, description);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addInterest(int userID, String interestDescription) {

        // I don't really know what to do for interests
       return false;
    }
    public boolean addAbstract(int userID, String abstractText) {
        String sql = "UPDATE faculty SET abstract = ? WHERE userID = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, abstractText);
            stmt.setInt(2, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get Functions for All Users
    public String getUserID(String username, String password){
        String sql = "SELECT userID FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("userID");
            } else {
                return "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
    public String getUserUsername(int userID){
        String sql = "SELECT username FROM users WHERE userID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("username");
            } else {
                return "No username found for userID: " + userID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
    public String getUserEmail(int userID){
        String sql = "SELECT email FROM users WHERE userID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("email");
            } else {
                return "No email found for userID: " + userID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
    public String getUserType(int userID){
        String sql = "SELECT userType FROM users WHERE userID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("userType");
            } else {
                return "No userType found for userID: " + userID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
    public String getUserFirstName(int userID){
        String userType = getUserType(userID);
        String sql = "SELECT firstName FROM "+userType+" WHERE userID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("firstName");
            } else {
                return "No firstName found for userID: " + userID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
    public String getUserLastName(int userID){
        String userType = getUserType(userID);
        String sql = "SELECT lastName FROM "+userType+" WHERE userID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("lastName");
            } else {
                return "No lastName found for userID: " + userID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
    public String getUserFullName(int userID){
        String userType = getUserType(userID);
        String sql = "SELECT (firstname, lastName) AS name FROM "+userType+" WHERE userID = ?";
        if(userType.equals("Community")){
            sql = "SELECT name FROM "+userType+" WHERE userID = ? ";
        }
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            } else {
                return "No name found for userID: " + userID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
    public String getUserDepartmentID(int userID){
        String userType = getUserType(userID);
        String sql = "SELECT departmentID FROM "+userType+" WHERE userID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("departmentID");
            } else {
                return "No department found for userID: " + userID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Get Functions for Faculty
    public String getFacultyAbstract(int userID){
        String sql = "SELECT abstract FROM faculty WHERE userID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("abstract");
            } else {
                return "No abstract found for userID: " + userID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Get Functions for Students
    public String getStudentYear(int userID){
        String sql = "SELECT categoryYear FROM faculty WHERE userID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("categoryYear");
            } else {
                return "No categoryYear found for userID: " + userID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Get Functions for Interests
    public String getInterest(int interestID){
        String sql = "SELECT description FROM interests WHERE interestID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, interestID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("description");
            } else {
                return "No description found for userID: " + interestID;
            }
         } catch (SQLException e) {
             e.printStackTrace();
            return "";
         }
    }

    // Get Functions for Projects
    public String getProjectName(int projectID){
        String sql = "SELECT name FROM projects WHERE projectID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, projectID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            } else {
                return "No name found for userID: " + projectID;
            }
         } catch (SQLException e) {
             e.printStackTrace();
            return "";
         }
    }
    public String getProjectDescription(int projectID){
        String sql = "SELECT description FROM projects WHERE projectID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, projectID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("description");
            } else {
                return "No description found for userID: " + projectID;
            }
         } catch (SQLException e) {
             e.printStackTrace();
            return "";
         }
    }

    // Get Functions for Departments
    public String getDepartmentName(int departmentID){
        String sql = "SELECT name FROM departments WHERE departmentID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, departmentID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            } else {
                return "No name found for userID: " + departmentID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Edit Users
    public boolean updateFirstName(int userID, String newName) {
        String userType = getUserType(userID);
        
        String tableName;
    
        switch (userType.toLowerCase()) {
            case "student":
                tableName = "students";
                break;
            case "faculty":
                tableName = "faculty";
                break;
            default:
                return false;
        }
    
        String sql = "UPDATE " + tableName + " SET firstName = ? WHERE userID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateLastName(int userID, String newName) {
        String userType = getUserType(userID);
        String tableName;
    
        switch (userType.toLowerCase()) {
            case "student":
                tableName = "students";
                break;
            case "faculty":
                tableName = "faculty";
                break;
            default:
                return false;
        }
    
        String sql = "UPDATE " + tableName + " SET lastName = ? WHERE userID = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updatePassword(int userID, String password) {
        String sql = "UPDATE users SET password = ? WHERE userID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, password);
            stmt.setInt(2, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateAbstract(int userID, String newAbstract) {
        String sql = "UPDATE faculty SET abstract = ? WHERE userID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newAbstract);
            stmt.setInt(2, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateProjectName(int projectID, String projectName) {
        String sql = "UPDATE users SET projectName = ? WHERE userID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projectName);
            stmt.setInt(2, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateProjectDescription(int projectID, String projectDescription) {
        String sql = "UPDATE users SET projectDescription = ? WHERE userID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projectDescription);
            stmt.setInt(2, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Remove from users
    public boolean removeAbstract(int userID) {
        String sql = "UPDATE faculty SET abstract = NULL WHERE userID = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean removeProject(int projectID) {
        String sql = "DELETE FROM projects WHERE projectID = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, projectID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
