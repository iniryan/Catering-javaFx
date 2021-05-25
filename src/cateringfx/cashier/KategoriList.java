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

import javafx.scene.control.ListCell;

public class KategoriList extends ListCell<Kategori>{
    protected void updateItem(Kategori kategori, boolean empty){
        super.updateItem(kategori, empty);
        if (empty) {
            setText(null);
        } else {
            setText(kategori.getEvent());
        }
    }
}
