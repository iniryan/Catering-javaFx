/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */
package cateringfx;

import javafx.application.Platform;

public class userSession {
    private static int user_id;
    private static String username;
    private static String nama_lengkap;
    private static int role;
    
    // ini method setter
    public static void setUser(int user_id, String Username, String nama_lengkap, int role ){
        userSession.user_id = user_id;
        userSession.username = username;
        userSession.nama_lengkap = nama_lengkap;
        userSession.role = role;
    }
    
     public static void authorizeUser(){
        if (user_id == 0) {
            Platform.exit();
            System.exit(0);   
        }
    }
    
    public static void removeUser(){
        userSession.user_id = 0;
        userSession.username = null;
        userSession.nama_lengkap = null;
        userSession.role = 0;
    }
    
    // ini method getter
    public static int getUserID(){
        return user_id;
    }
    
    public static String getUsername(){
        return username;
    }
    
    public static String getNamaLengkap(){
        return nama_lengkap;
    }

    public static int getRole(){
        return role;
    }
}
