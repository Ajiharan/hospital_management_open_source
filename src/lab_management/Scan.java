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
public class Scan {
    String scnid="";
    String TypeOfScn_AtLab="";
    String scdate ="";
    String refPhy_AtLab="";
    String remark_AtLab="";
    String pidAtLab="";
    double payment;
    
    String name="";
    String id="";
    double f;
    
public Scan(String sId, String type, String SDate,String rPhyname, String rmk, String Pid,double pay){
    scnid =sId;
    TypeOfScn_AtLab=type;
    scdate=SDate;
    refPhy_AtLab = rPhyname;
    remark_AtLab = rmk;
    pidAtLab = Pid;
    payment = pay;
}

public Scan(String a, String b, double c){
     id=a;
     name=b;
     f=c;

}

//ADD NEW SCAN
public void add_new_scanning() throws ClassNotFoundException{
        Statement st=null;
        ResultSet rs=null;
        
        try{
        Connection conn = MySQLConn.ConnectDB();
         
         st = conn.createStatement();
         
         String q = "insert into scantypedetails values ('"+id+"','"+name+"','"+f+"')";
                 
         
          st.executeUpdate(q);
            
         JOptionPane.showMessageDialog(null,"New scan added.");
         
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
}
public void insert_new_scan() throws ClassNotFoundException{
        Statement st=null;
        ResultSet rs=null;
        
        try{
        Connection conn = MySQLConn.ConnectDB();
         
         st = conn.createStatement();
         
         String q = "insert into scan1(`scan_id`,`type_of_scan`,`scanned_date`,`ref_phy_name`,`remarks`,`pId`,`payment`)"
                 + "values ('"+scnid+"','"+TypeOfScn_AtLab+"','"+scdate+"','"+refPhy_AtLab+"','"+remark_AtLab+"','"+pidAtLab+"','"+payment+"')";
         
          st.executeUpdate(q);
            
         JOptionPane.showMessageDialog(null,"New scan details added.");
         
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
}

public void edit_scan_details(){
    PreparedStatement pst = null;
    Statement st = null;

    try{
         Connection conn = MySQLConn.ConnectDB();
         
         st = conn.createStatement();
         
         String q = "update scan1 "
                 + "set type_of_scan='"+TypeOfScn_AtLab+"', scanned_date='"+scdate+"', ref_phy_name='"+refPhy_AtLab+"',remarks='"+remark_AtLab+"', payment='"+payment+"' where scan_id='"+scnid+"' ";
         
        pst=conn.prepareStatement(q);
        pst.execute();
        JOptionPane.showMessageDialog(null,"Scan details Updated Successfullly!");
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
}

//UPDATE SCANNING
public void edit_scan(){
    PreparedStatement pst = null;
    Statement st = null;

    try{
         Connection conn = MySQLConn.ConnectDB();
         
         st = conn.createStatement();
         
         String q = "update scantypedetails "
                 + "set ScanName='"+name+"', scanFee='"+f+"' where ScanID='"+id+"' ";
         
        pst=conn.prepareStatement(q);
        pst.execute();
        JOptionPane.showMessageDialog(null,"Scan details Updated Successfullly!");
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
}


    
}
