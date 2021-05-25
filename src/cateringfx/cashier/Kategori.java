/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */
package cateringfx.cashier;

public class Kategori {
    private int kategoriId;
    private String event;
    
    public Kategori(int kategoriId, String event){
        this.kategoriId = kategoriId;
        this.event = event;
    }
    
    public int getKategoriId(){
        return this.kategoriId;
    }
    
    public String getEvent(){
        return this.event;
    }
    
    @Override
    public String toString() {
        return this.getEvent();
    }
}
