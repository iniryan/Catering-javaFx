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

public class Transport {
   private int costId;
   private String tujuan;    
   private int biayaTransport;

    
    public Transport(int costId, String tujuan, int biayaTransport){
        this.costId = costId;
        this.tujuan = tujuan;
        this.biayaTransport = biayaTransport;
    }
    
    public int getCostId(){
        return this.costId;
    }
    
    public String getTujuan(){
        return this.tujuan;
    }
    
    public int getBiayaTransport(){
        return this.biayaTransport;
    }
    
    @Override
    public String toString() {
        return this.getTujuan()+" - "+String.valueOf(this.getBiayaTransport());
    }
}
