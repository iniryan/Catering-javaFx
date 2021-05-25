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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import cateringfx.koneksi;

public class CateringController implements Initializable {
    
    //koneksi
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    //halaman
    @FXML
    private BorderPane parent;
    
    //field
    @FXML
    private TextField usernameInput;
    
    @FXML
    private PasswordField passwordInput;
     
    //induknya 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //koneksi
        koneksi DB = new koneksi();
        DB.konek();
        con = DB.koneksi;
//        stat = DB.stm;
    }    
    
    //button click
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception{
        
        String nav = null;
        String username = usernameInput.getText().toLowerCase().trim();
        String password = passwordInput.getText();
        
        if (!username.isEmpty() && !password.isEmpty()) {
            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM c_user WHERE username = ? AND password = md5(?)");
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if(rs.next()){
                    int User_id = rs.getInt("user_id");
                    String Username = rs.getString("username");
                    String NamaLengkap = rs.getString("nama_lengkap");
                    int role = rs.getInt("role");
                    int aktivasi = rs.getInt("aktivasi");
                    
                    if(aktivasi == 1){
                        //usersession
                        userSession.setUser(User_id, Username, NamaLengkap, role);
                        
                        if (role == 1) {
                            nav = "/cateringfx/admin/dashboardAdmin.fxml";
                        } else {
                             nav = "/cateringfx/cashier/dashboardCashier.fxml";
                        }
                        
                        Parent root = FXMLLoader.load(getClass().getResource(nav));

                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

                        stage.getScene().setRoot(root);

                    }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Akun ini telah dinonaktifkan!");
                            alert.showAndWait();
                        }

                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Username atau password salah!");
                    alert.showAndWait();
                }

            } catch (SQLException | IOException e) {
                   Alert alert = new Alert(Alert.AlertType.ERROR, "Maaf sepertinya ada yang salah, mohon tunggu beberapa saat dan coba lagi!");
                   alert.showAndWait();
                   e.printStackTrace();
            }
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Username dan password!");
            alert.showAndWait();
        }
    }
    
    //btn minimize
    @FXML
    private void minimize_stage2(MouseEvent event) {
        CateringFX.stage.setIconified(true);
    }

//  btn close
    @FXML
    private void close_app2(MouseEvent event) {
        System.exit(0);
    }
    
    
}
