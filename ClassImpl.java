/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.DAOimpl;

import DA1.Entity.Classes;
import DA1.util.XQuery;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ClassImpl {
    String findAll = "Select * from Class";
    String findClasses = "SELECT Name FROM Class";
    public List<Classes> findAll(){
        return XQuery.getBeanList(Classes.class, findAll);
    }
    public List<Classes> findAllClass(){
        return XQuery.getBeanList(Classes.class, findClasses);
    }
}
