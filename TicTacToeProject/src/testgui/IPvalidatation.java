/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Laptop
 */
public class IPvalidatation {
    
    private static String validIp;
    
    public static boolean isValidIPAddress(String ip) 
    { 
        // Regex for digit from 0 to 255. 
        String zeroTo255 
            = "(\\d{1,2}|(0|1)\\"
              + "d{2}|2[0-4]\\d|25[0-5])"; 
        String regex  = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255; 
        // Compile the ReGex 
        Pattern p = Pattern.compile(regex); 
        // If the IP address is empty 
        // return false 
        if (ip == null) { 
            
            return false; 
        } 
        
        Matcher m =p.matcher(ip);
        if(m.matches())
            validIp=ip;
        
        return m.matches(); 
    } 
    public static String getIp()
    {
        return validIp;
    }
    
}
