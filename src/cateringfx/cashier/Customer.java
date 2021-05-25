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

public class Customer {
    private int customerId;
    private String namaCustomer;
    private String alamat;
    private String notelp;
    
    public Customer(int customerId, String namaCustomer, String notelp, String alamat){
        this.customerId = customerId;
        this.namaCustomer = namaCustomer;
        this.notelp = notelp;
        this.alamat = alamat;
    }
    
    public int getCustomerId(){
        return customerId;
    }
    
    public String getNamaCustomer(){
        return namaCustomer;
    }
    
    public String getNotelp(){
        return notelp;
    }
    
    public String getAlamat(){
        return alamat;
    }
    
    @Override
    public String toString() {
        return this.getNamaCustomer();
    }
}
