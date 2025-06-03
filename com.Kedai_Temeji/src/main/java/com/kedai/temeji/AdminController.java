/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kedai.temeji;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;


/**
 *
 * @author Lenovo
 */
public class AdminController implements Initializable {
    
     @FXML
    private ImageView IV_exit;
    @FXML
    private ComboBox<String> cb_posisi;
    @FXML
    private TableView<dataUser> tb_kelolaUser;
    @FXML
    private TableColumn<dataUser, Integer> kolom_idUser;
    @FXML
    private TableColumn<dataUser, String> kolom_namaAsli;
    @FXML
    private TableColumn<dataUser, String> kolom_password;
    @FXML
    private TableColumn<dataUser, String> kolom_posisi;
    @FXML
    private TableColumn<dataUser, String> kolom_username;    
    @FXML
    private TableView<dataMenu> tb_kelolaMenu;
    @FXML
    private TableColumn<dataMenu, Double> kolom_hargaMenu;
    @FXML
    private TableColumn<dataMenu, Integer> kolom_idMenu;
    @FXML
    private TableColumn<dataMenu, String> kolom_kategoriMenu;
    @FXML
    private TableColumn<dataMenu, String> kolom_namaMenu;
    @FXML
    private TableColumn<dataMenu, Integer> kolom_stokMenu;
    @FXML
    private TableColumn<dataMenu, String> kolom_ukuranMenu;
    @FXML
    private TextField tx_idUser;
    @FXML
    private TextField tx_namaAsli;
    @FXML
    private TextField tx_password;
    @FXML
    private TextField tx_username;
    @FXML
    private TextField tx_hargaMenu;
    @FXML
    private TextField tx_idMenu;
  @FXML
    private TextField tx_kategoriMenu;
    @FXML
    private TextField tx_namaMenu;
    @FXML
    private TextField tx_stokMenu;
    @FXML
    private TextField tx_ukuranMenu;
    @FXML
    private ImageView IV_gambarMenu;
    @FXML
    private Label lb_nc;
    @FXML
    private Label lb_numberSold;
    @FXML
    private Label lb_ti;
    @FXML
    private BarChart<String, Number> chartBar_customer;
    @FXML
    private TextField tx_cariMenu;
    @FXML
    private TextField tx_cariUser;
     @FXML
    private ComboBox<String> lihatPenjualan_cbBulan;
         @FXML
    private StackPane lihatPenjualan_StkPane;
    @FXML
    private ComboBox<String> lihatPenjualan_cbTanggal;
    @FXML
    private TableColumn<laporanMenu, Integer> lihatPenjualan_kolomId;
    @FXML
    private TableColumn<laporanMenu, Integer> lihatPenjualan_kolomJumlah;
    @FXML
    private TableColumn<laporanMenu, String> lihatPenjualan_kolomNama;
    @FXML
    private TableColumn<laporanMenu, String> lihatPenjualan_kolomP;
    @FXML
    private TableColumn<laporanMenu, String> lihatPenjualan_kolomUk;
    @FXML
    private TableColumn<laporanMenu, Integer> kolom_no;
    @FXML
    private TableView<laporanMenu> lihatPenjualan_tb;
    @FXML
    private TextField lihatPenjualan_txCari;
    @FXML
    private AnchorPane lihatPenjualan_btnLaporan;
    @FXML
    private AnchorPane APPP_lihatPenjualan;   
    @FXML
    private AnchorPane APPP_kelolaMenu;
    @FXML
    private AnchorPane APPP_kelolaUser;

        @FXML
    private TextField tx_Tdijual;
    @FXML
    private TextField tx_Tpendapatan;
     @FXML
    private TextField txTotalPerB;
     
     @FXML
    private AnchorPane APPP_kelolaWifi;
    @FXML
    private TableView<dataWifi> tb_kelolaWifi;
    @FXML
    private TableColumn<dataWifi, String> kolom_namaWifi;
    @FXML
    private TableColumn<dataWifi, Integer> kolom_noWifi;
    @FXML
    private TableColumn<dataWifi, String> kolom_passWifi;
     @FXML
    private TextField tx_cariWifi;
    @FXML
    private TextField tx_namaWifi;
    @FXML
    private TextField tx_passWifi;
    
    private ResultSet r;
    private PreparedStatement pre;
    private Alert alert;
    private Connection connn = databes.konek();
    
    
    
    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lihatPenjualan();

        


    }
    
public void kelolaUser(){
    APPP_kelolaUser.setVisible(true);
    APPP_kelolaMenu.setVisible(false);
    APPP_lihatPenjualan.setVisible(false);
    APPP_kelolaWifi.setVisible(false);

    tx_idUser.setText(idUserr().toString());
    userDataShow();
     showListLevel();
    
     tx_username.requestFocus();

    tx_username.setOnKeyPressed((KeyEvent event) -> {
        if (event.getCode() == KeyCode.ENTER) {tx_password.requestFocus();} });
    tx_password.setOnKeyPressed((KeyEvent event) -> {
        if (event.getCode() == KeyCode.ENTER) { tx_namaAsli.requestFocus();}});
    }

public void kelolaMenu(){
    APPP_kelolaUser.setVisible(false);
    APPP_kelolaMenu.setVisible(true);
    APPP_lihatPenjualan.setVisible(false);
    APPP_kelolaWifi.setVisible(false);

    tx_idMenu.setText(idMenu().toString());
    menuDataShow(); 
    
    tx_namaMenu.requestFocus();

    tx_namaMenu.setOnKeyPressed((KeyEvent event) -> {
        if (event.getCode() == KeyCode.ENTER) {tx_kategoriMenu.requestFocus();} });
    tx_kategoriMenu.setOnKeyPressed((KeyEvent event) -> {
        if (event.getCode() == KeyCode.ENTER) { tx_ukuranMenu.requestFocus();}});
    tx_ukuranMenu.setOnKeyPressed((KeyEvent event) -> {
        if (event.getCode() == KeyCode.ENTER) { tx_hargaMenu.requestFocus();}});
    tx_hargaMenu.setOnKeyPressed((KeyEvent event) -> {
        if (event.getCode() == KeyCode.ENTER) { tx_stokMenu.requestFocus();}});
    tx_stokMenu.setOnKeyPressed((KeyEvent event) -> {
        if (event.getCode() == KeyCode.ENTER) { btnImportGambar();}});
    }

public void lihatPenjualan(){
    APPP_kelolaUser.setVisible(false);
    APPP_kelolaMenu.setVisible(false);
    APPP_lihatPenjualan.setVisible(true);
    APPP_kelolaWifi.setVisible(false);

    lihatPenjualan_cbBulan.setValue(tangg());
    tampilkanNC();
        tampilkanTodayI();
        tampilkanNSP();
        showListTanggall();
        tampilkanChartCustomer();
        String abc = String.valueOf(lihatPenjualan_cbBulan.getSelectionModel().getSelectedItem());
        txTotalPerB.setText("Total Pemasukan per Bulan "+abc+"  Rp. "+formatUang(totalPerB()));
   }

public void kelolaWifii(){
    APPP_kelolaUser.setVisible(false);
    APPP_kelolaMenu.setVisible(false);
    APPP_lihatPenjualan.setVisible(false);
    APPP_kelolaWifi.setVisible(true);
    wifiDataShow();
    
    tx_namaWifi.requestFocus();
    tx_namaWifi.setOnKeyPressed((KeyEvent event) -> {
        if (event.getCode() == KeyCode.ENTER) {tx_passWifi.requestFocus();} });
}

    public void exitt(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("LoginDesign.css").toExternalForm();
            scene.getStylesheets().add(css);
            Stage stage = new Stage();
            stage.setTitle("Kedai Temeji");
            stage.setScene(scene);
            stage.show();
            loggg(dataData.getId_userLogin(), " LogOut ");
            IV_exit.getScene().getWindow().hide();
            
        } catch (IOException e) {
            e.printStackTrace();
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
     public void loggg(Integer id, String pesan){
        try{
            String sql ="INSERT INTO `log_aktivitas`(`id_users`, `keterangan`) VALUES (?,?)";
            pre = connn.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setString(2, pesan);
            pre.executeUpdate();
            pre.close();
        }catch(SQLException e){
             e.printStackTrace();
        }
    }
      
      public static String formatUang(double amount) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("id", "ID"));
        //symbols.setCurrencySymbol("Rp");
        symbols.setMonetaryDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        
        DecimalFormat rupiahFormat = new DecimalFormat(" #,##0.00", symbols);
        return rupiahFormat.format(amount);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Lihat Penjualan
    
    public void tampilkanNC() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try {
            String sql = "SELECT count(id_transaksi) FROM transaksi WHERE DATE(tanggal_transaksi)=?";
            int nc = 0;
            pre = connn.prepareStatement(sql);
            pre.setString(1, sqlDate.toString());
            r= pre.executeQuery();
            
            if (r.next()) {
                nc = r.getInt(1);
            }
            r.close();
            pre.close();
            lb_nc.setText(String.valueOf(nc));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void tampilkanTodayI() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
 
        try {
            double ti = 0;
            String sql = "SELECT SUM(total_harga) FROM `transaksi` WHERE DATE(tanggal_transaksi)=?";
            pre = connn.prepareStatement(sql);
            pre.setString(1, sqlDate.toString());
            r = pre.executeQuery();
            
            if (r.next()) {
                ti = r.getDouble(1);
            }
            r.close();
            pre.close();
            lb_ti.setText("Rp. " + formatUang(ti));
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tampilkanNSP() {   
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try {
            int q = 0;
            String sql = "SELECT SUM(detail_transaksi.kuantitas) FROM detail_transaksi \n" +
                            "JOIN transaksi on transaksi.id_transaksi=detail_transaksi.id_transaksi\n" +
                            "where DATE(transaksi.tanggal_transaksi)=?;";
            pre = connn.prepareStatement(sql);
            pre.setString(1, sqlDate.toString());
            r = pre.executeQuery();
            
            if (r.next()) {
                q = r.getInt(1);
            }
            r.close();
            pre.close();
            lb_numberSold.setText(String.valueOf(q));
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void showListTanggall() {
        
        List<String> listTanggall = new ArrayList<>();
       try{ 
        String sql = "SELECT DATE_FORMAT(tanggal_transaksi, '%M-%Y') AS bulan_tahun\n" +
                        "FROM transaksi\n" +
                        "GROUP BY DATE_FORMAT(tanggal_transaksi, '%M-%Y')\n" +
                        "ORDER BY bulan_tahun DESC;";
            pre= connn.prepareStatement(sql);
            r = pre.executeQuery();
            
         while (r.next()) {
                listTanggall.add(r.getString(1));
            }   
         r.close();
            pre.close();
       }catch(SQLException e){
           
       }
        ObservableList lBulanTahun = FXCollections.observableArrayList(listTanggall);
        lihatPenjualan_cbBulan.setItems(lBulanTahun);
    }
    
public String tangg(){
    Date now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("MMMM-YYYY");
    String tangggal = formatter.format(now);
    return tangggal;
    }

public void tampilkanChartCustomer(){
        
        chartBar_customer.getData().clear();
        String abc = String.valueOf(lihatPenjualan_cbBulan.getSelectionModel().getSelectedItem());
        chartBar_customer.setCategoryGap(5);
        
        XYChart.Series<String, Number> chart1 = new XYChart.Series();
        chart1.setName("Cash");
        
        XYChart.Series<String, Number> chart2 = new XYChart.Series();
        chart2.setName("Qris");
        
        chartBar_customer.setSnapToPixel(false);
        chartBar_customer.getParent().setStyle("-fx-background-color: transparent;");

        try {
            String sql = "SELECT DATE(tanggal_transaksi), SUM(total_harga)FROM transaksi "
                    + "where DATE_FORMAT(tanggal_transaksi, '%M-%Y')=? AND metode='CASH' "
                    + "GROUP by DATE(tanggal_transaksi) order by DATE(tanggal_transaksi)  ";
            pre= connn.prepareStatement(sql);
            pre.setString(1, abc);
            r = pre.executeQuery();
            while (r.next()) {
                chart1.getData().add(new XYChart.Data<>(r.getString(1), r.getInt(2)));
            }
            r.close();
            pre.close();

            String sql2 = "SELECT DATE(tanggal_transaksi), SUM(total_harga)FROM transaksi "
                    + "where DATE_FORMAT(tanggal_transaksi, '%M-%Y')=? AND metode='QRIS' "
                    + "GROUP by DATE(tanggal_transaksi) order by DATE(tanggal_transaksi)  ";
            pre = connn.prepareStatement(sql2);
            pre.setString(1, abc);
            r = pre.executeQuery();
            while (r.next()) {
                chart2.getData().add(new XYChart.Data<String, Number>(r.getString(1), r.getDouble(2)));    
            }
            r.close();
            pre.close();
            chartBar_customer.getData().addAll(chart1,chart2);
            for (XYChart.Data<String, Number> dataa : chart1.getData()) {
                setupHoverTooltip(dataa);
            }
           for (XYChart.Data<String, Number> dataa : chart2.getData()) {
                setupHoverTooltip(dataa);
            } 

        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Gagal Membuat Inchome Chart");
            alert.showAndWait();
            alert.close();
            e.printStackTrace();
        }
    }
    
 private void setupHoverTooltip(XYChart.Data<String, Number> data) {
    Node bar = data.getNode();
    if (bar != null) {
        Tooltip tooltip = new Tooltip(
            "Tanggal: " + data.getXValue() + "\n" +
             "Pemasukan : Rp. "+formatUang(data.getYValue().doubleValue())
        );
        //tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_LEFT);
        
        Tooltip.install(bar, tooltip);
   
        bar.setOnMouseMoved(e -> {
            tooltip.show(bar, e.getScreenX() + 10, e.getScreenY() + 10);
        });
        bar.setOnMouseExited(e -> {
            tooltip.hide();
        });
    }
}
 
 public Double totalPerB(){
     Double a=0.0;
     try{
         String abc = String.valueOf(lihatPenjualan_cbBulan.getSelectionModel().getSelectedItem());
         String sql = "SELECT SUM(total_harga)FROM transaksi where DATE_FORMAT(tanggal_transaksi, '%M-%Y')=?";
            pre= connn.prepareStatement(sql);
            pre.setString(1, abc);
            r = pre.executeQuery();
            
            while (r.next()) {
                a+=r.getDouble(1);
            }
            r.close();
            pre.close();
            return a;
     }catch(SQLException e){
         e.printStackTrace();
     }
     return a;
 }
 /////////////////////////////////////////Btn Laporan   
 
 
   public void showListTanggalBtnLaporan() {
        
        List<String> listTanggal123 = new ArrayList<>();
       try{ 
        String sql = "SELECT DATE(tanggal_transaksi) FROM transaksi GROUP BY DATE(tanggal_transaksi) ORDER BY DATE(tanggal_transaksi) DESC";
            pre= connn.prepareStatement(sql);
            r = pre.executeQuery();
            
         while (r.next()) {
                listTanggal123.add(r.getString(1));
            }   
         r.close();
         pre.close();
       }catch(SQLException e){
           e.printStackTrace();
       }
        ObservableList tanggal123 = FXCollections.observableArrayList(listTanggal123);
        lihatPenjualan_cbTanggal.setItems(tanggal123);
    }
   
   private ObservableList<laporanMenu> l = FXCollections.observableArrayList();
   private ObservableList<laporanMenu> List = FXCollections.observableArrayList();
   public void laporanMenuList() {
       List.clear();
        String tan=lihatPenjualan_cbTanggal.getSelectionModel().getSelectedItem();
        try {
            //String sql = "SELECT `id_users`, `username`, `password`, `level`, `nama_asli` FROM `users`;";
            String sql="SELECT menu.id_menu, menu.nama_menu, menu.ukuran_menu, SUM(detail_transaksi.kuantitas), "
                    + "SUM(detail_transaksi.subtotal)\n" +
                            "FROM menu JOIN detail_transaksi ON menu.id_menu=detail_transaksi.id_menu\n" +
                            "JOIN transaksi ON detail_transaksi.id_transaksi=transaksi.id_transaksi\n" +
                            "where DATE(transaksi.tanggal_transaksi)=?\n" +
                            "GROUP BY detail_transaksi.id_menu\n" +
                            "ORDER BY menu.nama_menu ASC;";
            pre = connn.prepareStatement(sql);
            pre.setString(1, tan);
            r = pre.executeQuery();    

            while (r.next()) {
                List.add(new laporanMenu(r.getInt(1),
                        r.getString(2),
                        r.getString(3),
                        r.getInt(4),
                        r.getDouble(5)));
            }
             l.setAll(List);
            r.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        laporanMenuShow();
        tx_Tdijual.setText("Total Dijual : " +totalDijual());
        tx_Tpendapatan.setText("Total Rp. "+formatUang(totalPendapatanPerTanggal()));
    }
    

   public void laporanMenuCari() {
     String aaa=lihatPenjualan_txCari.getText();
     if (aaa == null || aaa.isEmpty()) {
        l.setAll(List);
    } else {
     l.setAll(
            List.stream()
                .filter(menu -> menu.getNamaMenu().toLowerCase().contains(aaa.toLowerCase()))
                .collect(Collectors.toList())
        );
     laporanMenuShow();
}
}
    public void laporanMenuShow() {
        lihatPenjualan_kolomP.setStyle("-fx-alignment: CENTER-LEFT;");
         lihatPenjualan_kolomId.setStyle("-fx-alignment: CENTER;");
          lihatPenjualan_kolomJumlah.setStyle("-fx-alignment: CENTER;");
         
        lihatPenjualan_kolomId.setCellValueFactory(cellData -> cellData.getValue().idMenuProperty().asObject());
        lihatPenjualan_kolomNama.setCellValueFactory(cellData -> cellData.getValue().namaMenuProperty());
        lihatPenjualan_kolomUk.setCellValueFactory(cellData -> cellData.getValue().ukMenuProperty());
        lihatPenjualan_kolomJumlah.setCellValueFactory(cellData -> cellData.getValue().jDibeliProperty().asObject());
        //lihatPenjualan_kolomP.setCellValueFactory(cellData -> cellData.getValue().jPendapatanProperty().asObject());
        lihatPenjualan_kolomP.setCellValueFactory(cellData -> cellData.getValue().pFormatProperty());
        kolom_no.setCellValueFactory(param -> 
    new ReadOnlyObjectWrapper<>(param.getTableView().getItems().indexOf(param.getValue()) + 1));
        kolom_no.setSortable(false);
    
        lihatPenjualan_tb.setItems(l);
    }    
   
    public String tangg123(){
    Date now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String tangggal = formatter.format(now);
    return tangggal;
    }
    
    Integer i=0;
    public void btnLaporan(){
        i++;
        if(i==1){
            lihatPenjualan_StkPane.setVisible(true);
            lihatPenjualan_btnLaporan.setStyle("-fx-background-color: #0c52a6");
           showListTanggalBtnLaporan();
            lihatPenjualan_cbTanggal.setValue(tangg123());
            laporanMenuList();
        }else if(i==2){
            lihatPenjualan_StkPane.setVisible(false);
            lihatPenjualan_btnLaporan.setStyle("-fx-background-color: #92b0d4");
            i=0;
        }
    }
    
     public Integer totalDijual() {
        Integer abcc=0;
        for(laporanMenu item : List){
            abcc+=item.getJDibeli();
        }
        return abcc;
}
    public Double totalPendapatanPerTanggal() {
        Double abcc=0.0;
        for(laporanMenu item : List){
            abcc+=item.getJPendapatan();
        }
        return abcc;
}
    /*

    
    
    
*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Kelola User
        
    public ObservableList<dataUser> userDataList() {
        ObservableList<dataUser> listUser = FXCollections.observableArrayList();
        String carii=tx_cariUser.getText();
        try {
            //String sql = "SELECT `id_users`, `username`, `password`, `level`, `nama_asli` FROM `users`;";
            String sql="SELECT * FROM `users` WHERE id_users LIKE ? OR username LIKE ? OR level LIKE ? OR nama_asli LIKE ?;";
            pre = databes.konek().prepareStatement(sql);
            pre.setString(1, "%"+carii+"%");
            pre.setString(2, "%"+carii+"%");
            pre.setString(3, "%"+carii+"%");
            pre.setString(4, "%"+carii+"%");
            r = pre.executeQuery();    

            while (r.next()) {
                listUser.add(new dataUser(r.getInt("id_users"),
                        r.getString("username"),
                        r.getString("password"),
                        r.getString("level"),
                        r.getString("nama_asli")));
            }
            r.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }
    

    public void userDataShow() {
        kolom_idUser.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        kolom_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        kolom_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        kolom_posisi.setCellValueFactory(new PropertyValueFactory<>("level"));
        kolom_namaAsli.setCellValueFactory(new PropertyValueFactory<>("nama_asli"));
        
        tb_kelolaUser.setItems(userDataList());
    }    
    
    private String[] ListPosisi = {"Admin", "Kasir", "Owner", "Finance"};
    public void showListLevel() {
        
        List<String> lev = new ArrayList<>();
        
        lev.addAll(Arrays.asList(ListPosisi));
        
        ObservableList Level = FXCollections.observableArrayList(lev);
        cb_posisi.setItems(Level);
    }
    
    
    
     public void btnAdd() {       
        if (tx_idUser.getText().isEmpty()
                || tx_username.getText().isEmpty()
                || tx_password.getText().isEmpty()
                ||tx_namaAsli.getText().isEmpty()
                || cb_posisi.getSelectionModel().getSelectedItem() == null) {
            
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Semua Data Harus Diisi !");
            alert.showAndWait(); 
        } else {
            try {
                String sql1 = "SELECT `id_users` FROM `users` WHERE id_users=?;";
                 pre = connn.prepareStatement(sql1);
                 pre.setString(1, tx_idUser.getText());
                 r = pre.executeQuery(); 
                if (r.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("ID : "+tx_idUser.getText() + " sudah terpakai");
                    alert.showAndWait();
                } else {
                    sql1 = "SELECT `username` FROM `users` WHERE username=?;";
                    pre = connn.prepareStatement(sql1);
                    pre.setString(1, tx_username.getText());
                    r = pre.executeQuery();
                    if(r.next()){
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Username : "+tx_username.getText() + " sudah terpakai");
                        alert.showAndWait();
                    }else{
                        String sql = "INSERT INTO `users`(`id_users`, `username`, `password`, `level`, `nama_asli`) VALUES (?,?,?,?,?);";
                        pre = connn.prepareStatement(sql);
                        pre.setString(1, tx_idUser.getText());
                        pre.setString(2, tx_username.getText());
                        pre.setString(3, FXMLDocumentController.passhash(tx_password.getText()));
                        pre.setString(4, cb_posisi.getSelectionModel().getSelectedItem());
                        pre.setString(5, tx_namaAsli.getText());
                        //r = pre.executeQuery();
                        pre.executeUpdate();


                        loggg(dataData.getId_userLogin(), "Menambah user baru dengan ID: "+tx_idUser.getText());
                        loggg(dataData.getId_userLogin(),"ID: "+tx_idUser.getText()+", Username: "+tx_username.getText()+", Password: "+FXMLDocumentController.passhash(tx_password.getText()).substring(0, 5).concat("***")+", Posisi: "+cb_posisi.getSelectionModel().getSelectedItem()+", Nama Asli: "+tx_namaAsli.getText());
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Added!");
                        alert.showAndWait();

                        userDataShow();
                       btnClear();
                    }
                }
                    
                r.close();
                pre.close();
            } catch (SQLException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Gagal Menambah User");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
    
    public void btnUpdate() {
        Integer idLama=dataData.getId_user();
        if (tx_idUser.getText().isEmpty()
                || tx_username.getText().isEmpty()
                || tx_password.getText().isEmpty()
                ||tx_namaAsli.getText().isEmpty()
                || cb_posisi.getSelectionModel().getSelectedItem() == null) {
            
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Data Kurang Lengkap");
            alert.setContentText("Isi Semua Data !");
            alert.showAndWait();
        } else {
            try {
                String sql1 = "SELECT `id_users` FROM `users` WHERE id_users=?;";
                 pre = connn.prepareStatement(sql1);
                 pre.setString(1, tx_idUser.getText());
                 r = pre.executeQuery(); 
                if (!r.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("ID : "+tx_idUser.getText() + " Tidak Ditemukan");
                    alert.showAndWait();
                }else{
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Konfirmasi Update Data ?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {
                        String sql = "UPDATE `users` SET `username`=?,`password`=?,`level`=?,`nama_asli`=? WHERE id_users=? ";
                        pre = connn.prepareStatement(sql);
                        pre.setString(1, tx_username.getText());

                        if(tx_password.getText().equals(dataData.getPass())){
                            pre.setString(2, tx_password.getText());
                        }
                        else{
                            pre.setString(2, FXMLDocumentController.passhash(tx_password.getText()));
                        }
                        pre.setString(3, cb_posisi.getSelectionModel().getSelectedItem().toString());
                        pre.setString(4, tx_namaAsli.getText());
                        pre.setString(5, tx_idUser.getText());
                        pre.executeUpdate();

                        loggg(dataData.getId_userLogin(), "Mengubah data dari user dengan ID: "+tx_idUser.getText());
                        loggg(dataData.getId_userLogin(), "Lama (ID: "+dataData.getId_user().toString()+", Username: "+dataData.getUsername()+", Password: "+dataData.getPass().substring(0, 5).concat("***")+", Posisi: "+dataData.getLevel()+", Nama Asli: "+dataData.getNamaAsli());
                        loggg(dataData.getId_userLogin(),"Baru (ID: "+tx_idUser.getText()+", Username: "+tx_username.getText()+", Password: "+FXMLDocumentController.passhash(tx_password.getText()).substring(0, 5).concat("***")+", Posisi: "+cb_posisi.getSelectionModel().getSelectedItem()+", Nama Asli: "+tx_namaAsli.getText());
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Updated!");
                        alert.showAndWait();

                        userDataShow();
                       btnClear();
                    }else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Cancelled.");
                        alert.showAndWait();
                }
                } 
                pre.close();
            } catch (Exception e) {
                alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Gagal Update Data User.");
                    alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
    
    
public void btnDelete() {
        if (dataData.getId_user() == 0 || dataData.getId_user() == null) {
            
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Data Kurang Lengkap");
            alert.setContentText("Masukkan ID User yang akan di hapus");
            alert.showAndWait();
            
        } else {
            
            try{
                String sql1 = "SELECT id_users FROM `users` WHERE level='Admin';";
                    pre = connn.prepareStatement(sql1);
                    r = pre.executeQuery();
                    int aa=0;
                    while(r.next()){
                        aa++;
                    }
                if(dataData.getLevel().equalsIgnoreCase("admin") && aa==1){
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Gagal Menghapus User");
                        alert.setHeaderText(null);
                        alert.setContentText("User Admin setidaknya harus ada 1");
                        alert.showAndWait();
                }else{          
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Yakin Mau Hapus Data");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {
                        String sql = "DELETE FROM `users` WHERE id_users=?;";
                        try {
                            pre = connn.prepareStatement(sql);
                            pre.setString(1, dataData.getId_user().toString());
                            pre.executeUpdate();

                            loggg(dataData.getId_userLogin(), "Menghapus user dengan ID: "+tx_idUser.getText());
                            loggg(dataData.getId_userLogin(),"ID: "+ dataData.getId_user().toString()+"  Username: "+dataData.getUsername()+", Password: "+dataData.getPass().substring(0, 5).concat("***")+"  Posisi: "+dataData.getLevel()+"  Nama asli: "+dataData.getNamaAsli());
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle(null);
                            alert.setHeaderText(null);
                            alert.setContentText("successfully Deleted!");
                            alert.showAndWait();

                            userDataShow();
                           btnClear();

                            pre.close();
                        } catch (SQLException e) {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Gagal Menghapus Menu.");
                            alert.showAndWait();
                            e.printStackTrace();
                        }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled");
                    alert.showAndWait();
                }
                } 
            }catch(SQLException e){
                e.printStackTrace();
            }
            
            
            
        }
    }
    
    
  /* 
    
  */  
    public void btnClear() {   
        tx_idUser.setText(idUserr().toString());
        tx_username.setText("");
        tx_password.setText("");
        tx_namaAsli.setText("");
        cb_posisi.getSelectionModel().clearSelection();
        dataData.setId_user(Integer.parseInt(tx_idUser.getText()));
        dataData.setUsername("");
        dataData.setPass("");
        dataData.setLevel("");
        dataData.setNamaAsli("");
    }
    
    public void SelectData() {
        
        dataUser userdata = tb_kelolaUser.getSelectionModel().getSelectedItem();
        int num = tb_kelolaUser.getSelectionModel().getSelectedIndex();
        
        if ((num - 1) < -1) {
            return;
        }
        dataData.setId_user(userdata.getId_user());
        dataData.setUsername(userdata.getUsername());
        dataData.setPass(userdata.getPassword());
        dataData.setLevel(userdata.getLevel());
        dataData.setNamaAsli(userdata.getNama_asli());
        tx_idUser.setText(String.valueOf(userdata.getId_user()));
        tx_username.setText(userdata.getUsername());
        tx_password.setText(userdata.getPassword());
        tx_namaAsli.setText(userdata.getNama_asli());

        cb_posisi.setValue(userdata.getLevel());
    }
    
    public Integer idUserr(){
            Integer iddd=0;
            try{
            String sql = "SELECT id_users FROM users order by id_users DESC limit 1";
            pre=connn.prepareStatement(sql);
            ResultSet r = pre.executeQuery();
            if (r.next()) {
                iddd = r.getInt(1)+1;
            }else{
                iddd=(1);
            }
            r.close();
            pre.close();
        }catch(SQLException e){
            System.out.println("autonumber err");
        }
       return iddd;     
 }
     
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////kelola Menu

     public ObservableList<dataMenu> listMenu() {
        ObservableList<dataMenu> list = FXCollections.observableArrayList();
        String carii=tx_cariMenu.getText();
        try {
            //String sql = "SELECT `id_menu`, `nama_menu`, `harga`, `stok`, `kategori`, `ukuran_menu`, `gambar` FROM `menu`;"
            String sql = "SELECT `id_menu`, `nama_menu`, `harga`, `stok`, `kategori`, `ukuran_menu`, `gambar` "
                    + "FROM `menu`where nama_menu LIKE ? OR id_menu LIKE ?;";
            pre = databes.konek().prepareStatement(sql);
            pre.setString(1, "%"+carii+"%");
            pre.setString(2, "%"+carii+"%");
            r = pre.executeQuery();    

            while (r.next()) {
                list.add(new dataMenu(r.getInt(1),
                        r.getString(2),
                        r.getDouble(3),
                        r.getInt(4),
                        r.getString(5),
                        r.getString(6),
                        r.getBytes(7)));
            }
            r.close();
          pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void menuDataShow() {
        kolom_idMenu.setCellValueFactory(new PropertyValueFactory<>("id_menu"));
        kolom_namaMenu.setCellValueFactory(new PropertyValueFactory<>("nama_menu"));
        kolom_ukuranMenu.setCellValueFactory(new PropertyValueFactory<>("ukuran_menu"));
        kolom_kategoriMenu.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        kolom_hargaMenu.setCellValueFactory(new PropertyValueFactory<>("harga"));
        kolom_stokMenu.setCellValueFactory(new PropertyValueFactory<>("stok"));
        
        tb_kelolaMenu.setItems(listMenu());
    }    
    
    public void btnImportGambar(){
        FileChooser fileChooser =new FileChooser(); 
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("File Gambar (*.jpg, *.jpeg, *.png)", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(filter);
        
        File selectedImg = fileChooser.showOpenDialog(null);
        
         try { 
             String path = selectedImg.getAbsolutePath();
              IV_gambarMenu.setImage(new Image("file:///"+path));
              dataData.setGambar(path);
                
       } catch (Exception ex) { 
              ex.printStackTrace();
        }
    }
    
 
 public byte[] compressImage(File imageFile, float quality) throws IOException {
    // Baca gambar asli
    BufferedImage image = ImageIO.read(imageFile);
   // Dapatkan ImageWriter untuk format JPEG
    ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
    
    // Setup parameter kompresi
    ImageWriteParam param = writer.getDefaultWriteParam();
    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    param.setCompressionQuality(quality);
    
    // Konversi ke byte array dengan kompresi
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (ImageOutputStream ios = ImageIO.createImageOutputStream(baos)) {
        writer.setOutput(ios);
        writer.write(null, new IIOImage(image, null, null), param);
    } finally {
        writer.dispose();
    }
    
    return baos.toByteArray();
}

 
     public void btnAddMenu() {
        if (tx_idMenu.getText().isEmpty()
                || tx_namaMenu.getText().isEmpty()
                || tx_ukuranMenu.getText().isEmpty()
                || tx_kategoriMenu.getText().isEmpty()
                || tx_hargaMenu.getText().isEmpty()
                || tx_stokMenu.getText().isEmpty()
                || dataData.getGambar().isEmpty()
                ) {
            
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Semua Data Harus Diisi !");
            alert.showAndWait();
            
        } else {
            String a= tx_idMenu.getText();
            String b=tx_namaMenu.getText();
            String c = tx_ukuranMenu.getText();
            String d=tx_kategoriMenu.getText();
            String ee="0";
            if(!tx_hargaMenu.getText().replaceAll("[^0-9]", "").isEmpty()){
                ee=tx_hargaMenu.getText().replaceAll("[^0-9]", "");
            }
            String f="0";
            if(!tx_stokMenu.getText().replaceAll("[^0-9]", "").isEmpty()){
                f=tx_stokMenu.getText().replaceAll("[^0-9]", "");
            }
             try {
                 String sql1 = "SELECT `id_menu` FROM `menu` WHERE id_menu=?;";
                 pre = connn.prepareStatement(sql1);
                 pre.setString(1, tx_idMenu.getText());
                 r = pre.executeQuery();

                 if (r.next()) {
                     alert = new Alert(Alert.AlertType.ERROR);
                     alert.setTitle(null);
                     alert.setHeaderText(null);
                     alert.setContentText(tx_idMenu.getText() + " sudah terpakai");
                     alert.showAndWait();
                 } else {
                     String sql = "INSERT INTO menu (id_menu, nama_menu, harga, stok, kategori, ukuran_menu, gambar) VALUES (?,?,?,?,?,?,?);";
                     pre = connn.prepareStatement(sql);
                     pre.setString(1, a);
                     pre.setString(2, b);
                     pre.setDouble(3, Double.parseDouble(ee));
                     pre.setInt(4, Integer.parseInt(f));
                     pre.setString(5, d);
                     pre.setString(6, c);

                     try {
                         File imageFile = new File(dataData.getGambar());
                         //FileInputStream fis = new FileInputStream(imageFile);
                         //byte[] imageData = new byte[(int) imageFile.length()];
                         byte[] imageData = compressImage(imageFile, 0.4f);
                         //fis.read(imageData);

                         pre.setBytes(7, imageData);
                         //fis.close();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }

                     pre.executeUpdate();

                     loggg(dataData.getId_userLogin(), "Menambah menu baru dengan ID: " + tx_idMenu.getText());
                     loggg(dataData.getId_userLogin(), "ID: " +a+", Nama Menu: "+b+", Ukuran: "+c+", Kategori :"+d+", Harga: "+ee+", Stok: "+ f);
                     alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle(null);
                     alert.setHeaderText(null);
                     alert.setContentText("Successfully Added!");
                     alert.showAndWait();

                     btnClearMenu();
                     menuDataShow();

                 }
                 r.close();
                  pre.close();
             } catch (Exception e) {
                 alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Gagal Menambah Menu");
                alert.showAndWait();
                e.printStackTrace();
                 e.printStackTrace();
             }
         }
    }
    
    public void btnUpdateMenu() {
        Integer idLama=dataData.getId_menu();
        if (tx_idMenu.getText().isEmpty()
                || tx_namaMenu.getText().isEmpty()
                || tx_ukuranMenu.getText().isEmpty()
                || tx_kategoriMenu.getText().isEmpty()
                || tx_hargaMenu.getText().isEmpty()
                || tx_stokMenu.getText().isEmpty()) {
            
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Data Kurang Lengkap");
            alert.setContentText("Isi Semua Data !");
            alert.showAndWait();
            
        } else {
            try {    
                String sql1 = "SELECT `id_menu` FROM `menu` WHERE id_menu=?;";
                 pre = connn.prepareStatement(sql1);
                 pre.setString(1, tx_idMenu.getText());
                 r = pre.executeQuery(); 
                if (!r.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("ID : "+tx_idMenu.getText() + " Tidak Ditemukan");
                    alert.showAndWait();
                }else{
                String a= tx_idMenu.getText();
            String b=tx_namaMenu.getText();
            String c = tx_ukuranMenu.getText();
            String d=tx_kategoriMenu.getText();
            String ee="0";
            if(!tx_hargaMenu.getText().replaceAll("[^0-9]", "").isEmpty()){
                ee=tx_hargaMenu.getText().replaceAll("[^0-9]", "");
            }
            String f="0";
            if(!tx_stokMenu.getText().replaceAll("[^0-9]", "").isEmpty()){
                f=tx_stokMenu.getText().replaceAll("[^0-9]", "");
            }
             
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Konfirmasi Update Data Menu ?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if (option.get().equals(ButtonType.OK)) {
                    String sql = "UPDATE menu SET id_menu=?, nama_menu=?, harga=?, stok=?, kategori=?, ukuran_menu=? ";
                    if(!dataData.getGambar().isEmpty()){
                        sql += ", gambar=? ";
                    }
                        sql += "WHERE id_menu=?";
                        
                    pre = connn.prepareStatement(sql);
                    pre.setString(1, a);
                    pre.setString(2, b);
                    pre.setDouble(3, Double.parseDouble(ee));
                    pre.setInt(4, Integer.parseInt(f));
                    pre.setString(5, d);
                    pre.setString(6, c);  
                        if (!dataData.getGambar().isEmpty()) { 
                        try{
                            /*
                            File imageFile = new File(dataData.getGambar()); 
                            FileInputStream fis = new FileInputStream(imageFile); 
                            byte[] imageData = new byte[(int)imageFile.length()]; 
                            fis.read(imageData); 
                            */
                            File imageFile = new File(dataData.getGambar());
                         //FileInputStream fis = new FileInputStream(imageFile);
                         //byte[] imageData = new byte[(int) imageFile.length()];
                         byte[] imageData = compressImage(imageFile, 0.4f);
                         //fis.read(imageData);
                            pre.setBytes(7, imageData); 
                            pre.setString(8, String.valueOf(idLama));
                            //fis.close(); 
                        }
                        catch(IOException e){
                             e.printStackTrace();}
                        } else { 
                        pre.setString(7, String.valueOf(idLama));
                    }   
                    pre.executeUpdate();
                    /*
                    String sql2 = "UPDATE menu SET stok=? WHERE nama_menu=?";
                    pre = connn.prepareStatement(sql2);
                    pre.setString(1, tx_stokMenu.getText());
                    pre.setString(2, tx_namaMenu.getText());
                    pre.executeUpdate();
                    */
                    loggg(dataData.getId_userLogin(), "Mengedit menu "+tx_idMenu.getText());
                    loggg(dataData.getId_userLogin(), "Lama( ID: " +a+", Nama Menu: "+dataData.getNama_menu()+", Ukuran: "+dataData.getUkuran_menu()+", Kategori :"+dataData.getKategori()+", Harga: "+dataData.getHarga()+", Stok: "+ dataData.getStok()+" )");
                    loggg(dataData.getId_userLogin(), "Baru( ID: " + a+", Nama Menu: "+b+", Ukuran: "+c+", Kategori :"+d+", Harga: "+ee+", Stok: "+ f+" )");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    btnClearMenu();
                    menuDataShow();
                   
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
                }
                r.close();
                pre.close();
            } catch (SQLException e){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Gagal Update Data Menu");
                alert.showAndWait();
                e.printStackTrace();
            }   
        }
    }
    
    public void btnDeleteMenu() {
        if (dataData.getId_menu() == 0 || dataData.getId_menu() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Data Kurang Lengkap");
            alert.setContentText("Masukkan ID Menu yang akan di hapus");
            alert.showAndWait();     
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Yakin Mau Hapus Data Menu ?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                String sql = "DELETE FROM `menu` WHERE id_menu=?;";
                try {
                    pre = connn.prepareStatement(sql);
                    pre.setString(1, dataData.getId_menu().toString());
                    pre.executeUpdate();
                    
                    loggg(dataData.getId_userLogin(), "Menghapus menu "+tx_idMenu.getText());
                    loggg(dataData.getId_userLogin(), "ID: " + tx_idMenu.getText()+", Nama Menu: "+dataData.getNama_menu()+", Ukuran: "+dataData.getUkuran_menu()+", Kategori :"+dataData.getKategori()+", Harga: "+dataData.getHarga()+", Stok: "+ dataData.getStok());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Deleted!");
                    alert.showAndWait();
                    btnClearMenu();
                    menuDataShow();
                    r.close();
                    pre.close();
                } catch (Exception e) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Gagal Menghapus Menu");
                    alert.showAndWait();
                    e.printStackTrace();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
        }
    }
    
    public void btnClearMenu() {   
        tx_idMenu.setText(idMenu().toString());
        tx_namaMenu.setText("");
        tx_ukuranMenu.setText("");
       tx_kategoriMenu.setText("");
        tx_hargaMenu.setText("");
        tx_stokMenu.setText("");
        IV_gambarMenu.setImage(null);
        dataData.setId_menu(null);
        dataData.setGambar("");
    }
    
public void selectDataMenu() {
        dataMenu menudata = tb_kelolaMenu.getSelectionModel().getSelectedItem();
        int num = tb_kelolaMenu.getSelectionModel().getSelectedIndex();
        if (num<0) {
            return;
        }
        dataData.setId_menu(menudata.getId_menu());
        dataData.setNama_menu(menudata.getNama_menu());
        dataData.setUkuran_menu(menudata.getUkuran_menu());
        dataData.setKategori(menudata.getKategori());
        dataData.setHarga(menudata.getHarga());
        dataData.setStok(menudata.getStok());
        
        tx_idMenu.setText(String.valueOf(menudata.getId_menu()));
        tx_namaMenu.setText(menudata.getNama_menu());
        tx_ukuranMenu.setText(menudata.getUkuran_menu());
       tx_kategoriMenu.setText(menudata.getKategori());
        tx_hargaMenu.setText(String.valueOf(menudata.getHarga().intValue()));
        tx_stokMenu.setText(String.valueOf(menudata.getStok()));

            ByteArrayInputStream inputStream = new ByteArrayInputStream(menudata.getGambar());
            Image image = new Image(inputStream);
            IV_gambarMenu.setImage(image);
    }

    public Integer idMenu(){
            Integer iddd=0;
            try{
            String sql = "SELECT id_menu FROM menu order by id_menu DESC limit 1";
            pre=connn.prepareStatement(sql);
            ResultSet r = pre.executeQuery();
            if (r.next()) {
                iddd = r.getInt(1)+1;
            }else{
                iddd=(1);
            }
            r.close();
            pre.close();
        }catch(Exception e){
            System.out.println("autonumber err");
        }
       return iddd;     
 }
    
    
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Kelola Wifi
    public Integer idWifi(){
            Integer iddd=0;
            try{
            String sql = "SELECT id_wifi FROM wifi order by id_wifi DESC limit 1";
            pre=connn.prepareStatement(sql);
            ResultSet r = pre.executeQuery();
            if (r.next()) {
                iddd = r.getInt(1)+1;
            }else{
                iddd=(1);
            }
            r.close();
            pre.close();
        }catch(Exception e){
            System.out.println("autonumber err");
        }
       return iddd;     
 }
        
    public ObservableList<dataWifi> wifiDataList() {
        ObservableList<dataWifi> listWifi = FXCollections.observableArrayList();
        String carii=tx_cariWifi.getText();
        try {
            //String sql = "SELECT `id_users`, `username`, `password`, `level`, `nama_asli` FROM `users`;";
            String sql="SELECT * FROM wifi WHERE nama_wifi LIKE ?;";
            pre = databes.konek().prepareStatement(sql);
            pre.setString(1, "%"+carii+"%");
            r = pre.executeQuery();    

            while (r.next()) {
                listWifi.add(new dataWifi(r.getInt(1),
                        r.getString(2),
                        r.getString(3)));
            }
            r.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listWifi;
    }
    

    public void wifiDataShow() {
        kolom_noWifi.setCellValueFactory(param -> 
    new ReadOnlyObjectWrapper<>(param.getTableView().getItems().indexOf(param.getValue()) + 1));
        kolom_noWifi.setSortable(false);
        
        kolom_namaWifi.setCellValueFactory(new PropertyValueFactory<>("namaWifi"));
        kolom_passWifi.setCellValueFactory(new PropertyValueFactory<>("passWifi"));
        
        tb_kelolaWifi.setItems(wifiDataList());
    }    

     public void btnAddWifi() {       
        if (tx_namaWifi.getText().isEmpty()
                || tx_passWifi.getText().isEmpty()) {
            
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Semua Data Harus Diisi !");
            alert.showAndWait(); 
        } else {
            try {
                String sql1 = "SELECT `id_wifi` FROM `wifi` WHERE id_wifi=?;";

                 pre = connn.prepareStatement(sql1);
                 pre.setInt(1, dataData.getId_wifi());
                 r = pre.executeQuery();

                 if (r.next()) {
                     alert = new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("Tambah Wifi Gagal");
                     alert.setHeaderText(null);
                     alert.setContentText("coba tekan button clear terlebih dahulu, lalu coba lagi");
                     alert.showAndWait();
                 } else {
                    int id=idWifi();
                    String sql = "INSERT INTO `wifi`(`id_wifi`, `nama_wifi`, `pass_wifi`) VALUES (?,?,?);";
                    pre = connn.prepareStatement(sql);
                    pre.setInt(1, id);
                    pre.setString(2, tx_namaWifi.getText());
                    pre.setString(3, tx_passWifi.getText());
                    //r = pre.executeQuery();
                    pre.executeUpdate();


                    loggg(dataData.getId_userLogin(), "Menambah wifi baru");
                    loggg(dataData.getId_userLogin(),"nama wifi: "+tx_namaWifi.getText()+", password: "+tx_passWifi.getText());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    wifiDataShow();
                   btnClearWifi();
                 }
                }catch(SQLException e){
               alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Gagal Menambah Wifi Baru");
                    alert.showAndWait();
                    e.printStackTrace();
            }

        }
 }
    
    public void btnUpdateWifi() {
        if (tx_namaWifi.getText().isEmpty()
                || tx_passWifi.getText().isEmpty()) {
            
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Data Kurang Lengkap");
            alert.setContentText("Isi Semua Data !");
            alert.showAndWait();
        } else {
            try {
                String sql1 = "SELECT `id_wifi` FROM `wifi` WHERE id_wifi=?;";
                 pre = connn.prepareStatement(sql1);
                 pre.setInt(1, dataData.getId_wifi());
                 r = pre.executeQuery(); 
                if (!r.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Gagal Memperbarui Data");
                    alert.setHeaderText(null);
                    alert.setContentText("Pilih Wifi dari tabel terlebih dahulu, lalu coba lagi");
                    alert.showAndWait();
                }else{
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Konfirmasi Update Data ?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {
                        int id=dataData.getId_wifi() ;
                        String sql = "UPDATE `wifi` SET `nama_wifi`=?,`pass_wifi`=? WHERE id_wifi=? ";
                        pre = connn.prepareStatement(sql);
                        pre.setString(1, tx_namaWifi.getText());
                        pre.setString(2, tx_passWifi.getText());
                        pre.setInt(3, id);
                        pre.executeUpdate();

                        loggg(dataData.getId_userLogin(), "Mengubah data wifi");
                        loggg(dataData.getId_userLogin(), "Lama (Nama: "+dataData.getNama_wifi()+", Password: "+dataData.getPass_wifi());
                        loggg(dataData.getId_userLogin(),"Baru (Nama: "+tx_namaWifi.getText()+", Password: "+tx_passWifi.getText());
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Updated!");
                        alert.showAndWait();

                        wifiDataShow();
                       btnClearWifi();
                    }else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Cancelled.");
                        alert.showAndWait();
                }
                pre.close();
             }
            } catch (Exception e) {
                alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Gagal Update Data Wifi.");
                    alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
    
    public void btnDeleteWIfi() {
        if (dataData.getId_wifi() == 0) {
            
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Data Kurang Lengkap");
            alert.setContentText("Pilih wifi yang akan di hapus");
            alert.showAndWait();
            
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Yakin Mau Hapus Data");
            Optional<ButtonType> option = alert.showAndWait();
            
            if (option.get().equals(ButtonType.OK)) {
                String sql = "DELETE FROM `wifi` WHERE id_wifi=?;";
                try {
                    pre = connn.prepareStatement(sql);
                    pre.setInt(1, dataData.getId_wifi());
                    pre.executeUpdate();
                    
                    loggg(dataData.getId_userLogin(), "Menghapus Wifi");
                    loggg(dataData.getId_userLogin(),  "Nama Wifi : "+dataData.getNama_wifi()+", Password: "+dataData.getPass_wifi());
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Deleted!");
                    alert.showAndWait();

                    wifiDataShow();
                   btnClearWifi();

                    pre.close();
                } catch (Exception e) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Gagal Menghapus Wifi.");
                    alert.showAndWait();
                    e.printStackTrace();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
        }
    }
    
    public void btnClearWifi() {   
        tx_namaWifi.setText("");
        tx_passWifi.setText("");
        dataData.setId_wifi(0);
        dataData.setNama_wifi("");
        dataData.setPass_wifi("");
    }
    
    public void SelectDataWifi() {
        
        dataWifi data = tb_kelolaWifi.getSelectionModel().getSelectedItem();
        int num = tb_kelolaWifi.getSelectionModel().getSelectedIndex();
        
        if ((num - 1) < -1) {
            return;
        }
        dataData.setId_wifi(data.getIdWifi());
        dataData.setNama_wifi(data.getNamaWifi());
        dataData.setPass_wifi(data.getPassWifi());
     
        tx_namaWifi.setText(data.getNamaWifi());
        tx_passWifi.setText(data.getPassWifi());
    }
    
    
    
    
    
    
    //event
    
    
     

}
