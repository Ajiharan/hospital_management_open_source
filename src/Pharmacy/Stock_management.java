/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pharmacy;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author user
 */
public class Stock_management extends javax.swing.JInternalFrame {

    
    Connection conn = null;
    ResultSet rs,rs1;
    PreparedStatement pst;
    Statement st;
    DefaultTableModel dm1;
    DefaultTableModel dm;
    
    int InvoiceNo = 1;
    int seqNostock = 1;
    
    int ItemID = 1;
    
    String CDate="";
    
    double StockTotal=0;
    
    boolean isItemUpdate=false;
    /**
     * Creates new form Stock_management
     */
    public Stock_management() {
        initComponents();
        setMaximizable(true);
        
        
        
        ExpiryDateAtStock.setMinSelectableDate(new Date());
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/athlanta_hospital", "root", "");
            //JOptionPane.showMessageDialog(null,"DB conected");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        
        try{
            int count=0;
            String SQL;
            SQL = "SELECT COUNT(*) FROM item WHERE Availability < 100";
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs1 = pst.executeQuery();
            rs1.next();
            count=rs1.getInt(1);
            
            if(count>0)
            {
                
            NotificationAtMain.setText("Low Stock "+count+" Items");
            NotificationAtMain.setForeground(Color.red);
            
            }
            
            
        }
        catch(SQLException | NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        
        try{
            int count=0;
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, +3);
            Date date = cal.getTime();
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String expDate = formatter.format(date);
            
            String SQL;
            SQL = "SELECT COUNT(*) FROM stock WHERE expDate < '"+expDate+"' AND availability > 0";
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs1 = pst.executeQuery();
            rs1.next();
            count=rs1.getInt(1);
            
            if(count>0)
            {
                ExpiryDateAlert.setText(count+" Items are going to expire within 3 months");
                ExpiryDateAlert.setForeground(Color.red);
            }
        }
        catch(SQLException | NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        
       //  DBConection d1 = new DBConection();
      //  conn = d1.GetConection();
        dm1 = (DefaultTableModel) TableAtStock.getModel();
        
        dm=(DefaultTableModel) GreadAtItems.getModel(); 
        
        //getStock();
        ClearStock();
        try{
            String SQL;
            SQL = "SELECT MAX(InvoiceNo) FROM stock";
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs1= pst.executeQuery();
            while(rs1.next())
            {
                InvoiceNo = 1+Integer.parseInt(rs1.getString(1));
                        //Integer.parseInt(jTextField16.getText());
            }
        }
        catch(Exception e)
        {
           // JOptionPane.showMessageDialog(null,e);
        }
        
        try{
            String SQL;
            SQL = "SELECT MAX(ItemID) FROM item";
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs1= pst.executeQuery();
            while(rs1.next())
            {
                ItemID = 1+Integer.parseInt(rs1.getString(1));
                        //Integer.parseInt(jTextField16.getText());
            }
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null,e);
        }
        
        ItemGride();
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        CDate = dateFormat.format(date);
        AddedDateAtStock.setText(CDate);
        AddedDateAtStock.setEditable(false);
        
        InventoryNoAtStock.setText(String.valueOf(InvoiceNo));
        ItemIdAtItem.setText(String.valueOf(ItemID));
        DosageAtItem.setEnabled(false);
        DrugTypeAtItems.setEnabled(false);
        
        seqNoAtStock.setText(String.valueOf(seqNostock));
        
        //function for get supplier in dropdown list in stock
        try
        {
            String sql = "SELECT Name FROM Supplier";
            PreparedStatement pst;
            pst = conn.prepareStatement(sql);
            rs1 = pst.executeQuery();
            while(rs1.next())
            {
                SearchComboAtStock.addItem(rs1.getString(1));
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        
        //function for get item in dropdown list in stock
        try
        {
            String sql = "SELECT ItemName FROM item";
            PreparedStatement pst;
            pst = conn.prepareStatement(sql);
            rs1 = pst.executeQuery();
            while(rs1.next())
            {
                ItemNameComboBoxAtStock.addItem(rs1.getString(1));
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        
        stockbtncontroller();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TypeAtItems = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        ItemNameComboBoxAtStock = new javax.swing.JComboBox<String>();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        SerialNoAtStock = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        PurPriceAtStock = new javax.swing.JTextField();
        ExpiryDateAtStock = new com.toedter.calendar.JDateChooser();
        AddBtnAtStock = new javax.swing.JButton();
        QuantitySpinnerAtStock = new javax.swing.JTextField();
        ValidationForItemNameAtStock = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        seqNoAtStock = new javax.swing.JLabel();
        ValidationForQuantityAtStock = new javax.swing.JLabel();
        ValidationForExpirydateAtStock = new javax.swing.JLabel();
        ValidationForSerialNoAtStock = new javax.swing.JLabel();
        ValidationForPurchasingPriceAtStock = new javax.swing.JLabel();
        clearBtnAtStock = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableAtStock = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        AddedDateAtStock = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        SearchComboAtStock = new javax.swing.JComboBox<String>();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        InventoryNoAtStock = new javax.swing.JLabel();
        ValidationForSuppliernameAtStock = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        ItemIdAtItem = new javax.swing.JLabel();
        ItemNameAtItem = new javax.swing.JTextField();
        DosageAtItem = new javax.swing.JTextField();
        OthersRadioAtItem = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        OverTheCounterAtItemChkBox = new javax.swing.JCheckBox();
        UpdateDrugBtnAtItem = new javax.swing.JButton();
        DeleteDrugBtnAtItem = new javax.swing.JButton();
        AddDrugBtnAtItem = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        DrugRadioAtItem = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        DrugTypeAtItems = new javax.swing.JComboBox();
        ValidationForItemNameAtItems = new javax.swing.JLabel();
        ValidationForDrugTypeAtItems = new javax.swing.JLabel();
        ValidationForDosageAtItems = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        SearchComboBoxAtItem = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        GreadAtItems = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        NotificationAtMain = new javax.swing.JLabel();
        ExpiryDateAlert = new javax.swing.JLabel();

        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        ItemNameComboBoxAtStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ItemNameComboBoxAtStock.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select" }));
        ItemNameComboBoxAtStock.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ItemNameComboBoxAtStockItemStateChanged(evt);
            }
        });
        ItemNameComboBoxAtStock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ItemNameComboBoxAtStockFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ItemNameComboBoxAtStockFocusLost(evt);
            }
        });
        ItemNameComboBoxAtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemNameComboBoxAtStockActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Serial No : ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Purchasing Price : ");

        SerialNoAtStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SerialNoAtStock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SerialNoAtStockFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SerialNoAtStockFocusLost(evt);
            }
        });
        SerialNoAtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SerialNoAtStockKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Item Name : ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Expiry Date : ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Quantity : ");

        PurPriceAtStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PurPriceAtStock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PurPriceAtStockFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PurPriceAtStockFocusLost(evt);
            }
        });
        PurPriceAtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PurPriceAtStockKeyReleased(evt);
            }
        });

        ExpiryDateAtStock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ExpiryDateAtStockFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ExpiryDateAtStockFocusLost(evt);
            }
        });

        AddBtnAtStock.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AddBtnAtStock.setText("Add");
        AddBtnAtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtnAtStockActionPerformed(evt);
            }
        });

        QuantitySpinnerAtStock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                QuantitySpinnerAtStockFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                QuantitySpinnerAtStockFocusLost(evt);
            }
        });
        QuantitySpinnerAtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                QuantitySpinnerAtStockKeyReleased(evt);
            }
        });

        jLabel2.setText("No : ");

        clearBtnAtStock.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearBtnAtStock.setText("Clear");
        clearBtnAtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnAtStockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(108, 108, 108)
                        .addComponent(seqNoAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ValidationForSerialNoAtStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ValidationForExpirydateAtStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ValidationForQuantityAtStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ValidationForItemNameAtStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PurPriceAtStock)
                            .addComponent(SerialNoAtStock)
                            .addComponent(ItemNameComboBoxAtStock, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ExpiryDateAtStock, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(QuantitySpinnerAtStock)))
                    .addComponent(ValidationForPurchasingPriceAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(AddBtnAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearBtnAtStock)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(seqNoAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(ItemNameComboBoxAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ValidationForItemNameAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(QuantitySpinnerAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(ValidationForQuantityAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(ExpiryDateAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ValidationForExpirydateAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(SerialNoAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ValidationForSerialNoAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PurPriceAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ValidationForPurchasingPriceAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearBtnAtStock)
                    .addComponent(AddBtnAtStock))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jPanel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("Remove Entry");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        TableAtStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TableAtStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Item Name", "Quantity", "Expiry Date", "Serial No", "PurchasedPrice"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableAtStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TableAtStockMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(TableAtStock);

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setText("Finish");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(393, 393, 393))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel12);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Supplier Name : ");

        SearchComboAtStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SearchComboAtStock.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select" }));
        SearchComboAtStock.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SearchComboAtStockItemStateChanged(evt);
            }
        });
        SearchComboAtStock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                SearchComboAtStockFocusLost(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Invoice no : ");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Added Date : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(28, 28, 28)
                .addComponent(InventoryNoAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(26, 26, 26)
                        .addComponent(SearchComboAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ValidationForSuppliernameAtStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(110, 110, 110)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddedDateAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(SearchComboAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AddedDateAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7)
                        .addComponent(InventoryNoAtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ValidationForSuppliernameAtStock, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(91, 91, 91))
        );

        jTabbedPane2.addTab("Stock", jPanel8);

        jPanel13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Item ID : ");

        ItemNameAtItem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ItemNameAtItemFocusLost(evt);
            }
        });
        ItemNameAtItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ItemNameAtItemKeyReleased(evt);
            }
        });

        DosageAtItem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                DosageAtItemFocusLost(evt);
            }
        });
        DosageAtItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DosageAtItemKeyReleased(evt);
            }
        });

        TypeAtItems.add(OthersRadioAtItem);
        OthersRadioAtItem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OthersRadioAtItem.setText("Others");
        OthersRadioAtItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OthersRadioAtItemMouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Dosage : ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Item Name : ");

        OverTheCounterAtItemChkBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OverTheCounterAtItemChkBox.setText("Over the Counter");
        OverTheCounterAtItemChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OverTheCounterAtItemChkBoxActionPerformed(evt);
            }
        });
        OverTheCounterAtItemChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OverTheCounterAtItemChkBoxKeyPressed(evt);
            }
        });

        UpdateDrugBtnAtItem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateDrugBtnAtItem.setText("Update Drug");
        UpdateDrugBtnAtItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateDrugBtnAtItemActionPerformed(evt);
            }
        });

        DeleteDrugBtnAtItem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DeleteDrugBtnAtItem.setText("Delete Drug");
        DeleteDrugBtnAtItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteDrugBtnAtItemActionPerformed(evt);
            }
        });

        AddDrugBtnAtItem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AddDrugBtnAtItem.setText("Add Drug");
        AddDrugBtnAtItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDrugBtnAtItemActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Type : ");

        TypeAtItems.add(DrugRadioAtItem);
        DrugRadioAtItem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        DrugRadioAtItem.setText("Drug");
        DrugRadioAtItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DrugRadioAtItemMouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Drug Type :");

        DrugTypeAtItems.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "AAP", "AB", "AD", "AN", "AUH", "CNS", "CR", "CVS", "EENP", "ES", "GI", "GUTI", "LA", "NB", "NSAID", "O", "PP", "RA", "RJ", "SI", "SP", "VI" }));
        DrugTypeAtItems.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DrugTypeAtItemsItemStateChanged(evt);
            }
        });
        DrugTypeAtItems.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                DrugTypeAtItemsFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(DrugRadioAtItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(OthersRadioAtItem)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(ValidationForItemNameAtItems, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ItemNameAtItem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(ItemIdAtItem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(OverTheCounterAtItemChkBox))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(AddDrugBtnAtItem)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton1))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(UpdateDrugBtnAtItem)
                                        .addGap(18, 18, 18)
                                        .addComponent(DeleteDrugBtnAtItem))))
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ValidationForDrugTypeAtItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(DrugTypeAtItems, 0, 156, Short.MAX_VALUE)))
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addGap(36, 36, 36)
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(ValidationForDosageAtItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(DosageAtItem, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ItemIdAtItem, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(ItemNameAtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(ValidationForItemNameAtItems, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DrugRadioAtItem)
                    .addComponent(jLabel21)
                    .addComponent(OthersRadioAtItem))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(DrugTypeAtItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ValidationForDrugTypeAtItems, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel19))
                    .addComponent(DosageAtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ValidationForDosageAtItems, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OverTheCounterAtItemChkBox)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddDrugBtnAtItem)
                    .addComponent(UpdateDrugBtnAtItem)
                    .addComponent(DeleteDrugBtnAtItem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        SearchComboBoxAtItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchComboBoxAtItemKeyReleased(evt);
            }
        });

        GreadAtItems.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GreadAtItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Type", "Drug Type", "OTC", "Availability", "Selling Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        GreadAtItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GreadAtItemsMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(GreadAtItems);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Search : ");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(46, 46, 46)
                        .addComponent(SearchComboBoxAtItem, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchComboBoxAtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel33)))
                .addGap(61, 61, 61)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Items", jPanel9);

        NotificationAtMain.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        NotificationAtMain.setForeground(new java.awt.Color(204, 0, 0));
        NotificationAtMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NotificationAtMainMouseClicked(evt);
            }
        });

        ExpiryDateAlert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ExpiryDateAlert.setForeground(new java.awt.Color(204, 0, 0));
        ExpiryDateAlert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExpiryDateAlertMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExpiryDateAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NotificationAtMain, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NotificationAtMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ExpiryDateAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ItemNameComboBoxAtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemNameComboBoxAtStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ItemNameComboBoxAtStockActionPerformed

    private void AddBtnAtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnAtStockActionPerformed
        // TODO add your handling code here:
         
         Double purp = Double.parseDouble(PurPriceAtStock.getText());
        String itemName=ItemNameComboBoxAtStock.getSelectedItem().toString();
        //  int inventoryNo=Integer.parseInt(InvenoryNoAtStock.getText());
        int quantity=Integer.parseInt(QuantitySpinnerAtStock.getText());
//        String expDate = ExpiryDateAtStock.getDate().toString();
        int serialNo =Integer.parseInt(SerialNoAtStock.getText());
        double purPrice =Double.parseDouble(PurPriceAtStock.getText());
        String addDate = AddedDateAtStock.getText();
        String supplier = SearchComboAtStock.getSelectedItem().toString();
        
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String expDate = formatter.format(ExpiryDateAtStock.getDate());

//        String[] splExpDate = expDate.split(" ");
//        expDate=splExpDate[1]+splExpDate[2]+splExpDate[5];
        try{
            pst=conn.prepareStatement("Insert into stock values(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, InvoiceNo);
            pst.setInt(2, seqNostock);
            pst.setString(3,itemName);
            pst.setString(4,addDate);
            pst.setString(5,supplier);
            pst.setInt(6,quantity);
            pst.setString(7,expDate);
            pst.setString(8,String.valueOf(serialNo));
            pst.setDouble(9,purPrice);
            pst.setDouble(10,(quantity*purPrice));
            pst.setInt(11,quantity);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Inserted Successfully");
            dm1.addRow(new Object[]{String.valueOf(seqNostock),itemName,quantity,expDate,serialNo,purPrice});
            ++seqNostock;
            StockTotal=StockTotal+(quantity*purPrice);
            ClearStock();
            
        }
        catch (SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null,e);
             
        }
        
        int availability=0;
        try{
            String SQL;
            SQL = "SELECT availability FROM item WHERE ItemName LIKE '" + itemName + "'";
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs1= pst.executeQuery();
            rs1.next();
            
            availability= quantity+Integer.parseInt(rs1.getString(1));
                        //Integer.parseInt(jTextField16.getText());
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        double selPri =purp+(purp*0.1);
        try{
            st = conn.createStatement();
            String sql1 = "UPDATE item SET availability='"+availability+"',sel_price ='"+selPri+"',Last_Per_Price ='"+purp+"'  where ItemName LIKE '" + itemName + "'";
            st.executeUpdate(sql1);//reenter the updated record
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        
         
        try{
            int count=0;
            String SQL;
            SQL = "SELECT COUNT(*) FROM item WHERE Availability < 100";
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs1 = pst.executeQuery();
            rs1.next();
            count=rs1.getInt(1);
            
            if(count>0)
            {
                
            NotificationAtMain.setText("Low Stock "+count+" Items");
            NotificationAtMain.setForeground(Color.red);
            
            }
            
            
        }
        catch(SQLException | NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
     
        try{
            int count=0;
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, +3);
            Date date = cal.getTime();
            Format formatt = new SimpleDateFormat("yyyy-MM-dd");
            String expDate1 = formatt.format(date);
            
            String SQL;
            SQL = "SELECT COUNT(*) FROM stock WHERE expDate < '"+expDate1+"' AND availability > 0";
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs1 = pst.executeQuery();
            rs1.next();
            count=rs1.getInt(1);
            
            if(count>0)
            {
                ExpiryDateAlert.setText(count+" Items are going to expire within 3 months");
                ExpiryDateAlert.setForeground(Color.red);
            }
        }
        catch(SQLException | NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
         
ItemGride();

    }//GEN-LAST:event_AddBtnAtStockActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
        HashMap parameter = new HashMap();
        parameter.put("InvoiceNo", InvoiceNo);
        parameter.put("Total", StockTotal);

        try {
            com.mysql.jdbc.Connection con;
//            Class.forName("com.mysql.jdbc.Driver");
//            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy","root","thilak");
       
            String path="C:\\Users\\USER\\Desktop\\Desktop Folders\\ITP_SLIIT\\MY ITP\\After prototype\\Invoice.jrxml";
            
            JasperReport jr = JasperCompileManager.compileReport(path);  
            JasperPrint jp = JasperFillManager.fillReport(jr, parameter, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(Stock_management.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        }
        seqNostock = 1;
        InvoiceNo++;
        StockTotal=0;
        while(dm1.getRowCount() > 0)
        {
            dm1.removeRow(0);
        }
        ClearStock();
        SearchComboAtStock.setSelectedItem("Select");
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void OverTheCounterAtItemChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OverTheCounterAtItemChkBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OverTheCounterAtItemChkBoxActionPerformed

    private void AddDrugBtnAtItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddDrugBtnAtItemActionPerformed
        // TODO add your handling code here:
        String ItemName = ItemNameAtItem.getText();
        String Dosage = DosageAtItem.getText();
        String Type ="";
        String DrugType = DrugTypeAtItems.getSelectedItem().toString();
        if(DrugRadioAtItem.isSelected())
        {
            Type="Drug";
            ItemName = ItemName+"__("+Dosage+")";
        }
        else if(OthersRadioAtItem.isSelected())
        {
            Type="Others";
            DrugType="";
        }
        boolean OTC;
        if(OverTheCounterAtItemChkBox.isSelected())
        {
            OTC=true;
        }
        else
        {
            OTC=false;
        }
                
        
        
        try{
            pst=conn.prepareStatement("Insert into item values(?,?,?,?,?,?,?,?)");
            pst.setInt(1, ItemID);
            pst.setString(2,ItemName);
            pst.setString(3,Type);
            pst.setString(4,String.valueOf(OTC));
            pst.setString(5,String.valueOf(DrugType));
            pst.setInt(6, 0);
            pst.setDouble(7,0);
            pst.setDouble(8,0);
            

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Inserted Successfully");
            dm.addRow(new Object[]{String.valueOf(ItemID),ItemName,Type,DrugType,OTC,"0","0"});
            ++ItemID;
            ItemNameComboBoxAtStock.addItem(ItemName);
            

        }
        catch (SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        ClearItem();
        
    }//GEN-LAST:event_AddDrugBtnAtItemActionPerformed

    private void DrugRadioAtItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DrugRadioAtItemMouseClicked
        // TODO add your handling code here:
        if(DrugRadioAtItem.isSelected())
        {
            DosageAtItem.setEnabled(true);
            DrugTypeAtItems.setEnabled(true);
        }
        else if(OthersRadioAtItem.isSelected())
        {
            DrugTypeAtItems.setEnabled(false);
            DosageAtItem.setEnabled(false);
            DosageAtItem.setText("");
        }
        Itembtnscontroler();
    }//GEN-LAST:event_DrugRadioAtItemMouseClicked

    private void OthersRadioAtItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OthersRadioAtItemMouseClicked
        // TODO add your handling code here:
        if(DrugRadioAtItem.isSelected())
        {
            DrugTypeAtItems.setEnabled(true);
            DosageAtItem.setEnabled(true);
        }
        else if(OthersRadioAtItem.isSelected())
        {
            DrugTypeAtItems.setEnabled(false);
            DosageAtItem.setEnabled(false);
            DosageAtItem.setText("");
        }
        Itembtnscontroler();
    }//GEN-LAST:event_OthersRadioAtItemMouseClicked

    private void GreadAtItemsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GreadAtItemsMousePressed
        // TODO add your handling code here:
        
        int i=GreadAtItems.getSelectedRow();
        ItemIdAtItem.setText(dm.getValueAt(i, 0).toString());
        ItemNameAtItem.setText(dm.getValueAt(i, 1).toString());
        String type = dm.getValueAt(i, 2).toString();
        
        if(type.equals("Drug"))
        {
            DrugRadioAtItem.setSelected(true);
            String arr[] = dm.getValueAt(i, 1).toString().split("__");
            
            ItemNameAtItem.setText(arr[0]);
            arr[1]=arr[1].replace("(", "");
            arr[1]=arr[1].replace(")", "");
            
            DosageAtItem.setText(arr[1]);
            DosageAtItem.setEnabled(true);
            DrugTypeAtItems.setEnabled(true);
        }
        else
        {
            OthersRadioAtItem.setSelected(true);
        }
        
        DrugTypeAtItems.setSelectedItem(dm.getValueAt(i, 3));
        OverTheCounterAtItemChkBox.setSelected((boolean) dm.getValueAt(i, 4));
        
        isItemUpdate = true;
        Itembtnscontroler();
    }//GEN-LAST:event_GreadAtItemsMousePressed

    private void UpdateDrugBtnAtItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateDrugBtnAtItemActionPerformed
        // TODO add your handling code here:
        String ItemName = ItemNameAtItem.getText();
        String Dosage = DosageAtItem.getText();
        String Type ="";
        String DrugType = DrugTypeAtItems.getSelectedItem().toString();
        
        if(DrugRadioAtItem.isSelected())
        {
            Type="Drug";
            ItemName = ItemName+"__("+Dosage+")";
        }
        else if(OthersRadioAtItem.isSelected())
        {
            Type="Others";
        }
        boolean OTC;
        if(OverTheCounterAtItemChkBox.isSelected())
        {
            OTC=true;
        }
        else
        {
            OTC=false;
        }
        
        try{
            st = conn.createStatement();
            String SQL = "Update item SET ItemName='"+ItemName+"', Type='"+Type+"',OTC='"+String.valueOf(OTC)+"',DrugType='"+DrugType+"' WHERE ItemID Like "+ItemIdAtItem.getText();
            st.executeUpdate(SQL);
            
            ItemGride();
            JOptionPane.showMessageDialog(null,"Updated Successfully");
            
            
            

        }
        catch (SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        ClearItem();
    }//GEN-LAST:event_UpdateDrugBtnAtItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ClearItem();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void DeleteDrugBtnAtItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteDrugBtnAtItemActionPerformed
        // TODO add your handling code here:
        String ItemID = ItemIdAtItem.getText();
        String name=ItemNameAtItem.getText();
        try{
            String sql = "DELETE from item where ItemID LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, ItemID);
            pst.execute();
            ItemGride();
            JOptionPane.showMessageDialog(null,"Deleted!");
            ClearItem();
            ItemNameComboBoxAtStock.removeItem(name);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_DeleteDrugBtnAtItemActionPerformed

    private void SearchComboBoxAtItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchComboBoxAtItemKeyReleased
        // TODO add your handling code here:
        String key = SearchComboBoxAtItem.getText();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
        try{
            String SQL;
            SQL = "SELECT * FROM item WHERE ItemID LIKE '%"+key+"%' OR ItemName LIKE '%"+key+"%' OR Type LIKE '%"+key+"%' OR DrugType LIKE '%"+key+"%' OR OTC LIKE '%"+key+"%'" ;
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs = pst.executeQuery();
            while(rs.next())
            {
                dm.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),Boolean.parseBoolean(rs.getString(4)),rs.getString(6),rs.getString(7),rs.getString(8)});
            }
            
            
        }
        catch(SQLException | NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_SearchComboBoxAtItemKeyReleased

    private void ItemNameAtItemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ItemNameAtItemFocusLost
        // TODO add your handling code here:
        if(ItemNameAtItem.getText().equals(""))
        {
            ValidationForItemNameAtItems.setText("Item name field can't be empty!");
            ValidationForItemNameAtItems.setForeground(Color.red);
        }
        else
        {
            ValidationForItemNameAtItems.setText("");
        }
        Itembtnscontroler();
    }//GEN-LAST:event_ItemNameAtItemFocusLost

    private void ItemNameAtItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ItemNameAtItemKeyReleased
        // TODO add your handling code here:
        if(ItemNameAtItem.getText().equals(""))
        {
            ValidationForItemNameAtItems.setText("Item name field can't be empty!");
            ValidationForItemNameAtItems.setForeground(Color.red);
        }
        else
        {
            ValidationForItemNameAtItems.setText("");
        }
        Itembtnscontroler();
    }//GEN-LAST:event_ItemNameAtItemKeyReleased

    private void DrugTypeAtItemsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DrugTypeAtItemsItemStateChanged
        // TODO add your handling code here:
        if(DrugTypeAtItems.getSelectedItem().toString().equals("Select") && DrugRadioAtItem.isSelected())
        {
            ValidationForDrugTypeAtItems.setText("you have to select the drug type.");
            ValidationForDrugTypeAtItems.setForeground(Color.red);
            
        }
        else
        {
            ValidationForDrugTypeAtItems.setText("");
            
        }
        Itembtnscontroler();
    }//GEN-LAST:event_DrugTypeAtItemsItemStateChanged

    private void DrugTypeAtItemsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DrugTypeAtItemsFocusLost
        if(DrugTypeAtItems.getSelectedItem().toString().equals("Select") && DrugRadioAtItem.isSelected())
        {
            ValidationForDrugTypeAtItems.setText("you have to select the drug type.");
            ValidationForDrugTypeAtItems.setForeground(Color.red);
            
        }
        else
        {
            ValidationForDrugTypeAtItems.setText("");
            
        }
        Itembtnscontroler();        // TODO add your handling code here:
    }//GEN-LAST:event_DrugTypeAtItemsFocusLost

    private void DosageAtItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DosageAtItemKeyReleased
        // TODO add your handling code here:
        if(DosageAtItem.getText().equals(""))
        {
            ValidationForDosageAtItems.setText("dosage fiels can't be empty");
            ValidationForDosageAtItems.setForeground(Color.red);
        }
        else
        {
            ValidationForDosageAtItems.setText("");
        }
        Itembtnscontroler();
    }//GEN-LAST:event_DosageAtItemKeyReleased

    private void DosageAtItemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DosageAtItemFocusLost
        // TODO add your handling code here:
        if(DosageAtItem.getText().equals(""))
        {
            ValidationForDosageAtItems.setText("dosage fiels can't be empty");
            ValidationForDosageAtItems.setForeground(Color.red);
        }
        else
        {
            ValidationForDosageAtItems.setText("");
        }
        Itembtnscontroler();
    }//GEN-LAST:event_DosageAtItemFocusLost

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
        String invoiceNo = InventoryNoAtStock.getText();
        int i =TableAtStock.getSelectedRow();
        String seqNo = dm1.getValueAt(i,0).toString();
        try{
            String sql = "DELETE from stock where invoiceNo LIKE ? AND seqNostock LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, invoiceNo);
            pst.setString(2, seqNo);
            pst.execute();
            //StockTotal=StockTotal-(Double.parseDouble((String) dm1.getValueAt(i,2))*Double.parseDouble((String) dm1.getValueAt(i,5)));

            getStock();
            JOptionPane.showMessageDialog(null,"Deleted!");
            ClearStock();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void TableAtStockMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableAtStockMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TableAtStockMousePressed

    private void ItemNameComboBoxAtStockItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ItemNameComboBoxAtStockItemStateChanged
        // TODO add your handling code here:
//        if(ItemNameComboBoxAtStock.getSelectedItem().toString().equals("Select"))
//        {
//            ValidationForItemNameAtStock.setText("Please Select an item");
//            ValidationForItemNameAtStock.setForeground(Color.red);
//        }
//        else
//        {
//            ValidationForItemNameAtStock.setText("");
//        }
//        stockbtncontroller();
        //ItemNameComboBoxAtStock.setSelectedItem("Select");
    }//GEN-LAST:event_ItemNameComboBoxAtStockItemStateChanged

    private void ItemNameComboBoxAtStockFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ItemNameComboBoxAtStockFocusLost
        // TODO add your handling code here:
          if(ItemNameComboBoxAtStock.getSelectedItem().toString().equals("Select"))
        {
            ValidationForItemNameAtStock.setText("Please Select an item");
            ValidationForItemNameAtStock.setForeground(Color.red);
        }
        else
        {
            ValidationForItemNameAtStock.setText("");
        }
        stockbtncontroller();
    }//GEN-LAST:event_ItemNameComboBoxAtStockFocusLost

    private void QuantitySpinnerAtStockFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_QuantitySpinnerAtStockFocusLost
      String s = QuantitySpinnerAtStock.getText();
        if(s.equals(""))
        {
            ValidationForQuantityAtStock.setText("Quantity field can't be empty");
            ValidationForQuantityAtStock.setForeground(Color.red);
        }
        else
        {
            for (int i = 0 ; i < s.length(); i++)
            {
                char a = s.charAt(i);
            
                if (Character.isDigit(a))
                {
                    ValidationForQuantityAtStock.setText("");
                }
                else
                {
                   ValidationForQuantityAtStock.setText("You only can have numbers in Quantity field!");
                   ValidationForQuantityAtStock.setForeground(Color.red);
                }
            
            }
        }   
        stockbtncontroller();
    }//GEN-LAST:event_QuantitySpinnerAtStockFocusLost

    private void QuantitySpinnerAtStockFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_QuantitySpinnerAtStockFocusGained
        // TODO add your handling code here:
          if(SearchComboAtStock.getSelectedItem().toString().equals("Select"))
        {
            ValidationForSuppliernameAtStock.setText("Please Select a Supplier");
            ValidationForSuppliernameAtStock.setForeground(Color.red);
        }
        else
        {
            ValidationForSuppliernameAtStock.setText("");
        }
        stockbtncontroller();
    }//GEN-LAST:event_QuantitySpinnerAtStockFocusGained

    private void SerialNoAtStockFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SerialNoAtStockFocusLost
        // TODO add your handling code here:
        String s = SerialNoAtStock.getText();
        if(s.equals(""))
        {
            ValidationForSerialNoAtStock.setText("Serial No field can't be empty");
            ValidationForSerialNoAtStock.setForeground(Color.red);
        }
        else
        {
            for (int i = 0 ; i < s.length(); i++)
            {
                char a = s.charAt(i);
            
                if (Character.isDigit(a))
                {
                    ValidationForSerialNoAtStock.setText("");
                }
                else
                {
                   ValidationForSerialNoAtStock.setText("You only can have numbers in Serial No field!");
                   ValidationForSerialNoAtStock.setForeground(Color.red);
                }
            
            }
        }
        stockbtncontroller();
    }//GEN-LAST:event_SerialNoAtStockFocusLost

    private void SerialNoAtStockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SerialNoAtStockKeyReleased
        // TODO add your handling code here:
        // TODO add your handling code here:
        String s = SerialNoAtStock.getText();
        if(s.equals(""))
        {
            ValidationForSerialNoAtStock.setText("Serial No field can't be empty");
            ValidationForSerialNoAtStock.setForeground(Color.red);
        }
        else
        {
            for (int i = 0 ; i < s.length(); i++)
            {
                char a = s.charAt(i);
            
                if (Character.isDigit(a))
                {
                    ValidationForSerialNoAtStock.setText("");
                }
                else
                {
                   ValidationForSerialNoAtStock.setText("You only can have numbers in Serial No field!");
                   ValidationForSerialNoAtStock.setForeground(Color.red);
                }
            
            }
        }
        stockbtncontroller();
    }//GEN-LAST:event_SerialNoAtStockKeyReleased

    private void PurPriceAtStockFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PurPriceAtStockFocusLost
        // TODO add your handling code here:
        
         String s = SerialNoAtStock.getText();
         
        if(s.equals(""))
        {
            ValidationForSerialNoAtStock.setText("Serial No field can't be empty");
            ValidationForSerialNoAtStock.setForeground(Color.red);
        }
        else
        {
            for (int i = 0 ; i < s.length(); i++)
            {
                char a = s.charAt(i);
            
                if (Character.isDigit(a))
                {
                    ValidationForSerialNoAtStock.setText("");
                }
                else
                {
                   ValidationForSerialNoAtStock.setText("You only can have numbers in Serial No field!");
                   ValidationForSerialNoAtStock.setForeground(Color.red);
                }
            
            }
        }
        stockbtncontroller();
        
    }//GEN-LAST:event_PurPriceAtStockFocusLost

    private void PurPriceAtStockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PurPriceAtStockKeyReleased
        // TODO add your handling code here:
            String s = PurPriceAtStock.getText();
        if(s.equals(""))
        {
            ValidationForPurchasingPriceAtStock.setText("Purchase Price field can't be empty");
            ValidationForPurchasingPriceAtStock.setForeground(Color.red);
        }
        else
        {
            for (int i = 0 ; i < s.length(); i++)
            {
                char a = s.charAt(i);
            
                if (Character.isDigit(a))
                {
                   ValidationForPurchasingPriceAtStock.setText("");
                }
                else
                {
                   ValidationForPurchasingPriceAtStock.setText("You only can have numbers in Purchase price field!");
                   ValidationForPurchasingPriceAtStock.setForeground(Color.red);
                }
            
            }
        }
        stockbtncontroller();
    }//GEN-LAST:event_PurPriceAtStockKeyReleased

    private void SearchComboAtStockFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchComboAtStockFocusLost
        // TODO add your handling code here:
          if(SearchComboAtStock.getSelectedItem().toString().equals("Select"))
        {
            ValidationForSuppliernameAtStock.setText("Please Select a Supplier");
            ValidationForSuppliernameAtStock.setForeground(Color.red);
        }
        else
        {
            ValidationForSuppliernameAtStock.setText("");
        }
        stockbtncontroller();
    }//GEN-LAST:event_SearchComboAtStockFocusLost

    private void SearchComboAtStockItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SearchComboAtStockItemStateChanged
        // TODO add your handling code here:
//          if(SearchComboAtStock.getSelectedItem().toString().equals("Select"))
//        {
//            ValidationForSuppliernameAtStock.setText("Please Select a Supplier");
//            ValidationForSuppliernameAtStock.setForeground(Color.red);
//        }
//        else
//        {
//            ValidationForSuppliernameAtStock.setText("");
//        }
        stockbtncontroller();
        //SearchComboAtStock.setSelectedItem("Select");
    }//GEN-LAST:event_SearchComboAtStockItemStateChanged

    private void ItemNameComboBoxAtStockFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ItemNameComboBoxAtStockFocusGained
        // TODO add your handling code here:
          if(SearchComboAtStock.getSelectedItem().toString().equals("Select"))
        {
            ValidationForSuppliernameAtStock.setText("Please Select a Supplier");
            ValidationForSuppliernameAtStock.setForeground(Color.red);
        }
        else
        {
            ValidationForSuppliernameAtStock.setText("");
        }
        stockbtncontroller();
    }//GEN-LAST:event_ItemNameComboBoxAtStockFocusGained

    private void SerialNoAtStockFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SerialNoAtStockFocusGained
        // TODO add your handling code here:
          if(SearchComboAtStock.getSelectedItem().toString().equals("Select"))
        {
            ValidationForSuppliernameAtStock.setText("Please Select a Supplier");
            ValidationForSuppliernameAtStock.setForeground(Color.red);
        }
        else
        {
            ValidationForSuppliernameAtStock.setText("");
        }
        stockbtncontroller();
    }//GEN-LAST:event_SerialNoAtStockFocusGained

    private void ExpiryDateAtStockFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ExpiryDateAtStockFocusGained
        // TODO add your handling code here:
          if(SearchComboAtStock.getSelectedItem().toString().equals("Select"))
        {
            ValidationForSuppliernameAtStock.setText("Please Select a Supplier");
            ValidationForSuppliernameAtStock.setForeground(Color.red);
        }
        else
        {
            ValidationForSuppliernameAtStock.setText("");
        }
        stockbtncontroller();
    }//GEN-LAST:event_ExpiryDateAtStockFocusGained

    private void PurPriceAtStockFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PurPriceAtStockFocusGained
        // TODO add your handling code here:
          if(SearchComboAtStock.getSelectedItem().toString().equals("Select"))
        {
            ValidationForSuppliernameAtStock.setText("Please Select a Supplier");
            ValidationForSuppliernameAtStock.setForeground(Color.red);
        }
        else
        {
            ValidationForSuppliernameAtStock.setText("");
        }
        stockbtncontroller();
    }//GEN-LAST:event_PurPriceAtStockFocusGained

    private void QuantitySpinnerAtStockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QuantitySpinnerAtStockKeyReleased
        // TODO add your handling code here:
              String s = QuantitySpinnerAtStock.getText();
        if(s.equals(""))
        {
            ValidationForQuantityAtStock.setText("Quantity field can't be empty");
            ValidationForQuantityAtStock.setForeground(Color.red);
        }
        else
        {
            for (int i = 0 ; i < s.length(); i++)
            {
                char a = s.charAt(i);
            
                if (Character.isDigit(a))
                {
                    ValidationForQuantityAtStock.setText("");
                }
                else
                {
                   ValidationForQuantityAtStock.setText("You only can have numbers in Quantity field!");
                   ValidationForQuantityAtStock.setForeground(Color.red);
                }
            
            }
        }   
        stockbtncontroller();

    }//GEN-LAST:event_QuantitySpinnerAtStockKeyReleased

    private void ExpiryDateAtStockFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ExpiryDateAtStockFocusLost
        // TODO add your handling code here:
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String cd = dateFormat.format(date);
        String cda[] = cd.split("-");
        
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String expDate = formatter.format(ExpiryDateAtStock.getDate());
        String exp [] = expDate.split("-");
        
        System.out.println(cda[2] +" < "+ exp[2]);
        if(Integer.parseInt(cda[2])<Integer.parseInt(exp[2]))
        {
            ValidationForExpirydateAtStock.setText("");
            
        }
        else if(Integer.parseInt(cda[1])<Integer.parseInt(exp[1]))
        {
            ValidationForExpirydateAtStock.setText("");
        }
        else if(Integer.parseInt(cda[0])<Integer.parseInt(exp[0]))
        {
            ValidationForExpirydateAtStock.setText("");
        }
        else
        {
            ValidationForExpirydateAtStock.setText("Select a valid date! Date has been passed!");
        }
        
        
        
        
    }//GEN-LAST:event_ExpiryDateAtStockFocusLost

    private void clearBtnAtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnAtStockActionPerformed
        // TODO add your handling code here:
//        SearchComboAtStock.setSelectedItem("Select");
//        ItemNameComboBoxAtStock.setSelectedItem("Select");
//        QuantitySpinnerAtStock.setText("");
//        SerialNoAtStock.setText("");
//        PurPriceAtStock.setText("");
//        SelPriceAtStock.setText("");

        ClearStock();
    }//GEN-LAST:event_clearBtnAtStockActionPerformed

    private void OverTheCounterAtItemChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OverTheCounterAtItemChkBoxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OverTheCounterAtItemChkBoxKeyPressed

    private void NotificationAtMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotificationAtMainMouseClicked
        // TODO add your handling code here:
        Notification nf = new Notification();
        nf.setVisible(true);
    }//GEN-LAST:event_NotificationAtMainMouseClicked

    private void ExpiryDateAlertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExpiryDateAlertMouseClicked
        // TODO add your handling code here:
        ExpiryDateAlert eda = new ExpiryDateAlert();
        eda.setVisible(true);
    }//GEN-LAST:event_ExpiryDateAlertMouseClicked


    //stock
    public void getStock()
    {
        
        while(dm1.getRowCount() > 0)
        {
            dm1.removeRow(0);
        }
        try{
            String SQL;
            SQL = "SELECT * FROM stock WHERE InvoiceNo LIKE '"+InvoiceNo+"'";
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs = pst.executeQuery();
            while(rs.next())
            {
                InvoiceNo = 1+Integer.parseInt(rs.getString(1));
                      //  Integer.parseInt(InventoryNoAtStock.getText());
                dm1.addRow(new Object[]{rs.getString(2),rs.getString(3),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),});
                ClearStock();
            }
            
            
        }
        catch(SQLException | NumberFormatException e)
        {
           // JOptionPane.showMessageDialog(null,e);
        }
    }
     
    public void ClearStock()
    {
        InventoryNoAtStock.setText(String.valueOf(InvoiceNo));
        seqNoAtStock.setText(String.valueOf(seqNostock));
        AddedDateAtStock.setText(CDate);
        ItemNameComboBoxAtStock.setSelectedItem("Select");
        QuantitySpinnerAtStock.setText("");
        SerialNoAtStock.setText("");
        PurPriceAtStock.setText("");
        SerialNoAtStock.setText("");
        ExpiryDateAtStock.setCalendar(null);
     
    }
    
    public void stockbtncontroller()
    {
        if(ValidationForItemNameAtStock.getText().equals("") && ValidationForQuantityAtStock.getText().equals("") && 
                ValidationForExpirydateAtStock.getText().equals("") && ValidationForSerialNoAtStock.getText().equals("")&&
                ValidationForPurchasingPriceAtStock.getText().equals("")&& ValidationForSuppliernameAtStock.getText().equals("")&&!ItemNameComboBoxAtStock.getSelectedItem().toString().equals("Select")&&
                !QuantitySpinnerAtStock.getText().equals("")&&!SerialNoAtStock.getText().equals("")&&
                !PurPriceAtStock.getText().equals("")&&!SearchComboAtStock.getSelectedItem().toString().equals("Select"))
        {
            AddBtnAtStock.setEnabled(true);
        
            
        }
        else
        {
            AddBtnAtStock.setEnabled(false);
            
        }
     
           
            
    }
    
    //Item
    public void ItemGride()
    {
    while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
        try{
            String SQL;
            SQL = "SELECT * FROM item";
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs = pst.executeQuery();
            while(rs.next())
            {
                dm.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),Boolean.parseBoolean(rs.getString(4)),rs.getString(6),rs.getString(7)});
            }
            
            
        }
        catch(SQLException | NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void ClearItem()
    {
        ItemIdAtItem.setText(String.valueOf(ItemID));
        ItemNameAtItem.setText("");
//        DrugRadioAtItem.setSelected(false);
//        OthersRadioAtItem.setSelected(false);
        TypeAtItems.clearSelection();
        OverTheCounterAtItemChkBox.setSelected(false);
        DrugTypeAtItems.setEnabled(false);
        DosageAtItem.setEnabled(false);
        DosageAtItem.setText("");
        DrugTypeAtItems.setSelectedIndex(0);
        
        ValidationForItemNameAtItems.setText("");
        DrugTypeAtItems.setSelectedItem("Select");
        ValidationForDrugTypeAtItems.setText("");
        DosageAtItem.setText("");
        ValidationForDosageAtItems.setText("");
        
        isItemUpdate = false;
        Itembtnscontroler();
    }
    
    public void Itembtnscontroler()
    {
        AddDrugBtnAtItem.setEnabled(false);
        UpdateDrugBtnAtItem.setEnabled(false);
        DeleteDrugBtnAtItem.setEnabled(false);
        String type = "";
        if(DrugRadioAtItem.isSelected())
        {
            type = "drug";
        }
        else if(OthersRadioAtItem.isSelected())
        {
            type = "other";
        }
        
        if(!ItemNameAtItem.getText().equals("") && ValidationForItemNameAtItems.getText().equals("") && type.equals("other"))
        {
            
            if(isItemUpdate)
            {
                UpdateDrugBtnAtItem.setEnabled(true);
                DeleteDrugBtnAtItem.setEnabled(true);
                
            }
            else
            {
                AddDrugBtnAtItem.setEnabled(true);
            }
        }
        else if(!ItemNameAtItem.getText().equals("") && ValidationForItemNameAtItems.getText().equals("") && type.equals("drug") && !DrugTypeAtItems.getSelectedItem().toString().equals("Select") && ValidationForDrugTypeAtItems.getText().equals("") && !DosageAtItem.getText().equals("") && ValidationForDosageAtItems.getText().equals(""))
        {
            if(isItemUpdate)
            {
                UpdateDrugBtnAtItem.setEnabled(true);
                DeleteDrugBtnAtItem.setEnabled(true);
                
            }
            else
            {
                AddDrugBtnAtItem.setEnabled(true);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtnAtStock;
    private javax.swing.JButton AddDrugBtnAtItem;
    private javax.swing.JTextField AddedDateAtStock;
    private javax.swing.JButton DeleteDrugBtnAtItem;
    private javax.swing.JTextField DosageAtItem;
    private javax.swing.JRadioButton DrugRadioAtItem;
    private javax.swing.JComboBox DrugTypeAtItems;
    private javax.swing.JLabel ExpiryDateAlert;
    private com.toedter.calendar.JDateChooser ExpiryDateAtStock;
    private javax.swing.JTable GreadAtItems;
    private javax.swing.JLabel InventoryNoAtStock;
    private javax.swing.JLabel ItemIdAtItem;
    private javax.swing.JTextField ItemNameAtItem;
    private javax.swing.JComboBox<String> ItemNameComboBoxAtStock;
    private javax.swing.JLabel NotificationAtMain;
    private javax.swing.JRadioButton OthersRadioAtItem;
    private javax.swing.JCheckBox OverTheCounterAtItemChkBox;
    private javax.swing.JTextField PurPriceAtStock;
    private javax.swing.JTextField QuantitySpinnerAtStock;
    private javax.swing.JComboBox<String> SearchComboAtStock;
    private javax.swing.JTextField SearchComboBoxAtItem;
    private javax.swing.JTextField SerialNoAtStock;
    private javax.swing.JTable TableAtStock;
    private javax.swing.ButtonGroup TypeAtItems;
    private javax.swing.JButton UpdateDrugBtnAtItem;
    private javax.swing.JLabel ValidationForDosageAtItems;
    private javax.swing.JLabel ValidationForDrugTypeAtItems;
    private javax.swing.JLabel ValidationForExpirydateAtStock;
    private javax.swing.JLabel ValidationForItemNameAtItems;
    private javax.swing.JLabel ValidationForItemNameAtStock;
    private javax.swing.JLabel ValidationForPurchasingPriceAtStock;
    private javax.swing.JLabel ValidationForQuantityAtStock;
    private javax.swing.JLabel ValidationForSerialNoAtStock;
    private javax.swing.JLabel ValidationForSuppliernameAtStock;
    private javax.swing.JButton clearBtnAtStock;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel seqNoAtStock;
    // End of variables declaration//GEN-END:variables
}
