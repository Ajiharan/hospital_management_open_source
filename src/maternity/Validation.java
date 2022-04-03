/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maternity;

import javax.swing.JOptionPane;

/**
 *
 * @author Sivakumar
 */
public class Validation {
    
    //Validate Phone Number
    public static boolean validatePhone(javax.swing.JTextField t,javax.swing.JLabel l, boolean p){
        p = t.getText().matches("\\d{10}");
        if(p)
            l.setVisible(false);
        else
            l.setVisible(true);
        
        return p;
    }
    
    //Validate NIC Number 
    public static boolean validateNIC(javax.swing.JTextField t,javax.swing.JLabel l,boolean n ){
        n = t.getText().matches("\\d{9}[v|V]");
        if(n)
            l.setVisible(false);
        else
            l.setVisible(true);
        
        return n;
    }
    
    //Checking empty fields 
    public static boolean isempty(String s, String f)
    {
        if(!s.equals(""))
            return true;
        else
            JOptionPane.showMessageDialog(null,f+" field must not be empty");
            return false;
    }
    
    //Validate combo boxes
    public static boolean checkCombo(String s)
    {
        if(!s.equals("select"))
            return true;
        else
            JOptionPane.showMessageDialog(null,"Please Select a field");
            return false;
    }
    
    //Validate number format
    public static boolean checkNumber(String s){
    for (int i = 0 ; i < s.length(); i++)
    {
        char a = s.charAt(i);
            
        if (Character.isLetter(a))
        {
            JOptionPane.showMessageDialog(null,"You cannot have letters in this field");
            return false;
        }
    }
        return true;
    }
    
    public static boolean checkLetter(String s){
    for (int i = 0 ; i < s.length(); i++)
    {
        char a = s.charAt(i);
            
        if (Character.isDigit(a))
        {
            JOptionPane.showMessageDialog(null,"You cannot have numbers in this field");
            return false;
        }
    }
        return true;
    }
}
