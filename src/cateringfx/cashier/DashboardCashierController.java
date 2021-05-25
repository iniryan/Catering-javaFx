/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * FXML Controller class
 *
 * @author asus
 */
package cateringfx.cashier;

import cateringfx.cashier.dashboardCashier;
import cateringfx.koneksi;
import cateringfx.userSession;
import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Locale;
//import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class DashboardCashierController implements Initializable {

    //koneksi
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    //halaman
    @FXML
    private AnchorPane paneTransaksi;
    
    @FXML
    private AnchorPane contentPelanggan;
    
    @FXML
    private AnchorPane contentPaket;
    
    @FXML
    private AnchorPane contentMenu;
    
    @FXML
    private AnchorPane contentInvoice;
    
    //sidebar + btn
    @FXML
    private JFXButton home;
    
    @FXML
    private JFXButton paket;
    
    @FXML
    private JFXButton tambahTransaksi;
    
    @FXML
    private JFXButton tambahTransaksi2;
    
    @FXML
    private JFXButton menu;
    
    @FXML
    private JFXButton pelanggan;
    
    @FXML
    private JFXButton keluar;
    
    //itemList
    @FXML
    private VBox itemPelanggan2 = null;
    
    @FXML
    private VBox itemMenu = null;
    
    @FXML
    private VBox itemPaket = null;
    
    @FXML
    private VBox itemMenu2 = null;
    
    @FXML
    private VBox itemMenu3 = null;
    
    @FXML
    private VBox itemDetail = null;
    
     @FXML
    private VBox itemPesanan = null;
    
    @FXML
    private VBox itemTransaksi = null;
    
    //search or filter
    @FXML
    private TextField cariPaket;
    
    @FXML
    private TextField cariTransaksi;
    
    @FXML
    private TextField cariMenu;
    
    @FXML
    private TextField cariPelanggan;
    
    @FXML
    private HBox subTotalContainer;
    
    @FXML
    private HBox btnBuatPaketContainer;
    
    @FXML
    private Label getSubTotal;
    
    @FXML
    private Label getSubTotalPaket;
    
    @FXML
    private Label subTotalInvoice;
       
    private InputStream input;
    
    @FXML
    private TextField nameField;

    @FXML
    private TextField numberField;

    @FXML
    private JFXTextArea addressField;

    @FXML
    private JFXDatePicker datePick; 
    
    private LocalDate datePicker;

    @FXML
    private Label idTransaksi;

    @FXML
    private ComboBox kategoriPick;

    @FXML
    private ComboBox transportPick;

    @FXML
    private Label totalBayar;

    @FXML
    private ComboBox statusBayar;

    @FXML
    private TextField bayarField;

    @FXML
    private Label kembalian;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private JFXButton btnToPaket;
    
    @FXML
    private JFXButton btnCetakLaporan;

    @FXML
    private JFXButton btnToCustom;
    
    @FXML
    private JFXDatePicker historyStart;
    
    @FXML
    private JFXDatePicker historyEnd;
    
    @FXML
    private Button clearHistorySearch;
    
    private LocalDate historyBegin; 
    
    private LocalDate historyFinish; 
    
    @FXML
    private Label kodeTransaksi;
    
    @FXML
    private Label idCustomer;
    
    @FXML
    private Label namaCustomer;

    @FXML
    private Label kategoriPesanan;

    @FXML
    private Label transportPesanan;

    @FXML
    private Label subTotalPesanan;

    @FXML
    private Label totalBayarPesanan;

    @FXML
    private TextField bayarPesanan;

    @FXML
    private Label kembalianPesanan;

    @FXML
    private Label bayarLunas;
    
    @FXML
    private HBox bayarPesananBox;
        
    @FXML
    private HBox bayarLunasBox;
    
    @FXML
    private HBox statusPesananBox;
    
    @FXML
    private HBox kembalianBox;
    
    @FXML
    private HBox gantiLunasBox;
    
    @FXML
    private Label countTransaksi;
    
    @FXML
    private Label countTotalPendapatan;
    
    @FXML
    private Label countPendapatan;
    
    @FXML
    private VBox vboxDetail;
  
    @FXML
    private Label statusPesanan;
    
    @FXML
    private JFXButton gantiLunas;
    
    private ArrayList<Paket> items = new ArrayList<Paket>();
    
    private ArrayList<Customer> people = new ArrayList<Customer>();
   
    ObservableList<Menu> listSelected = FXCollections.observableArrayList();
        
    //btn minimize pr
    @FXML
    private JFXButton minimize_stage1;
    
    @FXML
    private JFXButton minimize_stage2;
    
    @FXML
    private JFXButton minimize_stage3;
    
    @FXML
    private JFXButton minimize_stage4;
    
    @FXML
    private JFXButton minimize_stage5;
    
    //induknya
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userSession.authorizeUser();
        
        //koneksi
        koneksi DB = new koneksi();
        DB.konek();
        con = DB.koneksi;
        stat = DB.stm;
        
        //halaman kasir
        paneTransaksi.toFront();
        transaksi(null, null);
        
//        autocomplete pelanggan
        JFXAutoCompletePopup<Customer> autoCompletePopup = new JFXAutoCompletePopup<>();
        
        try {
            PreparedStatement p = con.prepareStatement("SELECT * FROM c_customer");
            ResultSet r = p.executeQuery();
            while (r.next()) {  
                autoCompletePopup.getSuggestions().add(new Customer(r.getInt("customer_id"), r.getString("nama_customer"), r.getString("notelp"), r.getString("alamat")));                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardCashierController.class.getName()).log(Level.SEVERE, null, ex);
        }
            autoCompletePopup.setSelectionHandler(event -> {
                nameField.setText(event.getObject().getNamaCustomer());
                numberField.setText(event.getObject().getNotelp());
                addressField.setText(event.getObject().getAlamat());
            });

            // filter
            nameField.textProperty().addListener(observable -> {
                autoCompletePopup.filter(string -> string.getNamaCustomer().toLowerCase().contains(nameField.getText().toLowerCase()));
                if (autoCompletePopup.getFilteredSuggestions().isEmpty() || nameField.getText().isEmpty() ) {
                    autoCompletePopup.hide();
                } else {
                    autoCompletePopup.show(nameField);
                }
            });
        
        //search
        cariMenu.textProperty().addListener((obs, oldValue, newValue) -> {
            itemMenu.getChildren().forEach((listItem) -> {
                String NamaMenu = ((Label) listItem.lookup("#menu_nama")).getText().toLowerCase().trim();

                if(NamaMenu.contains(newValue.toLowerCase().trim())){
                    listItem.setVisible(true);
                    listItem.setManaged(true);
                }else{
                    listItem.setVisible(false);
                    listItem.setManaged(false);
                }
            });
        });
        
        cariPaket.textProperty().addListener((obs, oldValue, newValue) -> {
            itemPaket.getChildren().forEach((listItem) -> {
                String NamaPaket = ((Label) listItem.lookup("#paket_nama")).getText().toLowerCase().trim();

                if(NamaPaket.contains(newValue.toLowerCase().trim())){
                    listItem.setVisible(true);
                    listItem.setManaged(true);
                }else{
                    listItem.setVisible(false);
                    listItem.setManaged(false);
                }
            });
        });
        
        cariPelanggan.textProperty().addListener((obs, oldValue, newValue) -> {
            itemPelanggan2.getChildren().forEach((listItem) -> {
                String CustomerId = ((Label) listItem.lookup("#customer_id")).getText().toLowerCase().trim();
                String CustomerNama = ((Label) listItem.lookup("#customer_nama")).getText().toLowerCase().trim();
                String CustomerTelp = ((Label) listItem.lookup("#customer_telepon")).getText().toLowerCase().trim();
                String CustomerAlamat = ((Label) listItem.lookup("#customer_alamat")).getText().toLowerCase().trim();

                if(CustomerId.contains(newValue.toLowerCase().trim())||CustomerNama.contains(newValue.toLowerCase().trim())||CustomerTelp.contains(newValue.toLowerCase().trim())||CustomerAlamat.contains(newValue.toLowerCase().trim())){
                    listItem.setVisible(true);
                    listItem.setManaged(true);
                }else{
                    listItem.setVisible(false);
                    listItem.setManaged(false);
                }
            });
        });
        
        cariTransaksi.textProperty().addListener((obs, oldValue, newValue) -> {
            itemTransaksi.getChildren().forEach((listItem) -> {
                String TCode = ((Label) listItem.lookup("#kodeTransaksi")).getText().toLowerCase().trim();
                String TKasir = ((Label) listItem.lookup("#namaKasir")).getText().toLowerCase().trim();
                String TOrder = ((Label) listItem.lookup("#tglPesan")).getText().toLowerCase().trim();
                String TSend = ((Label) listItem.lookup("#tglKirim")).getText().toLowerCase().trim();

                if(TCode.contains(newValue.toLowerCase().trim())||TKasir.contains(newValue.toLowerCase().trim())||TOrder.contains(newValue.toLowerCase().trim())||TSend.contains(newValue.toLowerCase().trim())){
                    listItem.setVisible(true);
                    listItem.setManaged(true);
                }else{
                    listItem.setVisible(false);
                    listItem.setManaged(false);
                }
            });
        });
        
        // History filter
        historyStart.valueProperty().addListener((ov, oldValue, newValue) -> {
            this.historyBegin = newValue;
            filterHistory();
        });
        
        historyEnd.valueProperty().addListener((ov, oldValue, newValue) -> {
            this.historyFinish= newValue;
            filterHistory();
        });
        
        clearHistorySearch.setOnAction((event) -> {
            historyStart.setValue(null);
            historyEnd.setValue(null);
            this.historyBegin = null;
            this.historyFinish = null;
            transaksi(null, null);
            countPendapatanSort(null, null);
        });
        
        countTransaksii();
        countTotalPendapatann();
        countPendapatanSort(null, null);
    }    
    
    //format rp
    public String formatRp(int data) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.UK);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        formatter.setDecimalFormatSymbols(symbols);
        return String.valueOf(formatter.format(data));
    }
    
    public void countTransaksii(){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as total_transaksi FROM c_transaksi");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            countTransaksi.setText(String.valueOf(rs.getInt("total_transaksi")));
            System.out.println(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void countTotalPendapatann(){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT SUM(bayar) as bayarr FROM c_transaksi");
            ResultSet rs = ps.executeQuery();
            
            PreparedStatement psr = con.prepareStatement("SELECT SUM(kembalian) as kembali FROM c_transaksi");
            ResultSet rsr = psr.executeQuery();
            
            PreparedStatement ssql = con.prepareStatement("SELECT SUM(total_bayar) as total_pendapatan FROM c_transaksi");
            ResultSet rss = ssql.executeQuery();
            
            int bayaar = 0;
            int k = 0;
            int p = 0;
            
            if(rs.next())
                bayaar = rs.getInt("bayarr");
            if(rsr.next())
                k = rsr.getInt("kembali");
            if (rss.next()) {
                p = rss.getInt("total_pendapatan");
            }
            System.out.println(p);
            int t = bayaar - k;
                countTotalPendapatan.setText(formatRp(t));
                System.out.println(bayaar);
                System.out.println(k);
                System.out.println(t);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    public void countPendapatanSort(LocalDate start, LocalDate end){
     try {
         boolean filter = start != null && end != null;
            String sql = filter 
                    ? "SELECT SUM(bayar) as bayarr FROM c_transaksi WHERE tanggal_pesan >= ? AND tanggal_pesan <= ?"
                    : "SELECT SUM(bayar) as bayarr FROM c_transaksi WHERE CAST(tanggal_pesan AS DATE) = CURDATE()";
            PreparedStatement ps = con.prepareStatement(sql);
            
            if (filter) {
                ps.setString(1, start.toString());
                ps.setString(2, end.toString());
            }

            ResultSet rs = ps.executeQuery();
         
         String sqll = filter 
                    ? "SELECT SUM(kembalian) as kembali FROM c_transaksi WHERE tanggal_pesan >= ? AND tanggal_pesan <= ?"
                    : "SELECT SUM(kembalian) as kembali FROM c_transaksi WHERE CAST(tanggal_pesan AS DATE) = CURDATE()";
            PreparedStatement pst = con.prepareStatement(sqll);
            
            if (filter) {
                pst.setString(1, start.toString());
                pst.setString(2, end.toString());
            }
            
         ResultSet rst = pst.executeQuery();
            
         PreparedStatement ssql = con.prepareStatement("SELECT SUM(total_bayar) as total_pendapatan FROM c_transaksi where CAST(tanggal_pesan AS DATE) = CURDATE()");
         ResultSet rss = ssql.executeQuery();
            
         int b = 0;
         int k = 0;
         int p = 0;
         
         if(rs.next())
             b = rs.getInt("bayarr");
         if(rst.next())
             k = rst.getInt("kembali");
         if (rss.next()) {
             p = rss.getInt("total_pendapatan");
         }
         System.out.println(p);
         int bk = b - k;
             countPendapatan.setText(formatRp(bk));
             System.out.println(b);
             System.out.println(k);
             System.out.println(bk);
     } catch (SQLException ex) {
         Logger.getLogger(DashboardCashierController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
    public void filterHistory(){
        if (historyBegin != null && historyFinish != null) {
            transaksi(historyBegin, historyFinish);
            countPendapatanSort(historyBegin, historyFinish);
        }
    }

    //btn close
    @FXML
    private void close_app2(MouseEvent event) {
        System.exit(0);
    }
    
    //pelanggan
    public void pelanggann(){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM view_customer");
            rs = ps.executeQuery();
            
            itemPelanggan2.getChildren().clear();
            while(rs.next()){
                String customerId = rs.getString("customCustomer_id");
                String customerNama = rs.getString("nama_customer");
                String customerTelp = rs.getString("notelp");
                String customerAlamat = rs.getString("alamat");
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/cashier/itemPelanggan.fxml"));
                Label labelCustomerId = (Label)wrapper.lookup("#customer_id");
                Label labelCustomerNama = (Label)wrapper.lookup("#customer_nama");
                Label labelCustomerTelp = (Label)wrapper.lookup("#customer_telepon");
                Label labelCustomerAlamat = (Label)wrapper.lookup("#customer_alamat");
                labelCustomerId.setText(customerId);
                labelCustomerNama.setText(customerNama);                
                labelCustomerTelp.setText(customerTelp);
                labelCustomerAlamat.setText(customerAlamat);

                minimize_stage1.setOnAction(e -> {
                    ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
                });
                
                itemPelanggan2.getChildren().add(wrapper);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    //menu
    public void menu(){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM c_menu");
            rs = ps.executeQuery();
                
            listSelected.clear();
            itemMenu.getChildren().clear();
            while(rs.next()){
                String menuNama = rs.getString("nama_menu");
//                String menuHarga = rs.getString("harga_satuan");
                int hargaSatuan = rs.getInt("harga_satuan");
                int id = rs.getInt("menu_id");
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/cashier/itemMenu.fxml"));
                Label labelMenuNama = (Label)wrapper.lookup("#menu_nama");
                Label labelMenuHarga = (Label)wrapper.lookup("#menu_harga");
                labelMenuNama.setText(menuNama);                
                labelMenuHarga.setText("Rp. "+String.valueOf(formatRp(hargaSatuan)));

                ImageView mGambar = (ImageView)wrapper.lookup("#menuGambar");
                
                //show image if tipe data blob
                input = rs.getBinaryStream("gambar_menu");
                if (input != null && input.available() > 1) {
                    System.out.println("image available");
                    mGambar.setImage(new Image(input));
                        mGambar.setFitHeight(123);
                        mGambar.setFitWidth(158);
                        mGambar.setPreserveRatio(true);
                        mGambar.setSmooth(true);
                        System.out.println(input);
                }
                
                JFXButton AddMenuu = (JFXButton)wrapper.lookup("#AddMenu");
                
                AddMenuu.setOnAction((event) -> {
                        menuPaket(id);
                });
                
                minimize_stage5.setOnAction(e -> {
                    ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
                });
                
                
                itemMenu.getChildren().add(wrapper);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void menuPaket(int id){
        try {
            boolean exists = false;
            for (Menu item : listSelected) {
                if (item.menu_id == id) {
                    exists = true;
                }
            }
            if(!exists){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM c_menu where menu_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while(rs.next()){
                listSelected.add(new Menu(rs.getInt("menu_id"), rs.getString("nama_menu"), rs.getInt("harga_satuan")));

                
                String menuNama = rs.getString("nama_menu");
//                String menuHarga = rs.getString("harga_satuan");
                int hargaSatuan = rs.getInt("harga_satuan");
            
                int subTotal = Integer.valueOf(getSubTotal.getText());
                subTotal += hargaSatuan;
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/cashier/itemMenu3.fxml"));
                //menu id untuk dapet id dari menu combobox yg telah di klik dan untuk ditambah juga
                String menuId = String.valueOf(rs.getInt("menu_id"));
                int menuIdInt = rs.getInt("menu_id");
                //menampilkan item menu di kanan (set id)
                ((HBox)wrapper).setId("menuu"+menuId);
                //tutup item menu kanan
                Label labelMenuNama = (Label)wrapper.lookup("#nama_menu");
                Label labelMenuHarga = (Label)wrapper.lookup("#harga_menu");
                labelMenuNama.setText(menuNama);    
                labelMenuHarga.setText("Rp. "+String.valueOf(formatRp(hargaSatuan)));
                
                //remove menu yg telah di klik
                JFXButton removeMenu = (JFXButton)wrapper.lookup("#removeMenu");
                removeMenu.setOnAction(event -> {
                    itemMenu3.getChildren().remove(itemMenu3.lookup("#menuu"+menuId));
                    listSelected.removeIf(menu -> menu.menu_id == menuIdInt);
                    int subTotall = Integer.valueOf(getSubTotal.getText());
                    subTotall -= hargaSatuan;
                    getSubTotal.setText(String.valueOf(subTotall));
                });
                
                ImageView mGambar = (ImageView)wrapper.lookup("#gambarMenu");
                //show image if tipe data blob
                input = rs.getBinaryStream("gambar_menu");
                if (input != null && input.available() > 1) {
                    System.out.println("image available");
                    mGambar.setImage(new Image(input));
                        mGambar.setFitHeight(100);
                        mGambar.setFitWidth(94);
                        mGambar.setPreserveRatio(true);
                        mGambar.setSmooth(true);
                        System.out.println(input);
                }
               
                
                itemMenu3.getChildren().add(wrapper);
                
                getSubTotal.setText(String.valueOf(subTotal));
            }}
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void tambahpakett(ActionEvent actionEvent) throws IOException {
        String nama_paket = "Custom Paket";     
        String hargaPaket = getSubTotal.getText();
        int hPaket = 0;
        int generatedKey = 0;
        if(listSelected.size() > 0){
        try {
            //tambah paket dan get id paket setelah diinsertkan
            PreparedStatement ps = con.prepareStatement("INSERT INTO c_paket(nama_paket, harga_paket) VALUES(?, ?) ", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nama_paket);
            hPaket = Integer.parseInt(hargaPaket)+10000;
            ps.setInt(2, hPaket);
            ps.execute();
            //get id paket yg baru saja dimasukkan
            ResultSet rsid = ps.getGeneratedKeys();
            if(rsid.next()){
                generatedKey = rsid.getInt(1);
            for(Menu m : listSelected) {
                PreparedStatement psrv = con.prepareStatement("INSERT INTO c_paket_menu(menu_id, paket_id) VALUES(?, ?) ");
                psrv.setInt(1, m.getMenuid());
                psrv.setInt(2, generatedKey);
                psrv.executeUpdate();
            }}
            items.add(new Paket(generatedKey, nama_paket, hPaket, 1));
            itemMenu3.getChildren().clear();
            getSubTotal.setText(String.valueOf(0));
            
                    if (items.size() > 0) {
                        contentInvoice.toFront();
                        home.getStyleClass().clear();
                        home.getStyleClass().add("labelSidebar");
                        pelanggan.getStyleClass().clear();
                        pelanggan.getStyleClass().add("labelSidebar");
                        menu.getStyleClass().clear();
                        menu.getStyleClass().add("labelSidebar");
                        paket.getStyleClass().clear();
                        paket.getStyleClass().add("labelSidebar");
                        try {
                            invoice();
                        } catch (IOException ex) {
                            Logger.getLogger(DashboardCashierController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Mohon tambahkan paket yang dipilih!");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                
        } catch (SQLException e ) {
            e.printStackTrace();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }
    }
    
    //paket
    public void paket(){
        try {
            String custom = "Custom Paket";
            PreparedStatement ps = con.prepareStatement("SELECT * FROM c_paket WHERE nama_paket != ?");
            ps.setString(1, custom);
            rs = ps.executeQuery();
            
            itemPaket.getChildren().clear();
            while(rs.next()){
                String paketNama = rs.getString("nama_paket");
//                String paketHargaa = rs.getString("harga_paket");
                int hargaPaketini = rs.getInt("harga_paket");
                int id=rs.getInt("paket_id");
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/cashier/itemPaket.fxml"));
                Label labelPaketNama = (Label)wrapper.lookup("#paket_nama");
                Label labelPaketHarga = (Label)wrapper.lookup("#paket_harga");
                labelPaketNama.setText(paketNama);                
                labelPaketHarga.setText("Rp. "+String.valueOf(formatRp(hargaPaketini)));
                Text daftarMenuu = (Text)wrapper.lookup("#listMenu");

                //isi menu dari paket ditampilkan
                PreparedStatement psr = con.prepareStatement("SELECT paket.*, menu.* FROM c_paket_menu paket_menu JOIN c_paket paket ON paket_menu.paket_id = paket.paket_id JOIN c_menu menu ON paket_menu.menu_id = menu.menu_id where paket_menu.paket_id = ?");
                psr.setInt(1, id);
                ResultSet prsv = psr.executeQuery();        
                String daftarMenu = "";
                while(prsv.next()){
                    daftarMenu += prsv.getString("nama_menu")+", ";
                }
//                dicek jika paket memiliki menu atau tidak
                daftarMenu = daftarMenu.length() == 0 ? daftarMenu: daftarMenu.substring(0,daftarMenu.length()-2);
                daftarMenuu.setText(daftarMenu);
                
                ImageView mGambar = (ImageView)wrapper.lookup("#gambarPaket");
                //show image if tipe data blob
                input = rs.getBinaryStream("gambar_paket");
                if (input != null && input.available() > 1) {
                    System.out.println("image available");
                    mGambar.setImage(new Image(input));
                        mGambar.setFitHeight(123);
                        mGambar.setFitWidth(158);
                        mGambar.setPreserveRatio(true);
                        mGambar.setSmooth(true);
                        System.out.println(input);
                }           
                
                JFXButton AddPakett = (JFXButton)wrapper.lookup("#AddPaket");
                AddPakett.setOnAction((event) -> {
                        paketPaket(id);
                });
                
                minimize_stage2.setOnAction(e -> {
                    ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
                });
                
                tambahTransaksi.setOnAction(event -> {
                    if (items.size() > 0) {
                        contentInvoice.toFront();
                        home.getStyleClass().clear();
                        home.getStyleClass().add("labelSidebar");
                        pelanggan.getStyleClass().clear();
                        pelanggan.getStyleClass().add("labelSidebar");
                        menu.getStyleClass().clear();
                        menu.getStyleClass().add("labelSidebar");
                        paket.getStyleClass().clear();
                        paket.getStyleClass().add("labelSidebar");
                        try {
                            invoice();
                        } catch (IOException ex) {
                            Logger.getLogger(DashboardCashierController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Mohon tambahkan paket yang dipilih!");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                });
                
                itemPaket.getChildren().add(wrapper);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void paketPaket(int id){
        try {
            boolean exists = false;
            for (Paket item : items) {
                if (item.paketId == id) {
                    exists = true;
                }
            }
            if(!exists){
                PreparedStatement ps = con.prepareStatement("SELECT * FROM c_paket where paket_id = ?");
                ps.setInt(1, id);
                rs = ps.executeQuery();

                while(rs.next()){

                    String namaPaket = rs.getString("nama_paket");
    //                String hargaPaket = rs.getString("harga_paket");
                    int hPaket = rs.getInt("harga_paket");

                    items.add(new Paket(id, namaPaket, hPaket, 1));

                    Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/cashier/itemMenu2.fxml"));
                    //menu id untuk dapet id dari menu combobox yg telah di klik dan untuk ditambah juga
                    String pakettId = String.valueOf(rs.getInt("paket_id"));
                    int paketIdInt = rs.getInt("paket_id");

                    //menampilkan item menu di kanan (set id)
                    ((HBox)wrapper).setId("pakett"+pakettId);
                    //tutup item menu kanan
                    Label labelPaketNama = (Label)wrapper.lookup("#nama_paket");
                    Label labelPaketHarga = (Label)wrapper.lookup("#harga_paket");
                    labelPaketNama.setText(namaPaket);    
                    labelPaketHarga.setText("Rp. "+String.valueOf(formatRp(hPaket)));

                    //remove menu yg telah di klik
                    JFXButton removePaket = (JFXButton)wrapper.lookup("#removePaket");
                    removePaket.setOnAction(event -> {
                        itemMenu2.getChildren().remove(itemMenu2.lookup("#pakett"+pakettId));
                        items.removeIf(paket -> paket.paketId == paketIdInt);
                    });

                    ImageView mGambar = (ImageView)wrapper.lookup("#gambarPaket");
                    //show image if tipe data blob
                    input = rs.getBinaryStream("gambar_paket");
                    if (input != null && input.available() > 1) {
                        System.out.println("image available");
                        mGambar.setImage(new Image(input));
                            mGambar.setFitHeight(100);
                            mGambar.setFitWidth(94);
                            mGambar.setPreserveRatio(true);
                            mGambar.setSmooth(true);
                            System.out.println(input);
                    }
                    

                    itemMenu2.getChildren().add(wrapper);
                }
            }
            
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    //format date
    public String formatDate(java.util.Date data) {
        java.util.Date date = new java.util.Date(data.getTime());
        DateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
        return formatter.format(date);
    }
    
    public void transaksi(LocalDate start, LocalDate end){
        try {
            vboxDetail.setVisible(false);
            itemTransaksi.getChildren().clear();
            boolean filter = start != null && end != null;
            String sql = filter 
                    ? "SELECT * FROM view_transaksi JOIN c_user ON view_transaksi.user_id = c_user.user_id JOIN view_customer ON view_transaksi.customer_id = view_customer.customer_id JOIN c_kategori ON view_transaksi.kategori_id = c_kategori.kategori_id WHERE tanggal_pesan >= ? AND tanggal_pesan <= ? ORDER BY transaksi_id DESC"
                    : "SELECT * FROM view_transaksi JOIN c_user ON view_transaksi.user_id = c_user.user_id JOIN view_customer ON view_transaksi.customer_id = view_customer.customer_id JOIN c_kategori ON view_transaksi.kategori_id = c_kategori.kategori_id ORDER BY transaksi_id DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            
            if (filter) {
                ps.setString(1, start.toString());
                ps.setString(2, end.toString());
            }
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int trId = rs.getInt("transaksi_id");
                String transaksiId = rs.getString("customTransaksi_id");
                String namaKasir = rs.getString("nama_lengkap");
                String tglPesan = formatDate(rs.getTimestamp("tanggal_pesan"));
                String tglKirim = formatDate(rs.getTimestamp("tanggal_kirim"));
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/cashier/itemTransaksi.fxml"));
                Label labelTransaksiiId = (Label)wrapper.lookup("#kodeTransaksi");
                Label labelNamaKasir = (Label)wrapper.lookup("#namaKasir");
                Label labelTglPesan = (Label)wrapper.lookup("#tglPesan");
                Label labelTglKirim = (Label)wrapper.lookup("#tglKirim");
                labelTransaksiiId.setText(transaksiId);
                labelNamaKasir.setText(namaKasir);                
                labelTglPesan.setText(tglPesan);
                labelTglKirim.setText(tglKirim);
                
                String custId = rs.getString("customCustomer_id");
                String namaCustomerr = rs.getString("nama_customer");
                String namaKategori = rs.getString("event");
                int transport = rs.getInt("biaya_transport");
                int sub_total = rs.getInt("sub_total");
                int total = rs.getInt("total_bayar");
                int kembaliann = rs.getInt("kembalian");
                int bayar = rs.getInt("bayar");
                String status = rs.getString("status_bayar");
                
                wrapper.setOnMouseClicked(event -> {
                    vboxDetail.setVisible(true);
                    detailTransaksi(trId);
                    idCustomer.setText(String.valueOf(custId));
                    namaCustomer.setText(namaCustomerr);
                    kategoriPesanan.setText(namaKategori);
                    transportPesanan.setText(String.valueOf(formatRp(transport)));
                    subTotalPesanan.setText(String.valueOf(formatRp(sub_total)));
                    totalBayarPesanan.setText(String.valueOf(formatRp(total)));
                    kembalianPesanan.setText(String.valueOf(formatRp(kembaliann)));
                    bayarLunas.setText(String.valueOf(formatRp(bayar)));
                    if (status.equals("Lunas")) {
                        bayarPesananBox.setVisible(false);
                        statusPesanan.setText(status);
                        gantiLunasBox.setVisible(false);
                    }else if(status.equals("Belum Lunas")){
                        bayarPesananBox.setVisible(true);
                        statusPesanan.setText(status);
                        gantiLunasBox.setVisible(true);
                    
                    bayarPesanan.textProperty().addListener(new ChangeListener<String>() {

                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                if (!newValue.matches("\\d*")) {
                                bayarPesanan.setText(newValue.replaceAll("[^\\d]", ""));
                            }}
                        });
                        bayarPesanan.textProperty().addListener((obs, oldValue, newValue) -> {
                            if(bayarPesanan.getText().length()>0){
                            int kembalian = 0;
                            int bayarr = Integer.parseInt(bayarPesanan.getText().trim());
//                            String a = totalBayarPesanan.getText();
//                            String b = bayarLunas.getText();
//                            int aa = Integer.parseInt(a);
//                            int bb = Integer.parseInt(b);
                            int aa = total;
                            int bb = bayar;
                            int totall = aa - bb;
                            int min = bayarr - totall;
                            if(bayarr > 0){
                                    this.kembalianPesanan.setText(String.valueOf(formatRp(min)));
                                if(bayarr < totall){
                                    this.kembalianPesanan.setText(String.valueOf(formatRp(kembalian)));
                                }else{
                                    this.kembalianPesanan.setText(String.valueOf(formatRp(min)));
                            }
                            }else{
                                this.kembalianPesanan.setText(String.valueOf(formatRp(kembalian)));
                            }
                            }
                        });
                    }
                    
                    gantiLunas.setOnAction((evt) -> {
                    if(bayarPesanan.getText().length()>0){
                        String a = bayarPesanan.getText().trim();
//                        String b = bayarLunas.getText();
//                        String c = totalBayarPesanan.getText();
                        int aa = Integer.parseInt(a);
//                        int bb = Integer.parseInt(b);
//                        int cc = Integer.parseInt(c);
                        int bb = bayar;
                        int cc = total;
                        int totall = aa + bb;
                        int min = aa - (cc-bb);
                            if (aa < cc-bb) {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field dengan benar!");
                                alert.showAndWait();  
                            }else{
                                lunasi(trId, totall, min);
                            }
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field dengan benar!");
                            alert.showAndWait();
                        } 
                    });
                    
                    
                        
                });
                
                minimize_stage4.setOnAction(e -> {
                    ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
                });
                
                btnCetakLaporan.setOnAction(e ->{
                    cetakCetak(start, end);
                });
                    
                itemTransaksi.getChildren().add(wrapper);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public void lunasi(int id, int totall, int min){
            try {
                PreparedStatement ps = con.prepareStatement("UPDATE c_transaksi set bayar=?, kembalian=?, status_bayar=? WHERE transaksi_id = ? ");
                ps.setInt(1, totall);
                ps.setInt(2, min);
                ps.setString(3, "Lunas");
                ps.setInt(4, id);
                ps.execute();
                
                try {
             
             Map param = new HashMap();
             param.put("ID", id);
             JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\asus\\Documents\\Project\\Daring 2021\\PBO\\CateringFX\\src\\cateringfx\\report\\nota.jrxml");
             JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, param, con);
             JasperViewer.viewReport(JasperPrint, false);
            
         } catch (Exception e) {
             Alert alert = new Alert(Alert.AlertType.ERROR, "Maaf sepertinya ada yang salah dengan sistem kami, tunggu beberapa saat dan coba lagi!");
             alert.setHeaderText(null);
             alert.showAndWait();
             e.printStackTrace();
         }
                transaksi(null, null);
                          
                countTransaksii();
                countTotalPendapatann();
                countPendapatanSort(null, null);
                bayarPesanan.setText("");
            } catch (SQLException e ) {
                e.printStackTrace();
            }                       
    }
    
    public void detailTransaksi(int trId){
        try {
            PreparedStatement psD = con.prepareStatement("SELECT * FROM view_transaksi JOIN c_pesanan ON view_transaksi.transaksi_id = c_pesanan.transaksi_id JOIN c_paket ON c_pesanan.paket_id = c_paket.paket_id WHERE view_transaksi.transaksi_id = ?");
            psD.setInt(1, trId);
            ResultSet rsD = psD.executeQuery();

            itemDetail.getChildren().clear();
            while(rsD.next()){
                
                String namaPaket = rsD.getString("nama_paket");
//                String hargaPaket= rsD.getString("harga_paket");
                int hPaket = rsD.getInt("harga_paket");
                int paketId = rsD.getInt("paket_id");
                String qtyD = rsD.getString("qty");
                int qtyDD = rsD.getInt("qty");
                
//                items.add(new Paket(paketId, namaPaket, hPaket, qtyDD));
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/cashier/itemDetail.fxml"));
                
                Label labelPaketNama = (Label)wrapper.lookup("#namaDetail");
                Label labelPaketHarga = (Label)wrapper.lookup("#hargaDetail");
                Label labelQty = (Label)wrapper.lookup("#qtyDetail");
                labelPaketNama.setText(namaPaket);    
                labelPaketHarga.setText("Rp. "+String.valueOf(formatRp(hPaket)));
                labelQty.setText(qtyD+"x");
                
                ImageView mGambar = (ImageView)wrapper.lookup("#gambarDetail");
                //show image if tipe data blob
                input = rsD.getBinaryStream("gambar_paket");
                if (input != null && input.available() > 1) {
                    System.out.println("image available");
                    mGambar.setImage(new Image(input));
                        mGambar.setFitHeight(65);
                        mGambar.setFitWidth(70);
                        mGambar.setPreserveRatio(true);
                        mGambar.setSmooth(true);
                        System.out.println(input);
                }
               
                itemDetail.getChildren().add(wrapper);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public void invoice() throws IOException{
        try {            
            home.setDisable(true);
            paket.setDisable(true);
            menu.setDisable(true);
            pelanggan.setDisable(true);
            
            numberField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                    numberField.setText(newValue.replaceAll("[^\\d]", ""));
                }}
            });
            
            bayarField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                    bayarField.setText(newValue.replaceAll("[^\\d]", ""));
                }}
            });
            
            PreparedStatement psc = con.prepareStatement("SELECT * FROM c_kategori");
            ResultSet rsc = psc.executeQuery();
            kategoriPick.setCellFactory(lv -> new KategoriList());
            kategoriPick.setButtonCell(new KategoriList());
            kategoriPick.getItems().clear();
            while(rsc.next()){
                int kategori_id = rsc.getInt("kategori_id");
                String event = rsc.getString("event");
                kategoriPick.getItems().add(new Kategori(kategori_id, event));
            }
            
            statusBayar.getItems().clear();
            statusBayar.getItems().addAll(
                "Belum Lunas",
                "Lunas"
            );
//            statusBayar.setDisable(true);
            
            int subTotal = 0;
            
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM c_paket");
//            rs = ps.executeQuery();
                        
            itemMenu2.getChildren().clear();
            itemPesanan.getChildren().clear();

        for (int i = 0; i < items.size() ; i++) {
            Paket item = items.get(i);

            Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/cashier/itemPesanan.fxml"));

            Label labelName = (Label) wrapper.lookup("#namaPaketInvoice");
            Label labelQty = (Label) wrapper.lookup("#QtyLabel"); 
            Label labelPrice = (Label) wrapper.lookup("#hargaPaketInvoice");
            Text daftarMenuu = (Text)wrapper.lookup("#listMenuInvoice");
            //isi menu dari paket ditampilkan
                PreparedStatement psr = con.prepareStatement("SELECT paket.*, menu.* FROM c_paket_menu paket_menu JOIN c_paket paket ON paket_menu.paket_id = paket.paket_id JOIN c_menu menu ON paket_menu.menu_id = menu.menu_id where paket_menu.paket_id = ?");
                psr.setInt(1, item.getPaketId());
                ResultSet prsv = psr.executeQuery();        
                String daftarMenu = "";
                while(prsv.next()){
                    daftarMenu += prsv.getString("nama_menu")+", ";
                }
//                dicek jika paket memiliki menu atau tidak
                daftarMenu = daftarMenu.length() == 0 ? daftarMenu: daftarMenu.substring(0,daftarMenu.length()-2);
                daftarMenuu.setText(daftarMenu);

            labelName.setText(item.getNamaPaket());
            labelPrice.setText(String.valueOf(formatRp(item.getHargaPaket())));
            ((HBox)wrapper).setId("pakett"+item.getPaketId());
            JFXButton btnPlusQty = (JFXButton)wrapper.lookup("#btnPlusQty");
            JFXButton btnMinQty = (JFXButton)wrapper.lookup("#btnMinQty");
            int index = i;
            btnPlusQty.setOnAction(evt -> {
                item.addQty();
                items.set(index, item);
                setSubTotal(subTotalInvoice);
                labelQty.setText(String.valueOf(item.getQty()));
            });
            
            btnMinQty.setOnAction((evt) -> {
                item.minQty();
                items.set(index, item);
                if (item.getQty() == 0) {
                    itemPesanan.getChildren().remove(itemPesanan.lookup("#pakett"+item.getPaketId()));
                    
                    items.remove(index);
                    System.out.println(items.size());
                }
                setSubTotal(subTotalInvoice);
                labelQty.setText(String.valueOf(item.getQty()));
            });

            itemPesanan.getChildren().add(wrapper);
            
            subTotal += item.getHargaPaket();
        }
            setSubTotal(subTotalInvoice);
            
                PreparedStatement pst = con.prepareStatement("SELECT * FROM c_biayatransport");
                ResultSet rst = pst.executeQuery();
                transportPick.setCellFactory(lv -> new TransportList());
                transportPick.setButtonCell(new TransportList());
                transportPick.getItems().clear();
                while(rst.next()){
                    int cost_id = rst.getInt("cost_id");
                    String tujuan = rst.getString("tujuan");
                    int biayaTransport = rst.getInt("biaya_transport");
                    transportPick.getItems().add(new Transport(cost_id, tujuan, biayaTransport));
                }
                
            transportPick.setOnAction(e -> {
                Transport selected = (Transport) transportPick.getSelectionModel().getSelectedItem();
                if(selected != null) {
                    int selectedTransportCost = ((Transport) transportPick.getValue()).getBiayaTransport();
                    setTotal(selectedTransportCost);
                }
            });
            
            bayarField.textProperty().addListener((obs, oldValue, newValue) -> {
             if(bayarField.getText().length()>0){
                int kembalian = 0;
                int bayar = Integer.parseInt(bayarField.getText().trim());
                int total = Integer.parseInt(totalBayar.getText());
                
                int min = bayar-total;
                if(bayar > 0){
                       this.kembalian.setText(String.valueOf(min));
//                    this.kembalian.setText(String.valueOf(formatRp(min)));
                   if(bayar < total){
                          this.kembalian.setText(String.valueOf(kembalian));                    
//                        this.kembalian.setText(String.valueOf(formatRp(kembalian)));
                        statusBayar.setValue("Belum Lunas");
                   }else{
                       this.kembalian.setText(String.valueOf(min));
//                        this.kembalian.setText(String.valueOf(formatRp(min)));
                        statusBayar.setValue("Lunas");
                   }
                }else{
                    this.kembalian.setText(String.valueOf(kembalian));                    
//                    this.kembalian.setText(String.valueOf(formatRp(kembalian)));
                }
            }
            });
            
            btnToPaket.setOnAction(evt -> {
                contentPaket.toFront();
                home.getStyleClass().clear();
                home.getStyleClass().add("labelSidebar");
                pelanggan.getStyleClass().clear();
                pelanggan.getStyleClass().add("labelSidebar");
                menu.getStyleClass().clear();
                menu.getStyleClass().add("labelSidebar");
                paket.getStyleClass().clear();
                paket.getStyleClass().add("labelSidebar");
                paket.getStyleClass().add("labelSidebarActive");
                paket();
            });
            btnToCustom.setOnAction(evt -> {
                contentMenu.toFront();
                home.getStyleClass().clear();
                home.getStyleClass().add("labelSidebar");
                pelanggan.getStyleClass().clear();
                pelanggan.getStyleClass().add("labelSidebar");
                menu.getStyleClass().clear();
                menu.getStyleClass().add("labelSidebar");
                menu.getStyleClass().add("labelSidebarActive");
                paket.getStyleClass().clear();
                paket.getStyleClass().add("labelSidebar");
                menu();
            });
            
            minimize_stage3.setOnAction(e -> {
                    ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
                });
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Maaf sepertinya ada yang salah dengan sistem kami, tunggu beberapa saat dan coba lagi!");
            alert.setHeaderText(null);
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    
    public int getSubTotal() {
        int sum = 0;
        for(int i = 0; i < items.size(); i++) {
            Paket item = items.get(i);
            sum += item.getHargaPaket() * item.getQty();
        }
        return sum;
    }
    
    public void setSubTotal(Label subTotal){
        subTotal.setText(String.valueOf(getSubTotal()));
        Transport selected = (Transport) transportPick.getSelectionModel().getSelectedItem();
        if(selected != null) {
            int selectedTransportCost = ((Transport) transportPick.getValue()).getBiayaTransport();
            setTotal(selectedTransportCost);
        }
    }
    
    public void setTotal(int transportCost) {
//        totalBayar.setText(String.valueOf(formatRp(getSubTotal() + transportCost)));
        totalBayar.setText(String.valueOf(getSubTotal() + transportCost));        
    }
    
    public void cetakCetak(LocalDate start, LocalDate end){
        boolean filter = start != null && end != null;
        if (!filter) {
            try {

                 JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\asus\\Documents\\Project\\Daring 2021\\PBO\\CateringFX\\src\\cateringfx\\report\\report.jrxml");
                 JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
                 JasperViewer.viewReport(JasperPrint, false);

             } catch (Exception e) {
                 Alert alert = new Alert(Alert.AlertType.ERROR, "Maaf sepertinya ada yang salah dengan sistem kami, tunggu beberapa saat dan coba lagi!");
                 alert.setHeaderText(null);
                 alert.showAndWait();
                 e.printStackTrace();
             }            
        }if (filter) {
            try {
                String begin = start.toString();
                String finish = end.toString();
             
             Map param = new HashMap();
             param.put("start", begin);
             param.put("end", finish);
             JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\asus\\Documents\\Project\\Daring 2021\\PBO\\CateringFX\\src\\cateringfx\\report\\reportfilter.jrxml");
             JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, param, con);
             JasperViewer.viewReport(JasperPrint, false);
            
         } catch (Exception e) {
             Alert alert = new Alert(Alert.AlertType.ERROR, "Maaf sepertinya ada yang salah dengan sistem kami, tunggu beberapa saat dan coba lagi!");
             alert.setHeaderText(null);
             alert.showAndWait();
             e.printStackTrace();
         }
        }

    }
    
    public void konfirmasiPesanan(ActionEvent actionEvent) throws IOException {
        String namaLengkap = nameField.getText();
        String noTelp = numberField.getText();
        String alamat = addressField.getText();
        
        String sub = subTotalInvoice.getText();
        String bayar = bayarField.getText().trim();
        String total = totalBayar.getText();
        String kembaliann = kembalian.getText();
       
        Date dateK = null;
        LocalDate datee = datePick.getValue();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateKirim;
        
        if (datePick.getValue() != null) {
            try {
                dateKirim = formatter.parse(datee.toString());
                dateK = new Date(dateKirim.getTime());
            } catch (ParseException ex) {
                Logger.getLogger(DashboardCashierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        if (!namaLengkap.isEmpty() && !noTelp.isEmpty() && !alamat.isEmpty() && !bayar.isEmpty() && !kategoriPick.getSelectionModel().isEmpty() && !transportPick.getSelectionModel().isEmpty() && !statusBayar.getSelectionModel().isEmpty()) {
            try {
                PreparedStatement p = con.prepareStatement("SELECT * FROM c_customer WHERE nama_customer = ? AND notelp = ? AND alamat = ?");
                p.setString(1, namaLengkap);
                p.setString(2, noTelp);
                p.setString(3, alamat);
                ResultSet r = p.executeQuery();
                    if (r.next()) {
                        int getCustId = r.getInt("customer_id");
                        Transport selected = (Transport) transportPick.getSelectionModel().getSelectedItem();
                        int selectedTransportCost = selected.getBiayaTransport();
                        String status = (String) statusBayar.getSelectionModel().getSelectedItem();
                        int selectedKategoriId = ((Kategori) kategoriPick.getValue()).getKategoriId();
                 
                            PreparedStatement psb = con.prepareStatement("INSERT INTO c_transaksi(user_id, customer_id, biaya_transport, total_bayar, bayar, kembalian, status_bayar, kategori_id, sub_total, tanggal_kirim) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
                            psb.setInt(1, userSession.getUserID());
                            psb.setInt(2, getCustId);
                            psb.setInt(3, selectedTransportCost);
                            psb.setInt(4, Integer.parseInt(total));
                            psb.setInt(5, Integer.parseInt(bayar));
                            psb.setInt(6, Integer.parseInt(kembaliann));
                            psb.setString(7, status);
                            psb.setInt(8, selectedKategoriId);
                            psb.setInt(9, Integer.parseInt(sub));
                            psb.setDate(10, dateK);
                            psb.executeUpdate();
                            nameField.setText("");
                            numberField.setText("");
                            addressField.setText("");
                            transportPick.getSelectionModel().clearSelection();
                            kategoriPick.getSelectionModel().clearSelection();
                            subTotalInvoice.setText("");
                            totalBayar.setText("");
                            statusBayar.getSelectionModel().clearSelection();
                            bayarField.setText("");
                            kembalian.setText(""); 
                            datePick.setValue(null);

                            ResultSet rsbid = psb.getGeneratedKeys();
                            int generatedKeys = 0;
                            if(rsbid.next()){
                                generatedKeys = rsbid.getInt(1);
                                for(Paket item : items) {
                                    PreparedStatement psd = con.prepareStatement("INSERT INTO c_pesanan(transaksi_id, paket_id, qty) VALUES(?, ?, ?) ");
                                    psd.setInt(1, generatedKeys);
                                    psd.setInt(2, item.getPaketId());
                                    psd.setInt(3, item.getQty());
                                    psd.executeUpdate();
                                    item.removeQty();
                                } 
                                    items.clear();
                                    itemPesanan.getChildren().clear();
                                    this.items = new ArrayList<Paket>();
                                try {
             
                                Map param = new HashMap();
                                param.put("ID", generatedKeys);
                                JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\asus\\Documents\\Project\\Daring 2021\\PBO\\CateringFX\\src\\cateringfx\\report\\nota.jrxml");
                                JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, param, con);
                                JasperViewer.viewReport(JasperPrint, false);

                            } catch (Exception e) {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Maaf sepertinya ada yang salah dengan sistem kami, tunggu beberapa saat dan coba lagi!");
                                alert.setHeaderText(null);
                                alert.showAndWait();
                                e.printStackTrace();
                            }
                                    
                            }else{
                                throw new Error("I Dont Know");
                            }
                
                }else{
                        PreparedStatement ps = con.prepareStatement("INSERT INTO c_customer(nama_customer, notelp, alamat) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, namaLengkap);
                        ps.setString(2, noTelp);
                        ps.setString(3, alamat);
                        ps.execute();
                        nameField.setText("");
                        numberField.setText("");
                        addressField.setText("");
                        //get id paket yg baru saja dimasukkan
                        ResultSet rsid = ps.getGeneratedKeys();
                        int generatedKey = 0;
                        if(rsid.next()){
                            generatedKey = rsid.getInt(1);
                            Transport selected = (Transport) transportPick.getSelectionModel().getSelectedItem();
                            int selectedTransportCost = selected.getBiayaTransport();
                            String status = (String) statusBayar.getSelectionModel().getSelectedItem();
                            int selectedKategoriId = ((Kategori) kategoriPick.getValue()).getKategoriId();
                            
                                    PreparedStatement psb = con.prepareStatement("INSERT INTO c_transaksi(user_id, customer_id, biaya_transport, total_bayar, bayar, kembalian, status_bayar, kategori_id, sub_total, tanggal_kirim) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
                                    psb.setInt(1, userSession.getUserID());
                                    psb.setInt(2, generatedKey);
                                    psb.setInt(3, selectedTransportCost);
                                    psb.setInt(4, Integer.parseInt(total));
                                    psb.setInt(5, Integer.parseInt(bayar));
                                    psb.setInt(6, Integer.parseInt(kembaliann));
                                    psb.setString(7, status);
                                    psb.setInt(8, selectedKategoriId);
                                    psb.setInt(9, Integer.parseInt(sub));
                                    psb.setDate(10, dateK);
                                    psb.executeUpdate();
                                    transportPick.getSelectionModel().clearSelection();
                                    kategoriPick.getSelectionModel().clearSelection();
                                    subTotalInvoice.setText("");
                                    totalBayar.setText("");
                                    statusBayar.getSelectionModel().clearSelection();
                                    bayarField.setText("");
                                    kembalian.setText("");                                    
                                    datePick.setValue(null);

                                    ResultSet rsbid = psb.getGeneratedKeys();
                                    int generatedKeys = 0;
                                    if(rsbid.next()){
                                        generatedKeys = rsbid.getInt(1);
                                        for(Paket item : items) {
                                            PreparedStatement psd = con.prepareStatement("INSERT INTO c_pesanan(transaksi_id, paket_id, qty) VALUES(?, ?, ?) ");
                                            psd.setInt(1, generatedKeys);
                                            psd.setInt(2, item.getPaketId());
                                            psd.setInt(3, item.getQty());
                                            psd.executeUpdate();
                                            item.removeQty();
                                        } 
                                            items.clear();
                                            itemPesanan.getChildren().clear();
                                            this.items = new ArrayList<Paket>();
                                        try {
             
                                            Map param = new HashMap();
                                            param.put("ID", generatedKeys);
                                            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\asus\\Documents\\Project\\Daring 2021\\PBO\\CateringFX\\src\\cateringfx\\report\\nota.jrxml");
                                            JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, param, con);
                                            JasperViewer.viewReport(JasperPrint, false);

                                        } catch (Exception e) {
                                            Alert alert = new Alert(Alert.AlertType.ERROR, "Maaf sepertinya ada yang salah dengan sistem kami, tunggu beberapa saat dan coba lagi!");
                                            alert.setHeaderText(null);
                                            alert.showAndWait();
                                            e.printStackTrace();
                                        }
                                    }else{
                                        throw new Error("I Dont Know");
                                    }
                
                    }}
                  
                paneTransaksi.toFront();
                home.setDisable(false);
                paket.setDisable(false);
                menu.setDisable(false);
                pelanggan.setDisable(false);
                home.getStyleClass().clear();
                home.getStyleClass().add("labelSidebar");
                home.getStyleClass().add("labelSidebarActive");
                pelanggan.getStyleClass().clear();
                pelanggan.getStyleClass().add("labelSidebar");
                menu.getStyleClass().clear();
                menu.getStyleClass().add("labelSidebar");
                paket.getStyleClass().clear();
                paket.getStyleClass().add("labelSidebar");
                vboxDetail.setVisible(false);
                transaksi(null, null);             
                countTransaksii();
                countTotalPendapatann();
                countPendapatanSort(null, null);

                items.clear();
                itemPesanan.getChildren().clear();
                this.items = new ArrayList<Paket>();
               
            }catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Maaf sepertinya ada yang salah dengan sistem kami, tunggu beberapa saat dan coba lagi!");
                alert.setHeaderText(null);
                alert.showAndWait();
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }
        
    }
    
    public void cancelTransaksi(ActionEvent actionEvent) throws IOException {
        nameField.setText("");
        numberField.setText("");
        addressField.setText("");        
        transportPick.getSelectionModel().clearSelection();
        kategoriPick.getSelectionModel().clearSelection();
        subTotalInvoice.setText("");
        totalBayar.setText("");
        statusBayar.getSelectionModel().clearSelection();
        bayarField.setText("");
        kembalian.setText("");
        datePick.setValue(null);
        paneTransaksi.toFront();
        home.setDisable(false);
        paket.setDisable(false);
        menu.setDisable(false);
        pelanggan.setDisable(false);
        home.getStyleClass().clear();
        home.getStyleClass().add("labelSidebar");
        home.getStyleClass().add("labelSidebarActive");
        pelanggan.getStyleClass().clear();
        pelanggan.getStyleClass().add("labelSidebar");
        menu.getStyleClass().clear();
        menu.getStyleClass().add("labelSidebar");
        paket.getStyleClass().clear();
        paket.getStyleClass().add("labelSidebar");
        vboxDetail.setVisible(false);
        transaksi(null, null);             
        countTransaksii();
        countTotalPendapatann();
        countPendapatanSort(null, null);        
        items.clear();
        itemPesanan.getChildren().clear();
        this.items = new ArrayList<Paket>();
    }
    
    //navigation
    public void navigation(ActionEvent actionEvent) throws IOException {
        if ("home".equals(((Control)actionEvent.getSource()).getId())) {
            paneTransaksi.toFront();
            home.getStyleClass().clear();
            home.getStyleClass().add("labelSidebar");
            home.getStyleClass().add("labelSidebarActive");
            pelanggan.getStyleClass().clear();
            pelanggan.getStyleClass().add("labelSidebar");
            menu.getStyleClass().clear();
            menu.getStyleClass().add("labelSidebar");
            paket.getStyleClass().clear();
            paket.getStyleClass().add("labelSidebar");
            vboxDetail.setVisible(false);
            transaksi(null, null);             
            countTransaksii();
            countTotalPendapatann();
            countPendapatanSort(null, null);
        }
        if ("pelanggan".equals(((Control)actionEvent.getSource()).getId())) {
            contentPelanggan.toFront();
            home.getStyleClass().clear();
            home.getStyleClass().add("labelSidebar");
            pelanggan.getStyleClass().clear();
            pelanggan.getStyleClass().add("labelSidebar");
            pelanggan.getStyleClass().add("labelSidebarActive");
            menu.getStyleClass().clear();
            menu.getStyleClass().add("labelSidebar");
            paket.getStyleClass().clear();
            paket.getStyleClass().add("labelSidebar");
            pelanggann();
        }
        if ("paket".equals(((Control)actionEvent.getSource()).getId())) {
            contentPaket.toFront();
            home.getStyleClass().clear();
            home.getStyleClass().add("labelSidebar");
            pelanggan.getStyleClass().clear();
            pelanggan.getStyleClass().add("labelSidebar");
            menu.getStyleClass().clear();
            menu.getStyleClass().add("labelSidebar");
            paket.getStyleClass().clear();
            paket.getStyleClass().add("labelSidebar");
            paket.getStyleClass().add("labelSidebarActive");
            paket();
        }
        if ("menu".equals(((Control)actionEvent.getSource()).getId())) {
            contentMenu.toFront();
            home.getStyleClass().clear();
            home.getStyleClass().add("labelSidebar");
            pelanggan.getStyleClass().clear();
            pelanggan.getStyleClass().add("labelSidebar");
            menu.getStyleClass().clear();
            menu.getStyleClass().add("labelSidebar");
            menu.getStyleClass().add("labelSidebarActive");
            paket.getStyleClass().clear();
            paket.getStyleClass().add("labelSidebar");
            menu();
        }
        if ("keluar".equals(((Control)actionEvent.getSource()).getId())) {
            userSession.removeUser();
            
            Parent root = FXMLLoader.load(getClass().getResource("/cateringfx/CateringFX.fxml"));
        
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            
            stage.getScene().setRoot(root);
        }
    }
    
}
