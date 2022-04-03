
package In_patient;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class payment {
    String paymentId="";
    String pId="";
    String roomNo="";
    String addDate="";
    String DisDate="";
    double advance=0.0;
    double total=0.0;
    double due=0.0;
    
    
    
    public payment(String a,String b,String c,String d,String e,double f,double g,double h){
        paymentId=a;
        pId=b;
        roomNo=c;
        addDate=d;
        DisDate=e;
        advance=f;
        total=g;
        due=h;
    
    }
    
    public void add_payment()throws ClassNotFoundException{
        Statement st=null;
        
        try{
            Connection conn = MySqlConnect.connectDB();
            st = conn.createStatement();
            
            String q="insert into WardPayment(`paymentId`,`pId`,`roomNo`,`addmittedDate`,`dischargeDate`,`addvancePayment`,`totalPayment`,`duePayment`)"
                    + "values('"+paymentId+"','"+pId+"','"+roomNo+"','"+addDate+"','"+DisDate+"','"+advance+"','"+total+"','"+due+"')";
        
             st.executeUpdate(q);
              JOptionPane.showMessageDialog(null,"Payment Added!!");
              conn.close();
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    
    
    }
    
    
    
    
}
