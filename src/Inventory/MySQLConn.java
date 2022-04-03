package Inventory;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.*;


public class MySQLConn {
    static Connection conn = null;
    
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/athlanta_hospital","root","");
            return conn;
        }catch(ClassNotFoundException | SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
}
