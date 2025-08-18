/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimpl;

import DA1.Entity.Subjects;
import DA1.util.XJdbc;
import DA1.util.XQuery;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SubjectsImpl {
    String findAllSQL = "SELECT * FROM MonHoc";
    String createSql = "  INSERT INTO MonHoc(TenMon, TinChi) VALUES(?, ?)";
    String updateSql = "UPDATE MonHoc SET TenMon = ?, TinChi = ? WHERE MaMon = ?";
    String deleteSql = "DELETE FROM MonHoc WHERE MaMon = ?";
    String findSubject = "SELECT * FROM MonHoc WHERE TenMon LIKE ?";
    
    public List<Subjects> findAll(){
        return XQuery.getBeanList(Subjects.class, findAllSQL);
    }
    public Subjects create(Subjects su){
        Object[] values ={
            su.getTenMon(),
            su.getTinChi()
        };
        XJdbc.executeUpdate(createSql, values);
        return su;
    }
    
    public void update(Subjects su){
        Object[] values = {
            su.getTenMon(),
            su.getTinChi(),
            su.getMaMon()
        };
        XJdbc.executeUpdate(updateSql, values);
    }
    
    public void delete(int id){
        XJdbc.executeUpdate(deleteSql, id);
    }
    public List<Subjects> findByName(String name){
        return XQuery.getBeanList(Subjects.class,findSubject, "%"+ name +"%");
    }
}
