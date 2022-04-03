package hms;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohamed Nifal
 */
public class DBConnection {
    java.sql.Connection conn;
    private Connection DBConnection;
    public  Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection Success");
            
        }
        catch   (ClassNotFoundException cnfe) {
            System.out.println("Connection Fail" + cnfe);
            
        }
        String url = "jdbc:mysql://localhost:3306/athlanta_hospital";
        try {
            DBConnection =  DriverManager.getConnection(url, "root", "root");
            System.out.println("Database Connected");
            
        }
        catch (SQLException se) {
            System.out.println("No Database" + se);
            
        }
        return DBConnection;
    }
    
    public java.sql.Connection GetConnection()
    {
        return conn;
    }
    
}
