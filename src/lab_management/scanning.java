package Lab_management;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author Matha
 */
public class scanning extends javax.swing.JFrame {

    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    DefaultTableModel model;
    DefaultTableModel model1;
            
    public scanning() 
    {
        initComponents();
        conn = MySQLConn.ConnectDB();
        scanning_details();
        scanning_table();
        AutoScanid();
        model=(DefaultTableModel)tblScan.getModel();
        model1=(DefaultTableModel)tblAddScan.getModel();
        
        
        TypeOfScn_AtLab.setSelectedItem("Select");
        
        //scdate.getJCalendar().setMaxSelectableDate(new Date());
        //scdate.setMaxSelectableDate(new Date());
       
    }
    
    private void scanning_details(){
        try{
        String sql="select * from scan1";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        tblScan.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void setScanDetails(){
        scnid.setText(String.valueOf(model.getValueAt(tblScan.getSelectedRow(), 0)));
        TypeOfScn_AtLab.setSelectedItem(String.valueOf(model.getValueAt(tblScan.getSelectedRow(), 1)));
        scDate_AtLab.setText(String.valueOf(model.getValueAt(tblScan.getSelectedRow(), 2)));
      
        
        refPhy_AtLab.setText(String.valueOf(model.getValueAt(tblScan.getSelectedRow(), 3)));
        remark_AtLab.setText(String.valueOf(model.getValueAt(tblScan.getSelectedRow(), 4)));
        search.setText(String.valueOf(model.getValueAt(tblScan.getSelectedRow(), 5)));
        payment.setText(String.valueOf(model.getValueAt(tblScan.getSelectedRow(), 6)));
    
    }
    
    public void getScanFee(){
        try {
            String x=TypeOfScn_AtLab.getSelectedItem().toString();
            
            Connection conn=MySQLConn.ConnectDB();
            String q="select scanFee from scantypedetails where ScanName= ?";
            pst =conn.prepareStatement(q);
            pst.setString(1,x);
            rs=pst.executeQuery();
            if(rs.next()){
                String fee=rs.getString("scanFee");
                payment.setText(fee);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(scanning.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
     public void Search_patient(){
        String s = search.getText();
            search.setText(null);
        
        
        try{
           Connection conn = MySQLConn.ConnectDB();
            String q="select * from patient where pId= ? or pNicOrPp=?";
            
            pst=conn.prepareStatement(q);
            pst.setString(1,s);
            pst.setString(2,s);
            rs=pst.executeQuery();
            
            if(rs.next()){     
                String p2 = rs.getString("pId");
                pidAtLab.setText(p2);
                String p3=rs.getString("pName");
                pname.setText(p3);
                String p5=rs.getString("gender");
                gender.setSelectedItem(p5);    
                String p6=rs.getString("age");
                age.setText(p6);
            }      
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);        
        }    
    }
    
     public void deleteScan(){
                 String value1= scnid.getText();
        if(value1!=null){
                  String sql="delete from scan1 where scan_id= ?";
                    try{

                        pst=conn.prepareStatement(sql);
                        pst.setString(1, value1);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(null,"Sucessfully scan details deleted.");
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e);
                    }
       }
        else
        {
             JOptionPane.showMessageDialog(null,"Select the Scan ID");
        }
     }
    
    
    private void insertScanningDetails(){  
         validation_scn v;
         v=new validation_scn(TypeOfScn_AtLab.getSelectedItem().toString(),payment.getText(),scDate_AtLab.getText(),refPhy_AtLab.getText(),pidAtLab.getText());
        boolean va = v.ScanValidation();
        
        if(va){
        try {
            Scan sc;
            sc = new Scan(scnid.getText(),TypeOfScn_AtLab.getSelectedItem().toString(), ((JTextField)scdate.getDateEditor().getUiComponent()).getText(), refPhy_AtLab.getText(),remark_AtLab.getText(),pidAtLab.getText(),Double.parseDouble(payment.getText()) );
            sc.insert_new_scan();
            clearScn();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(scanning.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    private void editScannDetails(){
        Scan sc1;
        sc1 = new Scan(scnid.getText(),TypeOfScn_AtLab.getSelectedItem().toString(), scDate_AtLab.getText(), refPhy_AtLab.getText(),remark_AtLab.getText(),pidAtLab.getText(),Double.parseDouble(payment.getText()) );
        sc1.edit_scan_details();
  
    }
    
    private void AutoScanid(){
        try{
        Connection conn = MySQLConn.ConnectDB();
        String p="SELECT scan_id FROM scan1 ORDER BY scan_id DESC LIMIT 1";
        pst=conn.prepareStatement(p);
        rs=pst.executeQuery();
            if(rs.next()){
                  String p1=rs.getString("scan_id");
                  String[] parts = p1.split("(?<=-)");
                    String part1 = parts[0];
                    String part2 = parts[1];
                  
                    int id = Integer.parseInt(part2);
                    int id2=id+1;
                    String id3=Integer.toString(id2);
                    String newid=part1+id3;
                    
                  
                  scnid.setText(newid);
                
            }        
        }
        catch(Exception e){
        
        }
    }
    
    
    private void clearScn(){
        
        TypeOfScn_AtLab.setSelectedItem("Select");
        payment.setText(null);
        scdate.setDate(null);
        refPhy_AtLab.setText(null);
        remark_AtLab.setText(null);
        pidAtLab.setText(null);
        pname.setText(null);
        age.setText(null);
        gender.setSelectedItem("Male");
        search.setText(null);
        AutoScanid();
    }
    
        public void search_scn(){
        String s = txtScanSrch.getText();
        txtScanSrch.setText(null);
        try{
            Connection conn = MySQLConn.ConnectDB();
            String q = "select * from scan1 where scan_id= ?";
            
            pst=conn.prepareStatement(q);
            pst.setString(1,s);
            rs=pst.executeQuery();
            
            if(rs.next()){
              String id = rs.getString("scan_id");
              scnid.setText(id);
                
              String a = rs.getString("type_of_scan");
              TypeOfScn_AtLab.setSelectedItem(a);
              
              String bd = rs.getString("scanned_date");
              scDate_AtLab.setText(bd);
              
              String c = rs.getString("ref_phy_name");
              refPhy_AtLab.setText(c);
              
              String d = rs.getString("remarks");
              remark_AtLab.setText(d);  
              
              String e = rs.getString("payment");
              payment.setText(e);
              
              String hh = rs.getString("pId");
              pidAtLab.setText(hh);
                  
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    
    }
        
    //ADD NEW SCAN
    private void scanning_table(){
        try{
        String sql="select * from scantypedetails";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        tblAddScan.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void setAddScanDetails(){
        sid.setText(String.valueOf(model1.getValueAt(tblAddScan.getSelectedRow(), 0)));
        sname.setText(String.valueOf(model1.getValueAt(tblAddScan.getSelectedRow(), 1)));
        sfee.setText(String.valueOf(model1.getValueAt(tblAddScan.getSelectedRow(), 2)));
    }
        
    private void insertNEWScanningDetails(){  
         
        try {
            Scan sc1;
            sc1 = new Scan(sid.getText(),sname.getText(),Double.parseDouble(sfee.getText()) );
            sc1.add_new_scanning();
            clearScan();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(scanning.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void editScann(){
        Scan sc1;
        sc1 = new Scan(sid.getText(),sname.getText(), Double.parseDouble(sfee.getText()) );
        sc1.edit_scan();
  
    }
    
    private void clearScan(){
        sid.setText(null);
        sname.setText(null);
        sfee.setText(null);   
    }
    
    public void search_scanName(){
        String s = scSearch.getText();
        scSearch.setText(null);
        try{
            Connection conn = MySQLConn.ConnectDB();
            String q = "select * from scantypedetails where ScanID= ?";
            
            pst=conn.prepareStatement(q);
            pst.setString(1,s);
            rs=pst.executeQuery();
            
            if(rs.next()){
              String id = rs.getString("ScanID");
              sid.setText(id);
                
              String a = rs.getString("ScanName");
              sname.setText(a);
              
              String b = rs.getString("scanFee");
              sfee.setText(b);                    
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    
    }
    
    public void deleteScanName(){
                 String value1= sid.getText();
        if(value1!=null){
                  String sql="delete from scantypedetails where ScanID= ?";
                    try{

                        pst=conn.prepareStatement(sql);
                        pst.setString(1, value1);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(null,"Sucessfully scan deleted.");
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e);
                    }
       }
        else
        {
             JOptionPane.showMessageDialog(null,"Select the Scan ID");
        }
     }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        TypeAtLab = new javax.swing.JLabel();
        Ref_Phy_IDAtLab = new javax.swing.JLabel();
        TypeOfScn_AtLab = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        remark_AtLab = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        refPhy_AtLab = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        payment = new javax.swing.JTextField();
        txtScanSrch = new javax.swing.JTextField();
        ScSearch = new javax.swing.JButton();
        scnid = new javax.swing.JLabel();
        valTOS = new javax.swing.JLabel();
        valSD = new javax.swing.JLabel();
        valRPN = new javax.swing.JLabel();
        scDate_AtLab = new javax.swing.JTextField();
        scdate = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblScan = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pname = new javax.swing.JTextField();
        age = new javax.swing.JTextField();
        search = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        gender = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        pidAtLab = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        scSearch = new javax.swing.JTextField();
        sid = new javax.swing.JTextField();
        sname = new javax.swing.JTextField();
        sfee = new javax.swing.JTextField();
        valSID = new javax.swing.JLabel();
        valSN = new javax.swing.JLabel();
        valSF = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAddScan = new javax.swing.JTable();
        NAdd = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        TypeAtLab.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        TypeAtLab.setText("Type of scan");

        Ref_Phy_IDAtLab.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Ref_Phy_IDAtLab.setText("Scanned date");

        TypeOfScn_AtLab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "ElectroCardioGraphy(ECG)", "Ultrasound (US)", "Magnetic Resonance (MR)", "Endoscopy (ES)", "Computed Radiography (CR)" }));
        TypeOfScn_AtLab.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TypeOfScn_AtLabItemStateChanged(evt);
            }
        });
        TypeOfScn_AtLab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TypeOfScn_AtLabFocusLost(evt);
            }
        });
        TypeOfScn_AtLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TypeOfScn_AtLabMouseReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Scan ID");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Remark");

        remark_AtLab.setColumns(20);
        remark_AtLab.setRows(5);
        jScrollPane3.setViewportView(remark_AtLab);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Refered Physician Name");

        refPhy_AtLab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                refPhy_AtLabFocusLost(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Payment");

        ScSearch.setText("Search");
        ScSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScSearchActionPerformed(evt);
            }
        });

        scnid.setText("scnid");

        scDate_AtLab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                scDate_AtLabFocusLost(evt);
            }
        });
        scDate_AtLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scDate_AtLabActionPerformed(evt);
            }
        });

        scdate.setDateFormatString("yyyy-MM-dd");
        scdate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                scdatePropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(TypeAtLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(122, 122, 122)
                            .addComponent(TypeOfScn_AtLab, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(126, 126, 126)
                            .addComponent(scnid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(261, 261, 261)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(valTOS, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(payment)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(122, 122, 122)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(valSD, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(refPhy_AtLab)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Ref_Phy_IDAtLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(122, 122, 122)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(scDate_AtLab, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(scdate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(valRPN, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtScanSrch, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScSearch)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Ref_Phy_IDAtLab, TypeAtLab, jLabel1, jLabel10, jLabel3, jLabel8});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtScanSrch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScSearch))
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(scnid))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TypeOfScn_AtLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TypeAtLab))
                .addGap(1, 1, 1)
                .addComponent(valTOS, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(payment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Ref_Phy_IDAtLab)
                    .addComponent(scDate_AtLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(valSD, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(refPhy_AtLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(valRPN, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(155, 155, 155))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Ref_Phy_IDAtLab, TypeAtLab, jLabel1, jLabel10, jLabel8});

        tblScan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ScanID", "Type of Scan", "Payment", "ScannedDate", "RefPhysicianName", "Remark", "PID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblScan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblScanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblScan);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient Info"));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Name");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Age");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Gender");

        pname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnameActionPerformed(evt);
            }
        });

        age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ageActionPerformed(evt);
            }
        });

        jButton6.setText("Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        gender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Patient ID");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(gender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pname, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                    .addComponent(age, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                    .addComponent(pidAtLab)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6)))
                        .addGap(40, 40, 40))))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {age, pname});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel4, jLabel5, jLabel6, jLabel9});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(pidAtLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(71, 71, 71))
        );

        jLabel2.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel2.setText("SCANNING");

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("EDIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("CLEAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton5});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(34, 34, 34)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton3)
                .addGap(35, 35, 35)
                .addComponent(jButton5)
                .addGap(71, 71, 71))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton5});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 37, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        jTabbedPane1.addTab("Scanning", jPanel3);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Scan ID");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Scan Name");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Scan Fee");

        jButton7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton7.setText("Search");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        sid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sidFocusLost(evt);
            }
        });

        sname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                snameFocusLost(evt);
            }
        });
        sname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snameActionPerformed(evt);
            }
        });

        sfee.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sfeeFocusLost(evt);
            }
        });
        sfee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sfeeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(scSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel12))
                                        .addGap(53, 53, 53)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(valSID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(sid, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                            .addComponent(sname)
                                            .addComponent(valSN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 118, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sfee, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                    .addComponent(valSF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(67, 67, 67))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(scSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(valSID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(sname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(valSN)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(sfee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valSF)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel11.setText("ADD NEW SCANNING");

        tblAddScan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblAddScan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAddScanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAddScan);

        NAdd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        NAdd.setText("ADD");
        NAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NAddActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton9.setText("UPDATE");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton10.setText("DELETE");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton8.setText("CLEAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10)
                            .addComponent(NAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(472, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {NAdd, jButton10, jButton8, jButton9});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(NAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {NAdd, jButton10, jButton8, jButton9});

        jTabbedPane1.addTab("Add", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1300, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Search_patient();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageActionPerformed

    private void pnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnameActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        deleteScan();
        scanning_details();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 
        insertScanningDetails();
          scanning_details();
        
        AutoScanid();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TypeOfScn_AtLabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TypeOfScn_AtLabMouseReleased
        // TODO add your handling code here:
        getScanFee();
    }//GEN-LAST:event_TypeOfScn_AtLabMouseReleased

    private void TypeOfScn_AtLabItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TypeOfScn_AtLabItemStateChanged
        // TODO add your handling code here:
        getScanFee();
    }//GEN-LAST:event_TypeOfScn_AtLabItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        editScannDetails();
        scanning_details();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     
        clearScn();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void ScSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScSearchActionPerformed
        // TODO add your handling code here:
        search_scn();
    }//GEN-LAST:event_ScSearchActionPerformed

    private void tblScanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblScanMouseClicked
       setScanDetails();
    }//GEN-LAST:event_tblScanMouseClicked

    private void TypeOfScn_AtLabFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TypeOfScn_AtLabFocusLost
        // TODO add your handling code here:
        String ss = TypeOfScn_AtLab.getSelectedItem().toString();
        if(ss.equals("Select")){
            valTOS.setText("Please Select the scan category!!");
            valTOS.setForeground(Color.red);
        }
        else
            valTOS.setText("");
        
    }//GEN-LAST:event_TypeOfScn_AtLabFocusLost

    private void refPhy_AtLabFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_refPhy_AtLabFocusLost
        // TODO add your handling code here:
        String ss= refPhy_AtLab.getText();
        if(ss.equals("")){
            valRPN.setText("Please enter Physician name!!");
            valRPN.setForeground(Color.red);
        }
        else{
            for(int i=0; i < ss.length(); i++){
                char a = ss.charAt(i);
                
                if(Character.isDigit(a)){
                    valRPN.setText("Dont enter numbers in physician Name.");
                    valRPN.setForeground(Color.red);
                }
                else
                    valRPN.setText("");
            }
        }
    }//GEN-LAST:event_refPhy_AtLabFocusLost

    private void scDate_AtLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scDate_AtLabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scDate_AtLabActionPerformed

    private void snameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_snameActionPerformed

    private void sfeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sfeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sfeeActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        editScann();
        scanning_table();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void NAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NAddActionPerformed
        // TODO add your handling code here:
        insertNEWScanningDetails();
        scanning_table();
    }//GEN-LAST:event_NAddActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        deleteScanName();
        scanning_table();
         clearScan();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void scdatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_scdatePropertyChange
        // TODO add your handling code here:
        scDate_AtLab.setText(((JTextField)scdate.getDateEditor().getUiComponent()).getText());
    }//GEN-LAST:event_scdatePropertyChange

    private void scDate_AtLabFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_scDate_AtLabFocusLost
        // TODO add your handling code here:
        String jj=scDate_AtLab.getText();
        if(jj.equals("")){
            valSD.setText("Please select the scanned date.");
            valSD.setForeground(Color.red);
        }
        else{
            valSD.setText("");
        }
    }//GEN-LAST:event_scDate_AtLabFocusLost

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        search_scanName();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        clearScan();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tblAddScanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAddScanMouseClicked
        setAddScanDetails();
    }//GEN-LAST:event_tblAddScanMouseClicked

    private void sidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sidFocusLost
        // TODO add your handling code here:
        String ss= sid.getText();
        if(ss.equals("")){
            valSID.setText("Please enter scan ID!!");
            valSID.setForeground(Color.red);
        }
        else
                    valSID.setText("");
    }//GEN-LAST:event_sidFocusLost

    private void snameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_snameFocusLost
        // TODO add your handling code here:
        String ss= sname.getText();
        if(ss.equals("")){
            valSN.setText("Please enter scan name!!");
            valSN.setForeground(Color.red);
        }
        else
                    valSN.setText("");
    }//GEN-LAST:event_snameFocusLost

    private void sfeeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sfeeFocusLost
        // TODO add your handling code here:
        String s = sfee.getText();
        if(s.equals("")){
            valSF.setText("Please enter Scan Fee!");
            valSF.setForeground(Color.red);     
        }
        else{
            for(int i=0; i < s.length(); i++){
                char a = s.charAt(i);
                
                if(Character.isDigit(a)){
                    
                    valSF.setText("");
                    valSF.setForeground(Color.red);
                    
                }
                else{
                    valSF.setText("You can enter number only.");
                    
                }
            }
        }
    }//GEN-LAST:event_sfeeFocusLost

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
            java.util.logging.Logger.getLogger(scanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(scanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(scanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(scanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new scanning().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NAdd;
    private javax.swing.JLabel Ref_Phy_IDAtLab;
    private javax.swing.JButton ScSearch;
    private javax.swing.JLabel TypeAtLab;
    private javax.swing.JComboBox<String> TypeOfScn_AtLab;
    private javax.swing.JTextField age;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField payment;
    private javax.swing.JTextField pidAtLab;
    private javax.swing.JTextField pname;
    private javax.swing.JTextField refPhy_AtLab;
    private javax.swing.JTextArea remark_AtLab;
    private javax.swing.JTextField scDate_AtLab;
    private javax.swing.JTextField scSearch;
    private com.toedter.calendar.JDateChooser scdate;
    private javax.swing.JLabel scnid;
    private javax.swing.JTextField search;
    private javax.swing.JTextField sfee;
    private javax.swing.JTextField sid;
    private javax.swing.JTextField sname;
    private javax.swing.JTable tblAddScan;
    private javax.swing.JTable tblScan;
    private javax.swing.JTextField txtScanSrch;
    private javax.swing.JLabel valRPN;
    private javax.swing.JLabel valSD;
    private javax.swing.JLabel valSF;
    private javax.swing.JLabel valSID;
    private javax.swing.JLabel valSN;
    private javax.swing.JLabel valTOS;
    // End of variables declaration//GEN-END:variables
}
