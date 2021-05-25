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

import cateringfx.cashier.*;

public class Paket {
    public int paketId;
    public String paketName;
    public int paketPrice;
    public int qty = 0;

    public Paket(int paketId, String paketName, int paketPrice, int qty) {
        this.paketId = paketId;
        this.paketName = paketName;
        this.paketPrice = paketPrice;
        this.qty = qty;
    }
    
    public int getPaketId(){
        return this.paketId;
    }

    public String getNamaPaket(){
        return this.paketName;
    }
    
    public int getHargaPaket(){
        return this.paketPrice;
    }
    
    public int getQty(){
        return this.qty;
    }
    
    public void addQty() {
        this.qty += 1;
    }
    
    public void minQty(){
        this.qty -= 1;
    }
}
