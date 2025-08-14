/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimpl;

import DA1.Entity.Departments;
import DA1.Entity.Teachers;
import DA1.util.XJdbc;
import DA1.util.XQuery;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class TeachersImpl {
    String createSQL = "INSERT INTO Teachers (User_ID, Fullname, Photo, Birthdate, Gmail, Department) VALUES(?,?,?,?,?,?)";
    String updateSql ="UPDATE Teachers SET Fullname =?, Photo = ?,Birthdate = ?, Gmail =?, Department =? WHERE User_ID =? ";
    String deleteSql = "DELETE FROM Teachers WHERE ID_GV = ?";
    String findByName = "SELECT * FROM Teachers WHERE Fullname like ?";
    String findAllDepartment ="SELECT * FROM Khoa";
    String findAllSql= "SELECT  Teachers.ID_GV as ID_GV, Users.User_ID, Users.Fullname, Users.Photo, Birthdate, Gmail, Department\n" +
"FROM Users left join Teachers on Users.User_ID = Teachers.User_ID WHERE Users.Role=1";
    
    public Teachers create(Teachers en){
        Object[] values ={
            en.getUser_ID(),
            en.getFullname(),
            en.getPhoto(),
            en.getBirthdate(),
            en.getGmail(),
            en.getDepartment()
        };
        XJdbc.executeUpdate(createSQL, values);
        return en;
    }
    public void update(Teachers en){
        Object[] values = {
            en.getFullname(),
            en.getPhoto(),
            en.getBirthdate(),
            en.getGmail(),
            en.getDepartment(),
            en.getUser_ID()
        };
        XJdbc.executeUpdate(updateSql, values);
    }
    public void deleteById(String id){
        XJdbc.executeUpdate(deleteSql, id);
    }
    
    public List<Teachers> findAll(){
        return XQuery.getBeanList(Teachers.class, findAllSql);
    }
    public  List<Teachers> findByName(String name){
        return XQuery.getBeanList(Teachers.class, findByName, "%" +name);
    }
    public List<Departments> findAllDepartment(){
        return XQuery.getBeanList(Departments.class, findAllDepartment);
    }
}
