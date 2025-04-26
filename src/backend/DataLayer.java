/*
 * File: Data Layer
 * Group: 2
 * Project: Group Project
 * Authors: Charles Coleman, Flavio Medina, Will Jacobs, Sean Guyon, David Kalinowski
 * Class: ISTE-330
 */

package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import types.StatsType;
import types.enums.CategoryYear;
import types.enums.UserType;
import types.user.userTypes.Faculty;
import types.user.userTypes.Student;

public class DataLayer {

    private final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";
    private Connection conn;

    public boolean connect(String username, String password, String url) {
        try {
            Class.forName(DEFAULT_DRIVER);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("DB Connected - Group 2");
            return true;
        } catch (SQLException e) {
            try {
                url = "jdbc:mysql://localhost:3306/" + url;
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("DB Connected - Group 2");
                return true;
            } catch (SQLException e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
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

    public Boolean checkUsername(String username) {
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

    public Boolean checkPassword(String username, String password) {
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
    public int getUserID(String username) {
        String sql = "SELECT userID FROM users WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("userID");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getUserUsername(int userID) {
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

    public String getUserEmail(int userID) {
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

    public UserType getUserType(int userID) {
        String sql = "SELECT userType FROM users WHERE userID = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userTypeString = rs.getString("userType").toLowerCase();
                UserType userType = UserType.valueOf(userTypeString);
                return userType;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAbstract(int userID) {
        System.out.println(userID);
        String sql = "SELECT abstract FROM faculty WHERE userID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Gets the abstract string from query
                return rs.getString(1);
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
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

    public List<Faculty> searchForFacultyByInterest(List<String> interests) {
        List<Faculty> toReturn = new ArrayList<Faculty>();
        String sql = "SELECT f.firstName, f.lastName, f.buildingNum, f.officeNum, i.description, u.email FROM users as u JOIN faculty AS f on u.userID = f.userID JOIN user_interests AS ui ON u.userID = ui.userID JOIN interests AS i ON ui.interestID = i.interestID WHERE i.description LIKE ?";

        for (String interest : interests) {
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, interest);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    toReturn.add(
                            new Faculty(rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                                    rs.getString("buildingNum"), rs.getString("officeNum")));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return toReturn;

    }

    public List<Student> searchForStudentByAbstract(String abstractString) {
        List<Student> toReturn = new ArrayList<Student>();

        String sql = "SELECT s.categoryYear, s.departmentID, s.firstName, s.lastName, u.email FROM students AS s JOIN users AS u USING (userID) JOIN user_interests AS ui USING (userID) JOIN interests AS i USING (interestID) WHERE LOCATE(i.description, ?) > 0";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, abstractString);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student newStudent = new Student(CategoryYear.valueOf(rs.getString("categoryYear")),
                        rs.getInt("departmentID"), rs.getString("email"), rs.getString("firstName"),
                        rs.getString("lastName"));
                toReturn.add(newStudent);
            }
            return toReturn;
        } catch (SQLException e) {
            System.out.println("There was an error with the SQL Statement: " + e.getMessage());
            return null;
        }

    }

    public StatsType getStats() {
        String sql = "SELECT (SELECT COUNT(*) FROM faculty) AS faculty_count, (SELECT COUNT(*) FROM students) AS student_count, (SELECT COUNT(*) FROM community_users) AS community_users_count;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            int facultyCount = 0;
            int studentCount = 0;
            int communityCount = 0;

            if (rs.next()) {
                facultyCount = rs.getInt("faculty_count");
                studentCount = rs.getInt("student_count");
                communityCount = rs.getInt("community_users_count");
            }

            return new StatsType(facultyCount, studentCount, communityCount);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
