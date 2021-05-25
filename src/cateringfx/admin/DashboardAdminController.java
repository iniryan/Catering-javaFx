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
package cateringfx.admin;

import cateringfx.CateringFX;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import cateringfx.koneksi;
import cateringfx.userSession;
import com.jfoenix.controls.JFXDatePicker;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class DashboardAdminController implements Initializable {
    
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
    private AnchorPane contentKaryawan;
    
    @FXML
    private AnchorPane contentKategori;
    
    @FXML
    private AnchorPane contentMenu;
    
    //sidebar
    @FXML
    private JFXButton home;
    
    @FXML
    private JFXButton paket;
    
    @FXML
    private JFXButton menu;
    
    @FXML
    private JFXButton pelanggan;
    
    @FXML
    private JFXButton karyawan;
    
    @FXML
    private JFXButton kategori;
    
    @FXML
    private JFXButton keluar;
    
    //itemList
    @FXML
    private VBox itemKaryawan = null;
    
    @FXML
    private VBox itemPelanggan = null;
    
    @FXML
    private VBox itemKategori = null;
    
    @FXML
    private VBox itemMenu = null;
    
    @FXML
    private VBox itemPaket = null;
    
    @FXML
    private VBox itemMenu2 = null;
    
    @FXML
    private VBox itemDetail = null;
    
    @FXML
    private VBox itemTransport = null;
    
    @FXML
    private VBox itemTransaksi = null;
    
    //isi itemKaryawan
    @FXML
    private Label karyawan_id;
    
    @FXML
    private Label karyawan_nama;
    
    @FXML
    private Label karyawan_username;
    
    @FXML
    private Label karyawan_join;
    
    @FXML
    private JFXButton karyawan_aktif;
    
    @FXML
    private JFXButton karyawan_hapus;
    
    //field halaman karyawan
    @FXML
    private TextField fieldUsername;
    
    @FXML
    private TextField fieldNamaLengkap;
    
    @FXML
    private JFXButton updateKaryawan;
    
    @FXML
    private JFXButton resetKaryawan;
    
    //isi itemKategori
    @FXML
    private Label kategori_id;
    
    @FXML
    private Label kategori_nama;
    
    @FXML
    private Label kategori_create;
    
    @FXML
    private JFXButton kategori_hapus;
    
    //field halaman kategori
    @FXML
    private JFXButton updateKategoribtn;
    
    @FXML
    private JFXButton resetKategori;
    
    @FXML
    private TextField fieldKategori;
    
    @FXML
    private TextField fieldBiaya;
    
    @FXML
    private TextField fieldTujuan;
    
    @FXML
    private JFXButton updateTransport;
    
    @FXML
    private JFXButton resetTransport;
    
    //isi itemMenu
    @FXML
    private Label menu_nama;
    
    @FXML
    private Label menu_harga;
    
    @FXML
    private JFXButton menu_hapus;
    
    //field halaman menu
    @FXML
    private TextField nama_menu;
    
    @FXML
    private TextField harga_menu;
    
    @FXML
    private JFXButton updateMenu;
    
    @FXML
    private JFXButton tambahMenu;
    
    @FXML
    private JFXButton resetMenu;
    
    @FXML
    private TextField filePathpaket;

    @FXML
    private JFXButton btnBrowse1;
    
    //isi itemPaket
    @FXML
    private Label paket_nama;
    
    @FXML
    private Label paket_harga;
    
    @FXML
    private JFXButton paket_hapus;
    
    @FXML
    private JFXButton removeMenu;
    
    //field halaman paket
    @FXML
    private TextField paketHarga;
    
    @FXML
    private TextField namaPaket;  
    
    @FXML
    private JFXButton updatePaket;
    
    @FXML
    private JFXButton tambahpaket;
    
    @FXML
    private JFXButton resetPaket;
    
    @FXML
    private ComboBox pilihMenu;
    
    @FXML
    private TextField filepathField;
    
    @FXML
    private JFXButton btnBrowse;
    
    //gambar
    @FXML
    private ImageView gambarPaket;
    
    @FXML
    private ImageView menuGambar;
    
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
    private TextField cariKategori;
    
    @FXML
    private TextField cariKaryawan;
    
    //upload image
    private InputStream input;
    
    private FileInputStream fin;
    
    private int len;
    
    private File selectedFile;
    
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
    
    @FXML
    private JFXButton btnCetakLaporan;
    
    private ArrayList<Paket> items = new ArrayList<Paket>();
    
    ObservableList<Menu> listSelected = FXCollections.observableArrayList();
    
    //btn minimize
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
    
    @FXML
    private JFXButton minimize_stage6;
        
    //induknya
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userSession.authorizeUser();

        //koneksi
        koneksi DB = new koneksi();
        DB.konek();
        con = DB.koneksi;
        stat = DB.stm;
        
        //halaman admin
        paneTransaksi.toFront();
        transaksi(null, null);
    
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
            itemPelanggan.getChildren().forEach((listItem) -> {
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
        
        cariKaryawan.textProperty().addListener((obs, oldValue, newValue) -> {
            itemKaryawan.getChildren().forEach((listItem) -> {
                String KaryawanId = ((Label) listItem.lookup("#karyawan_id")).getText().toLowerCase().trim();
                String KaryawanNama = ((Label) listItem.lookup("#karyawan_nama")).getText().toLowerCase().trim();
                String KaryawanUsername = ((Label) listItem.lookup("#karyawan_username")).getText().toLowerCase().trim();
                String KaryawanJoin = ((Label) listItem.lookup("#karyawan_join")).getText().toLowerCase().trim();
                String KaryawanAktif = ((JFXButton) listItem.lookup("#karyawan_aktif")).getText().toLowerCase().trim();


                if(KaryawanId.contains(newValue.toLowerCase().trim())||KaryawanNama.contains(newValue.toLowerCase().trim())||KaryawanUsername.contains(newValue.toLowerCase().trim())||KaryawanJoin.contains(newValue.toLowerCase().trim())||KaryawanAktif.contains(newValue.toLowerCase().trim())){
                    listItem.setVisible(true);
                    listItem.setManaged(true);
                }else{
                    listItem.setVisible(false);
                    listItem.setManaged(false);
                }
            });
        });
        
        cariKategori.textProperty().addListener((obs, oldValue, newValue) -> {
            itemKategori.getChildren().forEach((listItem) -> {
                String KategoriId = ((Label) listItem.lookup("#kategori_id")).getText().toLowerCase().trim();
                String KategoriNama = ((Label) listItem.lookup("#kategori_nama")).getText().toLowerCase().trim();
                String KategoriJoin = ((Label) listItem.lookup("#kategori_create")).getText().toLowerCase().trim();
                if(KategoriId.contains(newValue.toLowerCase().trim())||KategoriNama.contains(newValue.toLowerCase().trim())||KategoriJoin.contains(newValue.toLowerCase().trim())){
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
            System.out.println(rs.getInt("total_transaksi"));
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
         Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
    public void filterHistory(){
        if (historyBegin != null && historyFinish != null) {
            transaksi(historyBegin, historyFinish);
            countPendapatanSort(historyBegin, historyFinish);
        }
    }
    
    //btnClose
    @FXML
    private void close_app2(MouseEvent event) {
        System.exit(0);
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
                System.out.println(start.toString());
                System.out.println(end.toString());
            }
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int trId = rs.getInt("transaksi_id");
                String transaksiId = rs.getString("customTransaksi_id");
                String namaKasir = rs.getString("nama_lengkap");
                String tglPesan = formatDate(rs.getTimestamp("tanggal_pesan"));
                String tglKirim = formatDate(rs.getTimestamp("tanggal_kirim"));
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/admin/itemTransaksi.fxml"));
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
                
                btnCetakLaporan.setOnAction(e -> {
                    cetakCetak(start, end);
                });
                    
                itemTransaksi.getChildren().add(wrapper);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
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
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/admin/itemDetail.fxml"));
                
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
    
    //karyawan
    public void karyawan(){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM view_user");
            rs = ps.executeQuery();
            
            itemKaryawan.getChildren().clear();
            while(rs.next()){
                int role = rs.getInt("role");
                if (role == 2) {
                String karyawanId = rs.getString("custom_id");
                String karyawanNama = rs.getString("nama_lengkap");
                String karyawanUsername = rs.getString("username");
                String karyawanJoin = formatDate(rs.getTimestamp("created_at"));
                int karyawanAktif = rs.getInt("aktivasi");
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/admin/itemKaryawan.fxml"));
                Label labelKaryawanId = (Label)wrapper.lookup("#karyawan_id");
                Label labelKaryawanNama = (Label)wrapper.lookup("#karyawan_nama");
                Label labelKaryawanUsername = (Label)wrapper.lookup("#karyawan_username");
                Label labelKaryawanJoin = (Label)wrapper.lookup("#karyawan_join");
                JFXButton labelKaryawanAktif = (JFXButton)wrapper.lookup("#karyawan_aktif");
                if(karyawanAktif == 1){
                    labelKaryawanAktif.setText("Active");
                }if(karyawanAktif == 2){
                    labelKaryawanAktif.setText("Block"); 
                }
                labelKaryawanId.setText(karyawanId);
                labelKaryawanNama.setText(karyawanNama);
                labelKaryawanUsername.setText(karyawanUsername);
                labelKaryawanJoin.setText(karyawanJoin);
                                
                int id=rs.getInt("user_id");
                JFXButton btnHapus = (JFXButton)wrapper.lookup("#karyawan_hapus");

                wrapper.setOnMouseClicked(event -> {
                    fieldNamaLengkap.setText(karyawanNama);
                    fieldUsername.setText(karyawanUsername);
                    
                    updateKaryawan.setOnAction((evt) -> {
                        updateKaryawann(id);
                    });
                    
                    resetKaryawan.setOnAction((evt) -> {
                        resetKaryawann();
                    });

                });
                
                labelKaryawanAktif.setOnAction((evt) -> {
                    updateKaryawanAktif(id, karyawanAktif);
                });
                
                btnHapus.setOnAction((evt) -> {
                    hapusKaryawan(id);
                });
                
                minimize_stage6.setOnAction(e -> {
                    ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
                });

                itemKaryawan.getChildren().add(wrapper);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void tambahKaryawan(ActionEvent actionEvent) throws IOException {
        String username = fieldUsername.getText().toLowerCase().trim();
        String password = fieldUsername.getText().trim();
        String namaLengkap = fieldNamaLengkap.getText();

        if (!username.isEmpty() && !namaLengkap.isEmpty()) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO c_user(username, password, nama_lengkap) VALUES(?, md5(?), ?) ");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, namaLengkap);
            ps.execute();
            karyawan();
            fieldUsername.setText("");
            fieldNamaLengkap.setText("");
        } catch (SQLException e ) {
            e.printStackTrace();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }
    }
    
    public void updateKaryawanAktif(int id, int karyawanAktif) {
        if(karyawanAktif == 1){
            try {
            PreparedStatement ps = con.prepareStatement("UPDATE c_user set aktivasi = ? WHERE user_id = ? ");
            ps.setInt(1, 2);
            ps.setInt(2, id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ganti Aktivasi Data");
            alert.setHeaderText("Data akan diubah ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                ps.execute();
            } else {
                
            }
            karyawan();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        }
        if(karyawanAktif == 2){
            try {
            PreparedStatement ps = con.prepareStatement("UPDATE c_user set aktivasi = ? WHERE user_id = ? ");
            ps.setInt(1, 1);
            ps.setInt(2, id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ganti Aktivasi Data");
            alert.setHeaderText("Data akan diubah ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                ps.execute();
            } else {
                
            }
            karyawan();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        }
        
    }
    
    public void updateKaryawann(int id) {
        String username = fieldUsername.getText().toLowerCase().trim();
        String password = fieldUsername.getText().trim();
        String namaLengkap = fieldNamaLengkap.getText();
        
        if (!username.isEmpty() && !namaLengkap.isEmpty()) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE c_user set username=?, password=md5(?), nama_lengkap=? WHERE user_id = ? ");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, namaLengkap);
            ps.setInt(4, id);
            ps.execute();
            karyawan();
            fieldUsername.setText("");
            fieldNamaLengkap.setText("");
        } catch (SQLException e ) {
            e.printStackTrace();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }
    }
    
    public void resetKaryawann() {
        updateKaryawan.setOnAction(null);
        fieldUsername.setText("");
        fieldNamaLengkap.setText("");
    }
    
     public void hapusKaryawan(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM c_user WHERE user_id = ? ");
            ps.setInt(1, id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hapus Data");
            alert.setHeaderText("Data akan dihapus ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                ps.execute();
            } else {
                
            }
            karyawan();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //pelanggan
    public void pelanggann(){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM view_customer");
            rs = ps.executeQuery();
            
            itemPelanggan.getChildren().clear();
            while(rs.next()){
                String customerId = rs.getString("customCustomer_id");
                String customerNama = rs.getString("nama_customer");
                String customerTelp = rs.getString("notelp");
                String customerAlamat = rs.getString("alamat");
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/admin/itemPelanggan.fxml"));
                Label labelCustomerId = (Label)wrapper.lookup("#customer_id");
                Label labelCustomerNama = (Label)wrapper.lookup("#customer_nama");
                Label labelCustomerTelp = (Label)wrapper.lookup("#customer_telepon");
                Label labelCustomerAlamat = (Label)wrapper.lookup("#customer_alamat");
                labelCustomerId.setText(customerId);
                labelCustomerNama.setText(customerNama);                
                labelCustomerTelp.setText(customerTelp);
                labelCustomerAlamat.setText(customerAlamat);

                minimize_stage3.setOnAction(e -> {
                    ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
                });
                
                itemPelanggan.getChildren().add(wrapper);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    //biaya transport
    public void transport(){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM c_biayaTransport");
            rs = ps.executeQuery();
            fieldBiaya.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                    fieldBiaya.setText(newValue.replaceAll("[^\\d]", ""));
                }}
            });
            
            itemTransport.getChildren().clear();
            while(rs.next()){
                int costId = rs.getInt("cost_id");
                String tujuan = rs.getString("tujuan");
                int biayaTransportt = rs.getInt("biaya_transport");
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/admin/itemTransport.fxml"));
                Label labelTujuan = (Label)wrapper.lookup("#tujuan_transport");
                Label labelBiaya = (Label)wrapper.lookup("#biayaTransport");
                labelTujuan.setText(tujuan);
                labelBiaya.setText("Rp. "+String.valueOf(formatRp(biayaTransportt)));                

                JFXButton btnHapus = (JFXButton)wrapper.lookup("#hapusTransport");
                
                wrapper.setOnMouseClicked(event -> {
                    fieldTujuan.setText(tujuan);
                    fieldBiaya.setText(String.valueOf(biayaTransportt));
                    
                    updateTransport.setOnAction((evt) -> {
                        updateTransportt(costId);
                    });
                    
                    resetTransport.setOnAction((evt) -> {
                        resetTransportt();
                    });

                });

                 btnHapus.setOnAction((evt) -> {
                    hapusTransportCost(costId);
                });
                
                itemTransport.getChildren().add(wrapper);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void tambahTransport(ActionEvent actionEvent) throws IOException {
        String tujuan = fieldTujuan.getText();
        String biaya = fieldBiaya.getText();
        
        
        if (!tujuan.isEmpty() && !biaya.isEmpty()) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO c_biayatransport(tujuan, biaya_transport) VALUES(?, ?) ");
            ps.setString(1, tujuan);
            int hBiaya = Integer.parseInt(biaya);
            ps.setInt(2, hBiaya);
            ps.execute();
            transport();
            fieldTujuan.setText("");
            fieldBiaya.setText("");
        } catch (SQLException e ) {
            e.printStackTrace();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }
    }
    
    public void updateTransportt(int costId) {
        String tujuan = fieldTujuan.getText();
        String biaya = fieldBiaya.getText();
        
        if (!tujuan.isEmpty() && !biaya.isEmpty()) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE c_biayatransport set tujuan=?, biaya_transport=? WHERE cost_id = ? ");
            ps.setString(1, tujuan);
            int hBiaya = Integer.parseInt(biaya);
            ps.setInt(2, hBiaya);
            ps.setInt(3, costId);
            ps.execute();
            transport();
            fieldTujuan.setText("");
            fieldBiaya.setText("");
        } catch (SQLException e ) {
            e.printStackTrace();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }
    }
    
    public void resetTransportt() {
        updateTransport.setOnAction(null);
        fieldTujuan.setText("");
        fieldBiaya.setText("");
    }
        
    public void hapusTransportCost(int costId) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM c_biayatransport WHERE cost_id = ? ");
            ps.setInt(1, costId);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hapus Data");
            alert.setHeaderText("Data akan dihapus ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                ps.execute();
            } else {
                
            }
            transport();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //kategori
    public void kategori(){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM view_kategori");
            rs = ps.executeQuery();
            
            itemKategori.getChildren().clear();
            while(rs.next()){
                String kategoriId = rs.getString("customKategori_id");
                String kategoriNama = rs.getString("event");
                String kategoriJoin = formatDate(rs.getTimestamp("created_kategori_at"));
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/admin/itemKategori.fxml"));
                Label labelKategoriId = (Label)wrapper.lookup("#kategori_id");
                Label labelKategoriNama = (Label)wrapper.lookup("#kategori_nama");
                Label labelKategoriJoin = (Label)wrapper.lookup("#kategori_create");
                labelKategoriId.setText(kategoriId);
                labelKategoriNama.setText(kategoriNama);                
                labelKategoriJoin.setText(kategoriJoin);

                int id=rs.getInt("kategori_id");
                JFXButton btnHapus = (JFXButton)wrapper.lookup("#kategori_hapus");
                
                wrapper.setOnMouseClicked(event -> {
                    fieldKategori.setText(kategoriNama);
                    
                    updateKategoribtn.setOnAction((evt) -> {
                        updateKategori(id);
                    });
                    
                    resetKategori.setOnAction((evt) -> {
                        resetKategorii();
                    });

                });

                 btnHapus.setOnAction((evt) -> {
                    hapusKategori(id);
                });
                 
                 minimize_stage5.setOnAction(e -> {
                    ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
                });
                
                itemKategori.getChildren().add(wrapper);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void tambahKategori(ActionEvent actionEvent) throws IOException {
        String kategori = fieldKategori.getText();
        
        if (!kategori.isEmpty()) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO c_kategori(event) VALUES(?) ");
            ps.setString(1, kategori);
            ps.execute();
            kategori();
            fieldKategori.setText("");
        } catch (SQLException e ) {
            e.printStackTrace();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }
    }
    
    public void updateKategori(int id) {
        String kategori = fieldKategori.getText();
        
        if (!kategori.isEmpty()) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE c_kategori set event=? WHERE kategori_id = ? ");
            ps.setString(1, kategori);
            ps.setInt(2, id);
            ps.execute();
            kategori();
            fieldKategori.setText("");
        } catch (SQLException e ) {
            e.printStackTrace();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }
    }
    
    public void resetKategorii() {
        updateKategoribtn.setOnAction(null);
        fieldKategori.setText("");
    }
        
    public void hapusKategori(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM c_kategori WHERE kategori_id = ? ");
            ps.setInt(1, id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hapus Data");
            alert.setHeaderText("Data akan dihapus ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                ps.execute();
            } else {
                
            }
            kategori();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //menu
    public void menu(){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM c_menu");
            rs = ps.executeQuery();
            harga_menu.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                    harga_menu.setText(newValue.replaceAll("[^\\d]", ""));
                }}
            });
            itemMenu.getChildren().clear();
            while(rs.next()){
                String menuNama = rs.getString("nama_menu");
                int menuHarga = rs.getInt("harga_satuan");
                int id=rs.getInt("menu_id");
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/admin/itemMenu.fxml"));
                Label labelMenuNama = (Label)wrapper.lookup("#menu_nama");
                Label labelMenuHarga = (Label)wrapper.lookup("#menu_harga");
                labelMenuNama.setText(menuNama);                
                labelMenuHarga.setText("Rp. "+String.valueOf(formatRp(menuHarga)));
                
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));

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
                
                JFXButton btnHapus = (JFXButton)wrapper.lookup("#menu_hapus");
                
                btnBrowse.setOnAction(e -> {
                    selectedFile = fileChooser.showOpenDialog(dashboardAdmin.stage);
                    if (selectedFile != null) {
                        try {
                            BufferedImage bufferedImage = ImageIO.read(selectedFile);
                            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                            fin = new FileInputStream(selectedFile);
                            len = (int)selectedFile.length();
                        } catch (IOException ez) {
                            Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ez);
                           }
                    filepathField.setText(selectedFile.getPath());
                        
                    }
                });
                
                tambahMenu.setOnAction((evt) -> {
                    if(fin != null){
                        tambahMenuu(fin, len);
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Harap input gambar!");
                        alert.showAndWait();
                    }
                });
               
                wrapper.setOnMouseClicked(event -> {
                    nama_menu.setText(menuNama);
                    harga_menu.setText(String.valueOf(menuHarga));
                    
                    updateMenu.setOnAction((evt) -> {
                        try {
                            updateMenu(id, fin, len);
                        } catch (IOException ex) {
                            Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                                        
                    resetMenu.setOnAction((evt) -> {
                        resetMenuu();
                    });

                });

                 btnHapus.setOnAction((evt) -> {
                    hapusMenu(id);
                });
                 
                 minimize_stage1.setOnAction(e -> {
                    ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
                });
                
                itemMenu.getChildren().add(wrapper);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void hapusMenu(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM c_menu WHERE menu_id = ? ");
            ps.setInt(1, id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hapus Data");
            alert.setHeaderText("Data akan dihapus ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                ps.execute();
            } else {
                
            }
            menu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void tambahMenuu(FileInputStream fin, int len){
        String namaMenu = nama_menu.getText();     
        String hargaMenu = harga_menu.getText();
        
        if (!namaMenu.isEmpty() && !hargaMenu.isEmpty()) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO c_menu(nama_menu, harga_satuan, gambar_menu) VALUES(?, ?, ?) ");
            ps.setString(1, namaMenu);
            int hMenu = Integer.parseInt(hargaMenu);
            ps.setInt(2, hMenu);
            ps.setBinaryStream(3, fin, len);
            ps.execute();
            menu();
            nama_menu.setText("");
            harga_menu.setText("");
            filepathField.setText(null);
        } catch (SQLException e ) {
            e.printStackTrace();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }
    }
    
    public void updateMenu(int id, FileInputStream fin, int len) throws IOException {
        String namaMenu = nama_menu.getText();
        String hargaMenu = harga_menu.getText();
        
        if (!namaMenu.isEmpty() && !hargaMenu.isEmpty()) {
        try {
            if(fin != null && fin.available() > 1) {
                PreparedStatement ps = con.prepareStatement("UPDATE c_menu set nama_menu=?, harga_satuan=?, gambar_menu=? WHERE menu_id = ? ");
                ps.setString(1, namaMenu);
                int hMenu = Integer.parseInt(hargaMenu);
                ps.setInt(2, hMenu);
                ps.setBinaryStream(3, fin, len);
                ps.setInt(4, id);
                ps.execute();
            }
            else{
                PreparedStatement ps = con.prepareStatement("UPDATE c_menu set nama_menu=?, harga_satuan=? WHERE menu_id = ? ");
                ps.setString(1, namaMenu);
                int hMenu = Integer.parseInt(hargaMenu);
                ps.setInt(2, hMenu);  
                ps.setInt(3, id);
                ps.execute();
            }
            menu();
            nama_menu.setText("");
            harga_menu.setText("");
            filepathField.setText(null);
        } catch (SQLException e ) {
            e.printStackTrace();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }
    }
    
    public void resetMenuu() {
        updateMenu.setOnAction(null);
        nama_menu.setText("");
        harga_menu.setText("");
        filepathField.setText(null);
        btnBrowse.setOnAction(null);
    }
   
//    paket
    public void paket(){
        try {
            String custom = "Custom Paket";
            PreparedStatement ps = con.prepareStatement("SELECT * FROM c_paket WHERE nama_paket != ?");
            ps.setString(1, custom);
            rs = ps.executeQuery();
            paketHarga.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                    paketHarga.setText(newValue.replaceAll("[^\\d]", ""));
                }}
            });     
            
            itemMenu2.getChildren().clear();
            itemPaket.getChildren().clear();
            while(rs.next()){
                String paketNama = rs.getString("nama_paket");
                int paketHargaa = rs.getInt("harga_paket");
                int id=rs.getInt("paket_id");
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/admin/itemPaket.fxml"));
                Label labelPaketNama = (Label)wrapper.lookup("#paket_nama");
                Label labelPaketHarga = (Label)wrapper.lookup("#paket_harga");
                labelPaketNama.setText(paketNama);                
                labelPaketHarga.setText("Rp. "+String.valueOf(formatRp(paketHargaa)));
                Text daftarMenuu = (Text)wrapper.lookup("#listMenu");
            
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));

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
      
                
                JFXButton btnHapus = (JFXButton)wrapper.lookup("#paket_hapus");
                
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
                
            //btn buka file explorer
            btnBrowse1.setOnAction(e -> {
                    selectedFile = fileChooser.showOpenDialog(dashboardAdmin.stage);
                    if (selectedFile != null) {
                        try {
                            BufferedImage bufferedImage = ImageIO.read(selectedFile);
                            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                            fin = new FileInputStream(selectedFile);
                            len = (int)selectedFile.length();
                        } catch (IOException ez) {
                            Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ez);
                           }
                        filePathpaket.setText(selectedFile.getPath());
                    }
                });
            
            tambahpaket.setOnAction((evt) -> {
                    if(fin != null){
                        tambahpakett(fin, len);
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Harap input gambar!");
                        alert.showAndWait();
                    }
                });
                
                wrapper.setOnMouseClicked(event -> {
                    namaPaket.setText(paketNama);
//                    paketHarga.setText(String.valueOf(paketHargaa));
                    
                    try{
                        PreparedStatement psp = con.prepareStatement("SELECT * FROM c_paket_menu WHERE paket_id = ?");
                        psp.setInt(1, id);
                        ResultSet rsk = psp.executeQuery();
                        paketHarga.setText(String.valueOf(0));
                        listSelected.clear();
                        itemMenu2.getChildren().clear();
                        while(rsk.next()){
                            menuPaket(rsk.getInt("menu_id"));
                        }
                    }catch(SQLException e){
                         e.printStackTrace();

                    }
                    
                    updatePaket.setOnAction((evt) -> {
                        try {
                            updatePakett(id, fin, len);
                        } catch (IOException ex) {
                            Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    
                    resetPaket.setOnAction((evt) -> {
                        resetPakett();
                    });
                });

                 btnHapus.setOnAction((evt) -> {
                    hapusPaket(id);
                });
                 
                 minimize_stage2.setOnAction(e -> {
                    ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
                });
                
                itemPaket.getChildren().add(wrapper);
            }
                //fungsi pilih menu
                pilihMenu();
                
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    //combobox menu
    public void pilihMenu(){
        try {
            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM c_menu");
            ResultSet rs = ps.executeQuery();
            pilihMenu.setCellFactory(lv -> new MenuList());
            pilihMenu.setButtonCell(new MenuList());
            pilihMenu.getItems().clear();
            while(rs.next()){
                int menu_id = rs.getInt("menu_id");
                String namaMenu = rs.getString("nama_menu");
                int hargaSatuan = rs.getInt("harga_satuan");
                pilihMenu.getItems().add(new Menu(menu_id, namaMenu, hargaSatuan));
            }
                        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pilihMenu.setOnAction(event -> {
            Menu selected = (Menu) pilihMenu.getSelectionModel().getSelectedItem();
                if(selected != null) {
                    int selectedMenuId = ((Menu) pilihMenu.getValue()).getMenuid();
                    //fungsi menu paket
                    menuPaket(selectedMenuId);
                    System.out.println(selectedMenuId);
                }
            
        });

    }
    
    
    public void menuPaket(int selectedMenuId){
        try {
            boolean exists = false;
            for (Menu item : listSelected) {
                if (item.menu_id == selectedMenuId) {
                    exists = true;
                }
            }
            if(!exists){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM c_menu where menu_id = ?");
            ps.setInt(1, selectedMenuId);
            rs = ps.executeQuery();

//            itemMenu2.getChildren().clear();
            while(rs.next()){
                //simpan menu yang telah di klik
                listSelected.add(new Menu(rs.getInt("menu_id"), rs.getString("nama_menu"), rs.getInt("harga_satuan")));
                System.out.println(listSelected);
                
                String menuNama = rs.getString("nama_menu");
                int menuHarga = rs.getInt("harga_satuan");
                int subTotal = 0;
                subTotal = Integer.valueOf(paketHarga.getText());
                subTotal += menuHarga;
                
                Node wrapper = FXMLLoader.load(getClass().getResource("/cateringfx/admin/itemMenu2.fxml"));
                //menu id untuk dapet id dari menu combobox yg telah di klik dan untuk ditambah juga
                String menuId = String.valueOf(rs.getInt("menu_id"));
                int menuIdInt = rs.getInt("menu_id");
                //menampilkan item menu di kanan (set id)
                ((HBox)wrapper).setId("menu"+menuId);
                //tutup item menu kanan
                Label labelMenuNama = (Label)wrapper.lookup("#menu_nama");
                Label labelMenuHarga = (Label)wrapper.lookup("#menu_harga");
                labelMenuNama.setText(menuNama);    
                labelMenuHarga.setText("Rp. "+String.valueOf(formatRp(menuHarga)));
                
                //remove menu yg telah di klik
                JFXButton removeMenu = (JFXButton)wrapper.lookup("#removeMenu");
                removeMenu.setOnAction(event -> {
                    itemMenu2.getChildren().remove(itemMenu2.lookup("#menu"+menuId));
                    listSelected.removeIf(menu -> menu.menu_id == menuIdInt);
                    int subTotall = 0 ;
                    subTotall = Integer.valueOf(paketHarga.getText());
                    subTotall -= menuHarga;
                    paketHarga.setText(String.valueOf(subTotall));
                });
                
                ImageView mGambar = (ImageView)wrapper.lookup("#menuGambar");
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
               
                itemMenu2.getChildren().add(wrapper);

                paketHarga.setText(String.valueOf(subTotal));
            }}
            
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void tambahpakett(FileInputStream fin, int len) {
        String nama_paket = namaPaket.getText();     
        String hargaPaket = paketHarga.getText();
        
        if (!nama_paket.isEmpty() && !hargaPaket.isEmpty()) {
        try {
            //tambah paket dan get id paket setelah diinsertkan
            PreparedStatement ps = con.prepareStatement("INSERT INTO c_paket(nama_paket, harga_paket, gambar_paket) VALUES(?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nama_paket);
            int hPaket = Integer.parseInt(paketHarga.getText())+10000;
            ps.setInt(2, hPaket);
            ps.setBinaryStream(3, fin, len);
            ps.execute();
            //get last id paket yg baru saja dimasukkan
            ResultSet rsid = ps.getGeneratedKeys();
            int generatedKey = 0;
            if(rsid.next()){
                generatedKey = rsid.getInt(1);
            for(Menu m : listSelected) {
                PreparedStatement psrv = con.prepareStatement("INSERT INTO c_paket_menu(menu_id, paket_id) VALUES(?, ?) ");
                psrv.setInt(1, m.getMenuid());
                psrv.setInt(2, generatedKey);
                psrv.executeUpdate();
            }}
            paket();
            namaPaket.setText("");
            paketHarga.setText(String.valueOf(0));
            filePathpaket.setText(null);
            listSelected.clear();
            itemMenu2.getChildren().clear();
            
        } catch (SQLException e ) {
            e.printStackTrace();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }
    }
    
    public void updatePakett(int id, FileInputStream fin, int len) throws IOException{
        String nama_paket = namaPaket.getText();     
        String hargaPaket = paketHarga.getText();
        
        if (!nama_paket.isEmpty() && !hargaPaket.isEmpty()) {
        try {
            if (fin != null && fin.available() > 1) {
                PreparedStatement ps = con.prepareStatement("UPDATE c_paket set nama_paket=?, harga_paket=?, gambar_paket=? WHERE paket_id = ? ");
                ps.setString(1, nama_paket);
                int hPaket = Integer.parseInt(paketHarga.getText())+10000;
                ps.setInt(2, hPaket);
                ps.setBinaryStream(3, fin, len);
                ps.setInt(4, id);
                ps.execute();
            }else{
                PreparedStatement ps = con.prepareStatement("UPDATE c_paket set nama_paket=?, harga_paket=? WHERE paket_id = ? ");
                ps.setString(1, nama_paket);
                int hPaket = Integer.parseInt(paketHarga.getText())+10000;
                ps.setInt(2, hPaket);
                ps.setInt(3, id);
                ps.execute();
            }
            PreparedStatement psk = con.prepareStatement("DELETE FROM c_paket_menu WHERE paket_id = ?");
            psk.setInt(1, id);
            psk.execute();

            for(Menu m : listSelected) {
                PreparedStatement psrv = con.prepareStatement("INSERT INTO c_paket_menu(menu_id, paket_id) VALUES(?, ?) ");
                psrv.setInt(1, m.getMenuid());
                psrv.setInt(2, id);
                psrv.executeUpdate();
            }
            paket();
            namaPaket.setText("");
            paketHarga.setText(String.valueOf(0));
            filePathpaket.setText(null);
            listSelected.clear();
            itemMenu2.getChildren().clear();
            
        } catch (SQLException e ) {
            e.printStackTrace();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Harap isi Field!");
            alert.showAndWait();
        }
    }
    
    public void hapusPaket(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM c_paket WHERE paket_id = ? ");
            ps.setInt(1, id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hapus Data");
            alert.setHeaderText("Data akan dihapus ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                //hapus paket dari c_paket_menu, c_paket
                PreparedStatement psrv = con.prepareStatement("DELETE FROM c_paket_menu WHERE paket_id = ? ");
                psrv.setInt(1, id);
                psrv.execute();
                ps.execute();
                
            } else {
                
            }
            paket();
            namaPaket.setText("");
            paketHarga.setText(String.valueOf(0));
            listSelected.clear();
            itemMenu2.getChildren().clear();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void resetPakett(){
        updatePaket.setOnAction(null);
        namaPaket.setText("");
        paketHarga.setText(String.valueOf(0));
        listSelected.clear();
        itemMenu2.getChildren().clear();
        filePathpaket.setText(null);
        btnBrowse1.setOnAction(null);
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
            kategori.getStyleClass().clear();
            kategori.getStyleClass().add("labelSidebar");
            karyawan.getStyleClass().clear();
            karyawan.getStyleClass().add("labelSidebar");
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
            kategori.getStyleClass().clear();
            kategori.getStyleClass().add("labelSidebar");
            karyawan.getStyleClass().clear();
            karyawan.getStyleClass().add("labelSidebar");
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
            kategori.getStyleClass().clear();
            kategori.getStyleClass().add("labelSidebar");
            karyawan.getStyleClass().clear();
            karyawan.getStyleClass().add("labelSidebar");
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
            kategori.getStyleClass().clear();
            kategori.getStyleClass().add("labelSidebar");
            karyawan.getStyleClass().clear();
            karyawan.getStyleClass().add("labelSidebar");
            menu();
        }
        if ("kategori".equals(((Control)actionEvent.getSource()).getId())) {
            contentKategori.toFront();
            home.getStyleClass().clear();
            home.getStyleClass().add("labelSidebar");
            pelanggan.getStyleClass().clear();
            pelanggan.getStyleClass().add("labelSidebar");
            menu.getStyleClass().clear();
            menu.getStyleClass().add("labelSidebar");
            paket.getStyleClass().clear();
            paket.getStyleClass().add("labelSidebar");
            kategori.getStyleClass().clear();
            kategori.getStyleClass().add("labelSidebar");
            kategori.getStyleClass().add("labelSidebarActive");
            karyawan.getStyleClass().clear();
            karyawan.getStyleClass().add("labelSidebar");
            kategori();
            transport();
        }
        if ("karyawan".equals(((Control)actionEvent.getSource()).getId())) {
            contentKaryawan.toFront();
            home.getStyleClass().clear();
            home.getStyleClass().add("labelSidebar");
            pelanggan.getStyleClass().clear();
            pelanggan.getStyleClass().add("labelSidebar");
            menu.getStyleClass().clear();
            menu.getStyleClass().add("labelSidebar");
            paket.getStyleClass().clear();
            paket.getStyleClass().add("labelSidebar");
            kategori.getStyleClass().clear();
            kategori.getStyleClass().add("labelSidebar");
            karyawan.getStyleClass().clear();
            karyawan.getStyleClass().add("labelSidebar");
            karyawan.getStyleClass().add("labelSidebarActive");
            karyawan();
        }
        if ("keluar".equals(((Control)actionEvent.getSource()).getId())) {
            userSession.removeUser();
            
            Parent root = FXMLLoader.load(getClass().getResource("/cateringfx/CateringFX.fxml"));
        
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            
            stage.getScene().setRoot(root);
        }
    }
    
}
