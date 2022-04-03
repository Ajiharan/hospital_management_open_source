/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrmanagement;

import database.DBConnection;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author DELL
 */
public class Leave extends javax.swing.JFrame {

    /**
     * Creates new form Leave
     */
     private  Connection con;
     private PreparedStatement ps;
     private Statement st;
     private ResultSet rs;
     private String id;
     private String idl;
     private String utype;
     private boolean type,reason,days,from,to,search,searchl;
     private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     
     public void setType(String uType){
         this.utype=uType;
     }
     
     
    public Leave() {
        initComponents();
        emp_id_check.setText("");
        emp_leave_id_check.setText("");
        e_from.setText("");
        e_reason.setText("");
        bt_deletel.setEnabled(false);
        bt_update1.setEnabled(false);
        bt_cancell.setEnabled(false);
        e_to.setText("");
        bt_delete.setEnabled(false);
        e_type.setText("");
        bt_apply.setEnabled(false);
        
        emp_id_error.setText("");
        txt_otid_check.setText("");
        txt_leaveid.setEnabled(false);
        bt_update.setEnabled(false);
        jButton2.setEnabled(false);
        bt_cancel.setEnabled(false);
        
        bt_applyl.setEnabled(false);
      
        txt_otid.setEnabled(false);
        
        Date mydate=new Date();
       
        date_from.setMinSelectableDate(mydate);
         date_to.setMinSelectableDate(mydate);
      
        try
        {
            con=DBConnection.ConnectionDB();
            //JOptionPane.showMessageDialog(null,"connected with "+con.toString());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"not connect to server and message is"+e.getMessage());
        }
        
        String sql = "SELECT * FROM empleave";
 

        try
        {
            
            st = con.createStatement();
            rs = st.executeQuery(sql);
 
            int rows=0;
            while(rs.next())
            {
                rows++;
                String idd = rs.getString(1);
                id="EL"+(Integer.parseInt(idd.substring(2,9))+1);
            }
            if(rows<1)
            {
                id="EL1111111";
            }
        }
        catch(SQLException ex)
        {
            id="EL1111111";
        }
        
        finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        lid.setText(id);
        
        
        
        
        String sql2 = "SELECT * FROM othours";
 

        try
        {
            
            st = con.createStatement();
            rs = st.executeQuery(sql2);
 
            int rows=0;
            while(rs.next())
            {
                rows++;
                String idd = rs.getString(1);
                idl="OT"+(Integer.parseInt(idd.substring(2,9))+1);
            }
            if(rows<1)
            {
                idl="OT1111111";
            }
        }
        catch(SQLException ex)
        {
            idl="OT1111111";
        }
        finally{
            try {
                st.close();
                  rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
        otid.setText(idl);
        
        e_othours.setText("");
        e_amount.setText("");
        
        
        try
        {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM `empleave`");
            
            leave.setModel(DbUtils.resultSetToTableModel(rs));
 
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
         try
        {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM `othours`");
            
            ott.setModel(DbUtils.resultSetToTableModel(rs));
 
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
            }
             
         }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        emp_idl = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        la_namel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        la_designationl = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        la_contactnumberl = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        la_emaill = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_amount = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        bt_applyl = new javax.swing.JButton();
        bt_cancell = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        otid = new javax.swing.JLabel();
        e_othours = new javax.swing.JLabel();
        e_amount = new javax.swing.JLabel();
        bt_update1 = new javax.swing.JButton();
        bt_deletel = new javax.swing.JButton();
        txt_otid = new javax.swing.JTextField();
        s_ot = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        ott = new javax.swing.JTable();
        bt_edit1 = new javax.swing.JButton();
        txt_otid_check = new javax.swing.JLabel();
        emp_id_error = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        emp_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        la_name = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        la_designation = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        la_contactnumber = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        la_email = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        s_leave = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txt_reason = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_day = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        bt_apply = new javax.swing.JButton();
        bt_cancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lid = new javax.swing.JLabel();
        e_type = new javax.swing.JLabel();
        e_reason = new javax.swing.JLabel();
        e_from = new javax.swing.JLabel();
        e_to = new javax.swing.JLabel();
        bt_update = new javax.swing.JButton();
        bt_delete = new javax.swing.JButton();
        txt_leaveid = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        leave = new javax.swing.JTable();
        date_from = new com.toedter.calendar.JDateChooser();
        date_to = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        emp_id_check = new javax.swing.JLabel();
        emp_leave_id_check = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Leave & OverTime Details");

        jButton1.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(300, 300, 300)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane1.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N

        emp_idl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emp_idlKeyReleased(evt);
            }
        });

        jLabel6.setText("Employee ID");

        jLabel8.setText("Emp Name");

        la_namel.setText("Emp Name");

        jLabel10.setText("Emp Designation");

        la_designationl.setText("Emp Designation");

        jLabel17.setText("Emp Contact Number");

        la_contactnumberl.setText("Emp Contact Number");

        jLabel18.setText("Emp email");

        la_emaill.setText("Emp mail");

        jLabel19.setText("OT Hours");

        jLabel20.setText("Amount for hour");

        txt_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_amountKeyReleased(evt);
            }
        });

        jLabel22.setText("Total");

        txt_total.setEditable(false);
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });

        bt_applyl.setText("Apply");
        bt_applyl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_applylActionPerformed(evt);
            }
        });

        bt_cancell.setText("Cancel");
        bt_cancell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancellActionPerformed(evt);
            }
        });

        jLabel25.setText("OT ID");

        otid.setText("OT ID");

        e_othours.setForeground(new java.awt.Color(255, 0, 0));
        e_othours.setText("ERROR");

        e_amount.setForeground(new java.awt.Color(255, 0, 0));
        e_amount.setText("ERROR");

        bt_update1.setText("Update");
        bt_update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_update1ActionPerformed(evt);
            }
        });

        bt_deletel.setText("Delete");
        bt_deletel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_deletelActionPerformed(evt);
            }
        });

        txt_otid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_otidFocusLost(evt);
            }
        });
        txt_otid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_otidKeyReleased(evt);
            }
        });

        s_ot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "1", "2", "3", "4", "5", "6", "7", "8" }));

        ott.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(ott);

        bt_edit1.setText("Edit");
        bt_edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_edit1ActionPerformed(evt);
            }
        });

        txt_otid_check.setForeground(new java.awt.Color(255, 0, 0));
        txt_otid_check.setText("Error");

        emp_id_error.setForeground(new java.awt.Color(255, 0, 0));
        emp_id_error.setText("Error");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 51, 255));
        jLabel23.setText("ENTER OTID");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22)
                    .addComponent(jLabel25))
                .addGap(137, 137, 137)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(379, 379, 379)
                                .addComponent(bt_update1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(emp_id_error, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_applyl, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_deletel, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(bt_cancell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_edit1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(la_namel)
                                    .addComponent(emp_idl, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(la_designationl)
                                    .addComponent(la_contactnumberl)
                                    .addComponent(la_emaill)
                                    .addComponent(otid)
                                    .addComponent(s_ot, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(e_othours, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                    .addComponent(e_amount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_otid, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_otid_check, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(379, 379, 379)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emp_idl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(bt_applyl, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_deletel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emp_id_error)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(la_namel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(la_designationl))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(la_contactnumberl)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_update1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_cancell, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_edit1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(la_emaill))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(otid))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(s_ot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(e_othours))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(8, 8, 8)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(e_amount)
                        .addComponent(txt_otid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(txt_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(txt_otid_check))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );

        jTabbedPane1.addTab("OT", jPanel4);

        emp_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emp_idFocusLost(evt);
            }
        });
        emp_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_idActionPerformed(evt);
            }
        });
        emp_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emp_idKeyReleased(evt);
            }
        });

        jLabel2.setText("Employee ID");

        jLabel3.setText("Emp Name");

        la_name.setText("Emp Name");

        jLabel5.setText("Emp Designation");

        la_designation.setText("Emp Designation");

        jLabel7.setText("Emp Contact Number");

        la_contactnumber.setText("Emp Contact Number");

        jLabel9.setText("Emp email");

        la_email.setText("Emp mail");

        jLabel11.setText("Leave Type");

        s_leave.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Absent", "Sickness", "Casual", "Matenity Leave", "no pay", "half leave", "short leave", "others" }));

        jLabel12.setText("Reason");

        jLabel13.setText("No of Days");

        txt_day.setEditable(false);

        jLabel14.setText("Leave ");

        jLabel15.setText("from");

        jLabel16.setText("To");

        bt_apply.setText("Apply");
        bt_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_applyActionPerformed(evt);
            }
        });

        bt_cancel.setText("Cancel");
        bt_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelActionPerformed(evt);
            }
        });

        jLabel4.setText("Leave ID");

        lid.setText("Leave ID");

        e_type.setForeground(new java.awt.Color(255, 0, 0));
        e_type.setText("ERROR");

        e_reason.setForeground(new java.awt.Color(255, 0, 0));
        e_reason.setText("ERROR");

        e_from.setForeground(new java.awt.Color(255, 0, 0));
        e_from.setText("ERROR");

        e_to.setForeground(new java.awt.Color(255, 0, 0));
        e_to.setText("ERROR");

        bt_update.setText("Update");
        bt_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_updateActionPerformed(evt);
            }
        });

        bt_delete.setText("Delete");
        bt_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_deleteActionPerformed(evt);
            }
        });

        txt_leaveid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_leaveidFocusLost(evt);
            }
        });
        txt_leaveid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_leaveidKeyReleased(evt);
            }
        });

        leave.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(leave);

        jButton2.setText("Report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        bt_edit.setText("Edit");
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 255));
        jLabel21.setText("Enter Leave id");

        emp_id_check.setForeground(new java.awt.Color(204, 0, 0));
        emp_id_check.setText("ERROR");

        emp_leave_id_check.setForeground(new java.awt.Color(204, 0, 0));
        emp_leave_id_check.setText("Error");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(86, 86, 86)
                        .addComponent(jLabel15))
                    .addComponent(jLabel4))
                .addGap(93, 93, 93)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(la_email, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(la_contactnumber, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(la_designation, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(la_name, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lid, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bt_apply, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bt_delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(bt_update, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bt_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_leaveid, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emp_leave_id_check, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(328, 328, 328))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(date_from, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(e_from)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                            .addComponent(jLabel16))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txt_day, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                                .addComponent(txt_reason, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(e_type)
                                                .addComponent(e_reason))))
                                    .addComponent(s_leave, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addComponent(date_to, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(e_to)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emp_id, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(emp_id_check, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 271, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emp_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_apply, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_update, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(1, 1, 1)
                .addComponent(emp_id_check)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(bt_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(la_name))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(la_designation))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(la_contactnumber))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(la_email))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lid))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(s_leave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(e_type)))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txt_reason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e_reason))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(txt_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(txt_leaveid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(date_from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(date_to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(e_from)))
                            .addComponent(e_to)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emp_leave_id_check)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jTabbedPane1.addTab("Leave", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_deletelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deletelActionPerformed
        // TODO add your handling code here:
     
        bt_applyl.setEnabled(false);
        bt_deletel.setEnabled(true);
      
        txt_otid.setEnabled(true);
        bt_update1.setEnabled(false);

        if(searchl)
        {
            try
            {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(this, "Your Message", "Title on Box", dialogButton);
                if(dialogResult == 0)
                {
                    String sql2 = "DELETE FROM `othours` WHERE `id`='"+txt_otid.getText()+"'";
                     ps = con.prepareStatement(sql2);
                    int rowsDeleted1 = ps.executeUpdate();

                    if ( rowsDeleted1>0)
                    {
                        searchl=false;
                        JOptionPane.showMessageDialog(null,"A OT was deleted successfully!");

                        try
                        {
                            Statement stat1 = con.createStatement();
                            ResultSet res1 = stat1.executeQuery("SELECT * FROM `othours`");
            
                            ott.setModel(DbUtils.resultSetToTableModel(res1));
 
                        }
                        catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        
                        String sql1="Select * from empleave";
                        Statement statement1 = con.createStatement();
                        ResultSet result = statement1.executeQuery(sql1);

                        int rows=0;
                        while(result.next())
                        {
                            rows++;
                            String idd = result.getString(1);
                            idl="OT"+(Integer.parseInt(idd.substring(2,9))+1);
                        }
                        if(rows<1)
                        {
                            idl="OT1111111";
                        }
                        otid.setText(idl);

                        bt_applyl.setEnabled(false);

                        txt_otid.setText("");

                        la_namel.setText("");
                        la_designationl.setText("");
                        la_contactnumberl.setText("");
                        la_emaill.setText("");
                        emp_idl.setText("");

                        s_ot.setSelectedIndex(0);
                        txt_amount.setText("");
                        txt_total.setText("");
                        emp_idl.setText("");

                       
                     
                      
                         bt_deletel.setEnabled(false);
                         bt_update1.setEnabled(false);

                    }

                }
                else
                {
                    searchl=false;
                    JOptionPane.showMessageDialog(null,"Your Delete Option was Cancelled");
                    
                    bt_applyl.setEnabled(false);

                    txt_otid.setText("");

                    la_namel.setText("");
                    la_designationl.setText("");
                    la_contactnumberl.setText("");
                    la_emaill.setText("");
                    emp_idl.setText("");

                    s_ot.setSelectedIndex(0);
                    txt_amount.setText("");
                    txt_total.setText("");
                    emp_idl.setText("");

                    txt_otid.setEnabled(false);
                   
                 
               
                    bt_deletel.setEnabled(true);
                    bt_update1.setEnabled(true);
                    String sql1 = "SELECT * FROM othours";

                    try
                    {
                        Statement statement1 = con.createStatement();
                        ResultSet result = statement1.executeQuery(sql1);

                        int rows=0;
                        while(result.next())
                        {
                            rows++;
                            String idd = result.getString(1);
                            idl="OT"+(Integer.parseInt(idd.substring(2,9))+1);
                        }
                        if(rows<1)
                        {
                            idl="OT1111111";
                        }

                    }
                    catch(SQLException ex)
                    {
                        idl="OT1111111";
                    }
                    otid.setText(idl);
                }

            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bt_deletelActionPerformed

    private void bt_update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_update1ActionPerformed
        // TODO add your handling code here:
        
        double total=0;
        int ot=0;
        boolean output=false;
        int a;
        try
        {
            ot=Integer.parseInt(txt_amount.getText());
            output=true;
        }
        catch(NumberFormatException ex)
        {
            e_amount.setText("Input OT amount");
            output=false;
        }
        
        
      
        bt_applyl.setEnabled(false);
        bt_deletel.setEnabled(false);
        
     
        txt_otid.setEnabled(true);
        
        if(searchl)
        {
            
            if(s_ot.getSelectedIndex()<1 || txt_amount.getText().equals("") || output==false)
            {
                if(s_ot.getSelectedIndex()<1)
                {
                    e_othours.setText("Selece Employees' OT hours");
                }
                else
                {
                    e_othours.setText("");
                }
                if(txt_amount.getText().equals(""))
                {
                e_amount.setText("Input OT amount");
                }
                else
                {
                    e_amount.setText("");
                }
            
                JOptionPane.showMessageDialog(null, "Input all values");
            }
            else
            {
                try
                {

                    String sql = "UPDATE  `othours` set `othours`=?,`otamount`=?,`total`=? WHERE `id`='"+txt_otid.getText()+"'";

                    ps = con.prepareStatement(sql);
                    ps.setString(1, s_ot.getSelectedItem().toString());
                     ps.setString(2, txt_amount.getText().toString());
                    total=Integer.parseInt((String)s_ot.getSelectedItem())*ot;
                     ps.setString(3, ""+total);
                    int rowsupdate =  ps.executeUpdate();
                    if (rowsupdate > 0)
                    {
                        searchl=false;
                        JOptionPane.showMessageDialog(null,"The  OT was updated successfully!");

                        try
                        {
                            Statement stat1 = con.createStatement();
                            ResultSet res1 = stat1.executeQuery("SELECT * FROM `othours`");
            
                            ott.setModel(DbUtils.resultSetToTableModel(res1));
 
                        }
                        catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        String sql1 = "SELECT * FROM othours";

                        try
                        {
                            Statement statement1 = con.createStatement();
                            ResultSet result = statement1.executeQuery(sql1);

                            int rows=0;
                            while(result.next())
                            {
                                rows++;
                                String idd = result.getString(1);
                                idl="OT"+(Integer.parseInt(idd.substring(2,9))+1);
                            }
                            if(rows<1)
                            {
                                idl="OT1111111";
                            }
                            otid.setText(idl);

                            la_namel.setText("");
                            la_designationl.setText("");
                            la_contactnumberl.setText("");
                            la_emaill.setText("");
                            emp_idl.setText("");
                    
                            txt_amount.setText("");
                            txt_total.setText("");
                            s_ot.setSelectedIndex(0);
                            bt_applyl.setEnabled(false);
                            bt_deletel.setEnabled(false);
                            bt_update1.setEnabled(false);
                        
                            
                            txt_otid.setText("");
                       
                           
                        }
                        catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }

                    }

                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                finally{
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else
        {
            la_namel.setText("");
            la_designationl.setText("");
            la_contactnumberl.setText("");
            la_emaill.setText("");
            emp_idl.setText("");        
            txt_amount.setText("");
            txt_total.setText("");
            s_ot.setSelectedIndex(0);
            bt_applyl.setEnabled(false);
        }
        
    }//GEN-LAST:event_bt_update1ActionPerformed

    private void bt_cancellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancellActionPerformed
        // TODO add your handling code here:
        searchl = false;
        emp_idl.setText("");
        bt_applyl.setEnabled(true);
        bt_cancell.setEnabled(false);
        bt_edit1.setEnabled(true);
        bt_update1.setEnabled(false);
        bt_deletel.setEnabled(false);
        txt_otid.setEnabled(false);
        bt_applyl.setEnabled(false);
        la_namel.setText("");
        la_designationl.setText("");
        la_contactnumberl.setText("");
        la_emaill.setText("");
        emp_idl.setText("");
        txt_otid.setText("");
        txt_amount.setText("");
        txt_total.setText("");
        s_ot.setSelectedIndex(0);
        
        String sql2 = "SELECT * FROM othours";
 

        try
        {
            
           st = con.createStatement();
            rs = st.executeQuery(sql2);
 
            int rows=0;
            while(rs.next())
            {
                rows++;
                String idd = rs.getString(1);
                idl="OT"+(Integer.parseInt(idd.substring(2,9))+1);
            }
            if(rows<1)
            {
                idl="OT1111111";
            }
        }
        catch(SQLException ex)
        {
            idl="OT1111111";
        }
        finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        otid.setText(idl);
        
        
    }//GEN-LAST:event_bt_cancellActionPerformed

    private void bt_applylActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_applylActionPerformed
        // TODO add your handling code here:
        double total=0;
        int ot=0;
        boolean output=false;
        int a;
        try
        {
            ot=Integer.parseInt(txt_amount.getText());
            output=true;
        }
        catch(NumberFormatException ex)
        {
            e_amount.setText("Input OT amount");
            output=false;
        }
        if(s_ot.getSelectedIndex()<1 || txt_amount.getText().equals("") || output==false)
        {
            if(s_ot.getSelectedIndex()<1)
            {
                e_othours.setText("Select Employees' OT hours");
            }
            else
            {
                e_othours.setText("");
            }
            if(txt_amount.getText().equals(""))
            {
               e_amount.setText("Input OT amount");
            }
            else
            {
                e_amount.setText("");
            }
            
            JOptionPane.showMessageDialog(null, "Input all values");
        }
        else
        {
                total=ot*Integer.parseInt((String) s_ot.getSelectedItem());
                //JOptionPane.showMessageDialog(null, ""+total);
                txt_total.setText(""+total);
            
            try
            {
                String sql = "INSERT INTO `othours` (`id`,`othours`,`otamount`,`total`,`empidot`) VALUES (?, ?, ?, ?, ?)";

                ps = con.prepareStatement(sql);
                ps.setString(1, idl);
                ps.setString(2,s_ot.getSelectedItem().toString());
                ps.setString(3,txt_amount.getText().toString());
                ps.setString(4,""+total);
                ps.setString(5, emp_idl.getText().toString());

                int rowsInserted = ps.executeUpdate();

                if (rowsInserted > 0)
                {
                    JOptionPane.showMessageDialog(null,"A new OT was inserted successfully!");

              
        
                try
                {
                    Statement stat1 = con.createStatement();
                    ResultSet res1 = stat1.executeQuery("SELECT * FROM `othours`");
            
                    ott.setModel(DbUtils.resultSetToTableModel(res1));
 
                }
                catch(Exception ex)
                {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                    String sql1 = "SELECT * FROM othours";

                    try
                    {

                        Statement statement1 = con.createStatement();
                        ResultSet result = statement1.executeQuery(sql1);

                        int rows=0;
                        while(result.next())
                        {
                            rows++;
                            String idd = result.getString(1);
                            idl="OT"+(Integer.parseInt(idd.substring(2,9))+1);
                        }
                        if(rows<1)
                        {
                            idl="OT1111111";
                        }
                    }
                    catch(SQLException ex)
                    {
                        idl="OT1111111";
                    }
                    otid.setText(idl);
                    
                    la_namel.setText("");
                    la_designationl.setText("");
                    la_contactnumberl.setText("");
                    la_emaill.setText("");
                    emp_idl.setText("");
                    
                    txt_amount.setText("");
                    txt_total.setText("");
                    s_ot.setSelectedIndex(0);
                    bt_applyl.setEnabled(false);
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            finally{
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
            
    }//GEN-LAST:event_bt_applylActionPerformed

    private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed
        // TODO add your handling code here:
    

        if(search)
        {
            try
            {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure want to Delete", "Delete Option", dialogButton);
                if(dialogResult == 0)
                {
                    String sql2 = "DELETE FROM `empleave` WHERE `leveid`='"+txt_leaveid.getText()+"'";
                    ps = con.prepareStatement(sql2);
                    int rowsDeleted1 = ps.executeUpdate();

                    if ( rowsDeleted1>0)
                    {
                        search=false;
                        JOptionPane.showMessageDialog(null,"A Leave was deleted successfully!");
                      
                        try
                        {
                            st = con.createStatement();
                            rs = st.executeQuery("SELECT * FROM `empleave`");
            
                            leave.setModel(DbUtils.resultSetToTableModel(rs));
 
                        }
                        catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        finally{
                            st.close();
                            rs.close();
                        }
        
                        String sql1="Select * from empleave";
                        st = con.createStatement();
                        rs = st.executeQuery(sql1);

                        int rows=0;
                        while(rs.next())
                        {
                            rows++;
                            String idd = rs.getString(1);
                            id="EL"+(Integer.parseInt(idd.substring(2,9))+1);
                        }
                        st.close();
                        rs.close();
                        if(rows<1)
                        {
                            id="EL1111111";
                        }
                        lid.setText(id);

                        txt_leaveid.setText("");
                       
                        e_from.setText("");
                        e_reason.setText("");
                        e_to.setText("");
                        e_type.setText("");
                        bt_apply.setEnabled(false);

                        la_name.setText("");
                        la_designation.setText("");
                        la_contactnumber.setText("");
                        la_email.setText("");
                        emp_id.setText("");

                        s_leave.setSelectedIndex(0);
                        txt_reason.setText("");
                        txt_day.setText("");
                        date_from.setDate(null);
                        date_to.setDate(null);
                        emp_id.setText("");

                        txt_leaveid.setText("");
                        
                         bt_apply.setEnabled(false);
                         bt_delete.setEnabled(false);
                      
                         txt_leaveid.setEnabled(true);
                         bt_update.setEnabled(false);

                    }

                }
                else
                {
                    search=false;
                    JOptionPane.showMessageDialog(null,"Your Delete Option was Cancelled");
                   
                    e_from.setText("");
                    e_reason.setText("");
                    e_to.setText("");
                    e_type.setText("");
                    bt_apply.setEnabled(false);

                    txt_leaveid.setText("");

                    la_name.setText("");
                    la_designation.setText("");
                    la_contactnumber.setText("");
                    la_email.setText("");
                    emp_id.setText("");

                    s_leave.setSelectedIndex(0);
                    txt_reason.setText("");
                    txt_day.setText("");
                    date_from.setDate(null);
                    date_to.setDate(null);
                    emp_id.setText("");

                    txt_leaveid.setEnabled(false);
                  
                 
                    bt_delete.setEnabled(true);
                    bt_update.setEnabled(true);
                    String sql1 = "SELECT * FROM empleave";

                    try
                    {
                        st = con.createStatement();
                        rs = st.executeQuery(sql1);

                        int rows=0;
                        while(rs.next())
                        {
                            rows++;
                            String idd = rs.getString(1);
                            id="EL"+(Integer.parseInt(idd.substring(2,9))+1);
                        }
                        if(rows<1)
                        {
                            id="EL1111111";
                        }

                    }
                    catch(SQLException ex)
                    {
                        id="EM1111111";
                    }
                    finally{
                        rs.close();
                        st.close();
                    }
                    lid.setText(id);
                }

            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bt_deleteActionPerformed

    private void bt_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_updateActionPerformed
        // TODO add your handling co
      
        check();
        if(search)
        {
            if(type && reason && from && to)
            {
                try
                {

                    Date dateBefore =formatter.parse(formatter.format(date_from.getDate()));
                    Date dateAfter = formatter.parse(formatter.format(date_to.getDate()));
                    long difference = dateAfter.getTime() - dateBefore.getTime();
                    long daysBetween = (difference / (1000*60*60*24));
                    String sql = "UPDATE  `empleave` set `type`=?,`reason`=?,`days`=?,`from`=?,`to`=? WHERE `leveid`='"+lid.getText()+"'";

                    ps = con.prepareStatement(sql);
                    ps.setString(1, s_leave.getSelectedItem().toString());
                    ps.setString(2, txt_reason.getText());
                     ps.setString(3, String.valueOf(daysBetween));
                    ps.setString(4, formatter.format(date_from.getDate()));
                   ps.setString(5, formatter.format(date_to.getDate()));

                    int rowsupdate = ps.executeUpdate();
                    if (rowsupdate > 0)
                    {
                        search=false;
                        JOptionPane.showMessageDialog(null,"The  leave was updated successfully!");
                           
                        try
                        {
                            st = con.createStatement();
                            rs= st.executeQuery("SELECT * FROM `empleave`");
            
                            leave.setModel(DbUtils.resultSetToTableModel(rs));
 
                        }
                        catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        finally{
                            st.close();
                            rs.close();
                        }
        
                        
                        String sql1 = "SELECT * FROM empleave";

                        try
                        {
                            st = con.createStatement();
                            rs = st.executeQuery(sql1);

                            int rows=0;
                            while(rs.next())
                            {
                                rows++;
                                String idd = rs.getString(1);
                                id="EL"+(Integer.parseInt(idd.substring(2,9))+1);
                            }
                            if(rows<1)
                            {
                                id="EL1111111";
                            }
                            lid.setText(id);

                           
                            e_from.setText("");
                            e_reason.setText("");
                            e_to.setText("");
                            e_type.setText("");
                            bt_apply.setEnabled(false);

                            la_name.setText("");
                            la_designation.setText("");
                            la_contactnumber.setText("");
                            la_email.setText("");
                            emp_id.setText("");

                            s_leave.setSelectedIndex(0);
                            txt_reason.setText("");
                            txt_day.setText("");
                            date_from.setDate(null);
                            date_to.setDate(null);
                            emp_id.setText("");

                            txt_leaveid.setText("");
                            
                            bt_update.setEnabled(false);
                         
                            bt_delete.setEnabled(false);
                           
                        }
                        catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        finally{
                            st.close();
                            rs.close();
                        }

                    }

                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                finally{
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Check all inputs");
            }
        }
        else
        {
            bt_apply.setEnabled(false);

        
            e_from.setText("");
            e_reason.setText("");
            e_to.setText("");
            e_type.setText("");
            bt_apply.setEnabled(false);

            la_name.setText("");
            la_designation.setText("");
            la_contactnumber.setText("");
            la_email.setText("");
            emp_id.setText("");

            s_leave.setSelectedIndex(0);
            txt_reason.setText("");
            txt_day.setText("");
            date_from.setDate(null);
            date_to.setDate(null);
            emp_id.setText("");

        }
    }//GEN-LAST:event_bt_updateActionPerformed

    private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
        // TODO add your handling code here:
        search=false;
        bt_update.setEnabled(false);
   
         txt_leaveid.setText("");
         bt_edit.setEnabled(true);
      
        txt_leaveid.setEnabled(false);
         bt_cancel.setEnabled(false);
         bt_delete.setEnabled(false);

         
       

    
        e_from.setText("");
        e_reason.setText("");
        e_to.setText("");
        e_type.setText("");

        la_name.setText("");
        la_designation.setText("");
        la_contactnumber.setText("");
        la_email.setText("");
        emp_id.setText("");

        s_leave.setSelectedIndex(0);
        txt_reason.setText("");
        txt_day.setText("");
        date_from.setDate(null);
        date_to.setDate(null);
        emp_id.setText("");

        String sql = "SELECT * FROM empleave";

        try
        {

            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int rows=0;
            while(result.next())
            {
                rows++;
                String idd = result.getString(1);
                id="EL"+(Integer.parseInt(idd.substring(2,9))+1);
            }
            if(rows<1)
            {
                id="EL1111111";
            }
        }
        catch(SQLException ex)
        {
            id="EL1111111";
        }
        lid.setText(id);
    }//GEN-LAST:event_bt_cancelActionPerformed

    private void bt_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_applyActionPerformed
        // TODO add your handling code here:
        check();

        if(type && reason && from && to)
        {
            try
            {
                  Date dateBefore =formatter.parse(formatter.format(date_from.getDate()));
	       Date dateAfter = formatter.parse(formatter.format(date_to.getDate()));
	       long difference = dateAfter.getTime() - dateBefore.getTime();
                long daysBetween = (difference / (1000*60*60*24));
                String sql = "INSERT INTO `empleave` (`leveid`,`type`,`reason`,`days`,`from`,`to`,`empid`) VALUES (?, ?, ?, ?, ?, ?, ?)";

                 ps= con.prepareStatement(sql);
                  ps.setString(1, id);
                 ps.setString(2,s_leave.getSelectedItem().toString());
                 ps.setString(3,txt_reason.getText().toString());
                 ps.setString(4,String.valueOf(daysBetween));
                 ps.setString(5, formatter.format(date_from.getDate()).toString());
                 ps.setString(6, formatter.format(date_to.getDate()).toString());
                 ps.setString(7, emp_id.getText().toString());

                int rowsInserted =  ps.executeUpdate();

                if (rowsInserted > 0)
                {
                    JOptionPane.showMessageDialog(null,"A new leave was inserted successfully!");
                    try
                    {
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM `empleave`");
            
                        leave.setModel(DbUtils.resultSetToTableModel(rs));
 
                    }
                    catch(SQLException ex)
                    {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    finally{
                        st.close();
                        rs.close();
                    }
        

                    String sql1 = "SELECT * FROM empleave";

                    try
                    {

                        st = con.createStatement();
                        rs = st.executeQuery(sql1);

                        int rows=0;
                        while(rs.next())
                        {
                            rows++;
                            String idd = rs.getString(1);
                            id="EL"+(Integer.parseInt(idd.substring(2,9))+1);
                        }
                        if(rows<1)
                        {
                            id="EL1111111";
                        }
                    }
                    catch(SQLException ex)

                    {
                        id="EL1111111";
                    }
                    finally{
                        rs.close();
                        st.close();
                    }
                    lid.setText(id);

              
                    e_from.setText("");
                    e_reason.setText("");
                    e_to.setText("");
                    e_type.setText("");
                    bt_apply.setEnabled(false);

                    la_name.setText("");
                    la_designation.setText("");
                    la_contactnumber.setText("");
                    la_email.setText("");
                    emp_id.setText("");

                    s_leave.setSelectedIndex(0);
                    txt_reason.setText("");
                    txt_day.setText("");
                    date_from.setDate(null);
                    date_to.setDate(null);
                    emp_id.setText("");

                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            finally{
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Check all inputs");
        }

    }//GEN-LAST:event_bt_applyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         this.setVisible(false);
        OverView view=new OverView();
        view.setType(utype);
        view.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

       /* try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sulaiman", "root", "thilak");
            String path= "C:\\Users\\USER\\Desktop\\Desktop Folders\\ITP_SLIIT\\MY ITP\\After prototype\\Leave.jrxml";

            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            JasperViewer.viewReport(jp,false);
        }
        catch (ClassNotFoundException ex) {

        } catch (JRException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }//GEN-LAST:event_jButton2ActionPerformed

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        bt_apply.setEnabled(false);
        bt_delete.setEnabled(false);
       
        txt_leaveid.setEnabled(true);
        
         bt_cancel.setEnabled(true);
      
        bt_edit.setEnabled(false);
    }//GEN-LAST:event_bt_editActionPerformed

    private void txt_leaveidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_leaveidKeyReleased
         
        
     String sqlcheck="select * from empleave where leveid='"+txt_leaveid.getText()+"'";
         boolean isCorrect=false;
        try{
           
            st=con.createStatement();
            rs=st.executeQuery(sqlcheck);
            
            while(rs.next()){
                isCorrect=true;
            }
        }
        catch(SQLException ex){
          
            JOptionPane.showMessageDialog(null, ex);
        }
        
        if(txt_leaveid.getText().isEmpty()){
            emp_leave_id_check.setText("some fields are empty");
        }
         else if(!isCorrect){
             emp_leave_id_check.setText("leave id not found");
        }
        else{
            emp_leave_id_check.setText("");
        }
        
        String sql1 = "SELECT * FROM empleave where `leveid`='"+txt_leaveid.getText()+"'";
            try
            {

                st = con.createStatement();
                rs = st.executeQuery(sql1);

                int count = 0;
                if (rs.next())
                {
                    search=true;
                    lid.setText(rs.getString(1));
                    txt_reason.setText(rs.getString(3));
                    if(rs.getString(2).equals("Absent"))
                    {
                        s_leave.setSelectedIndex(1);
                    }
                    else if(rs.getString(2).equals("Sickness"))
                    {
                        s_leave.setSelectedIndex(2);
                    }
                    else if(rs.getString(2).equals("Casual"))
                    {
                        s_leave.setSelectedIndex(3);
                    }
                    else if(rs.getString(2).equals("Matenity Leave"))
                    {
                        s_leave.setSelectedIndex(4);
                    }
                    else if(rs.getString(2).equals("others"))
                    {
                        s_leave.setSelectedIndex(5);
                    }
                    txt_day.setText(rs.getString(4));
                    Date dateValue=null;
                    SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy");
                    try
                    {
                        dateValue = date1.parse(rs.getString(5).toString());
                        date_from.setDate(dateValue);
                    } 
                    catch (ParseException ex) 
                    {
                    }
                    
                    Date dateValue1=null;
                    SimpleDateFormat date2 = new SimpleDateFormat("dd/MM/yyyy");
                    try
                    {
                        dateValue1 = date2.parse(rs.getString(6));
                        date_to.setDate(dateValue1);
                    } 
                    catch (ParseException ex) 
                    {
                    }
                    

                    String sql2 = "SELECT * FROM employee where `idEmployee`='"+rs.getString(7)+"'";
                    try
                    {
                            rs.close();
                            st.close();
                            st = con.createStatement();
                            rs = st.executeQuery(sql2);

                        int count1 = 0;
                        if (rs.next())
                        {
                            search=true;
                            emp_id.setText(rs.getString(1));
                            la_name.setText(rs.getString(2)+" "+rs.getString(3));
                            la_designation.setText(rs.getString(18));
                            la_contactnumber.setText(rs.getString(8));
                            la_email.setText(rs.getString(9));
                            bt_delete.setEnabled(true);
                            bt_update.setEnabled(true);
                        }

                    }
                    catch(SQLException ex)
                    {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    finally{
                        rs.close();
                        st.close();
                    }

                }
                else
                {
                    
                  
                  
              
                    e_from.setText("");
                    e_reason.setText("");
                    e_to.setText("");
                    e_type.setText("");

                    la_name.setText("");
                    la_designation.setText("");
                    la_contactnumber.setText("");
                    la_email.setText("");
                    emp_id.setText("");

                    s_leave.setSelectedIndex(0);
                    txt_reason.setText("");
                    bt_delete.setEnabled(false);
                    bt_update.setEnabled(false);
                    txt_day.setText("");
                    date_from.setDate(null);
                    date_to.setDate(null);
                    emp_id.setText("");
                }
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

    }//GEN-LAST:event_txt_leaveidKeyReleased

    private void emp_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_idKeyReleased
        
             String sql="select * from employee where idEmployee='"+emp_id.getText()+"'";
     boolean isAvailable=false;
         try {
             st=con.createStatement();
             rs=st.executeQuery(sql);
             
             while(rs.next()){
                 isAvailable=true;
             }
         } catch (SQLException ex) {
             Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
         try {
             st.close();
              rs.close();
         } catch (SQLException ex) {
             Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
         }
            
         }
     
       if(emp_id .getText().isEmpty()){
          emp_id_check.setText("field is empty");
        }
       else if(isAvailable){
            emp_id_check.setText("");
       }
       else{
           emp_id_check.setText("invalid employee id"); 
       }
        
        String id=emp_id.getText().toString();
      
      
            String sql1 = "SELECT * FROM employee where `idEmployee`='"+emp_id.getText()+"'";
            try
            {

                st = con.createStatement();
                rs = st.executeQuery(sql1);

                //int count = 0;
                if (rs.next())
                {
                       
                        la_name.setText(rs.getString(2)+" "+rs.getString(3));
                        la_designation.setText(rs.getString(18));
                        la_contactnumber.setText(rs.getString(8));
                        la_email.setText(rs.getString(9));
                        bt_apply.setEnabled(true);
                    
                    
                    
                    if(rs.getString("employee").equals("Visiting"))
                    {
                        JOptionPane.showMessageDialog(null, "You can't add Leave for visiting doctors");
                        emp_id.setText(null);
                        la_name.setText("");
                        la_designation.setText("");
                        la_contactnumber.setText("");
                        la_email.setText("");
                        bt_apply.setEnabled(false);
                        
               
                    }
                                         
                    
                  }
                    else
                     {
                           
                        e_from.setText("");
                        e_reason.setText("");
                        e_to.setText("");
                        e_type.setText("");
                        bt_apply.setEnabled(false);
                        
                        la_name.setText("");
                        la_designation.setText("");
                        la_contactnumber.setText("");
                        la_email.setText("");
                       
                     }
                }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
        
    }//GEN-LAST:event_emp_idKeyReleased

    private void emp_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emp_idFocusLost
     
      
     String sql="select * from employee where idEmployee='"+emp_id.getText()+"'";
     boolean isAvailable=false;
         try {
             st=con.createStatement();
             rs=st.executeQuery(sql);
             
             while(rs.next()){
                 isAvailable=true;
             }
         } catch (SQLException ex) {
             Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
            try {
                st.close();
                 rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         }
     
       if(emp_id .getText().isEmpty()){
          emp_id_check.setText("field is empty");
        }
       else if(isAvailable){
            emp_id_check.setText("");
       }
       else{
           emp_id_check.setText("invalid employee id"); 
       }
      
    }//GEN-LAST:event_emp_idFocusLost

    private void emp_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emp_idActionPerformed

    private void txt_leaveidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_leaveidFocusLost
         String sqlcheck="select * from empleave where leveid='"+txt_leaveid.getText()+"'";
         boolean isCorrects=false;
        try{
           
            st=con.createStatement();
            rs=st.executeQuery(sqlcheck);
            
            while(rs.next()){
                isCorrects=true;
            }
            System.out.println(isCorrects);
        }
        
        catch(SQLException ex){
          
            JOptionPane.showMessageDialog(null, ex);
        }
        
        if(txt_leaveid.getText().isEmpty()){
            emp_leave_id_check.setText("some fields are empty");
        }
         else if(!isCorrects){
             emp_leave_id_check.setText("leave id not found");
        }
        else{
            emp_leave_id_check.setText("");
        }
    }//GEN-LAST:event_txt_leaveidFocusLost

    private void bt_edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_edit1ActionPerformed
       bt_edit1.setEnabled(false);
       bt_cancell.setEnabled(true);
       bt_applyl.setEnabled(false);
       txt_otid.setEnabled(true);
    }//GEN-LAST:event_bt_edit1ActionPerformed

    private void txt_otidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_otidKeyReleased
        // TODO add your handling code here:
        boolean isAvailable=false;
    
      
      String sql3="select * from othours where id='"+txt_otid.getText()+"'";
      
      try{
          st=con.createStatement();
          rs=st.executeQuery(sql3);
          
          while(rs.next()){
              isAvailable=true;
              
          }
      }
      catch(SQLException ex){
          JOptionPane.showMessageDialog(null,ex);
      }
      finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
            }
          
      }
      
      
        if(txt_otid.getText().isEmpty()){
          txt_otid_check.setText("Field is Empty");
        }
        else if(isAvailable){
            txt_otid_check.setText("");
            
        }
        else{
              txt_otid_check.setText("Invalid Id"); 
        }
            try
            {
                 String sql1 = "SELECT * FROM othours where `id`='"+txt_otid.getText()+"'";    
                Statement statement1 = con.createStatement();
                ResultSet result = statement1.executeQuery(sql1);

                int count = 0;
                if (result.next())
                {
                    search=true;
                    otid.setText(result.getString(1));
                    txt_amount.setText(result.getString(3));
                    
                      int value=0;
       
                        switch(result.getString(2)){
                            case "1":
                                s_ot.setSelectedIndex(1);
                                break;
                            case "2":
                                s_ot.setSelectedIndex(2);
                                 break;
                            case "3":
                                 s_ot.setSelectedIndex(3);
                                break;

                            case "4":
                               s_ot.setSelectedIndex(4);
                                break;
                            case "5":
                                s_ot.setSelectedIndex(5);
                                break;
                            case "6":
                                 s_ot.setSelectedIndex(6);
                                break;

                            case "7":
                              s_ot.setSelectedIndex(7);
                                break;

                            case "8":
                                 s_ot.setSelectedIndex(8);
                                break;
                            default:
                                s_ot.setSelectedIndex(0);
                        }
        
                    
                    txt_total.setText(result.getString(4));
                    bt_update1.setEnabled(true);
                    bt_deletel.setEnabled(true);
                    
                    String sql2 = "SELECT * FROM employee where `idEmployee`='"+result.getString(5)+"'";
                    try
                    {

                        Statement statement2 = con.createStatement();
                        ResultSet result2 = statement2.executeQuery(sql2);

                        int count1 = 0;
                        if (result2.next())
                        {
                            searchl=true;
                            emp_idl.setText(result.getString(5));
                            la_namel.setText(result2.getString(2)+" "+result.getString(3));
                            la_designationl.setText(result2.getString(18));
                            la_contactnumberl.setText(result2.getString(8));
                            la_emaill.setText(result2.getString(9));
                        }

                    }
                    catch(SQLException ex)
                    {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                         bt_update1.setEnabled(false);
                         bt_deletel.setEnabled(false);
                    }

                }
                else
                {
                    bt_update1.setEnabled(false);
                    bt_deletel.setEnabled(false);
                    la_namel.setText("");
                    la_designationl.setText("");
                    la_contactnumberl.setText("");
                    la_emaill.setText("");
                    emp_idl.setText("");
                    
                    txt_amount.setText("");
                    txt_total.setText("");
                    s_ot.setSelectedIndex(0);
                    bt_applyl.setEnabled(false);
                            
                    searchl=false;
               
                 
                  
                 
                    
                }
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        
    }//GEN-LAST:event_txt_otidKeyReleased

    private void emp_idlKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_idlKeyReleased
       String id=emp_idl.getText();
       boolean isAvailable=false;
         String sqlemp = "SELECT * FROM employee where `idEmployee`='"+emp_idl.getText()+"'";
         try{
             st=con.createStatement();
             rs=st.executeQuery(sqlemp);
             while(rs.next()){
                 isAvailable=true;
             }
             
         }
         catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex);
         }
       
        if(emp_idl.getText().isEmpty())
        {
            emp_id_error.setText("field is Empty");
        }
        else if(!isAvailable){
            emp_id_error.setText("Invalid employee id"); 
        }
        else{
            emp_id_error.setText("");
        }
       
            String sql1 = "SELECT * FROM employee where `idEmployee`='"+emp_idl.getText()+"'";
            try
            {

                Statement statement1 = con.createStatement();
                ResultSet result = statement1.executeQuery(sql1);

                //int count = 0;
                if (result.next())
                { 
                    la_namel.setText(result.getString(2)+" "+result.getString(3));
                    la_designationl.setText(result.getString(18));
                    la_contactnumberl.setText(result.getString(8));
                    la_emaill.setText(result.getString(9));
                    bt_applyl.setEnabled(true);
                    
                    if(result.getString(19).equals("Visiting"))
                    {
                        JOptionPane.showMessageDialog(null, "You can't add ot for visiting doctors");
                        emp_idl.setText("");
                        la_namel.setText("");
                        la_designationl.setText("");
                        la_contactnumberl.setText("");
                        la_emaill.setText("");
                        bt_applyl.setEnabled(false);
                    }
                 }   
                   
                  else
                {
                       
                        la_namel.setText("");
                        la_designationl.setText("");
                        la_contactnumberl.setText("");
                        la_emaill.setText("");
                        bt_applyl.setEnabled(false);
                }
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
       
    }//GEN-LAST:event_emp_idlKeyReleased

    private void txt_amountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_amountKeyReleased
         int value=0;
       
       switch(s_ot.getSelectedIndex()){
           case 1:
               value=1;
               break;
           case 2:
                value=2;
                break;
           case 3:
               value=3;
               break;
               
           case 4:
               value=4;
               break;
           case 5:
               value=5;
               break;
           case 6:
               value=6;
               break;
               
           case 7:
               value=7;
               break;
               
           case 8:
               value=8;
               break;
           default:
              value=0;
       }
        
        try{
            int num=Integer.parseInt(txt_amount.getText())*value;
            txt_total.setText(String.valueOf(num));
        }
        catch(Exception e){
          //  txt_amount.setText("");
        }
    }//GEN-LAST:event_txt_amountKeyReleased

    private void txt_otidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_otidFocusLost
       boolean isAvailable=false;
    
      
      String sql3="select * from othours where id='"+txt_otid+"'";
      
      try{
          st=con.createStatement();
          rs=st.executeQuery(sql3);
          
          while(rs.next()){
              isAvailable=true;
          }
      }
      catch(SQLException ex){
          JOptionPane.showMessageDialog(null,ex);
      }
      finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
            }
          
      }
    }//GEN-LAST:event_txt_otidFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Leave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Leave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Leave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Leave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Leave().setVisible(true);
            }
        });
    }

    public void check()
    {
        if(s_leave.getSelectedIndex()<1)
        {
            type=false;
            e_type.setText("Select a Leave type");
        }
        else
        {
            type=true;
            e_type.setText("");
        }
        
        if(txt_reason.getText().equals(""))
        {
            e_reason.setText("Input your leave reason");
            reason=false;
        }
        else
        {
            reason=true;
            e_reason.setText("");
        }
       
        
        
        
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        if(date_from.getDate()==null)
        {
            from=false;
            e_from.setText("Select a date");
        }
        else
        {
            from=true;
            e_from.setText("");
        }
        
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        if(date_to.getDate()==null)
        {
            to=false;
            e_to.setText("Select a date");
        }
        else
        {
            to=true;
            e_to.setText("");
        }
     
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_apply;
    private javax.swing.JButton bt_applyl;
    private javax.swing.JButton bt_cancel;
    private javax.swing.JButton bt_cancell;
    private javax.swing.JButton bt_delete;
    private javax.swing.JButton bt_deletel;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_edit1;
    private javax.swing.JButton bt_update;
    private javax.swing.JButton bt_update1;
    private com.toedter.calendar.JDateChooser date_from;
    private com.toedter.calendar.JDateChooser date_to;
    private javax.swing.JLabel e_amount;
    private javax.swing.JLabel e_from;
    private javax.swing.JLabel e_othours;
    private javax.swing.JLabel e_reason;
    private javax.swing.JLabel e_to;
    private javax.swing.JLabel e_type;
    private javax.swing.JTextField emp_id;
    private javax.swing.JLabel emp_id_check;
    private javax.swing.JLabel emp_id_error;
    private javax.swing.JTextField emp_idl;
    private javax.swing.JLabel emp_leave_id_check;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel la_contactnumber;
    private javax.swing.JLabel la_contactnumberl;
    private javax.swing.JLabel la_designation;
    private javax.swing.JLabel la_designationl;
    private javax.swing.JLabel la_email;
    private javax.swing.JLabel la_emaill;
    private javax.swing.JLabel la_name;
    private javax.swing.JLabel la_namel;
    private javax.swing.JTable leave;
    private javax.swing.JLabel lid;
    private javax.swing.JLabel otid;
    private javax.swing.JTable ott;
    private javax.swing.JComboBox<String> s_leave;
    private javax.swing.JComboBox<String> s_ot;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_day;
    private javax.swing.JTextField txt_leaveid;
    private javax.swing.JTextField txt_otid;
    private javax.swing.JLabel txt_otid_check;
    private javax.swing.JTextField txt_reason;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}