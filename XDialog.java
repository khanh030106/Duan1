/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.util;

import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class XDialog {
    public static void alert(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE); 
    }
    public static void alert(String message){
        XDialog.alert(message, "Thông báo");
    }
    public static boolean confirm(String message, String title){
        int result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return (result == JOptionPane.YES_NO_OPTION);
    }
    public static boolean confirm(String message){
        return XDialog.confirm(message, "Confirm");
    }
    public static String prompt(String message, String title){
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    public static String prompt(String message){
        return XDialog.prompt(message, "Input");
    }
}
