
package In_patient;
import In_patient.ward_booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class ward {
    String pid="";
    String roomNo="";
    String roomcat="";
    String roomtype="";
    double roomcharge=0.0;
    double deposit;
    String addDr="";
    String underDr="";
    boolean rAvailable=false;
    String addDate;
    String addTime;
    
    public ward(String rNo,String id,double depo,String aDr,String uDr,String date,String time,boolean rAvai){
    roomNo=rNo;
    pid=id;
    deposit=depo;
    addDr=aDr;
    underDr=uDr;
    rAvailable=rAvai;
    addDate=date;
    addTime=time;
    
    
    
    
    }
    
    private ward(String rNo,String id,double depo,String aDr,String uDr,String date,String time,boolean rAvai,String rCat,String rType,double rCharge){
    
        this(rNo,id,depo,aDr,uDr,date,time,rAvai);
        
        roomcat=rCat;
        roomtype=rType;
        roomcharge=rCharge;
        
    
    }
    
    public void update_roomStatus(){
        PreparedStatement pst=null;
        Statement st=null; 
        ResultSet rs=null;
        try{
            Connection conn = MySqlConnect.connectDB();
            st=conn.createStatement();
            
           // String q="update room set  pId='"+pid+"', dipositAmount='"+deposit+"', admittedDrName='"+addDr+"', admittedUnderDrName='"+underDr+"', roomAvailabilityStatus='"+rAvailable+"', addmittedDate='"+addDate+"', addmittedTime='"+addTime+"' where roomNo='"+roomNo+"' ";
           // pst=conn.prepareStatement(q);
           // pst.execute();
          //  JOptionPane.showMessageDialog(null,"Booking is Successful");
        
            String q1="update room set  pId=?, dipositAmount=?, admittedDrName=?, admittedUnderDrName=?, roomAvailabilityStatus=?, addmittedDate=?, addmittedTime=? where roomNo=? ";
            pst=conn.prepareStatement(q1);
            pst.setString(1,pid);
            pst.setDouble(2,deposit);
            pst.setString(3,addDr);
            pst.setString(4,underDr);
            pst.setBoolean(5,rAvailable);
            pst.setString(6,addDate);
            pst.setString(7,addTime);
            pst.setString(8,roomNo);
            pst.execute();
           // rs=pst.executeQuery();
         //   JOptionPane.showMessageDialog(null,"Booking is Successful");
            
            conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        
        }
    
    
    }
   
    
}
