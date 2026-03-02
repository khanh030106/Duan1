/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimplTest;

import DA1.Entity.Marks;
import DA1.Entity.SubjectTitle;
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
    String insert ="INSERT INTO Marks (Student_ID, Student, Subject)\n" +
            "values(?,?,?)";
    String getAllSubjectTitle = "SELECT Distinct Subject as 'subject'  from Marks";
    String getMarksBySubjecTitle = "SELECT * FROM Marks WHERE Subject = ?";
    String deleteMarks = "UPDATE Marks set RegularGrade = null, MidtermGrade = null, FinalExam = null WHERE Student_ID = ? and Subject = ?";
    
    public List<Marks> findAll(){
        return XQuery.getBeanList(Marks.class, findAll);
    }
    
    public List<Subjects> findAllSubjects(){
        return XQuery.getBeanList(Subjects.class, findAllSubject);
    }
    
      public List<Marks> findAllBySubjectsTitle(String subjectTitle){
        return XQuery.getBeanList(Marks.class, getMarksBySubjecTitle, subjectTitle);
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

    public void insert(Marks m){
        Object[] values = {
                m.getStudent_ID(),
                m.getStudent(),
                m.getSubject()
        };
        System.out.println("sss"+values.toString());
        XJdbc.executeUpdate(insert, values);
    }
    public List<Marks> findMarks(String name){
        return XQuery.getBeanList(Marks.class, findMarks, "%"+ name);
    }
    
     public List<SubjectTitle> getAllSubjectTitle(){
        return XQuery.getBeanList(SubjectTitle.class, getAllSubjectTitle);
    }
    
    public void deleteMarks(Marks m){
        Object[] values = {
            m.getStudent_ID(),
            m.getSubject()
        };
        XJdbc.executeUpdate(deleteMarks, values);
    }
}
