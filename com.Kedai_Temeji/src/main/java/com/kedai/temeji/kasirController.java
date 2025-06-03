/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kedai.temeji;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lenovo
 */
public class kasirController implements Initializable{
    @FXML
    private AnchorPane AP_kasir;
    @FXML
    private ImageView IV_exit;
    @FXML
    private Label btn_konfirm;
     @FXML
    private AnchorPane AP_filterAll;
    @FXML
    private AnchorPane AP_filterBread;
    @FXML
    private AnchorPane AP_filterDrinks;
    @FXML
    private AnchorPane AP_filterHM;
    @FXML
    private AnchorPane AP_filterSnack;
    @FXML
    private AnchorPane AP_filternoodle;
    @FXML
    private ScrollPane SC_daftarMenu;
    @FXML
    protected TableView<dataMenuOrder> tb_menuPilih;
    @FXML
    private TableColumn<dataMenuOrder, Double> kolom_hargaMenu;
    @FXML
    private TableColumn<dataMenuOrder, Integer> kolom_jumlahMenu;
    @FXML
    private TableColumn<dataMenuOrder, String> kolom_namaMenu;
     @FXML
    private TextField lb_jam;
    @FXML
    private Label lb_namaKasir;
    @FXML
    private TextField lb_tanggal;
    @FXML
    private Label lb_totalHarga;
    @FXML
    private TextField lb_uangBayar;
    @FXML
    private Label lb_uangKembali;
    @FXML
    private Label idCust;
    @FXML
    private TextField tx_searchMenu;
    @FXML
    protected GridPane GP_daftarMenu;
    @FXML
    private ComboBox<String> cb_metodeBayar;
        @FXML
    private Button kasir_btnRemove;

    private Boolean running;
     @FXML
    private Label lb_Bread;
    @FXML
    private Label lb_QallMenu;
    @FXML
    private Label lb_Qdrink;
    @FXML
    private Label lb_QheavyMeal;
    @FXML
    private Label lb_Qnoodle;
    @FXML
    private Label lb_Qsnack;
    
       @FXML
    private TextField tx_pajak;

           @FXML
    private AnchorPane AP_tambahan;
    //private Double totalHarga, uangBayar, Kembalian;
      //public String idTrx, idCust;
    private ResultSet r;
    private PreparedStatement pre;
    private Alert alert;
    private Connection connn = databes.konek();
    private ObservableList<dataMenu> allMenuData = FXCollections.observableArrayList();
    private ObservableList<dataMenu> cardListData = FXCollections.observableArrayList();
    private ObservableList<dataMenu> filterCardListData = FXCollections.observableArrayList();
      private String sqlFilter;   
      
      
      
    public void initialize(URL url, ResourceBundle rb){

        listDataMenu();
        menuDisplayCard();
        selectFilterPane(AP_filterAll);
        
        lb_namaKasir.setText(dataData.getNamaAsli());
        showListMetode();
        referess();
        
        itemAll();
        itemMinuman();
        itemMie();
        itemMakananR();
        itemMakananB();
        itemRoti();
        jamm();
    }
    
    
  
    
    
    
    
 
    
    public void exitt(){
        try {
            if (time != null) {
        time.stop();}
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
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);} 
    }
    public void loggg(Integer id, String pesan){
        try{
            String sql ="INSERT INTO `log_aktivitas`(`id_users`, `keterangan`) VALUES (?,?)";
            pre = connn.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setString(2, pesan);
            pre.executeUpdate();
            pre.close();
        }catch(Exception e){
             e.printStackTrace();
        }  
}
       
private String[] listMetodeBayar = {"CASH","QRIS"};
    public void showListMetode() {
        
        List<String> M = new ArrayList<>();
        
        for (String data : listMetodeBayar) {
            M.add(data);
        }
        
        ObservableList listMetode = FXCollections.observableArrayList(M);
        cb_metodeBayar.setItems(listMetode);
    }    
    
   
    
public void listDataMenu() {
        //String cariNama=tx_searchMenu.getText();
        String sql = "SELECT id_menu, nama_menu, harga, stok, kategori, ukuran_menu, gambar FROM menu ";
        //ObservableList<dataMenu> list = FXCollections.observableArrayList();
        try (PreparedStatement pree  = connn.prepareStatement(sql);
                ResultSet res = pree.executeQuery();){
    
            allMenuData.clear();
            while (res.next()) {
                allMenuData.add(new dataMenu(res.getInt(1),
                        res.getString(2),
                        res.getDouble(3),
                        res.getInt(4),
                        res.getString(5),
                        res.getString(6),
                        res.getBytes(7)));
            }
            allMenuData.sort(Comparator.comparing(dataMenu::getNama_menu));
            cardListData.setAll(allMenuData);
            filterCardListData.setAll(allMenuData);
            res.close();
            pree.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
public void filterMenuData() {
    String cariii=tx_searchMenu.getText();
    if (cariii == null || cariii.isEmpty()) {
        cardListData.setAll(filterCardListData);
    } else {
        cardListData.setAll(
            filterCardListData.stream()
                .filter(menu -> menu.getNama_menu().toLowerCase().contains(cariii.toLowerCase()))
                .collect(Collectors.toList()) );
    }
    for(dataMenuOrder dataQ:ListOrder){
        for(dataMenu dataQ2:cardListData){
            if(dataQ.getIdM()==dataQ2.getId_menu()){
                dataQ2.quantityAwal=dataQ.getKuantitasM();
            }
        }
    }
    menuDisplayCard();
}

public void filterMenuAll(){
    filterCardListData.setAll(
            allMenuData
        );
    selectFilterPane(AP_filterAll);
    filterMenuData();
}
public void filterMenuMinuman() {
     filterCardListData.setAll(
            allMenuData.stream()
                .filter(menu -> menu.getKategori().equalsIgnoreCase("minuman"))
                .collect(Collectors.toList())
        );
     selectFilterPane(AP_filterDrinks);
     filterMenuData();
}
public void filterMenuMakananR() {
    filterCardListData.setAll(
            allMenuData.stream()
                .filter(menu -> menu.getKategori().equalsIgnoreCase("makanan ringan"))
                .collect(Collectors.toList())
        );
    selectFilterPane(AP_filterSnack);
    filterMenuData();
}
public void filterMenuMakananB() {
    filterCardListData.setAll(
            allMenuData.stream()
                .filter(menu -> menu.getKategori().equalsIgnoreCase("makanan berat"))
                .collect(Collectors.toList())
        );
    selectFilterPane(AP_filterHM);
    filterMenuData();
}
public void filterMenuMie() {
    filterCardListData.setAll(
            allMenuData.stream()
                .filter(menu -> menu.getKategori().equalsIgnoreCase("mie"))
                .collect(Collectors.toList())
        );
    selectFilterPane(AP_filternoodle);
    filterMenuData();
}
public void filterMenuRoti() {
    filterCardListData.setAll(
            allMenuData.stream()
                .filter(menu -> menu.getKategori().equalsIgnoreCase("roti"))
                .collect(Collectors.toList())
        );
    selectFilterPane(AP_filterBread);
    filterMenuData();
}

private AnchorPane selectedFilterPane = null;
private void selectFilterPane(AnchorPane pane) {
        if (selectedFilterPane != null) {
            selectedFilterPane.getStyleClass().remove("selected");
        }
        pane.getStyleClass().add("selected");
        selectedFilterPane = pane;
    }
    
public void menuDisplayCard() {
        GP_daftarMenu.getChildren().clear();
        GP_daftarMenu.getRowConstraints().clear();
        GP_daftarMenu.getColumnConstraints().clear();  

        int row = 0;
        int column = 0;
        for (dataMenu menu : cardListData) {
            try {
                FXMLLoader load = new FXMLLoader(getClass().getResource("cardMenu.fxml"));
                Parent pane = load.load();
                CardMenuController cardC = load.getController();
                cardC.setMainController(this);
                cardC.setData(menu);

                pane.setUserData(cardC);
                
                if (column == 5) {
                    column = 0;
                    row += 1; }
                
                GP_daftarMenu.add(pane, column++, row);  
                GridPane.setMargin(pane, new Insets(5,13,5,13));                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
public void refreshMenuData() {
    listDataMenu();
    filterMenuData(); 
}
    
ObservableList<dataMenuOrder> ListOrder = FXCollections.observableArrayList();
public void Order(dataMenuOrder order) {

        boolean found = false;
        for (dataMenuOrder itemAda : ListOrder) {
            if (itemAda.getIdM()==order.getIdM()) {
                itemAda.setKuantitasM(order.getKuantitasM());
                itemAda.setSubTotalM(order.getSubTotalM());
                found = true;
                break;
            }
        }
        
        if (!found && order.getKuantitasM() > 0) {
            ListOrder.add(order);
        }

        ListOrder.removeIf(item -> item.getKuantitasM() <= 0);
    }
public void menuOrderShow() {
    if (tb_menuPilih == null) return;
    
    kolom_namaMenu.setCellValueFactory(cellData -> cellData.getValue().namaDanUkProperty());
    kolom_jumlahMenu.setCellValueFactory(cellData -> cellData.getValue().kuantitasMProperty().asObject());
    kolom_hargaMenu.setCellValueFactory(cellData -> cellData.getValue().subTotalMProperty().asObject());
    
    tb_menuPilih.setItems(ListOrder);
    tb_menuPilih.refresh();
   tampilkanTotalHarga();
}

public void tampilkanTotalHarga(){
    lb_totalHarga.setText(formatUang(hargaP()));
    lb_uangKembali.setText(formatUang(Kembalian()));
   
}

public double TotalHarga(){
    double a=0;
    for (dataMenuOrder stotal : ListOrder){
        a+=stotal.getSubTotalM();
    }
    return a;
}

public void qrisbayar(ActionEvent event) {   
                if(!cb_metodeBayar.getSelectionModel().isEmpty()){
                    String selected = cb_metodeBayar.getValue();
                     if (selected.equalsIgnoreCase("QRIS")) {
                    lb_uangBayar.setText(String.valueOf(hargaP().intValue()));
                }
                }
                tampilkanTotalHarga();
} 

public double uangBayar(){
    double bayar;
    if(lb_uangBayar.getText().isEmpty()){
        bayar=0;
    }else if(lb_uangBayar.getText().matches(".*[^0-9].*")){
        bayar = Double.parseDouble(lb_uangBayar.getText().replaceAll("[^0-9]", ""));
    }else{
        bayar = Double.parseDouble(lb_uangBayar.getText());
    }
    return bayar;
}
   
public double Kembalian(){
    double b=uangBayar()-hargaP();
    return b;
}

public Double persenPajak(){
    
    Double a=0.0;
try{
    if(tx_pajak.getText().isEmpty()){
        a=0.0;
    }else if(tx_pajak.getText().contains(",")){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Gunakan titik( . ) untuk angka Decimal");
        alert.showAndWait();
        a=0.0;
    }else if(tx_pajak.getText().matches(".*[^0-9.].*")){
        a = Double.parseDouble(tx_pajak.getText().replaceAll("[^0-9.].", "").replaceAll("(\\.)(?=.*\\.)", ""));
    }else{
        a = Double.parseDouble(tx_pajak.getText());
}}catch(NumberFormatException e){
        e.printStackTrace();
   }
    return a;
}

public Double pajak(){
    double a=persenPajak();
    double b =TotalHarga();
    double c= b*a/100;
    return c;
}
  public Double hargaP(){
       double a=persenPajak(); 
       
        double b =TotalHarga();
        double c= b+(b*a/100);

        return c;
    }

public static String formatUang(double amount) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("id", "ID"));
        //symbols.setCurrencySymbol("Rp");
        symbols.setMonetaryDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat rupiahFormat = new DecimalFormat(" #,##0.00", symbols);
        return rupiahFormat.format(amount);
    }
    


 public String idPembeli(){
            String iddd=null;
            try{
            String sql = "SELECT `id_pembeli` FROM `transaksi` WHERE DATE(`tanggal_transaksi`) = CURDATE() "
                    + "order by id_transaksi DESC limit 1";
            pre=connn.prepareStatement(sql);
            ResultSet r = pre.executeQuery();
            if (r.next()) {
                String NoID = r.getString("id_pembeli").replaceAll("[^0-9]", "");
                Integer id = Integer.parseInt(NoID)+1;
                iddd=("T-"+ id);
            }else{
                iddd=("T-1");
            }
            r.close();
            pre.close();
        }catch(Exception e){
        }
       return iddd;     
 }
 public String idTransaksi(){
            SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
            String idT = "Trx-" +formatter.format(new Date());
            return idT;
    }

public void buatTransaksi(String idTransaksi){
    String idtrx=idTransaksi;
    Integer idUsr = dataData.getId_userLogin();
    
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
     String formattedDateTime = now.format(formatter);

    Double total =  TotalHarga();
    Double uBayar= uangBayar();
    Double uKembali= Kembalian();
    
    Double a=persenPajak();
    Double b=pajak();
     Double hSPajak=hargaP();
    
    String idP=idPembeli();
    String metode=cb_metodeBayar.getSelectionModel().getSelectedItem();
    try{
        String sql = "INSERT INTO `transaksi`(`id_transaksi`, `id_users`, `tanggal_transaksi`, `total_harga`, `uang_pembayaran`, `uang_kembalian`, `id_pembeli`, `metode`, `PersenPajak`,`pajak`, `Total_setelah_Pajak`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        pre = connn.prepareStatement(sql);
        pre.setString(1, idtrx);
        pre.setInt(2, idUsr);
        pre.setString(3, formattedDateTime);
        pre.setDouble(4, total);
        pre.setDouble(5, uBayar);
        pre.setDouble(6, uKembali);
        pre.setString(7, idP);
        pre.setString(8, metode);
        pre.setDouble(9, a);
        pre.setDouble(10,b);
        pre.setDouble(11,hSPajak);
        
        pre.executeUpdate();
        idCust.setText(idPembeli());
        
        r.close();
        pre.close();   
    }catch(SQLException e){
        e.printStackTrace();
         System.out.println("Gagal Membuat Transaksi"); 
     }
}
public void insertDetailTrx(String idTransaksi){
    try {
        String sql = "INSERT INTO `detail_transaksi`(`id_transaksi`, `id_menu`, `kuantitas`, `subtotal`) VALUES (?,?,?,?)";
         pre = connn.prepareStatement(sql);
            for(dataMenuOrder item : ListOrder) {
                pre.setString(1, idTransaksi);
                pre.setInt(2, item.getIdM());
                pre.setInt(3, item.getKuantitasM());
                pre.setDouble(4, item.getSubTotalM());
                pre.addBatch();
            }
            pre.executeBatch();
        pre.close();  
        } catch (Exception e) {
            e.printStackTrace();
             System.out.println("Gagal Menyimpan Detail Transaksi");
        }
}    



public void abcdefghij(){
    for (dataMenuOrder n1 : ListOrder) {
    if(n1.getUkuranMenu().equalsIgnoreCase("Besar")||n1.getUkuranMenu().equalsIgnoreCase("Double")){
                int sss=n1.getKuantitasM()*2;
                n1.setKuantitasM(sss);
                n1.setFinalQ(sss);
            }
    }
}
public void finalStok() {
    abcdefghij();
        for (dataMenuOrder menu1 : ListOrder) {
            if(menu1.getSamaaa()==0){
                menu1.setFinalQ(menu1.getKuantitasM());
                for(dataMenuOrder menu2 : ListOrder){
                    if(menu1.getIdM()!=menu2.getIdM()){
                        if(menu1.getNamaM().equalsIgnoreCase(menu2.getNamaM())){
                            int s=menu1.getKuantitasM()+menu2.getKuantitasM();
                            menu1.setFinalQ(s);
                            menu2.setFinalQ(s);
                            menu1.setSamaaa(1);
                            menu2.setSamaaa(1);
                    }
                   }

                }
            }
        }
    }
public void updateStok(){
    finalStok();
    try{
    ObservableList<dataMenuOrder> ListNama=ListOrder;
    String sql = "UPDATE `menu` SET `stok`=? WHERE nama_menu=?";
    pre = connn.prepareStatement(sql);

            for(dataMenuOrder stokk : ListOrder) {
                //System.out.println(stokk.getStokAwal()+" - "+stokk.getKuantitasM());
                pre.setInt(1, Math.max(stokk.getStokAwal()-stokk.getFinalQ(), 0));
                pre.setString(2, stokk.getNamaM());
                pre.addBatch();
            }
            pre.executeBatch();
        pre.close();  
    }catch(SQLException e){
        e.printStackTrace();
        System.out.println("Gagal Update Stok");
    }
}

 public void konfirmasiOrder(){
    if (lb_uangBayar.getText().isEmpty()
        ||cb_metodeBayar.getSelectionModel().isEmpty()
        ||ListOrder.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Semua Data Harus Diisi !");
            alert.showAndWait();
            alert.close();
 } else {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("Konfirmasi Pesanan?");
        Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
        String idTran=idTransaksi();
        try {
            buatTransaksi(idTran);
            insertDetailTrx(idTran);
            updateStok();
            alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle(null);
             alert.setHeaderText(null);
             alert.setContentText("Pesanan Berhasil Dibuat!");
             alert.showAndWait();
             alert.close();
             btnRemove();
             referess();
             cetakk(idTran);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Gagal Membuat Pesanan");
                alert.showAndWait(); 
            }
         }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
    }
 }    
 
 public void btnRemove(){
     ListOrder.clear();
     lb_uangBayar.setText("");
     tampilkanTotalHarga();
     cb_metodeBayar.getSelectionModel().clearSelection();
     listDataMenu();
     menuDisplayCard();
 }
  
    

  
    
    
    
    
    
    
    
    
    
    
    
    


     
 
 
 
 
public String tangg(){
    Date now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM YYYY", new Locale("id", "ID"));
    String tangggal = formatter.format(now);
    return tangggal;
    }
 public void referess(){
     idCust.setText(idPembeli());
     lb_tanggal.setText(tangg());
 }

 private Timeline time;
 public void jamm() {
    DateTimeFormatter formatJam = DateTimeFormatter.ofPattern("HH:mm:ss z");
    time= new Timeline(
        new KeyFrame(Duration.seconds(1), event -> {
            ZonedDateTime zonaWaktu = ZonedDateTime.now(ZoneId.of("Asia/Jakarta"));
            lb_jam.setText(zonaWaktu.format(formatJam));}
        ) );    
    time.setCycleCount(Animation.INDEFINITE);
    time.play();
}

public void itemRoti() {
        try {
            String sql5 = "SELECT COUNT(id_menu) FROM menu where kategori='roti' GROUP BY kategori;";
            pre = connn.prepareStatement(sql5);
            r = pre.executeQuery();
            while (r.next()) {lb_Bread.setText(String.valueOf(r.getInt(1)));}
            r.close();
        pre.close();  
        } catch (SQLException ex) {
            //Logger.getLogger(kasirController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void itemMinuman() {
        try {
            String sql1 = "SELECT COUNT(id_menu) FROM menu where kategori='minuman' GROUP BY kategori;";
            pre = connn.prepareStatement(sql1);
            r = pre.executeQuery();
            while (r.next()) {lb_Qdrink.setText(String.valueOf(r.getInt(1)));}
            r.close();
        pre.close();  
        } catch (SQLException ex) {
            //Logger.getLogger(kasirController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void itemMakananR() {
        try {
            String sql3 = "SELECT COUNT(id_menu) FROM menu where kategori='makanan ringan' GROUP BY kategori;";
            pre = connn.prepareStatement(sql3);
            r = pre.executeQuery();
            while (r.next()) {lb_Qsnack.setText(String.valueOf(r.getInt(1)));}
            r.close();
        pre.close();  
        } catch (SQLException ex) {
            //Logger.getLogger(kasirController.class.getName()).log(Level.SEVERE, null, ex);
        }  
}
public void itemMakananB(){
        try {
            String sql4 = "SELECT COUNT(id_menu) FROM menu where kategori='minuman berat' GROUP BY kategori;";
            pre = connn.prepareStatement(sql4);
            r = pre.executeQuery();
            while (r.next()) {lb_QheavyMeal.setText(String.valueOf(r.getInt(1)));}
            r.close();
        pre.close();  
        } catch (SQLException ex) {
           // Logger.getLogger(kasirController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void itemMie(){
        try {
            String sql2 = "SELECT COUNT(id_menu) FROM menu where kategori='mie' GROUP BY kategori;";
            pre = connn.prepareStatement(sql2);
            r = pre.executeQuery();
            while (r.next()) {lb_Qnoodle.setText(String.valueOf(r.getInt(1)));}
            r.close();
        pre.close();  
        } catch (SQLException ex) {
            //Logger.getLogger(kasirController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
 public void itemAll() {
        try {
            String sql = "SELECT COUNT(id_menu) FROM menu;";
            pre = connn.prepareStatement(sql);
            r = pre.executeQuery();
            while (r.next()) {lb_QallMenu.setText(String.valueOf(r.getInt(1)));}
            r.close();
        pre.close();  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
 private String getConfigPath() {
    String jarDir = Paths.get("").toAbsolutePath().toString();
    return jarDir + File.separator + "config.properties";
}
 
 public void cetakk(String idTran) {
    try {
        String configPath = getConfigPath();
        Properties prop = new Properties();

        prop.load(new FileInputStream(configPath));
        String printerKasir = prop.getProperty("printer.kasir", "");
        String printerDapur = prop.getProperty("printer.dapur", "");
        String cetakKasir = prop.getProperty("printer.kasir.struk", "");
        String cetakDapur = prop.getProperty("printer.dapur.struk", "");
        String param = prop.getProperty("printer.parameter.nama", "");
        
        if (!printerKasir.isBlank() && !printerKasir.equalsIgnoreCase("OFF")) {
            JasperPrint print1 = generatePrint(cetakKasir, idTran, param);
            printToSpecificPrinter(print1, printerKasir);
        } else {
            System.out.println("Printer kasir dinonaktifkan via config");
        }
        if (!printerDapur.isBlank() && !printerDapur.equalsIgnoreCase("OFF")) {
            JasperPrint print2 = generatePrint(cetakDapur, idTran, param);
        printToSpecificPrinter(print2, printerDapur);
        } else {
            System.out.println("Printer dapur dinonaktifkan via config");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private JasperPrint generatePrint(String reportPath, String idTransak, String Param) throws JRException {
    HashMap<String, Object> params = new HashMap<>();
    params.put(Param, idTransak);
    return JasperFillManager.fillReport(reportPath, params, connn);
}

private void printToSpecificPrinter(JasperPrint print, String printerName) {
    try {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService targetPrinter = null;
        
        for (PrintService service : services) {
            if (service.getName().equalsIgnoreCase(printerName)) {
                targetPrinter = service;
                break;
            }
        }
        if (targetPrinter != null) {
            JRPrintServiceExporter exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, targetPrinter);
            exporter.exportReport();
        } else {
            System.out.println("Printer " + printerName + " tidak ditemukan!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Printer " + printerName + " tidak ditemukan!");
                alert.showAndWait(); 
        }
    } catch (JRException e) {
        e.printStackTrace();
    }
}

}