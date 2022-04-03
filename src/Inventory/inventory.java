/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author Matha
 */
public class inventory {
    
    String SIIDAtInv="";
    String itmNameAtInv="";
    String CategryAtInv="";
    String itmDescriAtInv="";
    int qtyAtInv;
    double unitPurchsCostAtInv;
    double ttlAmntAtInv;
    String purchsdDateAtInv="";
 


public inventory(String ID,String iName,String cat,String iDes,int qty,double uPCost,double TAmnt,String puDate){
    SIIDAtInv = ID;
    itmNameAtInv = iName;
    CategryAtInv = cat;
    itmDescriAtInv = iDes;
    qtyAtInv = qty;
    unitPurchsCostAtInv = uPCost;
    ttlAmntAtInv = TAmnt;
    purchsdDateAtInv = puDate;
 
} 

//overload

public void insert_inventory_details() throws ClassNotFoundException  {
     Statement st=null;
     ResultSet rs=null;
     
     try{       
         Connection conn = MySQLConn.ConnectDB();
         
         st = conn.createStatement();
         
         String q = "insert into inventory2 (`IID`,`IName`,`Cat`,`IDescription`,`Quan`,`UpurchaseCost`,`TAmount`,`PuDate`)"
                   +" values('"+SIIDAtInv+"','"+itmNameAtInv+"','"+CategryAtInv+"','"+itmDescriAtInv+"','"+qtyAtInv+"','"+unitPurchsCostAtInv+"','"+ttlAmntAtInv+"','"+purchsdDateAtInv+"') ";
         
                st.executeUpdate(q);
            
                JOptionPane.showMessageDialog(null,"New Details Added!!");
     }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
     
}

public void edit_inv_details(){
    PreparedStatement pst = null;
    Statement st = null;

    try{
         Connection conn = MySQLConn.ConnectDB();
         
         st = conn.createStatement();
         
        String q = "update inventory2 set IName='"+itmNameAtInv+"',Cat='"+CategryAtInv+"', IDescription='"+itmDescriAtInv+"',Quan='"+qtyAtInv+"', UpurchaseCost='"+unitPurchsCostAtInv+"',TAmount='"+ttlAmntAtInv+"',puDate='"+purchsdDateAtInv+"' where IID='"+SIIDAtInv+"'  ";
        
        pst=conn.prepareStatement(q);
        pst.execute();
        JOptionPane.showMessageDialog(null,"Data is Updated Successfullly!");
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
}           
    
    
}
