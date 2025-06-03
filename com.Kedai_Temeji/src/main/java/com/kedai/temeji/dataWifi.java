/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kedai.temeji;

/**
 *
 * @author Lenovo
 */
public class dataWifi {
    private String namaWifi, passWifi;
    private int idWifi;

    public dataWifi(int idWifi, String namaWifi, String passWifi) {
        this.idWifi=idWifi;
        this.namaWifi = namaWifi;
        this.passWifi = passWifi;
    }

    public String getNamaWifi() {
        return namaWifi;
    }

    public void setNamaWifi(String namaWifi) {
        this.namaWifi = namaWifi;
    }

    public String getPassWifi() {
        return passWifi;
    }

    public void setPassWifi(String passWifi) {
        this.passWifi = passWifi;
    }

    public int getIdWifi() {
        return idWifi;
    }

    public void setIdWifi(int idWifi) {
        this.idWifi = idWifi;
    }
    
    
}
