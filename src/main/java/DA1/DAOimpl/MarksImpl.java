/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimpl;

import DA1.Entity.Marks;
import DA1.Entity.Subjects;
import DA1.util.XJdbc;
import DA1.util.XQuery;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class MarksImpl {
    String findAll = "SELECT * FROM Marks";
    String findAllSubject = " SELECT * FROM MonHoc";
    String updateMarks = "UPDATE Marks SET RegularGrade = ?, MidtermGrade = ?, FinalExam = ?"
            + " WHERE Student_ID = ? and Subject = ?";
    String findMarks = "SELECT * FROM Marks WHERE Student LIKE ?";
    String deleteMarks = "UPDATE Marks set RegularGrade = null, MidtermGrade = null, FinalExam = null WHERE Student_ID = ? and Subject = ?";
    
    public List<Marks> findAll(){
        return XQuery.getBeanList(Marks.class, findAll);
    }
    
    public List<Subjects> findAllSubjects(){
        return XQuery.getBeanList(Subjects.class, findAllSubject);
    }
    
    public void update(Marks m){
        Object[] values = {
            m.getRegularGrade(),
            m.getMidtermGrade(),
            m.getFinalExam(),
            m.getStudent_ID(),
            m.getSubject()
        };
        XJdbc.executeUpdate(updateMarks, values);
    }
    public List<Marks> findMarks(String name){
        return XQuery.getBeanList(Marks.class, findMarks, "%"+ name);
    }
    public void deleteMarks(Marks m){
        Object[] values = {
            m.getStudent_ID(),
            m.getSubject()
        };
        XJdbc.executeUpdate(deleteMarks, values);
    }
}
