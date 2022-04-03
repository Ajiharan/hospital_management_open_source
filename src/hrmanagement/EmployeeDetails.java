/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrmanagement;

import database.DBConnection;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;
import link1.LinkListX;
import link1.LinkX;
import org.jdom.DocType;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.engine.*;

/**
 *
 * @author Haran
 */
public class EmployeeDetails extends javax.swing.JFrame {
    private String uType;
    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
     private static LinkListX newlink;  
    private static LinkX temp;
    private static int count=0;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     private  boolean search=false;
    boolean dobcheck,pass1_check,pass2_check,quali_check,lang_check,check_qualification,nic_check,lname_check,fname_check,
            employee_check,des_check,status_check,gender_check,joindate_check,email_check,address_check,contact_check;
     private DefaultTableModel model;
    
    public void  Edit_selected_Employee_Details(){
       if(bt_search.isEnabled()){
        btn_next.setEnabled(true);
         btn_prev.setEnabled(true);
        btn_update.setEnabled(true);
        check_emp_password.setEnabled(true);
        bt_delete.setEnabled(true);
       id.setText(String.valueOf(model.getValueAt(employee_table.getSelectedRow(), 0)));
       set_next_details(String.valueOf(model.getValueAt(employee_table.getSelectedRow(), 0)));
       txt_name.setText(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),1)));
       txt_last.setText(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),2)));
       
       String gender_Type=String.valueOf(model.getValueAt(employee_table.getSelectedRow(), 3));
       
       if(gender_Type.equals("Male")){
           rb_male.setSelected(true);
          
       }
       else{
            rb_female.setSelected(true);
               
       }
      j_status.setSelectedItem(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),4)));
      
      
                        Date dateValue1=null;
                         SimpleDateFormat date2 = new SimpleDateFormat("dd/MM/yyyy");
                    
        try {
            dateValue1 = date2.parse(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),5)));
             datedob.setDate(dateValue1);
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
                       
                     
       txt_nic.setText(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),6)));
       txt_contact.setText(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),7)));
       txt_email.setText(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),8)));
       txt_address.setText(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),9)));
       ch_ol.setSelected(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),10)).equals("OL"));
       ch_al.setSelected(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),11)).equals("AL"));
       ch_oth.setSelected(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),12)).equals("OTHERS"));
       txt_qualification.setText(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),13)));
       ch_english.setSelected(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),14)).equals("English"));
       ch_tamil.setSelected(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),15)).equals("Tamil"));
       ch_sinhala.setSelected(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),16)).equals("Singala"));
        s_employee.setSelectedItem(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),17)));
        
        String doc_type=String.valueOf(model.getValueAt(employee_table.getSelectedRow(),18));
        
        if(doc_type.equals("Visiting")){
            rb_temprory.setSelected(true);
             rb_permanant.setSelected(false);
        }
        else if(doc_type.equals("Permanant")){
           rb_permanant.setSelected(true);
             rb_temprory.setSelected(false);
        }
      
                          Date dateValue3=null;
                         SimpleDateFormat date3 = new SimpleDateFormat("dd/MM/yyyy");
                    
        try {
            dateValue3 = date3.parse(String.valueOf(model.getValueAt(employee_table.getSelectedRow(),19)));
             datejoin.setDate(dateValue3);
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       else{
           btn_update.setEnabled(false);
          
       }
    }           
                   

    private void check(){
        if(txt_password1.getText().isEmpty()){
            pass1_check =false;
            e_password1.setText("Field is Empty");
        }
        else if(txt_password1.getText().length()!=8){
            pass1_check =false;
            e_password1.setText("Field contain mimimum 8 character");
        }
        else if(!txt_password1.getText().equals(txt_password2.getText())){
            pass1_check=false;
           
            e_password1.setText("Password not match");
            
        }
        else{
             pass1_check=true;
            e_password1.setText("");
        }
        
         if(txt_password2.getText().isEmpty()){
            pass2_check =false;
            e_password2.setText("Field is Empty");
        }
        else if(txt_password2.getText().length()!=8){
            pass2_check =false;
            e_password2.setText("Field contain mimimum 8 character");
        }
        else if(!txt_password1.getText().equals(txt_password2.getText())){
           
            pass2_check=false;
           
            e_password2.setText("Password not match");
        }
        else{
             e_password2.setText("");
            pass2_check=true;
        }
         
        if(ch_ol.isSelected() || ch_al.isSelected() || ch_oth.isSelected()){
            e_qualification.setText("");
            quali_check=true;
        }
        else{
            quali_check=false;
            e_qualification.setText("select qualification");
        }
         if(ch_english.isSelected() || ch_tamil.isSelected() || ch_sinhala.isSelected()){
             lang_check=true;
             e_language.setText("");
         }
         else{
             e_language.setText("select language");
             lang_check=false;
             
         }
         
         if(s_employee.getSelectedIndex()==1){
             rb_permanant.setEnabled(true);
             rb_temprory.setEnabled(true);
             if(rb_permanant.isSelected() || rb_temprory.isSelected()){
                 e_employee.setText("");
                 employee_check=true;
             }
             else{
                 e_employee.setText("select Type");
                 employee_check=false;
             }
             des_check=true;
         }
         else if(s_employee.getSelectedIndex()==0){
                e_desigination.setText("select Your Designation");
               rb_permanant.setEnabled(false);
               rb_temprory.setEnabled(false);
               des_check=false;
               
         }
         else{
             e_desigination.setText("");
               rb_permanant.setEnabled(false);
               rb_temprory.setEnabled(false);
               des_check=true;
               employee_check=true;
         }
         
         if(j_status.getSelectedIndex()==0){
             e_status.setText("select status");
             status_check=false;
         }
         else{
             e_status.setText("");
             status_check=true;
         }
        if(rb_male.isSelected() || rb_female.isSelected())
        {
            gender_check=true;
            e_gender.setText("");
        }
        else
        {
            gender_check=false;
            e_gender.setText("Select  Gender");
        }
        
        if(txt_qualification.getText().isEmpty()){
            check_qualification=false;
            e_qualificationdetails.setText("Field is Empty");
        }
        else{
             check_qualification=true;
            e_qualificationdetails.setText("");
        }
        if(datejoin.getDate()==null)
        {
            joindate_check=false;
            e_joindate.setText("Select a date");
        }
        else
        {
            joindate_check=true;
            e_joindate.setText("");
        }
        
        if(txt_email.getText().isEmpty())
        {
            e_email.setText("Input your email address");
            email_check=false;
        }
        else
        {
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(txt_email.getText());
            if(m.matches())
            {
                email_check=true;
                e_email.setText("");
            }
            else
            {
                email_check=false;
                e_email.setText("Input a correcct email address");
            }
        }
        if(txt_address.getText().isEmpty())
        {
            address_check=false;
            e_address.setText("Input Your address");
        }
        else
        {
            address_check=true;
            e_address.setText("");
        }
        if(txt_nic.getText().isEmpty())
        {
            nic_check=false;
            e_nic.setText("Input your NIC number");
        }
        else
        {
            nic_check=true;
            e_nic.setText("");
        }
        
        if(txt_contact.getText().isEmpty())
        {
            contact_check=false;
            e_contact.setText("Input your contact");
        }
        else
        {
            contact_check=true;
            e_contact.setText("");
        }
         if(datedob.getDate()==null)
        {
            dobcheck=false;
            e_dob.setText("Select a date");
        }
        else
        {
            dobcheck=true;
            e_dob.setText("");
        }
        
        if(txt_last.getText().isEmpty())
        {
            lname_check=false;
            e_last.setText("Input your Lastname");
        }
        else
        {
            lname_check=true;
            e_last.setText("");
        }
         if(txt_name.getText().isEmpty())
        {
            fname_check=false;
            e_name.setText("Input your Firstname");
        }
        else
        {
            fname_check=true;
            e_name.setText("");
        }
         String sql="select * from employee";
         int count1=0;
         int count2=0;
         try{
             st=con.createStatement();
             rs=st.executeQuery(sql);
             
             while(rs.next()){
                 if(rs.getString("nic").equals(txt_nic.getText())){
                     
                     count1++;
                     if(rs.getString("idEmployee").equals(id.getText())){
                         count2++;
                     }
                 }
                 
                
             }
         }
         catch(SQLException e){
             JOptionPane.showMessageDialog(null, e);
         }
         
          String nicnumber=txt_nic.getText();
        if(nicnumber.length()<10 || nicnumber.length() ==0)
        {
            nic_check=false;
            e_nic.setText("Input correct NIC number");
        }
        else
        {
            if(nicnumber.endsWith("v") || nicnumber.endsWith("V"))
            {
                try
                {
                   
                    e_nic.setText("");
                    nic_check=true;
                }
                catch(NumberFormatException ex)
                {
                    e_nic.setText("Input your Correct NIC number");
                    nic_check=false;
                }
            }
           
            
            else
            {
                e_nic.setText("Input your correct NIC number");
                nic_check=false;
            }
            if((count1 == 0&& count2 ==0) || (count1 == 1 && count2==1) ){
                     e_nic.setText("");
                    nic_check=true;
            }
            else
            {
                e_nic.setText("NIC Number already exist");
                nic_check=false;
            }
        }
        
          String contactnumstring=txt_contact.getText();
        
        if(contactnumstring.length()<10)
        {
            e_contact.setText("Input your correct contact number");
            contact_check=false;
        }
        else
        {
            try
            {
                
                contact_check=true;
                e_contact.setText("");
            }
            catch(NumberFormatException ex)
            {
                e_contact.setText("Input your correct contact number");
                contact_check=false;
            }
                        
        }
        
    }
    /**
     * Creates new form EmployeeDetails
     */
    public void setType(String type){
        this.uType=type;
    }
    public EmployeeDetails() {
        
         
        initComponents();
        con=DBConnection.ConnectionDB();
        ButtonGroup btn_grp = new ButtonGroup();
        btn_grp.add(rb_male);
         btn_grp.add(rb_female);
        // ch_search.setEnabled(false);
         ButtonGroup btn_grp1 = new ButtonGroup();
         btn_grp1.add(rb_permanant);
         btn_grp1.add(rb_temprory);
         btn_update.setEnabled(false);
         bt_cancel.setEnabled(false);
         e_search.setVisible(false);
          check_emp_password.setEnabled(false);
        e_name.setText("");
        e_last.setText("");
        e_status.setText("");
        e_gender.setText("");
        e_dob.setText("");
        e_contact.setText("");
        e_email.setText("");
        e_address.setText("");
        e_nic.setText("");
        e_qualification.setText("");
        e_qualificationdetails.setText("");
        e_language.setText("");
        e_desigination.setText("");
        e_password1.setText("");
        e_password2.setText("");
        e_employee.setText("");
        e_joindate.setText("");
     
        txt_search.setEnabled(false);
        bt_search.setEnabled(false);
        
        con=DBConnection.ConnectionDB();
        DBConnection ndb=new DBConnection();
       if(count == 0){
         newlink=ndb.setValues();
       
         temp=newlink.getFirst();
         count ++;
       }
       
 
    
         
            employee_table.setModel(DbUtils.resultSetToTableModel(ndb.getresultSet()));//show all employee details in a table
             model=(DefaultTableModel)employee_table.getModel();//currently add
          
     
       
        
             LinkX currentlink1=newlink.getFirst();
            
             if(currentlink1 !=null){
                 while(currentlink1 !=null){
                  
                    String eid=currentlink1.employee.getiData();
                    id.setText("EM"+(Integer.parseInt(eid.substring(2,9)) + 1));//get last employee id and add  + 1
                      currentlink1=currentlink1.next;
                   // ch_search.addItem("EM"+(Integer.parseInt(eid.substring(2,9))));
                 }
             }
              else{
                  id.setText("EM1111111");
                  }
            
           
        
     
     
        
        
         SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy");
             Date dateValue=null;
        try
        {
            dateValue = date1.parse("1/1/1970");
        
                //dateValue = date.parse(resultset.getstring(1));
        } 
        catch (ParseException ex) 
        {
            JOptionPane.showMessageDialog(null, "error found");
        }
        
          Date date=new Date();
          datedob.setMaxSelectableDate(date);
          
          rb_permanant.setEnabled(false);
          rb_temprory.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_last = new javax.swing.JTextField();
        rb_male = new javax.swing.JRadioButton();
        rb_female = new javax.swing.JRadioButton();
        j_status = new javax.swing.JComboBox<>();
        datedob = new com.toedter.calendar.JDateChooser();
        txt_nic = new javax.swing.JTextField();
        txt_contact = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_address = new javax.swing.JTextField();
        e_name = new javax.swing.JLabel();
        e_last = new javax.swing.JLabel();
        e_gender = new javax.swing.JLabel();
        e_status = new javax.swing.JLabel();
        e_dob = new javax.swing.JLabel();
        e_nic = new javax.swing.JLabel();
        e_contact = new javax.swing.JLabel();
        e_email = new javax.swing.JLabel();
        e_address = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        ch_ol = new javax.swing.JCheckBox();
        ch_al = new javax.swing.JCheckBox();
        ch_uni = new javax.swing.JCheckBox();
        ch_oth = new javax.swing.JCheckBox();
        txt_qualification = new javax.swing.JTextField();
        ch_english = new javax.swing.JCheckBox();
        ch_sinhala = new javax.swing.JCheckBox();
        ch_tamil = new javax.swing.JCheckBox();
        s_employee = new javax.swing.JComboBox<>();
        rb_permanant = new javax.swing.JRadioButton();
        rb_temprory = new javax.swing.JRadioButton();
        datejoin = new com.toedter.calendar.JDateChooser();
        txt_password1 = new javax.swing.JPasswordField();
        txt_password2 = new javax.swing.JPasswordField();
        txt_search = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        e_language = new javax.swing.JLabel();
        e_qualificationdetails = new javax.swing.JLabel();
        e_desigination = new javax.swing.JLabel();
        e_employee = new javax.swing.JLabel();
        e_joindate = new javax.swing.JLabel();
        e_password1 = new javax.swing.JLabel();
        e_password2 = new javax.swing.JLabel();
        e_qualification = new javax.swing.JLabel();
        bt_report = new javax.swing.JButton();
        bt_add = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();
        bt_cancel = new javax.swing.JButton();
        bt_delete = new javax.swing.JButton();
        bt_search = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        e_search = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employee_table = new javax.swing.JTable();
        btn_next = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        check_emp_password = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("First Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Last Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Gender");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Status");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Date of Birth");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("NIC Number");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Contact Number");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Emai Address");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Address");

        rb_male.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rb_male.setText("Male");

        rb_female.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rb_female.setText("Female");

        j_status.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        j_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Single", "Married" }));

        e_name.setForeground(new java.awt.Color(255, 0, 0));
        e_name.setText("Error");

        e_last.setForeground(new java.awt.Color(255, 0, 0));
        e_last.setText("Error");

        e_gender.setForeground(new java.awt.Color(255, 0, 0));
        e_gender.setText("Error");

        e_status.setForeground(new java.awt.Color(255, 0, 0));
        e_status.setText("Error");

        e_dob.setForeground(new java.awt.Color(255, 0, 0));
        e_dob.setText("Error");

        e_nic.setForeground(new java.awt.Color(255, 0, 0));
        e_nic.setText("Error");

        e_contact.setForeground(new java.awt.Color(255, 0, 0));
        e_contact.setText("Error");

        e_email.setForeground(new java.awt.Color(255, 0, 0));
        e_email.setText("Error");

        e_address.setForeground(new java.awt.Color(255, 0, 0));
        e_address.setText("Error");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Employee Id");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Qualification");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Qualification Details");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Knowledge of Language");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Position");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Doctor Type");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Join Date");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Password");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Conform Password");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 255));
        jLabel20.setText("Personal Details");

        id.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        id.setText("EmployeeId");

        ch_ol.setText("O/L");

        ch_al.setText("A/L");

        ch_uni.setText("University");

        ch_oth.setText("Others");

        ch_english.setText("English");

        ch_sinhala.setText("Sinhalam");

        ch_tamil.setText("Tamil");

        s_employee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Doctor", "Nurse", "Pharmacist", "Receptionest", "Lab Assistant", "Driver", "Administrator", "manager", "Assi.manager", "Accountant", "labour", "director", "technician", "clerk" }));
        s_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_employeeActionPerformed(evt);
            }
        });

        rb_permanant.setText("Permanent Doctor");

        rb_temprory.setText("Visiting Doctor");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 255));
        jLabel21.setText("Other Details");

        e_language.setForeground(new java.awt.Color(255, 0, 0));
        e_language.setText("Error");

        e_qualificationdetails.setForeground(new java.awt.Color(255, 0, 0));
        e_qualificationdetails.setText("Error");

        e_desigination.setForeground(new java.awt.Color(255, 0, 0));
        e_desigination.setText("Error");

        e_employee.setForeground(new java.awt.Color(255, 0, 0));
        e_employee.setText("Error");

        e_joindate.setForeground(new java.awt.Color(255, 0, 0));
        e_joindate.setText("Error");

        e_password1.setForeground(new java.awt.Color(255, 0, 0));
        e_password1.setText("Error");

        e_password2.setForeground(new java.awt.Color(255, 0, 0));
        e_password2.setText("Error");

        e_qualification.setForeground(new java.awt.Color(255, 0, 0));
        e_qualification.setText("Error");

        bt_report.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_report.setForeground(new java.awt.Color(0, 153, 0));
        bt_report.setText("Report");
        bt_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_reportActionPerformed(evt);
            }
        });

        bt_add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_add.setForeground(new java.awt.Color(0, 153, 153));
        bt_add.setText("Add");
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addActionPerformed(evt);
            }
        });

        bt_edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_edit.setForeground(new java.awt.Color(0, 153, 153));
        bt_edit.setText("Edit");
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        bt_cancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_cancel.setForeground(new java.awt.Color(0, 153, 153));
        bt_cancel.setText("Cancel");
        bt_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelActionPerformed(evt);
            }
        });

        bt_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_delete.setForeground(new java.awt.Color(0, 153, 153));
        bt_delete.setText("Delete");
        bt_delete.setEnabled(false);
        bt_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_deleteActionPerformed(evt);
            }
        });

        bt_search.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bt_search.setText("Search");
        bt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_searchActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_update.setForeground(new java.awt.Color(0, 153, 153));
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        e_search.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        e_search.setForeground(new java.awt.Color(255, 0, 0));
        e_search.setText("Enter employee id and click search");

        employee_table.setModel(new javax.swing.table.DefaultTableModel(
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
        employee_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(employee_table);

        btn_next.setText("Next");
        btn_next.setEnabled(false);
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_prev.setText("Prev");
        btn_prev.setEnabled(false);
        btn_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prevActionPerformed(evt);
            }
        });

        check_emp_password.setText("Check Password");
        check_emp_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_emp_passwordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rb_male)
                                        .addGap(18, 18, 18)
                                        .addComponent(rb_female))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(j_status, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_nic, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(datedob, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_contact, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                        .addComponent(txt_email)
                                        .addComponent(txt_address))
                                    .addComponent(btn_prev, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_last, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(e_last)
                            .addComponent(e_gender)
                            .addComponent(e_status)
                            .addComponent(e_dob)
                            .addComponent(e_nic)
                            .addComponent(e_contact)
                            .addComponent(e_email)
                            .addComponent(e_address)
                            .addComponent(e_name))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel11))))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_password2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_password1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datejoin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(e_password1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(e_password2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                                .addComponent(e_search, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_qualification, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(ch_ol)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ch_al)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ch_uni)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ch_oth)
                                        .addGap(18, 18, 18)
                                        .addComponent(e_qualification))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(79, 79, 79)
                                        .addComponent(e_qualificationdetails))))
                            .addComponent(id)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(ch_english)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ch_sinhala)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ch_tamil))
                                    .addComponent(s_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rb_permanant)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rb_temprory)))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(e_joindate)
                                    .addComponent(e_employee)
                                    .addComponent(e_desigination)
                                    .addComponent(e_language)))
                            .addComponent(check_emp_password))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(46, 46, 46))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(bt_search)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_add, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_report, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(datedob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txt_nic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e_nic)
                                    .addComponent(jLabel16)
                                    .addComponent(rb_permanant)
                                    .addComponent(rb_temprory)
                                    .addComponent(e_employee))
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e_name)
                                    .addComponent(id)
                                    .addComponent(jLabel11))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_last, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e_last)
                                    .addComponent(jLabel12)
                                    .addComponent(ch_ol)
                                    .addComponent(ch_al)
                                    .addComponent(ch_uni)
                                    .addComponent(ch_oth)
                                    .addComponent(e_qualification))
                                .addGap(1, 1, 1)
                                .addComponent(bt_report)
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(rb_male)
                                    .addComponent(rb_female)
                                    .addComponent(e_gender)
                                    .addComponent(jLabel13)
                                    .addComponent(txt_qualification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e_qualificationdetails))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(j_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e_status)
                                    .addComponent(jLabel14)
                                    .addComponent(ch_english)
                                    .addComponent(ch_sinhala)
                                    .addComponent(ch_tamil)
                                    .addComponent(e_language)
                                    .addComponent(bt_add))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel5))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(e_dob)
                                            .addComponent(jLabel15)
                                            .addComponent(s_employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(e_desigination)
                                            .addComponent(btn_update))))
                                .addGap(32, 32, 32)
                                .addComponent(bt_edit)
                                .addGap(33, 33, 33)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(e_contact)
                                        .addComponent(jLabel17))
                                    .addComponent(datejoin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e_joindate))
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(bt_cancel)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e_email)
                                    .addComponent(jLabel18)
                                    .addComponent(txt_password1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e_password1))
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(bt_delete)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_password2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(e_password2)
                            .addComponent(e_search)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(e_address)
                        .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_search))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_emp_password)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_next)
                            .addComponent(btn_prev))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Employee Maintain Details");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(jLabel10)
                .addContainerGap(528, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1120, 1030));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        OverView view =new OverView();
        view.setVisible(true);
        view.setType(uType);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bt_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addActionPerformed
        
        check();
        
        addDetail();
       
    }//GEN-LAST:event_bt_addActionPerformed

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        bt_search.setEnabled(true);
      txt_search.setEnabled(true);
      bt_add.setEnabled(false);
     // bt_report.setEnabled(false);
      bt_delete.setEnabled(false);
      //ch_search.setEnabled(true);
      bt_cancel.setEnabled(true);
     e_search.setVisible(true);
      e_search.setText("select employee id & click search");
      
       
    }//GEN-LAST:event_bt_editActionPerformed

    private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed
   

        
            try
            {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure want to Delete", "Delete Option", dialogButton);
                if(dialogResult == 0)
                {
                       /* String sql3 = "DELETE FROM `daily_attendance` WHERE `employeeid`='"+id.getText()+"'";
                    PreparedStatement statement3 = con.prepareStatement(sql3);
                    int rowsDeleted2 = statement3.executeUpdate();*/
                    String sql2 = "DELETE FROM `login_employee` WHERE `employeeid`='"+id.getText()+"'";
                    PreparedStatement statement2 = con.prepareStatement(sql2);
                    int rowsDeleted1 = statement2.executeUpdate();
                    
                  
                    
                        
                    String sql = "DELETE FROM `employee` WHERE `idEmployee`='"+id.getText()+"'";
                    PreparedStatement statement = con.prepareStatement(sql);
                    int rowsDeleted = statement.executeUpdate();
                    if (rowsDeleted > 0 && rowsDeleted1>0 /*&& rowsDeleted2 > 0*/)
                    {
                        JOptionPane.showMessageDialog(null,"A user was deleted successfully!");
                               con=DBConnection.ConnectionDB();
                                DBConnection ndb=new DBConnection();
                                 check_emp_password.setEnabled(false);
                                    employee_table.setModel(DbUtils.resultSetToTableModel(ndb.getresultSet()));//show all employee details in a table
                                     model=(DefaultTableModel)employee_table.getModel();//currently add

                                     
                                     newlink=ndb.setValues();

                                     LinkX currentlink1=newlink.getFirst();

                                     if(currentlink1 !=null){
                                         while(currentlink1.next !=null){
                                            currentlink1=currentlink1.next;
                                            String eid=currentlink1.employee.getiData();
                                            id.setText("EM"+(Integer.parseInt(eid.substring(2,9)) + 1));//get last employee id and add  + 1
                                             // ch_search.addItem("EM"+(Integer.parseInt(eid.substring(2,9))));
                                         }
                                     }
                                     else{
                                           id.setText("EM1111111");
                                     }
            
                    }

                   

                    txt_name.setText("");
                    txt_last.setText("");
                    rb_male.setSelected(false);
                    rb_female.setSelected(false);
                    j_status.setSelectedIndex(0);
                    datedob.setDate(null);
                    txt_nic.setText("");
                    txt_contact.setText("");
                    txt_email.setText("");
                    txt_address.setText("");
                    ch_ol.setSelected(false);
                    ch_al.setSelected(false);
                    ch_oth.setSelected(false);
                    txt_qualification.setText("");
                    ch_english.setSelected(false);
                    ch_tamil.setSelected(false);
                    ch_sinhala.setSelected(false);
                    rb_permanant.setSelected(false);
                    rb_temprory.setSelected(false);
                    s_employee.setSelectedIndex(0);
                    datejoin.setDate(null);
                   /* txt_search.setEnabled(false);
                    bt_search.setEnabled(false);
                    bt_add.setEnabled(true);
                    bt_edit.setEnabled(true);
                    search=false;*/

                    txt_password1.setText("");
                    txt_password2.setText("");

                   /* rb_permanant.setEnabled(false);
                    rb_temprory.setEnabled(false);*/
                    txt_password1.setEnabled(true);
                    txt_password2.setEnabled(true);

                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Your Delete Option was Cancelled");

                   /* txt_password1.setEnabled(true);
                    txt_password2.setEnabled(true);
                    txt_search.setText("");
                    txt_search.setEnabled(false);
                    bt_search.setEnabled(false);
                    bt_add.setEnabled(true);
                    bt_edit.setEnabled(true);
                    search=false;*/
                   // String sql1 = "SELECT * FROM employee";

                    con=DBConnection.ConnectionDB();
                    DBConnection ndb=new DBConnection();
                              
                    newlink=ndb.setValues();
                    LinkX currentlink1=newlink.getFirst();
                    if(currentlink1 !=null){
                        while(currentlink1.next !=null){
                            currentlink1=currentlink1.next;
                            String eid=currentlink1.employee.getiData();
                            id.setText("EM"+(Integer.parseInt(eid.substring(2,9)) + 1));//get last employee id and add  + 1
                                                 // ch_search.addItem("EM"+(Integer.parseInt(eid.substring(2,9))));
                         }
                    }
                    else{
                        id.setText("EM1111111");
                    }
                }

                txt_name.setText("");
                txt_last.setText("");
                rb_male.setSelected(false);
                rb_female.setSelected(false);
                j_status.setSelectedIndex(0);
                datedob.setDate(null);
                txt_nic.setText("");
                txt_contact.setText("");
                txt_email.setText("");
                txt_address.setText("");
                ch_ol.setSelected(false);
                ch_al.setSelected(false);
                ch_oth.setSelected(false);
                txt_qualification.setText("");
                ch_english.setSelected(false);
                ch_tamil.setSelected(false);
                ch_sinhala.setSelected(false);
                rb_permanant.setSelected(false);
                rb_temprory.setSelected(false);
                s_employee.setSelectedIndex(0);
                datejoin.setDate(null);
                txt_password1.setText("");
                txt_password2.setText("");

                e_password1.setText("");
                e_password2.setText("");

                txt_password1.setEnabled(true);
                txt_password2.setEnabled(true);
                
               /* rb_permanant.setEnabled(false);
                rb_temprory.setEnabled(false);*/
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        

        /*else
        {
            //JOptionPane.showMessageDialog(null, "User Not found");
            txt_name.setText("");
            txt_last.setText("");
            rb_male.setSelected(false);
            rb_female.setSelected(false);
            j_status.setSelectedIndex(0);
            datedob.setDate(null);
            txt_nic.setText("");
            txt_contact.setText("");
            txt_email.setText("");
            txt_address.setText("");
            ch_ol.setSelected(false);
            ch_al.setSelected(false);
            ch_oth.setSelected(false);
            txt_qualification.setText("");
            ch_english.setSelected(false);
            ch_tamil.setSelected(false);
            ch_sinhala.setSelected(false);
            rb_permanant.setSelected(false);
            rb_temprory.setSelected(false);
            datejoin.setDate(null);
            s_employee.setSelectedIndex(0);

            txt_password1.setText("");
            txt_password2.setText("");

            e_password1.setText("");
            e_password2.setText("");

            // txt_password1.setEnabled(true);
            //txt_password2.setEnabled(true);

            e_name.setText("");
            e_last.setText("");
            e_status.setText("");
            e_gender.setText("");
            e_dob.setText("");
            e_contact.setText("");
            e_email.setText("");
            e_address.setText("");
            e_nic.setText("");
            e_qualification.setText("");
            e_qualificationdetails.setText("");
            e_language.setText("");
            e_desigination.setText("");
            e_employee.setText("");
            e_joindate.setText("");
            
            rb_permanant.setEnabled(false);
            rb_temprory.setEnabled(false);
        }*/
    }//GEN-LAST:event_bt_deleteActionPerformed

    private void set_next_details(String emp_id){
              con=DBConnection.ConnectionDB();
              DBConnection ndb=new DBConnection();
              newlink=ndb.setValues();
              temp=newlink.search(emp_id);
                   
             
              if(temp.next == null){
                  btn_next.setEnabled(false);
                  btn_prev.setEnabled(true);
              }
                if(temp.preview == null){
                  btn_prev.setEnabled(false);
                    btn_next.setEnabled(true);
              }
            //String sql1 = "SELECT * FROM employee where `idEmployee`='"+txt_search.getText()+"'";
   
             
               
               
              
                  
    }
    
    private void bt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_searchActionPerformed
         if(txt_search.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Input the values to search");
        }
        else
        {
                          con=DBConnection.ConnectionDB();
                               DBConnection ndb=new DBConnection();
                               newlink=ndb.setValues();
                         LinkX mylink=newlink.search(txt_search.getText());
                   
                   
            //String sql1 = "SELECT * FROM employee where `idEmployee`='"+txt_search.getText()+"'";
   
             
               
              
              
                    if(mylink !=null){
                        btn_next.setEnabled(true);
                        btn_prev.setEnabled(true);
                        temp=mylink;
                     id.setText(mylink.employee.getiData());
                    btn_update.setEnabled(true);
                    search=true;
                     btn_update.setEnabled(true);
                     bt_delete.setEnabled(true);
                        set_next_details(mylink.employee.getiData());
               
                    txt_name.setText(mylink.employee.getFname());
                    txt_last.setText(mylink.employee.getLname());

                    if(mylink.employee.getGender().equals("Male"))
                    {
                        rb_male.setSelected(true);
                    }
                    else
                    {
                        rb_female.setSelected(true);
                    }
                    if(mylink.employee.getStatus().equals("Single"))
                    {
                        j_status.setSelectedIndex(1);
                    }
                    else if(mylink.employee.getStatus().equals("Married"))
                    {
                        j_status.setSelectedIndex(2);
                    }
                    
                    Date dateValue=null;
                    SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy");
                    try
                    {
                        dateValue = date1.parse(mylink.employee.getDob());
                        datedob.setDate(dateValue);
                    } 
                    catch (ParseException ex) 
                    {
                    }
                    txt_nic.setText(mylink.employee.getNic());
                    txt_contact.setText(mylink.employee.getContact_number());
                    txt_email.setText(mylink.employee.getEmail());
                    txt_address.setText(mylink.employee.getAddress());

                    if(mylink.employee.getOl().equals("OL"))
                    {
                        ch_ol.setSelected(true);
                    }
                    else
                    {
                        ch_ol.setSelected(false);
                    }
                    if(mylink.employee.getAl().equals("AL"))
                    {
                        ch_al.setSelected(true);
                    }
                    else
                    {
                        ch_ol.setSelected(false);
                    }
                    if(mylink.employee.getOthers().equals("OTHERS"))
                    {
                        ch_oth.setSelected(true);
                    }
                    else
                    {
                        ch_oth.setSelected(false);
                    }

                    txt_qualification.setText(mylink.employee.getQualificationDetails());

                    if(mylink.employee.getEnglish().equals("English"))
                    {
                        ch_english.setSelected(true);
                    }
                    else
                    {
                        ch_english.setSelected(false);
                    }

                    if(mylink.employee.getTamil().equals("Tamil"))
                    {
                        ch_tamil.setSelected(true);
                    }
                    else
                    {
                        ch_tamil.setSelected(false);
                    }
                    if(mylink.employee.getSingala().equals("Singala"))
                    {
                        ch_sinhala.setSelected(true);
                    }
                    else
                    {
                        ch_sinhala.setSelected(false);
                    }
                    
                    rb_permanant.setSelected(false);
                    rb_temprory.setSelected(false);

                    if(mylink.employee.getDesignation().equals("Doctor"))
                    {
                        s_employee.setSelectedIndex(1);
                        rb_permanant.setEnabled(true);
                        rb_temprory.setEnabled(true);
                        if(mylink.employee.getEmployee().equals("Permanant"))
                        {
                            rb_permanant.setSelected(true);
                        }
                        else if(mylink.employee.getEmployee().equals("Visiting"))
                        {
                            rb_temprory.setSelected(true);
                        }
                    }
                    else if(mylink.employee.getDesignation().equals("Nurse"))
                    {
                        rb_permanant.setEnabled(false);
                        rb_temprory.setEnabled(false);
                        s_employee.setSelectedIndex(2);
                    }
                    else if(mylink.employee.getDesignation().equals("Pharmacist"))
                    {
                        rb_permanant.setEnabled(false);
                        rb_temprory.setEnabled(false);
                        s_employee.setSelectedIndex(3);
                    }
                    else if(mylink.employee.getDesignation().equals("Receptionest"))
                    {
                        rb_permanant.setEnabled(false);
                        rb_temprory.setEnabled(false);
                        s_employee.setSelectedIndex(4);
                    }
                    else if(mylink.employee.getDesignation().equals("Lab Assistant"))
                    {
                        rb_permanant.setEnabled(false);
                        rb_temprory.setEnabled(false);
                        s_employee.setSelectedIndex(5);
                    }
                    else if(mylink.employee.getDesignation().equals("Driver"))
                    {
                        rb_permanant.setEnabled(false);
                        rb_temprory.setEnabled(false);
                        s_employee.setSelectedIndex(6);
                    }
                    else if(mylink.employee.getDesignation().equals("Other"))
                    {
                        rb_permanant.setEnabled(false);
                        rb_temprory.setEnabled(false);
                        s_employee.setSelectedIndex(7);
                    }

                    Date dateValue1=null;
                    SimpleDateFormat date2= new SimpleDateFormat("dd/MM/yyyy");
                    try
                    {
                        dateValue1 = date2.parse(mylink.employee.getJoin().toString());
                        datejoin.setDate(dateValue1);
                    } 
                    catch (ParseException ex) 
                    {
                    }
 
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "User Not found");
                    btn_next.setEnabled(false);
                        btn_prev.setEnabled(false);
                    bt_add.setEnabled(true);
                    bt_edit.setEnabled(true);
                    bt_delete.setEnabled(true);
                    search=false;
                    txt_search.setText("");
                }
           

        }
    }//GEN-LAST:event_bt_searchActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
       
            check();
            if(lname_check &&  fname_check && gender_check && dobcheck && status_check && address_check && pass1_check && pass2_check && email_check && contact_check && nic_check && quali_check && check_qualification && lang_check && des_check && employee_check && joindate_check)
            {
                JOptionPane.showMessageDialog(null,"All informations are validated");

                try
                {
                    String gen="";
                    String qualifiol="null";
                    String qualifial="null";
                    String qualifioth="null";
                    String lanen="null";
                    String lantam="null";
                    String lansing="null";
                    if(rb_female.isSelected())
                    {
                        gen="Female";
                    }
                    else if(rb_male.isSelected())
                    {
                        gen="Male";
                    }
                    if(ch_ol.isSelected())
                    {
                        qualifiol="OL";
                    }
                    if(ch_al.isSelected())
                    {
                        qualifial="AL";
                    }
                    if(ch_oth.isSelected())
                    {
                        qualifioth="OTHERS";
                    }
                    if(ch_tamil.isSelected())
                    {
                        lantam="Tamil";
                    }
                    if(ch_english.isSelected())
                    {
                        lanen="English";
                    }
                    if(ch_sinhala.isSelected())
                    {
                        lansing="Singala";
                    }

                    String emptype="";
                    if(s_employee.getSelectedIndex()==1)
                    {
                        if(rb_permanant.isSelected())
                        {
                            emptype="Permanant";
                        }
                        else if(rb_temprory.isSelected())
                        {
                            emptype="Visiting";
                        }
                    }
                    else
                    {
                        emptype=null;
                    }

                    String sql = "UPDATE  `employee` set `idEmployee`=?,`fname`=?,`lname`=?,`gender`=?,`status`=?,`dob`=?,`nic`=?,`contactnumber`=?,`email`=?,`address`=?,`ol`=?,`al`=?,`others`=?,`qualificationdetails`=?,`english`=?,`tamil`=?,`singala`=?,`desigination`=?,`employee`=?,`join`=? WHERE `idEmployee`='"+id.getText()+"'";
                    
                     ps= con.prepareStatement(sql);
                    ps.setString(1, id.getText());
                    ps.setString(2, txt_name.getText());
                    ps.setString(3, txt_last.getText());
                    ps.setString(4,gen );
                    ps.setString(5,j_status.getSelectedItem().toString());
                    ps.setString(6,formatter.format(datedob.getDate()).toString());
                    ps.setString(7,txt_nic.getText());
                    ps.setString(8,txt_contact.getText());
                    ps.setString(9,txt_email.getText());
                    ps.setString(10,txt_address.getText());
                    ps.setString(11,qualifiol);
                    ps.setString(12,qualifial);
                    ps.setString(13,qualifioth);
                    ps.setString(14,txt_qualification.getText());
                    ps.setString(15,lanen);
                    ps.setString(16,lantam);
                    ps.setString(17,lansing);
                    ps.setString(18,s_employee.getSelectedItem().toString());
                    ps.setString(19,emptype);
                    ps.setString(20, formatter.format(datejoin.getDate()).toString());

                    int rowsupdate = ps.executeUpdate();
                    ps.close();
                    if (rowsupdate > 0)
                    {
                        JOptionPane.showMessageDialog(null,"The  user was updated successfully!");
                         btn_update.setEnabled(false);
                          check_emp_password.setEnabled(false);
                        //String sql1 = "SELECT * FROM employee";
                            con=DBConnection.ConnectionDB();
                               DBConnection ndb=new DBConnection();
                          employee_table.setModel(DbUtils.resultSetToTableModel(ndb.getresultSet()));//show all employee details in a table
                             model=(DefaultTableModel)employee_table.getModel();//currently add
                                    newlink=ndb.setValues();
                                LinkX mylink1=newlink.getFirst();
                            if(mylink1 != null){
                                   while(mylink1.next !=null){
                                   mylink1=mylink1.next;
                                    String eid=mylink1 .employee.getiData();
                                    id.setText("EM"+(Integer.parseInt(eid.substring(2,9)) + 1));//get last employee id and add  + 1
                                   // ch_search.addItem("EM"+(Integer.parseInt(eid.substring(2,9))));
                                 }
                            }
                            else{
                                 id.setText("EM1111111");
                            }
                             
           

                      /*  try
                        {

                            st = con.createStatement();
                            rs = st.executeQuery(sql1);

                            int rows=0;
                            while (rs.next())
                            {
                                rows++;
                                String idd = rs.getString(1);
                                id.setText("EM"+(Integer.parseInt(idd.substring(2,9))+1));
                            }
                            if(rows<1)
                            {
                                 id.setText("EM1111111");
                            }
                        }
                        catch(SQLException ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            id.setText("EM1111111");
                        }
                        finally{
                            st.close();
                            rs.close();
                        }*/
                    }

                    txt_name.setText("");
                    txt_last.setText("");
                    rb_male.setSelected(false);
                    rb_female.setSelected(false);
                    j_status.setSelectedIndex(0);
                    datedob.setDate(null);
                    txt_nic.setText("");
                    txt_contact.setText("");
                    txt_email.setText("");
                    txt_address.setText("");
                    ch_ol.setSelected(false);
                    ch_al.setSelected(false);
                    ch_oth.setSelected(false);
                    txt_qualification.setText("");
                    ch_english.setSelected(false);
                    ch_tamil.setSelected(false);
                    ch_sinhala.setSelected(false);
                    rb_permanant.setSelected(false);
                    rb_temprory.setSelected(false);
                    s_employee.setSelectedIndex(0);
                    datejoin.setDate(null);
                    txt_search.setText("");

                    txt_password1.setText("");
                    txt_password2.setText("");

                    rb_permanant.setEnabled(false);
                    rb_temprory.setEnabled(false);
                    
                    txt_password1.setEnabled(true);
                    txt_password2.setEnabled(true);
                  
                   
                    
                    search=false;
                    

                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
            else
            {
                JOptionPane.showMessageDialog(null,"Check your all inputs");
                
            }
            
        
        
    }//GEN-LAST:event_btn_updateActionPerformed
    public void clearInputs(){
         txt_name.setText("");
            txt_last.setText("");
            rb_male.setSelected(false);
            rb_female.setSelected(false);
            j_status.setSelectedIndex(0);
            datedob.setDate(null);
            txt_nic.setText("");
            txt_contact.setText("");
            txt_email.setText("");
            txt_address.setText("");
            ch_ol.setSelected(false);
            ch_al.setSelected(false);
            ch_oth.setSelected(false);
            txt_qualification.setText("");
            ch_english.setSelected(false);
            ch_tamil.setSelected(false);
            ch_sinhala.setSelected(false);
            rb_permanant.setSelected(false);
            rb_temprory.setSelected(false);
            datejoin.setDate(null);
            s_employee.setSelectedIndex(0);
             txt_search.setText("");
            txt_password1.setText("");
            txt_password2.setText("");

            e_password1.setText("");
            e_password2.setText("");

            e_name.setText("");
            e_last.setText("");
            e_status.setText("");
            e_gender.setText("");
            e_dob.setText("");
            e_contact.setText("");
            e_email.setText("");
            e_address.setText("");
            e_nic.setText("");
            e_qualification.setText("");
            e_qualificationdetails.setText("");
            e_language.setText("");
            e_desigination.setText("");
            e_employee.setText("");
            e_joindate.setText("");
            rb_permanant.setEnabled(false);
            rb_temprory.setEnabled(false);
    }
    private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
        bt_add.setEnabled(true);
        btn_next.setEnabled(false);
        btn_prev.setEnabled(false);
        bt_delete.setEnabled(false);
       // bt_report.setEnabled(true);
        bt_edit.setEnabled(true);
        check_emp_password.setEnabled(false);
        bt_cancel.setEnabled(false);
        txt_search.setEnabled(false);
        bt_search.setEnabled(false);
        btn_update.setEnabled(false);
       //  ch_search.setEnabled(false);
         e_search.setVisible(false);
         String sql="select * from employee";
             try{
            st=con.createStatement();
            rs=st.executeQuery(sql);
          
          
            
            while(rs.next()){
                String eid=rs.getString(1);
                id.setText("EM"+(Integer.parseInt(eid.substring(2,9)) + 1));//get last employee id and add  + 1
               
            }
            st.close();
            rs.close();
        }
        catch(Exception e){
            id.setText("EM1111111");
            JOptionPane.showConfirmDialog(null,e);
        }
         
        clearInputs();
    }//GEN-LAST:event_bt_cancelActionPerformed

    private void employee_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_tableMouseClicked
        Edit_selected_Employee_Details();
    }//GEN-LAST:event_employee_tableMouseClicked

    private void s_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_employeeActionPerformed
        
      if(s_employee.getSelectedItem().equals("Doctor")){
          rb_permanant.setEnabled(true);
          rb_temprory.setEnabled(true);
      }
      else{
          rb_permanant.setEnabled(false);
          rb_temprory.setEnabled(false);
      }
    }//GEN-LAST:event_s_employeeActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
           if(temp !=null){
               btn_prev.setEnabled(true);
          }
        if(temp.next==null){
               btn_next.setEnabled(false);
          }
        
       else{
                        
                   temp=temp.next;
                            id.setText(temp.employee.getiData());
                           btn_update.setEnabled(true);
                           search=true;
                            btn_update.setEnabled(true);

                           txt_name.setText(temp.employee.getFname());
                           txt_last.setText(temp.employee.getLname());

                           if(temp.employee.getGender().equals("Male"))
                           {
                               rb_male.setSelected(true);
                           }
                           else
                           {
                               rb_female.setSelected(true);
                           }
                           if(temp.employee.getStatus().equals("Single"))
                           {
                               j_status.setSelectedIndex(1);
                           }
                           else if(temp.employee.getStatus().equals("Married"))
                           {
                               j_status.setSelectedIndex(2);
                           }

                           Date dateValue=null;
                           SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy");
                           try
                           {
                               dateValue = date1.parse(temp.employee.getDob());
                               datedob.setDate(dateValue);
                           } 
                           catch (ParseException ex) 
                           {
                           }
                           txt_nic.setText(temp.employee.getNic());
                           txt_contact.setText(temp.employee.getContact_number());
                           txt_email.setText(temp.employee.getEmail());
                           txt_address.setText(temp.employee.getAddress());

                           if(temp.employee.getOl().equals("OL"))
                           {
                               ch_ol.setSelected(true);
                           }
                           else
                           {
                               ch_ol.setSelected(false);
                           }
                           if(temp.employee.getAl().equals("AL"))
                           {
                               ch_al.setSelected(true);
                           }
                           else
                           {
                               ch_ol.setSelected(false);
                           }
                           if(temp.employee.getOthers().equals("OTHERS"))
                           {
                               ch_oth.setSelected(true);
                           }
                           else
                           {
                               ch_oth.setSelected(false);
                           }

                           txt_qualification.setText(temp.employee.getQualificationDetails());

                           if(temp.employee.getEnglish().equals("English"))
                           {
                               ch_english.setSelected(true);
                           }
                           else
                           {
                               ch_english.setSelected(false);
                           }

                           if(temp.employee.getTamil().equals("Tamil"))
                           {
                               ch_tamil.setSelected(true);
                           }
                           else
                           {
                               ch_tamil.setSelected(false);
                           }
                           if(temp.employee.getSingala().equals("Singala"))
                           {
                               ch_sinhala.setSelected(true);
                           }
                           else
                           {
                               ch_sinhala.setSelected(false);
                           }

                           rb_permanant.setSelected(false);
                           rb_temprory.setSelected(false);

                           if(temp.employee.getDesignation().equals("Doctor"))
                           {
                               s_employee.setSelectedIndex(1);
                               rb_permanant.setEnabled(true);
                               rb_temprory.setEnabled(true);
                               if(temp.employee.getEmployee().equals("Permanant"))
                               {
                                   rb_permanant.setSelected(true);
                               }
                               else if(temp.employee.getEmployee().equals("Visiting"))
                               {
                                   rb_temprory.setSelected(true);
                               }
                           }
                           else if(temp.employee.getDesignation().equals("Nurse"))
                           {
                               rb_permanant.setEnabled(false);
                               rb_temprory.setEnabled(false);
                               s_employee.setSelectedIndex(2);
                           }
                           else if(temp.employee.getDesignation().equals("Pharmacist"))
                           {
                               rb_permanant.setEnabled(false);
                               rb_temprory.setEnabled(false);
                               s_employee.setSelectedIndex(3);
                           }
                           else if(temp.employee.getDesignation().equals("Receptionest"))
                           {
                               rb_permanant.setEnabled(false);
                               rb_temprory.setEnabled(false);
                               s_employee.setSelectedIndex(4);
                           }
                           else if(temp.employee.getDesignation().equals("Lab Assistant"))
                           {
                               rb_permanant.setEnabled(false);
                               rb_temprory.setEnabled(false);
                               s_employee.setSelectedIndex(5);
                           }
                           else if(temp.employee.getDesignation().equals("Driver"))
                           {
                               rb_permanant.setEnabled(false);
                               rb_temprory.setEnabled(false);
                               s_employee.setSelectedIndex(6);
                           }
                           else if(temp.employee.getDesignation().equals("Other"))
                           {
                               rb_permanant.setEnabled(false);
                               rb_temprory.setEnabled(false);
                               s_employee.setSelectedIndex(7);
                           }

                           Date dateValue1=null;
                           SimpleDateFormat date2= new SimpleDateFormat("dd/MM/yyyy");
                           try
                           {
                               dateValue1 = date2.parse(temp.employee.getJoin().toString());
                               datejoin.setDate(dateValue1);
                           } 
                           catch (ParseException ex) 
                           {
                           }
                          
                   }
          
                    if(temp.next==null){
                        btn_next.setEnabled(false);
                        btn_prev.setEnabled(true);
                   }
                    
                 
 
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
          if(temp !=null){
               btn_next.setEnabled(true);
          }  
        if(temp==null){
               btn_prev.setEnabled(false);
          }
       else{
                        
                              temp=temp.preview;
                            id.setText(temp.employee.getiData());
                           btn_update.setEnabled(true);
                           search=true;
                            btn_update.setEnabled(true);

                           txt_name.setText(temp.employee.getFname());
                           txt_last.setText(temp.employee.getLname());

                           if(temp.employee.getGender().equals("Male"))
                           {
                               rb_male.setSelected(true);
                           }
                           else
                           {
                               rb_female.setSelected(true);
                           }
                           if(temp.employee.getStatus().equals("Single"))
                           {
                               j_status.setSelectedIndex(1);
                           }
                           else if(temp.employee.getStatus().equals("Married"))
                           {
                               j_status.setSelectedIndex(2);
                           }

                           Date dateValue=null;
                           SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy");
                           try
                           {
                               dateValue = date1.parse(temp.employee.getDob());
                               datedob.setDate(dateValue);
                           } 
                           catch (ParseException ex) 
                           {
                           }
                           txt_nic.setText(temp.employee.getNic());
                           txt_contact.setText(temp.employee.getContact_number());
                           txt_email.setText(temp.employee.getEmail());
                           txt_address.setText(temp.employee.getAddress());

                           if(temp.employee.getOl().equals("OL"))
                           {
                               ch_ol.setSelected(true);
                           }
                           else
                           {
                               ch_ol.setSelected(false);
                           }
                           if(temp.employee.getAl().equals("AL"))
                           {
                               ch_al.setSelected(true);
                           }
                           else
                           {
                               ch_ol.setSelected(false);
                           }
                           if(temp.employee.getOthers().equals("OTHERS"))
                           {
                               ch_oth.setSelected(true);
                           }
                           else
                           {
                               ch_oth.setSelected(false);
                           }

                           txt_qualification.setText(temp.employee.getQualificationDetails());

                           if(temp.employee.getEnglish().equals("English"))
                           {
                               ch_english.setSelected(true);
                           }
                           else
                           {
                               ch_english.setSelected(false);
                           }

                           if(temp.employee.getTamil().equals("Tamil"))
                           {
                               ch_tamil.setSelected(true);
                           }
                           else
                           {
                               ch_tamil.setSelected(false);
                           }
                           if(temp.employee.getSingala().equals("Singala"))
                           {
                               ch_sinhala.setSelected(true);
                           }
                           else
                           {
                               ch_sinhala.setSelected(false);
                           }

                           rb_permanant.setSelected(false);
                           rb_temprory.setSelected(false);

                           if(temp.employee.getDesignation().equals("Doctor"))
                           {
                               s_employee.setSelectedIndex(1);
                               rb_permanant.setEnabled(true);
                               rb_temprory.setEnabled(true);
                               if(temp.employee.getEmployee().equals("Permanant"))
                               {
                                   rb_permanant.setSelected(true);
                               }
                               else if(temp.employee.getEmployee().equals("Visiting"))
                               {
                                   rb_temprory.setSelected(true);
                               }
                           }
                           else if(temp.employee.getDesignation().equals("Nurse"))
                           {
                               rb_permanant.setEnabled(false);
                               rb_temprory.setEnabled(false);
                               s_employee.setSelectedIndex(2);
                           }
                           else if(temp.employee.getDesignation().equals("Pharmacist"))
                           {
                               rb_permanant.setEnabled(false);
                               rb_temprory.setEnabled(false);
                               s_employee.setSelectedIndex(3);
                           }
                           else if(temp.employee.getDesignation().equals("Receptionest"))
                           {
                               rb_permanant.setEnabled(false);
                               rb_temprory.setEnabled(false);
                               s_employee.setSelectedIndex(4);
                           }
                           else if(temp.employee.getDesignation().equals("Lab Assistant"))
                           {
                               rb_permanant.setEnabled(false);
                               rb_temprory.setEnabled(false);
                               s_employee.setSelectedIndex(5);
                           }
                           else if(temp.employee.getDesignation().equals("Driver"))
                           {
                               rb_permanant.setEnabled(false);
                               rb_temprory.setEnabled(false);
                               s_employee.setSelectedIndex(6);
                           }
                           else if(temp.employee.getDesignation().equals("Other"))
                           {
                               rb_permanant.setEnabled(false);
                               rb_temprory.setEnabled(false);
                               s_employee.setSelectedIndex(7);
                           }

                           Date dateValue1=null;
                           SimpleDateFormat date2= new SimpleDateFormat("dd/MM/yyyy");
                           try
                           {
                               dateValue1 = date2.parse(temp.employee.getJoin().toString());
                               datejoin.setDate(dateValue1);
                           } 
                           catch (ParseException ex) 
                           {
                           }
                        
                   }
          
                    if(temp.preview==null){
                        btn_next.setEnabled(true);
                        btn_prev.setEnabled(false);
                   }
                    
    }//GEN-LAST:event_btn_prevActionPerformed

    private void check_emp_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_emp_passwordActionPerformed
        Employee_password_check emp_pas=new Employee_password_check();
        emp_pas.setVisible(true);
        emp_pas.setId(id.getText());
    }//GEN-LAST:event_check_emp_passwordActionPerformed

    private void bt_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_reportActionPerformed
       try{
           String report="C:\\Users\\Haran\\Documents\\NetBeansProjects\\ps_Atlanta\\src\\Employee_Report\\emp1.jrxml";
           JasperReport jasp=JasperCompileManager.compileReport(report);
           JasperPrint jas_print=JasperFillManager.fillReport(jasp,null,con);
           JasperViewer.viewReport(jas_print);
       }
       catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
       }
    }//GEN-LAST:event_bt_reportActionPerformed

    public void addDetail(){
        if(lname_check &&  fname_check && gender_check && dobcheck && status_check && address_check && pass1_check && pass2_check && email_check && contact_check && nic_check && quali_check && check_qualification && lang_check && des_check && employee_check && joindate_check)
        {
            JOptionPane.showMessageDialog(null,"All informations are validated");
            try
            {
                String gen="";
                String qualifiol="null";
                String qualifial="null";
                String qualifioth="null";
                String lanen="null";
                String lantam="null";
                String lansing="null";
                if(rb_female.isSelected())
                {
                    gen="Female";
                }
                else if(rb_male.isSelected())
                {
                    gen="Male";
                }
                if(ch_ol.isSelected())
                {
                    qualifiol="OL";
                }
                if(ch_al.isSelected())
                {
                    qualifial="AL";
                }
                if(ch_oth.isSelected())
                {
                    qualifioth="OTHERS";
                }
                if(ch_tamil.isSelected())
                {
                    lantam="Tamil";
                }
                if(ch_english.isSelected())
                {
                    lanen="English";
                }
                if(ch_sinhala.isSelected())
                {
                    lansing="Singala";
                }

                String emptype="";
                if(s_employee.getSelectedIndex()==1)
                {
                    if(rb_permanant.isSelected())
                    {
                        emptype="Permanant";
                    }
                    else if(rb_temprory.isSelected())
                    {
                        emptype="Visiting";
                    }
                }
                else
                {
                    emptype=null;
                }

                String sql = "INSERT INTO `employee` (`idEmployee`,`fname`,`lname`,`gender`,`status`,`dob`,`nic`,`contactnumber`,`email`,`address`,`ol`,`al`,`others`,`qualificationdetails`,`english`,`tamil`,`singala`,`desigination`,`employee`,`join`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                 
                ps = con.prepareStatement(sql);
                ps.setString(1, id.getText());
                ps.setString(2, txt_name.getText());
                ps.setString(3, txt_last.getText());
                ps.setString(4,gen );
                ps.setString(5,j_status.getSelectedItem().toString());
                ps.setString(6,formatter.format(datedob.getDate()).toString());
                ps.setString(7,txt_nic.getText());
                ps.setString(8,txt_contact.getText());
                ps.setString(9,txt_email.getText());
                ps.setString(10,txt_address.getText());
                ps.setString(11,qualifiol);
                ps.setString(12,qualifial);
                ps.setString(13,qualifioth);
                ps.setString(14,txt_qualification.getText());
                ps.setString(15,lanen);
                ps.setString(16,lantam);
                ps.setString(17,lansing);
                ps.setString(18,s_employee.getSelectedItem().toString());
                ps.setString(19,emptype);
                ps.setString(20,formatter.format(datejoin.getDate()).toString());

                int rowsInserted = ps.executeUpdate();
                ps.close();
                String sql2 = "INSERT INTO `login_employee` (`employeeid`,`username`,`password`) VALUES (?, ?, ?)";
                
                ps = con.prepareStatement(sql2);
                 ps.setString(1, id.getText());
                 ps.setString(2, id.getText());
                 ps.setString(3, txt_password1.getText());

                 ps.executeUpdate();

                if (rowsInserted > 0)
                {
                    JOptionPane.showMessageDialog(null,"A new user was inserted successfully!");

                      con=DBConnection.ConnectionDB();
                               DBConnection ndb=new DBConnection();
                          employee_table.setModel(DbUtils.resultSetToTableModel(ndb.getresultSet()));//show all employee details in a table
                             model=(DefaultTableModel)employee_table.getModel();//currently add
                             newlink=ndb.setValues();
                                LinkX mylink1=newlink.getFirst();
                            if(mylink1 != null){
                                   while(mylink1.next !=null){
                                   mylink1=mylink1.next;
                                    String eid=mylink1 .employee.getiData();
                                    id.setText("EM"+(Integer.parseInt(eid.substring(2,9)) + 1));//get last employee id and add  + 1
                                   // ch_search.addItem("EM"+(Integer.parseInt(eid.substring(2,9))));
                                 }
                            }
                            else{
                                 id.setText("EM1111111");
                            }
                     }

                txt_name.setText("");
                txt_last.setText("");
                rb_male.setSelected(false);
                rb_female.setSelected(false);
                j_status.setSelectedIndex(0);
                datedob.setDate(null);
                txt_nic.setText("");
                txt_contact.setText("");
                txt_email.setText("");
                txt_address.setText("");
                ch_ol.setSelected(false);
                ch_al.setSelected(false);
                ch_oth.setSelected(false);
                txt_qualification.setText("");
                ch_english.setSelected(false);
                ch_tamil.setSelected(false);
                ch_sinhala.setSelected(false);
                rb_permanant.setSelected(false);
                rb_temprory.setSelected(false);
                datejoin.setDate(null);
                s_employee.setSelectedIndex(0);
                txt_password1.setText("");
                txt_password2.setText("");

                rb_permanant.setEnabled(false);
                rb_temprory.setEnabled(false);
                
                st= con.createStatement();
                rs = st.executeQuery("SELECT * FROM employee");

                employee_table.setModel(DbUtils.resultSetToTableModel(rs));

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
                    Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            

        }
         else
        {
            JOptionPane.showMessageDialog(null,"Check your all inputs");
        }
    }/*
   
    
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
            java.util.logging.Logger.getLogger(EmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JButton bt_cancel;
    private javax.swing.JButton bt_delete;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_report;
    private javax.swing.JButton bt_search;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JButton btn_update;
    private javax.swing.JCheckBox ch_al;
    private javax.swing.JCheckBox ch_english;
    private javax.swing.JCheckBox ch_ol;
    private javax.swing.JCheckBox ch_oth;
    private javax.swing.JCheckBox ch_sinhala;
    private javax.swing.JCheckBox ch_tamil;
    private javax.swing.JCheckBox ch_uni;
    private javax.swing.JButton check_emp_password;
    private com.toedter.calendar.JDateChooser datedob;
    private com.toedter.calendar.JDateChooser datejoin;
    private javax.swing.JLabel e_address;
    private javax.swing.JLabel e_contact;
    private javax.swing.JLabel e_desigination;
    private javax.swing.JLabel e_dob;
    private javax.swing.JLabel e_email;
    private javax.swing.JLabel e_employee;
    private javax.swing.JLabel e_gender;
    private javax.swing.JLabel e_joindate;
    private javax.swing.JLabel e_language;
    private javax.swing.JLabel e_last;
    private javax.swing.JLabel e_name;
    private javax.swing.JLabel e_nic;
    private javax.swing.JLabel e_password1;
    private javax.swing.JLabel e_password2;
    private javax.swing.JLabel e_qualification;
    private javax.swing.JLabel e_qualificationdetails;
    private javax.swing.JLabel e_search;
    private javax.swing.JLabel e_status;
    private javax.swing.JTable employee_table;
    private javax.swing.JLabel id;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> j_status;
    private javax.swing.JRadioButton rb_female;
    private javax.swing.JRadioButton rb_male;
    private javax.swing.JRadioButton rb_permanant;
    private javax.swing.JRadioButton rb_temprory;
    private javax.swing.JComboBox<String> s_employee;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_last;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_nic;
    private javax.swing.JPasswordField txt_password1;
    private javax.swing.JPasswordField txt_password2;
    private javax.swing.JTextField txt_qualification;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
