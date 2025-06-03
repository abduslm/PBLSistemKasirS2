/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package com.kedai.temeji;


import java.net.URL;
import java.security.MessageDigest;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
/**
 *
 * @author hpfq1
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button sl_ButtonLogin;
    @FXML
    private PasswordField sl_Pass;
    @FXML
    private TextField sl_users;
    @FXML
    private CheckBox cb_cekpassword;
    @FXML
    private TextField sl_pass2;

     private ResultSet r;
    private PreparedStatement pre;
    private Alert alert;
    private Connection connn=databes.konek();;
    public ImageView aaa;
    
    public FXMLLoader kasirLoader;
     

   public void loginnn() {
       if (sl_users.getText().isEmpty() || (sl_Pass.getText().isEmpty() && sl_pass2.getText().isEmpty())){
           alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error Message");
           alert.setHeaderText(null);
           alert.setContentText("Username dan Password harus diisi !");
           alert.showAndWait();  
       }else{
            String ab = sl_users.getText();
            String cd;
            
            if (cb_cekpassword.isSelected()) {
                cd = passhash(sl_pass2.getText());
            } else { cd = passhash(sl_Pass.getText());
            }
            try{ 
                String sql = "SELECT `id_users`, `username`, `password`, `level`, `nama_asli` FROM `users` WHERE username=? AND password=?;";
                pre = connn.prepareStatement(sql);
                pre.setString(1, ab);
                pre.setString(2, cd);
                r = pre.executeQuery();
                        
                if (r.next()){
                    dataData.setId_userLogin(r.getInt(1));
                    dataData.setNamaAsli(r.getString(5));
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Succes");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Berhasil");
                    alert.showAndWait();

                    if(r.getString("level").equalsIgnoreCase("Kasir")){    
                        FXMLLoader kasirLoader = new FXMLLoader(getClass().getResource("Form_Carsier.fxml"));
                        Parent roo = kasirLoader.load();
                        kasirController kasirC = kasirLoader.getController();

                        kasirC.menuDisplayCard(); 

                        for (Node node : kasirC.GP_daftarMenu.getChildren()) {
                            if (node.getUserData() instanceof CardMenuController) {
                                ((CardMenuController) node.getUserData()).setMainController(kasirC);
                            }
                        }

                        Scene scene = new Scene(roo);
                        Stage stage = new Stage();
                        stage.setTitle("Kasir Temeji");
                        stage.setScene(scene);
                        stage.show();
                        sl_ButtonLogin.getScene().getWindow().hide();
                    }
                    else if(r.getString("level").equalsIgnoreCase("Admin")){
                        Parent roo = FXMLLoader.load(getClass().getResource("Form_Admin.fxml"));
                        Scene scene = new Scene(roo);
                        Stage stage = new Stage();
                        stage.setTitle("Admin Temeji");

                        stage.setScene(scene);
                        stage.show();
                        sl_ButtonLogin.getScene().getWindow().hide();
                    }
                    else if(r.getString("level").equalsIgnoreCase("owner")){
                        Parent roo = FXMLLoader.load(getClass().getResource("Form_Owner.fxml"));
                        Scene scene = new Scene(roo);
                        Stage stage = new Stage();
                        stage.setTitle("Owner Temeji");
                        stage.setScene(scene);
                        stage.show();
                        sl_ButtonLogin.getScene().getWindow().hide();
                    }
                    else if(r.getString("level").equalsIgnoreCase("finance")){
                        Parent roo = FXMLLoader.load(getClass().getResource("Form_Finance.fxml"));
                        Scene scene = new Scene(roo);
                        Stage stage = new Stage();
                        stage.setTitle("Finance Temeji");
                        stage.setScene(scene);
                        stage.show();
                        sl_ButtonLogin.getScene().getWindow().hide();
                    }
                    loggg(dataData.getId_userLogin(), " Login ");
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Username atau Password Salah !");
                    alert.showAndWait();
                    sl_Pass.requestFocus();
                }
                r.close();
                pre.close();
            }catch(Exception e){
                System.out.println(e.getMessage());   }
       }
    }
    
    public static String passhash (String pass) {
        try{ 
            MessageDigest mdg = MessageDigest.getInstance("SHA-256");
            mdg.update (pass.getBytes()); 
            byte[] passbt = mdg.digest(); 
            StringBuilder ab = new StringBuilder();
        
            for (byte b: passbt){ 
            ab.append(String.format("%02x",b));
            }
            return ab.toString(); 
        } catch (Exception e) { }
        return null;
    }
    
    public void cekPass(){
        if (cb_cekpassword.isSelected()) {
            sl_pass2.setText(sl_Pass.getText());
            sl_pass2.setVisible(true);
            sl_Pass.setVisible(false);
            sl_pass2.requestFocus();
        } else {
            sl_Pass.setText(sl_pass2.getText());
            sl_Pass.setVisible(true);
            sl_pass2.setVisible(false);
            sl_Pass.requestFocus();
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
        }catch(Exception e){
             e.printStackTrace();
        }
    }
    
    public void exittt(){
        System.exit(0);
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO    

        sl_users.requestFocus();
        sl_users.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                sl_Pass.requestFocus();
            }
        });
            sl_pass2.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                loginnn();
            }
        });

            sl_Pass.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                loginnn();
            }
        });

        

}
}
