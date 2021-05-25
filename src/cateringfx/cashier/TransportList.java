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

public class TransportList extends ListCell<Transport>{
   protected void updateItem(Transport transport, boolean empty){
        super.updateItem(transport, empty);
        if (empty) {
            setText(null);
        } else {
            setText(transport.getTujuan() + " - " + String.valueOf(transport.getBiayaTransport()));
        }
    } 
}
