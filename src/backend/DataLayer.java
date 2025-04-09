/*
 * File: Data Layer
 * Group: 2
 * Project: Group Project
 * Class: ISTE-330
 */

package backend;

import java.sql.Connection;
import java.sql.DriverManager;
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
}
