/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Matha
 */
public class NewClSM {
    String tid="";
    String tname="";
    double fee;
    
    public NewClSM(String a, String b,double c){
        tid = a;
        tname = b;
        fee =c;
    }
    
    public void insert_new_test() throws ClassNotFoundException{
        Statement st=null;
        ResultSet rs=null;
        
        try{
        Connection conn = MySQLConn.ConnectDB();
         
         st = conn.createStatement();
         
         String q = "insert into testcategorydetails values ('"+tid+"','"+tname+"','"+fee+"')";
         
          st.executeUpdate(q);
            
         JOptionPane.showMessageDialog(null,"New test added.");
         
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
    
    public void edit_test(){
       PreparedStatement pst = null;
       Statement st = null;

        try{
          Connection conn = MySQLConn.ConnectDB();
         
          st = conn.createStatement();
         
          String q = "update testcategorydetails "
                 + "set testTypeID='"+tid+"', typeName='"+tname+"' where testFee='"+fee+"' ";
         
        pst=conn.prepareStatement(q);
        pst.execute();
        JOptionPane.showMessageDialog(null,"Test details Updated Successfullly!");
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
}
    
}
