/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kedai.temeji;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lenovo
 */
public class dataMenu {
    private Integer id_menu, stok;
    private String nama_menu, kategori, ukuran_menu;
    private Double harga;
    private byte[] gambar;
    protected Integer quantityAwal=0;

    
    public dataMenu(Integer id_menu, String nama_menu, Double harga, Integer stok, String kategori, String ukuran_menu, byte[] gambar) {
        this.id_menu= id_menu;
        this.stok = stok;
        this.nama_menu = nama_menu;
        this.kategori = kategori;
        this.ukuran_menu = ukuran_menu;
        this.harga = harga;
        this.gambar = gambar;
    }
    

    public Integer getId_menu() {
        return id_menu;
    }

    public Integer getStok() {
        return stok;
    }

    public String getNama_menu() {
        return nama_menu;
    }

    public String getKategori() {
        return kategori;
    }

    public String getUkuran_menu() {
        return ukuran_menu;
    }

    public Double getHarga() {
        return harga;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }


    

    
    
    
        




   
    
    
}
