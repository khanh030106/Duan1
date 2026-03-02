/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimplTest;

import DA1.Entity.Departments;
import DA1.util.XJdbc;
import DA1.util.XQuery;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DepartmentImpl {
    String findAllSQl = "SELECT Khoa.MaKhoa, Khoa.TenKhoa, COUNT(MaChuyenNganh) as SoLuongChuyenNganh  FROM Khoa "
            + "left join ChuyenNganh on Khoa.MaKhoa = ChuyenNganh.MaKhoa group by Khoa.MaKhoa, TenKhoa";
    String createSQL = "INSERT INTO Khoa(TenKhoa) VALUES (?)";
    String deleteByID = "DELETE FROM Khoa WHERE MaKhoa = ?";
    String updateSQL = "UPDATE Khoa SET TenKhoa = ? WHERE MaKhoa =? ";
    String findByNameSQL = "SELECT Khoa.MaKhoa, Khoa.TenKhoa, COUNT(MaChuyenNganh) as SoLuongChuyenNganh  FROM Khoa left join ChuyenNganh \n" +
"on Khoa.MaKhoa = ChuyenNganh.MaKhoa group by Khoa.MaKhoa, TenKhoa having TenKhoa like ?";
    
    public Departments create(Departments de){
        Object[] values = {
            de.getTenKhoa()
        };
        XJdbc.executeUpdate(createSQL, values);
        return de;
    }
    
  public void deleteById(String ID){
      XJdbc.executeUpdate(deleteByID, ID);
  }
  
  public void update(Departments de){
      Object[] data = {
          de.getTenKhoa(),
          de.getMaKhoa()
      };
      XJdbc.executeUpdate(updateSQL, data);
  }
    
    public List<Departments> findAll(){
        return XQuery.getBeanList(Departments.class, findAllSQl);
    }
    
    public List<Departments> findByName(String name){
        return XQuery.getBeanList(Departments.class, findByNameSQL, "%"+name +"%");
    }
}
