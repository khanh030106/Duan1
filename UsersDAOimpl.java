/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimplTest;

import DA1.DAO.UsersDAO;
import DA1.Entity.Users;
import DA1.util.XJdbc;
import DA1.util.XQuery;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UsersDAOimpl  {

    String createSql = "INSERT INTO Users (UserName, Fullname, PassWord, Photo, Role, IsActive) VALUES  ( ? , ?, ?, ?, ?, ?)";
    String updateSql = " UPDATE Users set UserName = ?, Fullname = ?,  PassWord = ? ,Photo = ?,  Role = ? , IsActive = ?  where User_ID = ? ";
    String deleteSql = "DELETE FROM Users WHERE User_ID =?";
    String findAllSql = "SELECT * from Users";
    String findByIdSql = "SELECT * from Users where UserName = ?";
    String findByNameSql = "SELECT * FROM Users WHERE Fullname like ?";


    public Users create(Users entity) {
        Object[] values = {
            entity.getUserName(),
            entity.getFullname(),
            entity.getPassWord(),
            entity.getPhoto(),
            entity.getRole(),
            entity.isIsActive()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

 
    public void update(Users entity) {
        Object[] values = {
            entity.getUserName(),
            entity.getFullname(),
            entity.getPassWord(),
            entity.getPhoto(),
            entity.getRole(),
            entity.isIsActive(),
            entity.getUser_ID()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

 
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

 
    public List<Users> findAll() {
           return XQuery.getBeanList(Users.class, findAllSql);
    }


    public Users findById(String id) {
        return XQuery.getSingleBean(Users.class, findByIdSql, id);
    }
    public List<Users> findByName(String name){
        return XQuery.getBeanList(Users.class, findByNameSql, "%"+name);
    }
}
