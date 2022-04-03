/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import com.jtattoo.plaf.DecorationHelper;
import database.DBConnection;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Haran
 */
public class HomeLogin extends javax.swing.JFrame{
     private Thread th;
     private  int s = 0;
     private int set_count=0;
     Connection con;
     PreparedStatement ps;
     ResultSet rs;
    /**
     * Creates new form HomeLogin
     */
    public HomeLogin() {
     
        initComponents();
       
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        staff_user = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        staff_password = new javax.swing.JPasswordField();
        staff_login = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Haran\\Documents\\NetBeansProjects\\ps_Atlanta\\src\\Home\\logpr1.png")); // NOI18N

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("User & Admin Signin");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        jButton2.setBackground(new java.awt.Color(0, 153, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("X");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 0));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("User Name");

        staff_user.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        staff_user.setToolTipText("Enter Your Name");
        staff_user.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Password");

        staff_password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        staff_password.setToolTipText("Enter Your Password");
        staff_password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        staff_login.setBackground(new java.awt.Color(102, 153, 255));
        staff_login.setFont(new java.awt.Font("Lucida Handwriting", 1, 14)); // NOI18N
        staff_login.setForeground(new java.awt.Color(255, 255, 255));
        staff_login.setText("Signin");
        staff_login.setBorder(null);
        staff_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staff_loginActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Haran\\Documents\\NetBeansProjects\\ps_Atlanta\\src\\Home\\user3.png")); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\Haran\\Documents\\NetBeansProjects\\ps_Atlanta\\src\\Home\\pass1.png")); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DarkMode", "LightGreen", "Smart", "Texture", "Default" }));
        jComboBox1.setToolTipText("select theme");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(116, 116, 116)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(staff_password, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(staff_user, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(staff_login, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addComponent(staff_user, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5))
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(staff_password, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(staff_login, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(671, 513));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void staff_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staff_loginActionPerformed
          String name=(String) jComboBox1.getSelectedItem();
        try{
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green","INSERT YOUR LICENSE KEY HERE","company");
            if(name.equals("DarkMode")){
                UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
                DecorationHelper.decorateWindows(false);
              
                
            }
            else if(name.equals("LightGreen")){
                UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                  DecorationHelper.decorateWindows(false);
               
            }
            else if(name.equals("Smart")){
                UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
                  DecorationHelper.decorateWindows(false);
            
            }
            else if(name.equals("Texture")){
                UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
                  DecorationHelper.decorateWindows(false);
            
               
            }
            else{
                  DecorationHelper.decorateWindows(false);
               
            }
        }
        catch(Exception e){
            
        }
        staff_log();
    }//GEN-LAST:event_staff_loginActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
      this.processWindowEvent(
                        new WindowEvent(
                                this, WindowEvent.WINDOW_CLOSING));
      
    }//GEN-LAST:event_jButton2ActionPerformed
  public void staff_log(){
        
        String username=staff_user.getText();
        String password=staff_password.getText();
        
        if(username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Some Fields Are Empty");
        }
        else{
        
        String q="select userType from loguser where userName=? and pwd=?";
       try {
          con=DBConnection.ConnectionDB();
           ps=con.prepareStatement(q);
           ps.setString(1, username);
           ps.setString(2, password);
           
           rs=ps.executeQuery();
           
           if(rs.next()){
               String utype=rs.getString("userType");
               if(utype.equals(null)){
               JOptionPane.showMessageDialog(null, "Invalid Login...! ");
               }
               else if(!utype.equals(null)){
                   this.setVisible(false);
                     
                  Loading load=new Loading();
                  load.setuType(utype);
                  load.setVisible(true);
                  
                  
                  ps.close();
                  rs.close();
               }
                    
           }else{
                 JOptionPane.showMessageDialog(null, "Invalid Login...! ");
                 staff_user.setText("");
                 staff_password.setText("");
           }
           
           
       } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
       }
       finally{
          
            try {
                rs.close();
                ps.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(HomeLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
        }
    }
    /*
  public void admin_log(){
        
        String username=admin_user.getText();
        String password=admin_password.getText();
        
        if(username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Some Fields Are Empty");
        }
        else{
        
        String q="select userType from loguser where userName=? and pwd=?";
       try {
          con=DBConnection.ConnectionDB();
           ps=con.prepareStatement(q);
           ps.setString(1, username);
           ps.setString(2, password);
           
           rs=ps.executeQuery();
           
           if(rs.next()){
               String utype=rs.getString("userType");
               if(utype.equals(null) ){
               JOptionPane.showMessageDialog(null, "Invalid Login...! ");
               }
               else if(!utype.equals(null) && utype.equals("admin")){
                   this.setVisible(false);
                  Loading load=new Loading();
                   load.setuType(utype);
                  load.setVisible(true);
                  ps.close();
                  rs.close();
               }
               else{
                    JOptionPane.showMessageDialog(null, "Invalid Login...! ");
               }
                    
           }else{
                 JOptionPane.showMessageDialog(null, "Invalid Login...! ");
           }
           
           
       } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
       }
       finally{
         
            try {
                rs.close();
                  ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(HomeLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
        }
    }
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
               //if ("Nimbus".equals(info.getName())) {
                   
                    
                     
                 //   break;
                //}
                
              UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                      DecorationHelper.decorateWindows(false);
              
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               HomeLogin l1= new HomeLogin();
              
                l1.setVisible(true);
               
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton staff_login;
    private javax.swing.JPasswordField staff_password;
    private javax.swing.JTextField staff_user;
    // End of variables declaration//GEN-END:variables
}
