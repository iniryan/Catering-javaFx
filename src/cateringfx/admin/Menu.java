/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */
package cateringfx.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Menu {
    public int menu_id;
    public String nama_menu;
    public int harga_satuan;

    // ini method setter
    public Menu(int menu_id, String nama_menu, int harga_satuan) {
        this.menu_id = menu_id;
        this.nama_menu = nama_menu;
        this.harga_satuan = harga_satuan;
    }
    
    // ini method getter
    public int getMenuid(){
        return this.menu_id;
    }

    public String getNamaMenu(){
        return this.nama_menu;
    }
    
    public int getRole(){
        return this.harga_satuan;
    }
    
    @Override
    public String toString() {
        return this.getNamaMenu();
    }
}
