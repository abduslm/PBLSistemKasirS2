/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kedai.temeji;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
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
public class laporanMenu {
    private IntegerProperty idMenu, jDibeli;
    private StringProperty namaMenu,ukMenu,pFormat;
    private DoubleProperty jPendapatan;

    public laporanMenu(Integer idMenu, String namaMenu, String ukMenu, Integer jDibeli,   Double jPendapatan) {
        this.idMenu = new SimpleIntegerProperty(idMenu);
        this.jDibeli = new SimpleIntegerProperty(jDibeli);
        this.namaMenu = new SimpleStringProperty(namaMenu);
        this.ukMenu = new SimpleStringProperty(ukMenu);
        this.jPendapatan = new SimpleDoubleProperty(jPendapatan);
        this.pFormat=new SimpleStringProperty("Rp. "+formatUang(jPendapatan));
    }

    
    // Property methods
    public IntegerProperty idMenuProperty() { return idMenu; }
    public StringProperty namaMenuProperty() { return namaMenu; }
    public IntegerProperty jDibeliProperty() { return jDibeli; }
    public StringProperty ukMenuProperty() { return ukMenu; }
    public DoubleProperty jPendapatanProperty() { return jPendapatan; }
    public StringProperty pFormatProperty() { return pFormat; }

    
    // Standard getters/setters
    public int getIdMenu() { return idMenu.get(); }
    public String getNamaMenu() { return namaMenu.get(); }
    public int getJDibeli() { return jDibeli.get(); }   
    public String getUkMenu() { return ukMenu.get(); }
    public double getJPendapatan() { return jPendapatan.get(); }
   
    
    //public void setNamaMenu(int value) { kuantitasM.set(value); }
    
    
    
    public static String formatUang(double amount) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("id", "ID"));
        symbols.setCurrencySymbol("Rp");
        symbols.setMonetaryDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        
        DecimalFormat rupiahFormat = new DecimalFormat(" #,##0.00", symbols);
        return rupiahFormat.format(amount);
    }
}
