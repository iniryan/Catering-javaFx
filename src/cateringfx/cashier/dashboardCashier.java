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

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class dashboardCashier extends Application {
    
    public static Stage stage = null;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("dashboardCashier.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/cateringfx/assets/icon/egg.png")));
        stage.setTitle("Food Catering | Pemesanan Catering");
        stage.setScene(scene);
        stage.setMaximized(true);
        //stage.setFullScreen(true);
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
