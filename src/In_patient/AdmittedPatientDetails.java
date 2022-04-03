
package In_patient;

import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;



public class AdmittedPatientDetails extends javax.swing.JFrame {
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
  
    public AdmittedPatientDetails() {
        initComponents();
       currentDate();
       set_payid();
       
       
    }
    public void set_payid(){
        String p=auto_payId();
        pay.setText(p);
    }
    
    public void currentDate(){
       
       
       
       Thread clock=new Thread(){
          public void run(){
              for(;;){
                  Calendar cal=new GregorianCalendar();
       
                    int month=cal.get(Calendar.MONTH);
                    int year=cal.get(Calendar.YEAR);
                    int day=cal.get(Calendar.DAY_OF_MONTH);
   
                    date.setText(year+"-"+(month+1)+"-"+day);
       
                    int second=cal.get(Calendar.SECOND);
                    int minute=cal.get(Calendar.MINUTE);
                    int hour=cal.get(Calendar.HOUR);
       
                    time.setText(hour+":"+minute+":"+second);
                  
                  try {
                      sleep(1000);
                  } catch (InterruptedException ex) {
                      Logger.getLogger(inPatient_management.class.getName()).log(Level.SEVERE, null, ex);
                  }
              
              
              }
          
          
          } 
       
       
       };
       clock.start();
   
   }
    public String p1;
    public double p19;
    public double p20;
    public void addmitted_patient(String roomNo){
        Connection conn = MySqlConnect.connectDB();
        String q="select * "
                + "from patient p, room r "
                + "where r.pId=p.pId and r.roomNo=?";
        
        try {
            pst=conn.prepareStatement(q);
            pst.setString(1,roomNo);
             rs=pst.executeQuery();
             if(rs.next()){
                
                   
                   
                   String p1=rs.getString("p.pId");
                   pid.setText(p1);
                   
                   String p2=rs.getString("p.pNameTitle");
                   ptitle.setText(p2);
                   
                   String p3=rs.getString("p.pName");
                   pname.setText(p3);
                   
                   String p4=rs.getString("p.gender");
                   pgender.setText(p4);
                   
                   String p5=rs.getString("p.age");
                   page.setText(p5);
                   
                   String p6=rs.getString("p.address");
                   paddress.setText(p6);
                   
                   String p7=rs.getString("p.landLine");
                   plandline.setText(p7);
                   
                  String p8=rs.getString("p.mobile");
                   pmobile.setText(p8);
                   
                   String p9=rs.getString("p.gNameTitle");
                   gtitle.setText(p9);
                   
                   String p10=rs.getString("p.gName");
                   gname.setText(p10);
                   
                   String p11=rs.getString("p.gRelationship");
                   grelation.setText(p11);
                   
                   String p12=rs.getString("p.gLandLine");
                   glandline.setText(p12);
                   
                   String p13=rs.getString("p.gMobile");
                   gmobile.setText(p13);
                  
                   
                   String p14=rs.getString("r.roomNo");
                   rid.setText(p14);
                   
                   String p15=rs.getString("r.addmittedDate");
                   aDate.setText(p15);
                   
                   String p16=rs.getString("r.addmittedTime");
                   aTime.setText(p16);
                   
                   String p17=rs.getString("r.admittedDrName");
                   aDr.setText(p17);
                   
                   
                   String p18=rs.getString("r.admittedUnderDrName");
                   auDr.setText(p18);
                   
                   p19=rs.getDouble("dipositAmount");
                   
                   p20=rs.getDouble("roomCharge");
                   
                   
                   
             }
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdmittedPatientDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    
    }
    
    
    public void discharge_patient(){
        
        String roomid=rid.getText();
        String pid=null;
        double depositAmount=0.0;
         String admittedDr=null;
        String DrUnderBy=null;
        String date=null;
        String time=null;
         boolean availabilty=true;
           ward w;
          w = new ward(roomid,pid,depositAmount,admittedDr,DrUnderBy,date,time,availabilty);
        
        w.update_roomStatus();

    }
    
    public int days_calc(){
      //   Calendar calendar1 = Calendar.getInstance();
      //   Calendar calendar2 = Calendar.getInstance();
    
      //   String addDate=aDate.getText();
       //  String DisDate=date.getText();
         
         
       String dateStart =aDate.getText(); //"01/14/2012 09:29:58";
       String dateStop = date.getText();  //"01/15/2012 10:31:48";
         
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       
       Date d1 = null;
       Date d2 = null;
      // long diffDays=null;
      int diffDays = 0;
       try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			//long diffSeconds = diff / 1000 % 60;
			//long diffMinutes = diff / (60 * 1000) % 60;
			//long diffHours = diff / (60 * 60 * 1000) % 24;
			 diffDays = (int) (diff / (24 * 60 * 60 * 1000));

			System.out.print(diffDays + " days, ");
			//System.out.print(diffHours + " hours, ");
			//System.out.print(diffMinutes + " minutes, ");
			//System.out.print(diffSeconds + " seconds.");

		}
       catch (Exception e) {
		e.printStackTrace();
	}
        
         if(diffDays==0){
                diffDays=diffDays+1;
         }
       
       
       return diffDays;
       
       
    }
    
    
 /*   public int days_calc(){
       // String addDate=aDate.getText();
      //  String DisDate=date.getText();

        Calendar cal=new GregorianCalendar();
       
                    int amonth=cal.get(Calendar.MONTH);
                    int ayear=cal.get(Calendar.YEAR);
                    int aday=cal.get(Calendar.DAY_OF_MONTH);
   
                    
        String addDate=aDate.getText();
        
         String[] add_parts = addDate.split("-");
        String add_year=add_parts[0];
        String add_month=add_parts[1];
        String add_day=add_parts[2];
        
        int dyear =Integer.parseInt(add_year);
        int dmonth =Integer.parseInt(add_month);
        int dday =Integer.parseInt(add_day);
        int day=0;
        int a,b;
        
        if(ayear == dyear){
            if(amonth == dmonth){
                day = dday - aday;
            
            }
            else if(dmonth >  amonth){
                a = aday - dday;
                day = 30 - a;
                
            }
        }
        else if(dyear > ayear){
            if(dmonth < amonth){
                if(dday < amonth ){
                    day = aday - dday;
                }
                else if(dday >= aday){
                    b = dday - aday;
                    day = 335+30 + b;    
                }
            }
            
            else if(dmonth >=  amonth){
                if(dday < aday ){
                    day = aday - dday;
                }
                else if(dday >= aday){
                    b = dday - aday;
                    day = 355 + 30 + b;    
                }
            }
        }
            
        if(day==0){
            day=day+1;
        }
        
        System.out.println(day);
       return day;
    }
   */ 
    public double total_payment(){
       double roomCharge=p20;
       int days=days_calc();
       
       double total=roomCharge*days;
       
       return total;
    
    
    }
    //public String payId;
    
    public String auto_payId(){
        String payId="";
        try{
                       Connection conn = MySqlConnect.connectDB();
                        String p="SELECT paymentId FROM WardPayment ORDER BY paymentId DESC LIMIT 1";
                         pst=conn.prepareStatement(p);
                        rs=pst.executeQuery();
                       if(rs.next()){
                            String paymentId=rs.getString("paymentId");
                           String[] parts = paymentId.split("(?<=-)");
                            String part1 = parts[0]; // 004-
                            String part2 = parts[1]; // 034556
                    
                             int id = Integer.parseInt(part2);
                             int id2=id+1;
                             String id3=Integer.toString(id2);
                             payId=part1+id3;
             
                          }
                       conn.close();
                       
        
        
        
             }
             catch(Exception e){
        
        }
        return payId;
    }
    
    public void calculate_payment1() throws ClassNotFoundException{
        
         String payId=auto_payId();
        String pId=pid.getText();
        String roomNo=rid.getText();
       String addDate=aDate.getText();
        String DisDate=date.getText();
        double advance=p19;
        double total=total_payment();
        double due=total-advance;
        
        
         
        
        
            
            
            payment pm=new payment(payId,pId,roomNo,addDate,DisDate,advance,total,due);
            pm.add_payment();
    
    
    } 
    
    
    public void report(){
        Connection conn = MySqlConnect.connectDB();
         HashMap parameter = new HashMap();
        String paymentId= JOptionPane.showInputDialog("Please insert the Payment ID");
        parameter.put("paymentId",paymentId);

//        try {
//            String path="C:\\Users\\USER\\Desktop\\Desktop Folders\\ITP_SLIIT\\MY ITP\\After prototype\\wardBill.jrxml";
//            
//            JasperReport jr = JasperCompileManager.compileReport(path);  
//            JasperPrint jp = JasperFillManager.fillReport(jr, parameter, conn);
//            JasperViewer.viewReport(jp,false);
//        } catch (JRException ex) {
//            Logger.getLogger(Stock_management.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null,ex);
//        }
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pid = new javax.swing.JLabel();
        ptitle = new javax.swing.JLabel();
        pname = new javax.swing.JLabel();
        pgender = new javax.swing.JLabel();
        page = new javax.swing.JLabel();
        paddress = new javax.swing.JLabel();
        plandline = new javax.swing.JLabel();
        pmobile = new javax.swing.JLabel();
        gtitle = new javax.swing.JLabel();
        gname = new javax.swing.JLabel();
        grelation = new javax.swing.JLabel();
        glandline = new javax.swing.JLabel();
        gmobile = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        rid = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        aDate = new javax.swing.JLabel();
        aTime = new javax.swing.JLabel();
        aDr = new javax.swing.JLabel();
        auDr = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pay = new javax.swing.JLabel();
        close = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admitted Patient Details");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Patient Id");

        jLabel3.setText("Name");

        jLabel4.setText("Gender");

        jLabel5.setText("Age");

        jLabel6.setText("Address");

        jLabel7.setText("Contact No");

        jLabel8.setText("Guardian's Name");

        jLabel9.setText("Relationship");

        jLabel10.setText("Guardian's Contact NO");

        pid.setText("Id");

        ptitle.setText("title");

        pname.setText("name");

        pgender.setText("gender");

        page.setText("age");

        paddress.setText("address");

        plandline.setText("landline");

        pmobile.setText("mobile");

        gtitle.setText("title");

        gname.setText("gName");

        grelation.setText("relation");

        glandline.setText("landline");

        gmobile.setText("mobile");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ptitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pgender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(page, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(plandline, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pmobile, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(gtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gname, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(grelation, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(glandline, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gmobile, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pid))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ptitle)
                    .addComponent(pname))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(pgender, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(page))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(paddress, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(plandline)
                    .addComponent(pmobile))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(gtitle)
                    .addComponent(gname))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(grelation))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(glandline)
                    .addComponent(gmobile))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Admitted Patient Details");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setText("RoomNo");

        rid.setText("roomNo");

        jLabel12.setText("Admitted Date");

        jLabel13.setText("Admitted Time");

        jLabel14.setText("Admitted Doctor");

        jLabel15.setText("Admitted Under By Doctor");

        aDate.setText("date");

        aTime.setText("time");

        aDr.setText("aDr");

        auDr.setText("auDr");

        jLabel16.setText("Payment Id");

        pay.setText("payId");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(rid, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                        .addComponent(aDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(aTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(aDr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(auDr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pay, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(rid))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(aDate))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(aTime))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(aDr))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(auDr))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(pay))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        jButton1.setText("Discharge");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel20.setText("Date");

        date.setText("D");

        jLabel22.setText("Time");

        time.setText("T");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(date)
                    .addComponent(jLabel22)
                    .addComponent(time))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
       this.setVisible(false);
       inPatient_management inp=new inPatient_management();
       inp.setVisible(true);
    }//GEN-LAST:event_closeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            calculate_payment1();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdmittedPatientDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        discharge_patient();
        
        inPatient_management inp=new inPatient_management();
       inp.setVisible(true);
       
       report();
    }//GEN-LAST:event_jButton1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(AdmittedPatientDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdmittedPatientDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdmittedPatientDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdmittedPatientDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdmittedPatientDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aDate;
    private javax.swing.JLabel aDr;
    private javax.swing.JLabel aTime;
    private javax.swing.JLabel auDr;
    private javax.swing.JButton close;
    private javax.swing.JLabel date;
    private javax.swing.JLabel glandline;
    private javax.swing.JLabel gmobile;
    private javax.swing.JLabel gname;
    private javax.swing.JLabel grelation;
    private javax.swing.JLabel gtitle;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel paddress;
    private javax.swing.JLabel page;
    private javax.swing.JLabel pay;
    private javax.swing.JLabel pgender;
    private javax.swing.JLabel pid;
    private javax.swing.JLabel plandline;
    private javax.swing.JLabel pmobile;
    private javax.swing.JLabel pname;
    private javax.swing.JLabel ptitle;
    private javax.swing.JLabel rid;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
