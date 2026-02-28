/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class XDate {
    public static final String FULL_PATERN = "HH:mm:ss dd/MM/yyyy";
    public static final String SHORT_PATERN = "dd/MM/yyyy"; 
    
    public static final SimpleDateFormat formater = new SimpleDateFormat();
    
    public static Date now(){
        return new Date();
    }
    
    public static Date parse(String date, String patern){
        formater.applyLocalizedPattern(patern);
        try {
            return formater.parse(date);
        } catch (Exception e) {
            return null;
        }
    }
    public static Date parse(String date){
        return parse(date, SHORT_PATERN);
    }
    
    public static String format(Date date, String patern){
        if (date == null) {
            return "";
        }
        formater.applyLocalizedPattern(patern);
        return formater.format(date);
        }
    public static String format(Date date){
        return format(date, SHORT_PATERN);
    }
}
