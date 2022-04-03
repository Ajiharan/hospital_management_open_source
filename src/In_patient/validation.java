
package In_patient;

import javax.swing.JOptionPane;


public class validation { 
    String nic_pp=""; 
    String p_landline;
    String p_mobile;
    String g_nic_pp="";
    String g_landline;
    String g_mobile;
    
    
    public  validation(String a,String b,String c,String d,String e,String f){
    nic_pp=a;
    p_landline=b;
    p_mobile=c;
    g_nic_pp=d;
    g_landline=e;
    g_mobile=f;

}
  //  public boolean ii;
    public boolean p_nic(){
        
        boolean x=true;
        if(!nic_pp.isEmpty() )
        {        
                  if(!nic_pp.matches("[0-9]{9}[v|V]")){
                    JOptionPane.showMessageDialog(null,"Invalid Patient Nic or Passport Number...");
                     x= false;
        
                   }
                     
                   else
                     x= true;
                  
                  
        }
        return x;
        
            
    }
    public boolean g_nic(){
        boolean x=true;
        if(!g_nic_pp.isEmpty()){
            
            if(!g_nic_pp.matches("[0-9]{9}[v|V]")){
                  JOptionPane.showMessageDialog(null,"Invalid gaurdian Nic ");
                    x= false;
            }
            else 
            x=true;
        
        
        }
        return x;
    }
    
    
    public boolean phoneNo_validation(){
        
        boolean y;
       // if(){
        if(!p_mobile.matches("[0-9]{10}")){
            JOptionPane.showMessageDialog(null,"Invalid patient mobile Number...");
            y= false;
        } 
        else if(!p_landline.matches("[0-9]{10}")) {
            JOptionPane.showMessageDialog(null,"Invalid patient LandLine Number...");
            y= false;
        }
        else if(!g_mobile.matches("[0-9]{10}")){
            JOptionPane.showMessageDialog(null,"Invalid gaurdian mobile Number...");
            y= false;
        }
        else if(!g_landline.matches("[0-9]{10}")){
            JOptionPane.showMessageDialog(null,"Invalid gaurdian LandLine Number...");
            y= false;
        }
        else
            y= true;
        
        return y;
    }

    
    

}
