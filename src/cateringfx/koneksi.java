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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class koneksi {
    public Connection koneksi;
    public Statement stm;
    
    //mysql connect
    public Connection konek(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Koneksi");
        }
        catch(ClassNotFoundException cnfe){
            System.out.println("Gagal Koneksi"+cnfe);
        }
        String url="jdbc:mysql://localhost:3306/cateringfx";
        try{
            koneksi=DriverManager.getConnection(url, "root","");
            stm = koneksi.createStatement();
            System.out.println("Data Berhasil Tersambung");
        }
        catch(SQLException se){
            System.out.println("Tidak Ada Database");
        }
        
        return koneksi;
    }

    //main
    public static void main(String[] args) {
        koneksi con = new koneksi();
        con.konek();
    }
    
}
