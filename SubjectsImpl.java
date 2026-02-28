/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimpl;

import DA1.Entity.Subjects;
import DA1.util.XQuery;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SubjectsImpl {
    String findAllSQL = "SELECT mh.*, cn.TenChuyenNganh FROM MonHoc mh JOIN ChuyenNganh cn ON mh.MaChuyenNganh = cn.MaChuyenNganh";
    
    public List<Subjects> findAll(){
        return XQuery.getBeanList(Subjects.class, findAllSQL);
    }
}
