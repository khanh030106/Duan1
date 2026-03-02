/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimplTest;

import DA1.Entity.Majors;
import DA1.util.XQuery;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class MajorsImpl {
    String findAll = "SELECT cn.*, k.TenKhoa FROM ChuyenNganh cn JOIN Khoa k ON cn.MaKhoa = k.MaKhoa";
    
    public List<Majors> findAll(){
        return XQuery.getBeanList(Majors.class, findAll);
    }
}
