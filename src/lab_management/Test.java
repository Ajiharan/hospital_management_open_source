/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Matha
 */
public class Test {
    int labRefNo;
    int specimenNO;
    String specimen="";
    String samplColtdDate = "";
    String refPhyName = "";
    String rstatus = "";  
    String pid = "";
    
   String tName="";
    
public Test(int labRNo,int SpNO,String speci, String colDate ,String phyName, String rs,String pID){
    labRefNo = labRNo;
    specimenNO = SpNO;
    specimen = speci;
    samplColtdDate = colDate;
    refPhyName = phyName;
    rstatus = rs; 
    pid = pID;
    }

public Test(int labRNo,String tName1){
    labRefNo = labRNo;
    tName= tName1;
}
public void insert_labtests() throws ClassNotFoundException  {
     Statement st=null;
     ResultSet rs=null;
     
     try{
         Connection conn = MySQLConn.ConnectDB();
         
         st = conn.createStatement();
         
         String q = "insert into labtests(`LAB_REF_NO`,`TEST_NAME`)"
                 + "values('"+labRefNo+"','"+tName+"')";
               
      st.executeUpdate(q);           
     // JOptionPane.showMessageDialog(null,"New tests Added for Lab Testing!!");
     }
     catch(Exception e){
         JOptionPane.showMessageDialog(null,e.toString());
     }
     
}

public void delete_tests(){
     ResultSet rs=null;
     Statement st=null;
       try{
         Connection conn = MySQLConn.ConnectDB();
         
         st = conn.createStatement();
         
         String q ="delete from labtests where LAB_REF_NO='"+labRefNo+"' and TEST_NAME='"+tName+"' ";
         
         //and TEST_NAME='"+tName+"'
               
      st.executeUpdate(q);           
     // JOptionPane.showMessageDialog(null,"New tests Added for Lab Testing!!");
     }
     catch(Exception e){
         JOptionPane.showMessageDialog(null,e.toString());
     }

}


public void insert_test_details() throws ClassNotFoundException  {
     Statement st=null;
     ResultSet rs=null;
     
     try{
         Connection conn = MySQLConn.ConnectDB();
         
         st = conn.createStatement();
         
         String q = "insert into test(`LAB_REF_NO`,`SPECIMEN_NO`,`SPECIMEN`,`SAMPLE_COL_DATE`,`REF_PHY_NAME`,`REMARKS`,`PID`)"
                 + "values('"+labRefNo+"','"+specimenNO+"','"+specimen+"','"+samplColtdDate+"','"+refPhyName+"','"+rstatus+"','"+pid+"')";
               
      st.executeUpdate(q);           
      JOptionPane.showMessageDialog(null,"New Details Added for Lab Test!!");
     }
     catch(Exception e){
         JOptionPane.showMessageDialog(null,e.toString());
     }
     
}

public void edit_test_details(){
    PreparedStatement pst = null;
    Statement st = null;

    try{
         Connection conn = MySQLConn.ConnectDB();
         
         st = conn.createStatement();
         
         String q="update test set SPECIMEN_NO=? ,SPECIMEN=?,SAMPLE_COL_DATE=?,REF_PHY_NAME=?,REMARKS=? where LAB_REF_NO=? ";
       
         pst=conn.prepareStatement(q);
        pst.setInt(1,specimenNO);
        pst.setString(2,specimen);
        pst.setString(3,samplColtdDate);
        pst.setString(4,refPhyName);
        pst.setString(5,rstatus);
        pst.setInt(6,labRefNo);
        
        pst.execute();
        JOptionPane.showMessageDialog(null,"Test Data is Updated Successfullly!");
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
    
}
}
