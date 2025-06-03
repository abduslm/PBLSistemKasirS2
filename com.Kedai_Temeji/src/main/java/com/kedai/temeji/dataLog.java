/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kedai.temeji;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lenovo
 */
public class dataLog {
    private IntegerProperty idAktivitas, idUser;
    private StringProperty ket;
    private StringProperty waktu;

    public dataLog(Integer idAktivitas, Integer idUser, String ket, String waktu) {
        this.idAktivitas = new SimpleIntegerProperty(idAktivitas);
        this.idUser = new SimpleIntegerProperty(idUser);
        this.ket = new SimpleStringProperty(ket);
        this.waktu = new SimpleStringProperty(waktu);
    }

    public IntegerProperty idAkProperty() { return idAktivitas; }
    public IntegerProperty idUserProperty() { return idUser; }
    public StringProperty ketProperty() { return ket; }
    public StringProperty waktuProperty() { return waktu; }

    
    // Standard getters/setters
    public Integer getIdAk() { return idAktivitas.get(); }
    public Integer getIdUser() { return idUser.get(); }
    public String getKet() { return ket.get(); }   
    public String getWaktu() { return waktu.get(); }


   

   
    
}
