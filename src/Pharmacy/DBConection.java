/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author kathir
 */
public class DBConection {
    Connection conn;
    
    public DBConection ()
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/athlanta_hospital", "root", "");
            //JOptionPane.showMessageDialog(null,"DB conected");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public Connection GetConection()
    {
        return conn;
    }
}
