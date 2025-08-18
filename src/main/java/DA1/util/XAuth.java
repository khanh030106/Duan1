/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.util;

import DA1.Entity.Users;

/**
 *
 * @author ADMIN
 */
public class XAuth {
    public static Users user = Users.builder()
            .UserName("khanh")
            .PassWord("111")
            .Fullname("Phạm Gia Khanh") 
            .Photo("avt.jpg")
            .Role(0)
            .IsActive(true)
            .build();
}
