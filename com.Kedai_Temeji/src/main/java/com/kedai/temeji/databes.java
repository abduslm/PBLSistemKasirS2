/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kedai.temeji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author hpfq1
 */
public class databes {

public static String url="";
 public static String db="";
 public static String user="";
 public static String password="";



    public static java.sql.Connection konek(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url+db,user,password);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static java.sql.Connection k(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,user,password);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
public static void main(String[] args) {
    //System.out.println(testConnection());
    Connection conn = konek();
    if (conn != null) {
        System.out.println("Koneksi berhasil!");
    }
    else{
        System.out.println("Koneksi gagal!");
    }        
    }



















// Cek koneksi ke MySQL server (tanpa specify database)
private static Alert ale;
    public static boolean testConnection() {
        
        try (Connection conn = k()) {
            System.out.println("Koneksi ke MySQL server berhasil!");
            return true;
        } catch (SQLException e) {
            ale= new Alert(Alert.AlertType.ERROR);
            ale.setTitle("Tidak Dapat Terhubung ke MySQL Server");
            ale.setHeaderText(null);
            ale.setContentText("Database Tidak Dapat Teerhubung");
            ale.showAndWait();
            e.printStackTrace();
            return false;
        }
    }

    public void initializeDatabase(String dbName) {
        try (Connection conn = k();){
            
             Statement stmt = conn.createStatement() ;
            
            // Cek apakah database sudah ada
            if (!databaseExists(conn, dbName)) {
                System.out.println("Database tidak ditemukan. Membuat baru...");
                ale = new Alert(Alert.AlertType.CONFIRMATION);
                ale.setTitle(null);
                ale.setHeaderText(null);
                ale.setContentText("Database Tidak Ditemukan! "
                                            + "Apakah Anda mau Membuat Database Baru ?");
                Optional<ButtonType> option = ale.showAndWait();
                if (option.get().equals(ButtonType.OK)) {   
                    try{
                     createDatabase(stmt, dbName);//buat database
                    imporTabel(konek()); // Buat tabel setelah database dibuat
                    ale = new Alert(Alert.AlertType.INFORMATION);
                    ale.setTitle(null);
                    ale.setHeaderText(null);
                    ale.setContentText("Database Berhasil Dibuat!");
                    ale.showAndWait();
                    ale.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    ale = new Alert(Alert.AlertType.ERROR);
                    ale.setTitle("Database Tidak Ditemukan");
                    ale.setHeaderText(null);
                    ale.setContentText("Periksa Konfigurasi Database Anda dan Muat Ulang Aplikasi");
                    ale.showAndWait();
                    System.exit(0);
                    }
            } else {
                System.out.println("Database sudah ada.");
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error saat inisialisasi database:");
            e.printStackTrace();
    }
}
    

    // Cek apakah database spesifik ada
    private static boolean databaseExists(Connection conn, String dbName) throws SQLException {
        ResultSet rs = conn.getMetaData().getCatalogs();
        while (rs.next()) {
            if (rs.getString(1).equalsIgnoreCase(dbName)) {
                rs.close();
                return true;
            }
        }
        rs.close();
        return false;
    }

    // Buat database baru
    private void createDatabase(Statement stmt, String dbName){
        try{
            String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
            stmt.executeUpdate(sql);
            System.out.println("Database '" + dbName + "' berhasil dibuat.");
        }catch(SQLException e){
            ale = new Alert(Alert.AlertType.ERROR);
            ale.setTitle("Error");
            ale.setHeaderText(null);
            ale.setContentText("Database Gagal Dibuat!");
            ale.showAndWait();
            ale.close();
        }
    }

//buat tabel di database
public void imporTabel(Connection conn) throws SQLException {

  InputStream sqlFile = getClass().getResourceAsStream("/com/kedai/dbb/temejiTabel.sql");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(sqlFile))) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("--")) {
                    continue;
                }
                
                sb.append(line);
                if (line.trim().endsWith(";")) {
                    String query = sb.toString().replace(";", "");
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute(query);
                    }
                    sb = new StringBuilder();
                }
            }
            conn.close();
        } catch (IOException e) {
            ale = new Alert(Alert.AlertType.ERROR);
            ale.setTitle("Error");
            ale.setHeaderText(null);
            ale.setContentText("Tabel Gagal Dibuat!");
            ale.showAndWait();
            ale.close();
            e.printStackTrace();
        }

    }

}
