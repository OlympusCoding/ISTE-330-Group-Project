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
    // Get Functions for All Users
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
}
