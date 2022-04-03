/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extras;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import opd.MySQLConn;

/**
 *
 * @author Sivakumar
 */
public class TableLoadThread implements Runnable {
    private Thread t;
    private JTable table;
    private ResultSet rs;
    private PreparedStatement st;
    private String sql ;
    private Connection conn;
    
    public TableLoadThread(JTable tab, String s){
        table = tab;
        sql = "SELECT * FROM "+s+"";
        t = new Thread(this);
        t.start();
    }
    
    public TableLoadThread(JTable tab){
        table = tab;
        sql = "SELECT opdID as OPDID,title as TITLE,fname as FIRST_NAME,lname as LAST_NAME,age as AGE,contact_no as CONTACT_NO,visit_type as VISIT_TYPE,payment as PAYMENT"
                + " FROM OPD";
        t = new Thread(this);
        t.start();
    }
    
    @Override
    public void run(){
        
        conn = MySQLConn.ConnectDB();
        
        try{
            st = conn.prepareStatement(sql);
            //rs = st.executeQuery();
            
            while(!t.interrupted()){                
                rs = st.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                t.sleep(1000);
                //t.run();
            }
        }catch(InterruptedException | SQLException e){System.out.println(e);}
    }
}