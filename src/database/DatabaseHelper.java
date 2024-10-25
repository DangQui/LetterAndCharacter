/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHelper {
      // Step 1: Setup the connection parameters
     // Step 1: Setup the connection parameters
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=qlnv";
    private static final String USER = "sa";
    private static final String PASSWORD = "1";
    
    private Connection conn;

    public DatabaseHelper() throws SQLException {
        try {
            // Step 2: Establish the connection
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public ResultSet selectQuery(String query, Object[] params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        
        // Dynamically set parameters
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        
        return stmt.executeQuery(); // Returns a ResultSet
    }
    
     //Step 6. Closing the connection
    //Step 2,3,4,5. Method to execute dynamic SQL queries (INSERT, UPDATE, DELETE)
    public int executeQuery(String query, Object[] params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        
        // Dynamically set parameters
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }        
        // Handle different query types
        return stmt.executeUpdate(); // Executes INSERT, UPDATE, DELETE        
        
    }
    
     //Step 6. Closing the connection
    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}