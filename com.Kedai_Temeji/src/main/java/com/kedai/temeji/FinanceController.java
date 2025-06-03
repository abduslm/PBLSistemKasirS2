/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kedai.temeji;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class FinanceController implements Initializable{
    @FXML
    private AnchorPane APPP_lihatPenjualan;
    @FXML
    private AnchorPane AP_finance;
    @FXML
    private AnchorPane AP_sidebarAdmin;
    @FXML
    private ImageView IV_exit;
    @FXML
    private ImageView IV_home;
    @FXML
    private BarChart<String, Number> chartBar_customer;
    @FXML
    private Label lb_nc;
    @FXML
    private Label lb_numberSold;
    @FXML
    private Label lb_ti;
    @FXML
    private StackPane lihatPenjualan_StkPane;
    @FXML
    private AnchorPane lihatPenjualan_btnLaporan;
    @FXML
    private ComboBox<String> lihatPenjualan_cbBulan;
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
    private ScrollPane sppppp;
    @FXML
    private Separator spr_lihatPenjualan;
    @FXML
    private TextField tx_Tdijual;
    @FXML
    private TextField tx_Tpendapatan;
    @FXML
    private TextField txTotalPerB;

    
    private ResultSet r;
    private PreparedStatement pre;
    private Alert alert;
    private Connection connn = databes.konek();

     public void initialize(URL url, ResourceBundle rb){
        lihatPenjualan_cbBulan.setValue(tangg());
    tampilkanNC();
        tampilkanTodayI();
        tampilkanNSP();
        showListTanggall();
        tampilkanChartCustomer();
        String abc = String.valueOf(lihatPenjualan_cbBulan.getSelectionModel().getSelectedItem());
        txTotalPerB.setText("Total Pemasukan per Bulan "+abc+"  Rp. "+formatUang(totalPerB()));
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
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
     public void loggg(Integer id, String pesan){
        try{
            String sql ="INSERT INTO `log_aktivitas`(`id_users`, `keterangan`) VALUES (?,?)";
            pre = connn.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setString(2, pesan);
            pre.executeUpdate();
        }catch(Exception e){
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
                    + "GROUP by DATE(tanggal_transaksi) order by DATE(tanggal_transaksi) ";
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
}