package com.kedai.temeji;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    public static void main(String[] args) throws IOException{
        launch();
    }
    
    
    
databes aaa=new databes();
    @Override
    public void start(Stage stage) throws Exception {
        cet();
        if (aaa.testConnection()) {
           aaa.initializeDatabase(databes.db);
        }else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Tidak Dapat Terhubung ke MySQL Server");
            alert.setHeaderText(null);
            alert.setContentText("Pastikan Konfigurasi Database Sudah Sesuai dan Muat Ulang Aplikasi");
            alert.showAndWait();
        }

        Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("LoginDesign.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        stage.setTitle("Kedai Temeji");
        stage.setScene(scene);
        stage.show();
    }

   
    
  

    
    private Alert alert;
   public void cet() {
    try {
        String configPath = getConfigPath();
        Properties prop = new Properties();
        if (!Files.exists(Paths.get(configPath))) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("File Configurasi Belum Tersedia! ");
            alert.setHeaderText(null);
            alert.setContentText("Apakah Anda mau Membuat File Konfigurasi ?");
            Optional<ButtonType> option = alert.showAndWait();
           if (option.get().equals(ButtonType.OK)) {
                createDefaultConfig(configPath);
           }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Tidak Ada File Konfigurasi");
                alert.setHeaderText(null);
                alert.setContentText("""
Buat File Konfigurasi Manual
lalu Mulai Ulang Aplikasi !
                         """);
                alert.showAndWait();
                System.exit(0);
                }
            }
        prop.load(new FileInputStream(configPath));
        databes.url= prop.getProperty("db.url", "");
        databes.db=prop.getProperty("db.namadb", "");
        databes.user = prop.getProperty("db.user", "");
        databes.password= prop.getProperty("db.password", "");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    private String getConfigPath() {
    String jarDir = Paths.get("").toAbsolutePath().toString();
    return jarDir + File.separator + "config.properties";
}


private void createDefaultConfig(String path) throws IOException {
    Properties defaultProp = new Properties();
    defaultProp.setProperty("printer.kasir", "OFF");
    defaultProp.setProperty("printer.dapur", "OFF");
    defaultProp.setProperty("printer.kasir.struk", "C:/Program Files/POS_KedaiTemejiV1/kasir.jasper");
    defaultProp.setProperty("printer.dapur.struk", "C:/Program Files/POS_KedaiTemejiV1/Dapur.jasper");
    defaultProp.setProperty("printer.parameter.nama", "Param");
   
    defaultProp.setProperty("db.url", "jdbc:mysql://localhost:3306/");
    defaultProp.setProperty("db.namadb", "temeji");
    defaultProp.setProperty("db.user", "root");
    defaultProp.setProperty("db.password", "");
    try (OutputStream output = new FileOutputStream(path)) {
        defaultProp.store(output, "Configuration");
    }
} 
    
    
    
    
    
    public void loginRFID(String ab){
        try{ 
            String sql = "SELECT `id_users`, `username`, `password`, `level`, `nama_asli` FROM `users` WHERE id_users=?;";
            PreparedStatement pre = databes.konek().prepareStatement(sql);
            pre.setString(1, ab);
            ResultSet r = pre.executeQuery();
            
            if (r.next()){
               // javax.swing.JOptionPane.showMessageDialog(null, "Login Succesfully");
                //namaUser = ab;
                if(r.getString("level").equalsIgnoreCase("Kasir")){
                    //this.dispose();
                    //new kasir().setVisible(true);
                }
                else if(r.getString("level").equalsIgnoreCase("Admin")){
                    //this.dispose(); //close the form
                    //new admin().setVisible(true);
                }
                else if(r.getString("level").equalsIgnoreCase("owner")){
                    //this.dispose(); //close the form
                    //new owner().setVisible(true);
                }
                else if(r.getString("level").equalsIgnoreCase("finance")){
                    //this.dispose();
                    //new finance().setVisible(true);
                }
            }else{
                //javax.swing.JOptionPane.showMessageDialog(null, "RFID Tidak Terdaftar");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());   }
    }

    

}