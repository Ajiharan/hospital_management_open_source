/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maternity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sivakumar
 */
public class DBConnection {

    private static String driverClass = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/athlanta_hospital";
    private static String db_username = "root";
    private static String db_password = "";
    
public static Connection getConnection(){
        
        Connection conn = null;
        Statement stmt = null;
            try {
                Class.forName(driverClass).newInstance();
                conn = DriverManager.getConnection(url, db_username, db_password);
                return conn;
                
            } catch (ClassNotFoundException ex) {
              ex.printStackTrace();
            } catch (InstantiationException ex) {
              ex.printStackTrace();
            } catch (IllegalAccessException ex) {
              ex.printStackTrace();
            } catch (SQLException ex) {
              ex.printStackTrace();
            }
            return null;
    }    
    
}
