/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class XQuery {

    private static <B> B ReadBean(ResultSet resultSet, Class<B> beanClass) throws Exception {
        B bean = beanClass.getDeclaredConstructor().newInstance();
        Method[] methods = beanClass.getDeclaredMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("set") && method.getParameterCount() == 1) {
                try {
                    Object value = resultSet.getObject(name.substring(3));
                    method.invoke(bean, value);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException e) {
                    System.out.printf("+ Column '%s' not found!\r\n", name.substring(3));
                }
            }
        }
        return bean;
    }

    public static <B> List<B> getBeanList(Class<B> beaClass, String sql, Object... values) {
        List<B> list = new ArrayList<>();
        try {
            ResultSet resultSet = XJdbc.executeQuery(sql, values);
            while (resultSet.next()) {
                list.add(XQuery.ReadBean(resultSet, beaClass));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return list;
    }
    
    public static <B> B getSingleBean(Class<B> beaClass, String sql, Object... values){
        List<B> list = XQuery.getBeanList(beaClass, sql, values);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
