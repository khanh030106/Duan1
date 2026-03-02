/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimplTest;
import DA1.Entity.sendMessage;
import DA1.util.XJdbc;
/**
 *
 * @author ADMIN
 */
public class SendMessageImpl {
    String sendMessage = "{CALL sp_InsertUserAndStudent(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    
    public sendMessage create(sendMessage send){
        Object[] values = {
            send.getUserName(),
            send.getFullName(),
            send.getPassword(),
            send.getPhoto(),
            send.getRole(),
            send.isActive(),
            send.getBirthDate(),
            send.getGmail(),
            send.getClassName()
        };
        XJdbc.executeUpdate(sendMessage, values);
        return send;
    }
}
