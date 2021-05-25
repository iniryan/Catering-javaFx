/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cateringfx.admin;

import javafx.scene.control.ListCell;

/**
 *
 * @author asus
 */
public class MenuList extends ListCell<Menu>{
    protected void updateItem(Menu menu, boolean empty){
        super.updateItem(menu, empty);
        if (empty) {
            setText(null);
        } else {
            setText(menu.getNamaMenu());
        }
    }
    
}
