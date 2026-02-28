/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.util;

/**
 *
 * @author ADMIN
 */
public class XStr {
    public static boolean isBlank(String text){
        return text == null || text.trim().length() ==0;
    }
    public static String valueOf(Object object){
        return object == null ? "" : String.valueOf(object);
    }
}
