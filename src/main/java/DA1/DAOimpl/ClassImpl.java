/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimpl;

import DA1.Entity.Classes;
import DA1.Entity.Teachers;
import DA1.util.XJdbc;
import DA1.util.XQuery;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ClassImpl {
    String findAll = "SELECT Class_ID, Name, Teachers, count(Students_ID) as 'SoLuong' "
            + "FROM Classes left join Students on Classes.Name = Students.Classes group by Classes, Class_ID, Name, Teachers";
    String findClasses = "SELECT Name FROM Classes";
    String createSql = " INSERT INTO Classes (Name, Teachers) VALUES(?,? )";
    public Classes create(Classes classes){
        Object[] values = {
            classes.getName(),
            classes.getTeachers()
        };
        XJdbc.executeUpdate(createSql, values);
        return classes;
    }
    
    public List<Classes> findAll(){
        return XQuery.getBeanList(Classes.class, findAll);
    }
    public List<Classes> findAllClass(){
        return XQuery.getBeanList(Classes.class, findClasses);
    }
    

}
