/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controllers;

/**
 *
 * @author ADMIN
 */
public interface CrudControllers <Entity>{
    void setForm(Entity entity);
    Entity getForm();
    void fillToTable();
    void edit();
    void create();
    void update();
    void delete();
    void clear();
    void setEditable(boolean editable);
    void checkAll();
    void uncheckAll();
    void deleteCheckedItems();
    void moveFirst();
    void movePrevious();
    void moveNext();
    void moveLast();
    void moveTo(int rowIndex);
}
