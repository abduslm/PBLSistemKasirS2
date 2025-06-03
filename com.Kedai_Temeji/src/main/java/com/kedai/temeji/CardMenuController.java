/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.kedai.temeji;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.text.DecimalFormat;

import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class CardMenuController implements Initializable{
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button car_btnmin;
    @FXML
    private Label card_Labharga;
    @FXML
    private Label card_LabnamaProduk;
    @FXML
    private Label card_Labnol;
    @FXML
    private Button card_btnplus;
    @FXML
    private ImageView card_imgmenu;
    @FXML
    private Label card_Labstok;
     @FXML
    private Label lb_ukuranMenu;
    
 /////////////////////////////////////////////////////////
   
    ////////////////////////////////////////////
    
    
    
    
    //private dataMenu ListMenuKasir;
    private Integer idMenu;
    private String namaMenu;
    private String ukuranMenu;
    private Double hargaMenu;
    protected Integer quantity;
    private Integer stokMenu;


  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //idTrxx=
        
    }    
    
     public void setData(dataMenu listMenuKasir) {
        //this.ListMenuKasir = listMenuKasir;

        idMenu =listMenuKasir.getId_menu();
        hargaMenu=listMenuKasir.getHarga();
        stokMenu = listMenuKasir.getStok();
        namaMenu=listMenuKasir.getNama_menu();
        ukuranMenu=listMenuKasir.getUkuran_menu();
        quantity=listMenuKasir.quantityAwal;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(listMenuKasir.getGambar());
        Image image = new Image(inputStream);
        
        
        
        
        card_Labharga.setText("Rp. "+ new DecimalFormat("#,##0").format(hargaMenu));
        card_Labstok.setText(String.valueOf(stokMenu));

        card_LabnamaProduk.setText(namaMenu);
        card_Labnol.setText(String.valueOf(quantity));
        lb_ukuranMenu.setText(ukuranMenu);
         card_imgmenu.setImage(image);

    }


 private kasirController kasirC; // Reference ke kasirController
    
    // Method untuk set reference
    public void setMainController(kasirController controller) {
        this.kasirC = controller;
    }    

    private void updateOrder() {
        String nama = card_LabnamaProduk.getText();
        double harga = Double.parseDouble(card_Labharga.getText().replaceAll("[^0-9]", ""));
        double subTotal = quantity * harga;
        String uk=lb_ukuranMenu.getText();
        
        dataMenuOrder order = new dataMenuOrder(idMenu, nama, quantity, harga,stokMenu,uk);
        
        if (kasirC != null) {
            kasirC.Order(order);
        }
        kasirC.menuOrderShow();
    }
    

public void Btnmin() {
    if (quantity > 0) {
        quantity--;
        if (quantity > stokMenu){
            card_Labnol.setStyle("-fx-text-fill: red");
        }else if(stokMenu != 0 && quantity <= stokMenu) {
            card_Labnol.setStyle("-fx-text-fill: black");
        }
        card_Labnol.setText(String.valueOf(quantity));
        updateOrder();
    }
}

public void Btnplus() {
    quantity++;
    if (quantity > stokMenu){
        card_Labnol.setStyle("-fx-text-fill: red");
    }else if(stokMenu != 0 && quantity <= stokMenu) {
        card_Labnol.setStyle("-fx-text-fill: black");
    }
    card_Labnol.setText(String.valueOf(quantity));
    updateOrder();
}



}
