/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimpl;

import DA1.Entity.Students;
import DA1.util.XJdbc;
import DA1.util.XQuery;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class StudentsImpl {
    String findAllSQL = "SELECT Students.Students_ID, Users.User_ID, Users.Fullname, Users.Photo ,UserName, BirthDate, Gmail, Classes  \n" +
   "FROM Users  left join Students ON Users.User_ID = Students.User_ID WHERE Users.Role =2";
    String createSql = "INSERT INTO Students (User_ID, Fullname, Photo, BirthDate, Gmail, Classes) VALUES (?,?,?,?,?,?)";
    String updateSQL = "  UPDATE Students SET BirthDate = ?, Gmail = ?, Classes = ? WHERE Students_ID = ?";
    String findStudent = "SELECT * FROM Students WHERE Fullname like ?";
    String deleteStudent = "DELETE FROM Students WHERE Students_ID = ?";
    String StudentList = "  SELECT Students_ID, Fullname, BirthDate, Gmail, Classes FROM Students WHERE Classes = ?";
    
    public Students create(Students en){
        Object[] values = {
            en.getUser_ID(),
            en.getFullname(),
            en.getPhoto(),
            en.getBirthDate(),
            en.getGmail(),
            en.getClasses()
        };
        XJdbc.executeUpdate(createSql, values);
        return en;
    }
    
    public void update(Students std){
        Object[] values = {
            std.getBirthDate(),
            std.getGmail(),
            std.getClasses(),
            std.getStudents_ID()
        };
        XJdbc.executeUpdate(updateSQL, values);
    }
    
    public List<Students> findAll(){
        return XQuery.getBeanList(Students.class, findAllSQL);
    }
    
    public List<Students> findByName(String name){
        return XQuery.getBeanList(Students.class, findStudent, "%"+name);
    }
    
    public void deleteStById(int Id){
        XJdbc.executeUpdate(deleteStudent, Id);
    }
    
    public List<Students> classList(String name){
        return XQuery.getBeanList(Students.class, StudentList, name);
    }
}
