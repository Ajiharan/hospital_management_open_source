/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import link1.LinkListX;

import link1.User;

/**
 *
 * @author Haran
 */
public class DBConnection {
    private  Connection con;
     private  PreparedStatement ps;
    private ResultSet rs;
    private static ResultSet myrs;
     private Statement st;
     public static Connection ConnectionDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/athlanta_hospital", "root", "");
            
            return conn;
            //JOptionPane.showMessageDialog(null,"DB conected");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
     }
       public  LinkListX setValues(){
         LinkListX newlink=new LinkListX();
       
        String sql = "SELECT * FROM employee";
        
        
        try{
           con=ConnectionDB();
           ps=con.prepareStatement(sql);
          
           rs=ps.executeQuery();
            while(rs.next()){
               
                String iData=rs.getString(1);
                String fname=rs.getString(2);
                String lname=rs.getString(3);
                String gender=rs.getString(4);
                String status=rs.getString(5);
                String dob=rs.getString(6);
                String nic=rs.getString(7);
                String contact_number=rs.getString(8);
                String email=rs.getString(9);
                String address=rs.getString(10);
                String Ol=rs.getString(11);
                String Al=rs.getString(12);
                String others=rs.getString(13);
                String qualificationDetails=rs.getString(14);
                String english=rs.getString(15);
                String singala=rs.getString(17);
                String tamil=rs.getString(16);
                String designation=rs.getString(18);
                String employee=rs.getString(19);
                String join=rs.getString(20);
                
                User user=new User(iData, fname, lname, gender, status, dob, nic, contact_number, email, address, Ol, Al, others, qualificationDetails, english, singala, tamil, designation, employee, join);
              newlink.insertLast(user);
                
               
            }
             myrs=rs;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
           
            try {
                 
                 ps.close();
                rs.close();
            } catch (SQLException ex) {
                
            }
        }
        
      return newlink; 

    
    }
       
    public ResultSet getresultSet(){
          con=ConnectionDB();
           String sql = "SELECT * FROM employee";
            try{
            st=con.createStatement();
            rs=st.executeQuery(sql);
            myrs=rs;
            
        }
        catch(Exception e){
            
            JOptionPane.showConfirmDialog(null,e);
        }
            
        return myrs;
    }

}
