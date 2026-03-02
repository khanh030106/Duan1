/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimplTest;
import DA1.Entity.Teachers;
import DA1.util.XJdbc;
import DA1.util.XQuery;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class TeachersImpl {
    String createSQL = "INSERT INTO Teachers (User_ID, Name, Photo, Birthdate, Gmail, Subjects) VALUES(?,?,?,?,?,?)";
    String updateSql ="UPDATE Teachers SET Name =?, Photo = ?,Birthdate = ?, Gmail =?, Subjects =? WHERE User_ID =? ";
    String deleteSql = "DELETE FROM Teachers WHERE ID_GV = ?";
    String findByName = "SELECT * FROM Teachers WHERE Name like ?";
    String findAllDepartment ="SELECT * FROM Khoa";
    String findAllSql= "SELECT  Teachers.ID_GV as ID_GV, Users.User_ID, Users.Fullname as 'Name', Users.Photo, Birthdate, Gmail, Subjects\n" +
"FROM Users left join Teachers on Users.User_ID = Teachers.User_ID WHERE Users.Role=1";
     String fillToTeacher ="SELECT Name FROM Teachers WHERE Name NOT IN (SELECT Teachers FROM Classes)";
    
    public Teachers create(Teachers en){
        Object[] values ={
            en.getUser_ID(),
            en.getName(),
            en.getPhoto(),
            en.getBirthdate(),
            en.getGmail(),
            en.getSubjects()
        };
        XJdbc.executeUpdate(createSQL, values);
        return en;
    }
    public void update(Teachers en){
        Object[] values = {
            en.getName(),
            en.getPhoto(),
            en.getBirthdate(),
            en.getGmail(),
            en.getSubjects(),
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
    public List<Teachers> fillToTeacher(){
        return XQuery.getBeanList(Teachers.class, fillToTeacher);
    }
}
