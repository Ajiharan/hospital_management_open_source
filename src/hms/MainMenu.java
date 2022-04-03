package hms;


import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohamed Nifal
 */
public final class MainMenu extends javax.swing.JFrame {
    Connection conn = new DBConnection().connect();
    ResultSet rs;
    PreparedStatement pst;
    Statement st;
    DefaultTableModel dtm;
    int attendance_id = 1;
    int inventory_id = 1;
    int payments_id = 1;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        showTime();
        Update_table();
        //txt_inventory_expiry_date.setMinSelectableDate(new Date());        
        //txt_staff_date.setMinSelectableDate(new Date());
        
        //txt_attendance_id.setVisible(true);
        //txt_inventory_inventory_id.setVisible(true);
        txt_payments_id.setVisible(true);
        
        txt_payments_booking_id.setVisible(false);
        txt_payments_staff_id.setVisible(false);
        jLabel12.setVisible(false);
        jLabel13.setVisible(false);
        
        //setEnableRec(jPanel7, false);
        
        /*try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem", "root", "nifal");
            //JOptionPane.showMessageDialog(null,"DB conected");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        
        DBConnection d1 = new DBConnection();
        conn = d1.GetConnection();
        dtm = (DefaultTableModel) tbl_attendance_report.getModel();

        
        getattendance();
        Clearattendance();*/
        
        try{
            String SQL;
            SQL = "SELECT MAX(inventory_id) FROM inventory";
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs= pst.executeQuery();
            while(rs.next())
            {
                inventory_id = 1+Integer.parseInt(rs.getString(1));
                        //Integer.parseInt(jTextField16.getText());
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        //txt_inventory_inventory_id.setText(String.valueOf(inventory_id));
        
        try{
            String SQL;
            SQL = "SELECT MAX(attendance_id) FROM attendance";
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs= pst.executeQuery();
            while(rs.next())
            {
                attendance_id = 1+Integer.parseInt(rs.getString(1));
                        //Integer.parseInt(jTextField16.getText());
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        //txt_attendance_id.setText(String.valueOf(attendance_id));
        
        try{
            String SQL;
            SQL = "SELECT MAX(payments_id) FROM payments";
            PreparedStatement pst;
            pst = conn.prepareStatement(SQL);
            rs= pst.executeQuery();
            while(rs.next())
            {
                payments_id = 1+Integer.parseInt(rs.getString(1));
                        //Integer.parseInt(jTextField16.getText());
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        txt_payments_id.setText(String.valueOf(payments_id));
    }
    
    
    private void Update_table(){
        
    }
    
    public void search_cat(){
        try{
            String cat=txt_payments_category.getSelectedItem().toString();
        String sql = "select payments_id, sub_category, date, amount_in, amount_out from payments where category='"+cat+"'";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        tbl_payments_search.setModel(DbUtils.resultSetToTableModel(rs));
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
        public void search_cat1(){
        try{
            String cat=txt_inventory_category1.getSelectedItem().toString();
            if(cat.equals("All"))
            {
                String sql = "select inventory_id, category, item_code, item_name, sum(quantity) from inventory group by item_code ";
                //order by desc(sum(quantity))
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            }
            else{
        String sql = "select inventory_id, category, item_code, item_name, sum(quantity) from inventory where category='"+cat+"'group by item_code ";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            }
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
        
    
    void showTime(){
        new Timer(0,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
                //txt_staff_time.setText(s.format(d));
                //txt_payment_time.setText(s.format(d));
                //txt_inventory_time.setText(s.format(d));
                txt_payments_time.setText(s.format(d));
            }
        }).start();
        
        ButtonGroup Redio = new ButtonGroup();
        Redio.add(but_payments_patient);
        Redio.add(but_payments_salary);
        Redio.add(but_payments_purchase);
        Redio.add(but_payments_bill_payments);
        Redio.add(but_payments_other);
        
        but_payments_patient.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txt_payments_sub_category.removeAllItems();
                txt_payments_sub_category.addItem("Baby");
                txt_payments_sub_category.addItem("Male");
                txt_payments_sub_category.addItem("Female");

            }
        });
        
        but_payments_salary.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txt_payments_sub_category.removeAllItems();
                txt_payments_sub_category.addItem("Doctor");
                txt_payments_sub_category.addItem("Nurse");
                txt_payments_sub_category.addItem("Receptionist");
                txt_payments_sub_category.addItem("Pharmacist");
                txt_payments_sub_category.addItem("Cleaners");
                txt_payments_sub_category.addItem("Technecians");
                txt_payments_sub_category.addItem("Cleaners");
                txt_payments_sub_category.addItem("Others");

            }
        });
        but_payments_purchase.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txt_payments_sub_category.removeAllItems();
                txt_payments_sub_category.addItem("Pharmacy Drugs");
                txt_payments_sub_category.addItem("Furniture");
                txt_payments_sub_category.addItem("Vehicles");
                txt_payments_sub_category.addItem("Electrical");
                txt_payments_sub_category.addItem("Other");

            }
        });
        but_payments_bill_payments.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                txt_payments_sub_category.removeAllItems();
                txt_payments_sub_category.addItem("Current Bill");
                txt_payments_sub_category.addItem("Water Bill");
                txt_payments_sub_category.addItem("Telephone Bill");
                txt_payments_sub_category.addItem("Wi-fi Bill");
                txt_payments_sub_category.addItem("Other");

            }
        });
            but_payments_other.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                txt_payments_sub_category.removeAllItems();
                txt_payments_sub_category.addItem("Donation");
                txt_payments_sub_category.addItem("Technician");
                txt_payments_sub_category.addItem("Plumber");
                txt_payments_sub_category.addItem("Electrician");
                txt_payments_sub_category.addItem("Other");

            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_category = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txt_attendance_in_time = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txt_attendance_out_time = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        txt_attendance_date = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        txt_attendance_working_hours = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txt_attendance_over_time = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        txt_attendance_salary = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        txt_attendance_staff_id = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        txt_search_attendance_id = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        txt_payments_date = new com.toedter.calendar.JDateChooser();
        txt_payments_time = new javax.swing.JTextField();
        cmd_payments_out = new javax.swing.JButton();
        cmd_payments_in = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        txt_search_payments_id = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txt_payments_amount = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        but_payments_patient = new javax.swing.JRadioButton();
        but_payments_salary = new javax.swing.JRadioButton();
        but_payments_purchase = new javax.swing.JRadioButton();
        but_payments_bill_payments = new javax.swing.JRadioButton();
        but_payments_other = new javax.swing.JRadioButton();
        txt_payments_sub_category = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_payments_booking_id = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_payments_staff_id = new javax.swing.JTextField();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        txt_payments_id = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jButton49 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_payments_search = new javax.swing.JTable();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        txt_payments_category = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_inventory_item_code = new javax.swing.JTextField();
        txt_inventory_item_name = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_inventory_quantity = new javax.swing.JTextField();
        txt_inventory_unit_price = new javax.swing.JTextField();
        txt_search_inventory_inventory_id = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        txt_inventory_category1 = new javax.swing.JComboBox<>();
        jLabel65 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 700));

        jLabel1.setFont(new java.awt.Font("Footlight MT Light", 2, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 153));
        jLabel1.setText("Business and Finance Management");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 700));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Attendance Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 2, 18), new java.awt.Color(102, 0, 153))); // NOI18N

        jLabel28.setText("Staff ID");

        jLabel38.setText(":");

        jLabel58.setText("In Time");

        txt_attendance_in_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_attendance_in_timeActionPerformed(evt);
            }
        });

        jLabel59.setText("Out Time");

        txt_attendance_out_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_attendance_out_timeActionPerformed(evt);
            }
        });

        jLabel60.setText("Date");

        txt_attendance_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_attendance_dateActionPerformed(evt);
            }
        });

        jLabel61.setText("Working hours");

        jLabel62.setText("Over Time");

        txt_attendance_over_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_attendance_over_timeActionPerformed(evt);
            }
        });

        jLabel63.setText("Salary");

        txt_attendance_salary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_attendance_salaryActionPerformed(evt);
            }
        });

        jButton14.setText("Reset");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        txt_attendance_staff_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_attendance_staff_idActionPerformed(evt);
            }
        });
        txt_attendance_staff_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_attendance_staff_idKeyReleased(evt);
            }
        });

        jLabel92.setText(":");

        jLabel93.setText(":");

        jLabel94.setText(":");

        jLabel95.setText(":");

        jLabel96.setText(":");

        jLabel97.setText(":");

        txt_search_attendance_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_search_attendance_idActionPerformed(evt);
            }
        });
        txt_search_attendance_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search_attendance_idKeyReleased(evt);
            }
        });

        jButton3.setText("Calcuate");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setText("Search");

        jLabel48.setText(":");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel8)
                            .addComponent(jLabel60)
                            .addComponent(jLabel59)
                            .addComponent(jLabel58))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel94)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addComponent(txt_attendance_in_time, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel93)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_attendance_out_time, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_attendance_staff_id, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txt_search_attendance_id, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_attendance_date, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel97))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel62)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel96)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_attendance_salary, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_attendance_working_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_attendance_over_time, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 507, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search_attendance_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel48)
                    .addComponent(jLabel61)
                    .addComponent(txt_attendance_working_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel97))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_attendance_staff_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62)
                            .addComponent(txt_attendance_over_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel96))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel92)
                                .addGap(192, 192, 192))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel63)
                                    .addComponent(txt_attendance_salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel95))
                                .addGap(183, 183, 183))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(txt_attendance_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58)
                            .addComponent(jLabel94)
                            .addComponent(txt_attendance_in_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(jLabel93)
                            .addComponent(txt_attendance_out_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton14))
                        .addGap(32, 32, 32))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(47, 47, 47))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Staff", jPanel1);

        jPanel14.setBackground(new java.awt.Color(153, 153, 153));

        txt_payments_date.setDateFormatString("yyyy/MM/d");

        txt_payments_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_payments_timeActionPerformed(evt);
            }
        });

        cmd_payments_out.setText("Spend");
        cmd_payments_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_payments_outActionPerformed(evt);
            }
        });

        cmd_payments_in.setText("Income");
        cmd_payments_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_payments_inActionPerformed(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Payments", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 2, 18), new java.awt.Color(102, 0, 153))); // NOI18N

        jLabel37.setText("Payment ID");

        txt_search_payments_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search_payments_idKeyReleased(evt);
            }
        });

        jLabel52.setText(":");

        jLabel55.setText("Amount");

        jLabel66.setText(":");

        jPanel16.setBackground(new java.awt.Color(153, 153, 153));

        jLabel53.setText("Category");

        but_payments_patient.setBackground(new java.awt.Color(153, 153, 153));
        btn_category.add(but_payments_patient);
        but_payments_patient.setText("Patient");
        but_payments_patient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_payments_patientActionPerformed(evt);
            }
        });
        but_payments_patient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                but_payments_patientKeyPressed(evt);
            }
        });

        but_payments_salary.setBackground(new java.awt.Color(153, 153, 153));
        btn_category.add(but_payments_salary);
        but_payments_salary.setText("Salary");
        but_payments_salary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_payments_salaryActionPerformed(evt);
            }
        });

        but_payments_purchase.setBackground(new java.awt.Color(153, 153, 153));
        btn_category.add(but_payments_purchase);
        but_payments_purchase.setText("Purchase");

        but_payments_bill_payments.setBackground(new java.awt.Color(153, 153, 153));
        btn_category.add(but_payments_bill_payments);
        but_payments_bill_payments.setText("Bill Payments");

        but_payments_other.setBackground(new java.awt.Color(153, 153, 153));
        btn_category.add(but_payments_other);
        but_payments_other.setText("Other");

        txt_payments_sub_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));

        jLabel54.setText("Sub Category");

        jLabel12.setText("Booking ID");

        txt_payments_booking_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_payments_booking_idActionPerformed(evt);
            }
        });

        jLabel13.setText("Staff ID");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(but_payments_salary)
                            .addComponent(but_payments_patient)
                            .addComponent(jLabel53))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54)
                            .addComponent(txt_payments_sub_category, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(but_payments_other)
                            .addComponent(but_payments_purchase)
                            .addComponent(but_payments_bill_payments))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(txt_payments_booking_id, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_payments_staff_id, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(but_payments_patient, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(but_payments_salary))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_payments_sub_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(but_payments_purchase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(but_payments_bill_payments)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(but_payments_other)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_payments_booking_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_payments_staff_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton46.setText("Update In");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        jButton47.setText("Delete");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jButton48.setText("Reset");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        txt_payments_id.setBackground(new java.awt.Color(255, 255, 255));
        txt_payments_id.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));
        txt_payments_id.setOpaque(true);

        jLabel11.setText("Search");

        jLabel56.setText(":");

        jButton49.setText("Update Out");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel14.setText("Pharmacy Price Control");

        jButton6.setText("Assign Price");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton9.setText("Report");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel66)
                        .addGap(18, 18, 18)
                        .addComponent(txt_payments_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_search_payments_id, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_payments_id, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton49, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search_payments_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel56)
                    .addComponent(jButton9))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel37)
                        .addComponent(jLabel52))
                    .addComponent(txt_payments_id, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(txt_payments_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton46)
                        .addGap(18, 18, 18)
                        .addComponent(jButton49)
                        .addGap(18, 18, 18)
                        .addComponent(jButton47)
                        .addGap(18, 18, 18)
                        .addComponent(jButton48)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(204, 204, 204));

        tbl_payments_search.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Payment ID", "Sub category", "Date", "Amount_In", "Amount_Out"
            }
        ));
        jScrollPane7.setViewportView(tbl_payments_search);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel67.setText("Category");

        jLabel68.setText(":");

        txt_payments_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Patient", "Salary", "Purchase", "Bill Payments", "Other" }));
        txt_payments_category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_payments_categoryMousePressed(evt);
            }
        });
        txt_payments_category.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_payments_categoryPropertyChange(evt);
            }
        });

        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmd_payments_in, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_payments_date, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_payments_time, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmd_payments_out, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_payments_category, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_payments_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_payments_time, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmd_payments_in, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmd_payments_out, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel67)
                            .addComponent(jLabel68)
                            .addComponent(txt_payments_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 261, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Payments", jPanel14);

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inventory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Rounded MT Bold", 2, 18), new java.awt.Color(102, 0, 153))); // NOI18N

        jLabel16.setText("Item Code");

        jLabel17.setText("Item Name");

        txt_inventory_item_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inventory_item_codeActionPerformed(evt);
            }
        });

        txt_inventory_item_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inventory_item_nameActionPerformed(evt);
            }
        });

        jLabel20.setText("Quantity");

        jLabel21.setText("Unit Price");

        txt_inventory_unit_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_inventory_unit_priceKeyReleased(evt);
            }
        });

        txt_search_inventory_inventory_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_search_inventory_inventory_idActionPerformed(evt);
            }
        });
        txt_search_inventory_inventory_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search_inventory_inventory_idKeyReleased(evt);
            }
        });

        jLabel26.setText(":");

        jLabel31.setText(":");

        jLabel35.setText(":");

        jLabel36.setText(":");

        jLabel9.setText("Search");

        jButton35.setText("Reset");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addGap(30, 30, 30)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addGap(28, 28, 28)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_inventory_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_inventory_unit_price, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_search_inventory_inventory_id, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_inventory_item_name, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txt_inventory_item_code))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search_inventory_inventory_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(53, 53, 53)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel31)
                    .addComponent(txt_inventory_item_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel17)
                    .addComponent(txt_inventory_item_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel26)
                    .addComponent(txt_inventory_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel36)
                    .addComponent(txt_inventory_unit_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton35)
                .addContainerGap(207, Short.MAX_VALUE))
        );

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Inventory ID", "Category", "Item Code", "Item Name", "Quantity"
            }
        ));
        jScrollPane6.setViewportView(jTable5);

        txt_inventory_category1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Pharmacy Drugs", "Furniture", "Vehicles", "Electrical", "Other", "All" }));

        jLabel65.setText("Category");

        jLabel49.setText(":");

        jButton8.setText("Search");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_inventory_category1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jLabel49)
                    .addComponent(txt_inventory_category1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addGap(235, 235, 235))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Inventory", jPanel3);

        jButton1.setText("Accounts");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton16.setText("Log Out");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(275, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
        Accounts ac = new Accounts();
        ac.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmd_payments_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_payments_inActionPerformed
        // TODO add your handling code here:

        //int staff_id=Integer.parseInt(txt_staff_id.getText());
        String category ="";
        if(but_payments_patient.isSelected())
        {
            category="Patient";
            //ItemName = ItemName+"__("+Dosage+")";
        }
        else if(but_payments_salary.isSelected())
        {
            category="Salary";
        }
        else if(but_payments_purchase.isSelected())
        {
            category="Purchase";
        }
        else if(but_payments_bill_payments.isSelected())
        {
            category="Bill Payments";
        }
        else if(but_payments_other.isSelected())
        {
            category="Other";
        }

        String sub_category=txt_payments_sub_category.getSelectedItem().toString();

        double amount_in=Double.parseDouble(txt_payments_amount.getText());

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(txt_payments_date.getDate());

        //String attendance_out_time ="";

        //int attendance_working_hour =Integer.parseInt("");

        //int attendance_over_time =Integer.parseInt("");

        //int attendance_salary =Integer.parseInt("");

        try{
            pst=conn.prepareStatement("Insert into payments values (?, ?, ?, ?, ?, ?, ?, ?)");

            pst.setInt(1, payments_id);
            pst.setString(2,category);
            pst.setString(3,String.valueOf(sub_category));
            pst.setDouble(4, amount_in);
            pst.setNull(5, java.sql.Types.DOUBLE);
            pst.setString(6, date);
            pst.setNull(7, java.sql.Types.INTEGER);
            pst.setNull(8, java.sql.Types.INTEGER);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Inserted Successfully");
            dtm.addRow(new Object[]{String.valueOf(payments_id),category,sub_category,amount_in,date});
            payments_id++;

        }
        catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
        txt_payments_id.setText(String.valueOf(payments_id));
    }//GEN-LAST:event_cmd_payments_inActionPerformed

    private void cmd_payments_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_payments_outActionPerformed
        // TODO add your handling code here:
        
        String category ="";
        if(but_payments_patient.isSelected())
        {
            category="Patient";
            //ItemName = ItemName+"__("+Dosage+")";
        }
        else if(but_payments_salary.isSelected())
        {
            category="Salary";
        }
        else if(but_payments_purchase.isSelected())
        {
            category="Purchase";
        }
        else if(but_payments_bill_payments.isSelected())
        {
            category="Bill Payments";
        }
        else if(but_payments_other.isSelected())
        {
            category="Other";
        }

        String sub_category=txt_payments_sub_category.getSelectedItem().toString();

        double amount_out=Double.parseDouble(txt_payments_amount.getText());

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(txt_payments_date.getDate());

        //String attendance_out_time ="";

        //int attendance_working_hour =Integer.parseInt("");

        //int attendance_over_time =Integer.parseInt("");

        //int attendance_salary =Integer.parseInt("");

        try{
            pst=conn.prepareStatement("Insert into payments values (?, ?, ?, ?, ?, ?, ?, ?)");

            pst.setInt(1, payments_id);
            pst.setString(2,category);
            pst.setString(3,String.valueOf(sub_category));
            pst.setNull(4, java.sql.Types.DOUBLE);
            pst.setDouble(5, amount_out);
            pst.setString(6, date);
            pst.setNull(7, java.sql.Types.INTEGER);
            pst.setNull(8, java.sql.Types.INTEGER);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Inserted Successfully");
            dtm.addRow(new Object[]{String.valueOf(payments_id),category,sub_category,amount_out,date});
            ++payments_id;

        }
        catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_cmd_payments_outActionPerformed

    private void txt_payments_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_payments_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_payments_timeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        String time1 = txt_attendance_in_time.getText();
        String time2 = txt_attendance_out_time.getText();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try{
            Date attendance_in_time = format.parse(time1);
            Date attendance_out_time = format.parse(time2);
            long working_hours = attendance_out_time.getTime() - attendance_in_time.getTime();

            //String wh = Long.toString(working_hours);
            //int w = Integer.parseInt(wh);
            working_hours = working_hours/3600000;

            long over_time = working_hours- 8;

            txt_attendance_working_hours.setText(working_hours+"");

            if(over_time>=0)
            {
                txt_attendance_over_time.setText(over_time+"");
                double salary=working_hours*2000+over_time*1000;
                txt_attendance_salary.setText(salary+"");
            }
            else{
                txt_attendance_over_time.setText("0");
                double salary=working_hours*2000;
                txt_attendance_salary.setText(salary+"");
            }
            
            
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }

        /*int a =10;
        int b = 5;

        int c= a+b;

        txt_attendance_salary.setText(c+"");*/
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_search_attendance_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search_attendance_idKeyReleased
        // TODO add your handling code here:
        

        
        //if(txt_search_attendance_id.getText().equals(""))
        //{
           // txt_attendance_id.setVisible(true);
        //}
        
        
        
        
        try{
            String sql = "select * from attendance where attendance_id = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txt_search_attendance_id.getText());

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                /*String add1 = rs.getString("first_name");
                txt_attendance_first_name.setText(add1);

                String add2 = rs.getString("last_name");
                txt_attendance_last_name.setText(add2);

                String add4 = rs.getString("gender");
                txt_attendance_gender.setText(add4);

                String add5 = rs.getString("position");
                txt_attendance_position.setText(add5);

                String add6 = rs.getString("phone");
                txt_attendance_phone.setText(add6);*/

                String add9 = rs.getString("staff_id");
                txt_attendance_staff_id.setText(add9);

                String add10 = rs.getString("attendance_date");
                txt_attendance_date.setText(add10);

                String add11 = rs.getString("attendance_in_time");
                txt_attendance_in_time.setText(add11);

                String add12 = rs.getString("attendance_out_time");
                txt_attendance_out_time.setText(add12);

                String add13 = rs.getString("attendance_working_hours");
                txt_attendance_working_hours.setText(add13);

                String add14 = rs.getString("attendance_over_time");
                txt_attendance_over_time.setText(add14);

                String add15 = rs.getString("attendance_salary");
                txt_attendance_salary.setText(add15);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txt_search_attendance_idKeyReleased

    private void txt_search_attendance_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_search_attendance_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_search_attendance_idActionPerformed

    private void txt_attendance_staff_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_attendance_staff_idKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_attendance_staff_idKeyReleased

    private void txt_attendance_staff_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_attendance_staff_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_attendance_staff_idActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:

        txt_search_attendance_id.setText("");
        txt_attendance_staff_id.setText("");
        txt_attendance_date.setText("");
        txt_attendance_in_time.setText("");
        txt_attendance_out_time.setText("");
        txt_attendance_working_hours.setText("");
        txt_attendance_over_time.setText("");
        txt_attendance_salary.setText("");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void txt_attendance_over_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_attendance_over_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_attendance_over_timeActionPerformed

    private void txt_attendance_out_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_attendance_out_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_attendance_out_timeActionPerformed

    private void txt_attendance_in_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_attendance_in_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_attendance_in_timeActionPerformed

    private void txt_search_payments_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search_payments_idKeyReleased
        // TODO add your handling code here:
        
        txt_payments_id.setVisible(false);
        
        if(txt_search_payments_id.getText().equals(""))
        {
            txt_payments_id.setVisible(true);
        }
        
        try{
            String sql = "select * from payments where payments_id = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txt_search_payments_id.getText());

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                if(rs.getString("amount_in").equals(""))
                {    
                String add1 = rs.getString("amount_out");
                txt_payments_amount.setText(add1);
                
                //String add2 = rs.getString("category");
                //but_payments_salary.setSelectedIcon(add2);
                
                //String add3 = rs.getString("sub_category");
                //txt_payments_sub_category.setSelectedItem(add3);
                
                }
                else{
                
                String add1 = rs.getString("amount_in");
                txt_payments_amount.setText(add1);
                }
                
            }
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txt_search_payments_idKeyReleased

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // TODO add your handling code here:
        
        String category ="";
        if(but_payments_patient.isSelected())
        {
            category="Patient";
            //ItemName = ItemName+"__("+Dosage+")";
        }
        else if(but_payments_salary.isSelected())
        {
            category="Salary";
        }
        else if(but_payments_purchase.isSelected())
        {
            category="Purchase";
        }
        else if(but_payments_bill_payments.isSelected())
        {
            category="Bill Payments";
        }
        else if(but_payments_other.isSelected())
        {
            category="Other";
        }

        String sub_category=txt_payments_sub_category.getSelectedItem().toString();

        double amount_in=Double.parseDouble(txt_payments_amount.getText());

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(txt_payments_date.getDate());

        try{
            st = conn.createStatement();
            String SQL = "Update payments SET "
            + "category='"+category+"',"
            + "sub_category='"+sub_category+"',"
            + "amount_in='"+amount_in+"',"
            //+ "amount_out='"+null+"',"
            + "date='"+date+"' WHERE payments_id Like "+txt_search_payments_id.getText();
            st.executeUpdate(SQL);

            //ItemGride();
            JOptionPane.showMessageDialog(null,"Updated Successfully");

        }
        catch (SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        // TODO add your handling code here:
        
        try{
            String sql = "DELETE from payments where payments_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_search_payments_id.getText());
            pst.execute();
            //tbl_attendance_report();
            JOptionPane.showMessageDialog(null,"Deleted!");
            //ClearItem();
            //ItemNameComboBoxAtStock.removeItem(name);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:
        
        String category ="";
        if(but_payments_patient.isSelected())
        {
            category="Patient";
            //ItemName = ItemName+"__("+Dosage+")";
        }
        else if(but_payments_salary.isSelected())
        {
            category="Salary";
        }
        else if(but_payments_purchase.isSelected())
        {
            category="Purchase";
        }
        else if(but_payments_bill_payments.isSelected())
        {
            category="Bill Payments";
        }
        else if(but_payments_other.isSelected())
        {
            category="Other";
        }

        String sub_category=txt_payments_sub_category.getSelectedItem().toString();

        double amount_out=Double.parseDouble(txt_payments_amount.getText());

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(txt_payments_date.getDate());

        try{
            st = conn.createStatement();
            String SQL = "Update payments SET "
            + "category='"+category+"',"
            + "sub_category='"+sub_category+"',"
            + "amount_out='"+amount_out+"',"
            //+ "amount_in='"+null+"',"
            + "date='"+date+"' WHERE payments_id Like "+txt_search_payments_id.getText();
            st.executeUpdate(SQL);

            //ItemGride();
            JOptionPane.showMessageDialog(null,"Updated Successfully");

        }
        catch (SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        // TODO add your handling code here:
        
        txt_search_payments_id.setText("");
        txt_payments_id.setText("");
        txt_payments_booking_id.setText("");
        txt_payments_staff_id.setText("");
        txt_payments_amount.setText("");
        
    }//GEN-LAST:event_jButton48ActionPerformed

    private void txt_payments_booking_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_payments_booking_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_payments_booking_idActionPerformed

    private void but_payments_patientKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_but_payments_patientKeyPressed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_but_payments_patientKeyPressed

    private void but_payments_patientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_payments_patientActionPerformed
        // TODO add your handling code here:
        txt_payments_booking_id.setVisible(true);
        jLabel12.setVisible(true);
    }//GEN-LAST:event_but_payments_patientActionPerformed

    private void but_payments_salaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_payments_salaryActionPerformed
        // TODO add your handling code here:
        txt_payments_staff_id.setVisible(true);
        jLabel13.setVisible(true);
    }//GEN-LAST:event_but_payments_salaryActionPerformed

    private void txt_payments_categoryPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_payments_categoryPropertyChange
       
    }//GEN-LAST:event_txt_payments_categoryPropertyChange

    private void txt_payments_categoryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_payments_categoryMousePressed
         
    }//GEN-LAST:event_txt_payments_categoryMousePressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        search_cat();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.hide();
       new pms_pricecontroling().setVisible(true);
       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
         try{
           String report="C:\\Users\\Haran\\Documents\\NetBeansProjects\\ps_Atlanta\\src\\hms_report\\Payments.jrxml";
           JasperReport jasp=JasperCompileManager.compileReport(report);
           JasperPrint jas_print=JasperFillManager.fillReport(jasp,null,conn);
           JasperViewer.viewReport(jas_print);
       }
       catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
       }
    
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void txt_attendance_salaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_attendance_salaryActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_attendance_salaryActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        search_cat1();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:

        txt_search_inventory_inventory_id.setText("");
        txt_inventory_item_code.setText("");
        txt_inventory_item_name.setText("");
        txt_inventory_quantity.setText("");
        txt_inventory_unit_price.setText("");
    }//GEN-LAST:event_jButton35ActionPerformed

    private void txt_search_inventory_inventory_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search_inventory_inventory_idKeyReleased
        // TODO add your handling code here:

        //txt_inventory_inventory_id.setVisible(false);

        //if(txt_search_inventory_inventory_id.getText().equals(""))
        //{
            //txt_inventory_inventory_id.setVisible(true);
            //}

        try{
            String sql = "select * from inventory where inventory_id = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txt_search_inventory_inventory_id.getText());

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
              

                String add2 = rs.getString("item_code");
                txt_inventory_item_code.setText(add2);

                String add3 = rs.getString("item_name");
                txt_inventory_item_name.setText(add3);

                //String add4 = rs.getString("expiry_date");
                //txt_inventory_expiry_date.set(add4);

                String add5 = rs.getString("quantity");
                txt_inventory_quantity.setText(add5);

                String add6 = rs.getString("unit_price");
                txt_inventory_unit_price.setText(add6);

                //String add7 = rs.getString("unit_price");
                //txt_inventory_unit_price.setText(add6);

            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_txt_search_inventory_inventory_idKeyReleased

    private void txt_search_inventory_inventory_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_search_inventory_inventory_idActionPerformed
        // TODO add your handling code here:
        
       
    }//GEN-LAST:event_txt_search_inventory_inventory_idActionPerformed

    private void txt_inventory_unit_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_inventory_unit_priceKeyReleased
        // TODO add your handling code here

        
    }//GEN-LAST:event_txt_inventory_unit_priceKeyReleased

    private void txt_inventory_item_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inventory_item_codeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_inventory_item_codeActionPerformed

    private void txt_inventory_item_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inventory_item_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_inventory_item_nameActionPerformed

    private void txt_attendance_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_attendance_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_attendance_dateActionPerformed
    
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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
    
    /*private void setEnableRec(Component container, boolean enable){
    container.setEnabled(enable);

    try {
        Component[] components= ((Container) container).getComponents();
        for (int i = 0; i < components.length; i++) {
            setEnableRec(components[i], enable);
        }
    } catch (ClassCastException e) {

    }
    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btn_category;
    private javax.swing.JRadioButton but_payments_bill_payments;
    private javax.swing.JRadioButton but_payments_other;
    private javax.swing.JRadioButton but_payments_patient;
    private javax.swing.JRadioButton but_payments_purchase;
    private javax.swing.JRadioButton but_payments_salary;
    private javax.swing.JButton cmd_payments_in;
    private javax.swing.JButton cmd_payments_out;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable tbl_payments_search;
    private javax.swing.JTextField txt_attendance_date;
    private javax.swing.JTextField txt_attendance_in_time;
    private javax.swing.JTextField txt_attendance_out_time;
    private javax.swing.JTextField txt_attendance_over_time;
    private javax.swing.JTextField txt_attendance_salary;
    private javax.swing.JTextField txt_attendance_staff_id;
    private javax.swing.JTextField txt_attendance_working_hours;
    private javax.swing.JComboBox<String> txt_inventory_category1;
    private javax.swing.JTextField txt_inventory_item_code;
    private javax.swing.JTextField txt_inventory_item_name;
    private javax.swing.JTextField txt_inventory_quantity;
    private javax.swing.JTextField txt_inventory_unit_price;
    private javax.swing.JTextField txt_payments_amount;
    private javax.swing.JTextField txt_payments_booking_id;
    private javax.swing.JComboBox<String> txt_payments_category;
    private com.toedter.calendar.JDateChooser txt_payments_date;
    private javax.swing.JLabel txt_payments_id;
    private javax.swing.JTextField txt_payments_staff_id;
    private javax.swing.JComboBox<String> txt_payments_sub_category;
    private javax.swing.JTextField txt_payments_time;
    private javax.swing.JTextField txt_search_attendance_id;
    private javax.swing.JTextField txt_search_inventory_inventory_id;
    private javax.swing.JTextField txt_search_payments_id;
    // End of variables declaration//GEN-END:variables

    private void ClearItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    private void getattendance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void Clearattendance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
