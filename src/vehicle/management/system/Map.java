package vehicle.management.system;


import java.net.URL;
import javax.swing.JOptionPane;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kiros
 */
public class Map {

    private static Document loadXMLDoc(String url) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory.newDocumentBuilder().parse(new URL(url).openStream());
    }

	public static String GetDistance(String origin, String destination) throws Exception
        {
            String url = "http://maps.googleapis.com/maps/api/distancematrix/xml?origins=" +
              origin + "Srilanka&destinations=" + destination +
              "Srilanka&mode=driving&sensor=false&language=en-EN&units=metric";

                Document xmldoc = loadXMLDoc(url);
                xmldoc.getDocumentElement().normalize();
                String rootNode =  xmldoc.getDocumentElement().getNodeName();
                String statusVal = (String)xmldoc.getElementsByTagName("status").item(1).getChildNodes().item(0).getNodeValue();
                
            	if(statusVal.equals("OK"))
            	{
            		String nodeDistance = (String)xmldoc.getElementsByTagName("text").item(1).getChildNodes().item(0).getNodeValue();
            		System.out.println(nodeDistance);
                        
                        //int x=3;
                        String nd = nodeDistance;
                        String[] parts = nd.split(" ");
                        int jjj= Integer.parseInt(parts[0]);
                        int time = jjj*3;
                        JOptionPane.showMessageDialog(null,"The distance is "+nodeDistance +"\n  Time taken to reach the destination is "+time+" minutes");
                        
                        //JOptionPane.showMessageDialog(null,"The distance is "+time);
                      
            	}
            	else
            	{
            		return "There has been an error";
            	}
                return "error";
        }
	
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		
//		
//	}

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) throws Exception {
//        // TODO code application logic here
//                try
//		{
//			GetDistance("wellawatte","bambalapitya");
//		}
//		catch(Exception e)
//		{
//			System.out.println("Exception thrown");
//		}
//    }
    
}
