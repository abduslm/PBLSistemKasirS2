/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kedai.temeji;

/**
 *
 * @author Lenovo
 */
public class dataData {

    private static Integer id_userLogin;
    
    private static Integer Id_user;
    private static String Pass, username, level, namaAsli;

    
    private static Integer id_menu, stok;
    private static String nama_menu, kategori, ukuran_menu;
    private static Double harga;
    private static String gambar="";
    
    
    private static int id_wifi=0;
    private static String nama_wifi,pass_wifi;

    public static Integer getId_user() {
        return Id_user;
    }

    public static String getPass() {
        return Pass;
    }

    public static String getUsername() {
        return username;
    }

    public static String getLevel() {
        return level;
    }

    public static String getNamaAsli() {
        return namaAsli;
    }

    public static Integer getId_menu() {
        return id_menu;
    }

    public static Integer getStok() {
        return stok;
    }

    public static String getNama_menu() {
        return nama_menu;
    }

    public static String getKategori() {
        return kategori;
    }

    public static String getUkuran_menu() {
        return ukuran_menu;
    }

    public static Double getHarga() {
        return harga;
    }

    public static String getGambar() {
        return gambar;
    }

    public static void setId_user(Integer Id_user) {
        dataData.Id_user = Id_user;
    }

    public static void setPass(String Pass) {
        dataData.Pass = Pass;
    }

    public static void setUsername(String username) {
        dataData.username = username;
    }

    public static void setLevel(String level) {
        dataData.level = level;
    }

    public static void setNamaAsli(String namaAsli) {
        dataData.namaAsli = namaAsli;
    }

    public static void setId_menu(Integer id_menu) {
        dataData.id_menu = id_menu;
    }

    public static void setStok(Integer stok) {
        dataData.stok = stok;
    }

    public static void setNama_menu(String nama_menu) {
        dataData.nama_menu = nama_menu;
    }

    public static void setKategori(String kategori) {
        dataData.kategori = kategori;
    }

    public static void setUkuran_menu(String ukuran_menu) {
        dataData.ukuran_menu = ukuran_menu;
    }

    public static void setHarga(Double harga) {
        dataData.harga = harga;
    }

    public static void setGambar(String gambar) {
        dataData.gambar = gambar;
    }

    public static Integer getId_userLogin() {
        return id_userLogin;
    }

    public static void setId_userLogin(Integer id_userLogin) {
        dataData.id_userLogin = id_userLogin;
    }

    
    
    public static int getId_wifi() {
        return id_wifi;
    }

    public static void setId_wifi(int id_wifi) {
        dataData.id_wifi = id_wifi;
    }

    public static String getNama_wifi() {
        return nama_wifi;
    }

    public static void setNama_wifi(String nama_wifi) {
        dataData.nama_wifi = nama_wifi;
    }

    public static String getPass_wifi() {
        return pass_wifi;
    }

    public static void setPass_wifi(String pass_wifi) {
        dataData.pass_wifi = pass_wifi;
    }
    
    
    
    
}
