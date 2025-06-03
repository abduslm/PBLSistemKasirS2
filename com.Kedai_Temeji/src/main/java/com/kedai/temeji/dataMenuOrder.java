/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kedai.temeji;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
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
public class dataMenuOrder {
    private final IntegerProperty idM;
    private final StringProperty namaM;
    private final IntegerProperty kuantitasM;
    private final DoubleProperty hargaM;
    private final DoubleProperty subTotalM;
    //private final StringProperty subT;
    private final IntegerProperty stokAwal;
    
    private final StringProperty namaDanUk;
    private final StringProperty ukuranMenu;
    private final IntegerProperty samaaa;
    private final IntegerProperty finalQ;
    
    public dataMenuOrder(Integer id, String nama, Integer kuantitas, Double harga, Integer stokA, String uk) {
        this.idM = new SimpleIntegerProperty(id);
        this.namaM = new SimpleStringProperty(nama);
        this.kuantitasM = new SimpleIntegerProperty(kuantitas);
        this.hargaM = new SimpleDoubleProperty(harga);
        this.subTotalM = new SimpleDoubleProperty(kuantitas * harga);
        //this.subT=new SimpleStringProperty(formatUang(subTotalM.get()));
        this.stokAwal=new SimpleIntegerProperty(stokA);
       
        
        this.namaDanUk=new SimpleStringProperty(nama+" - "+uk.substring(0,1).toUpperCase());
        this.ukuranMenu=new SimpleStringProperty(uk);
        this.samaaa= new SimpleIntegerProperty(0);
        this.finalQ=new SimpleIntegerProperty(kuantitas);
    }
    
    // Property methods
    public StringProperty namaDanUkProperty(){return namaDanUk;}
            
    public StringProperty namaMProperty() { return namaM; }
    public IntegerProperty kuantitasMProperty() { return kuantitasM; }
    public DoubleProperty subTotalMProperty() { return subTotalM; }
    //public StringProperty subTProperty() { return subT; }
    
    // Standard getters/setters
    public String getNamaM() { return namaM.get(); }
    public int getKuantitasM() { return kuantitasM.get(); }
    public void setKuantitasM(int value) { kuantitasM.set(value); }
    public double getSubTotalM() { return subTotalM.get(); }
    public void setSubTotalM(double value) { subTotalM.set(value); }
    public int getIdM() { return idM.get(); }   
    public int getStokAwal() { return stokAwal.get(); }   
    public String getUkuranMenu() { return ukuranMenu.get(); }
    public void setUkuranMenu(String value) { ukuranMenu.set(value); }
    public int getSamaaa() { return samaaa.get(); }
    public void setSamaaa(int value) { samaaa.set(value); }
    public int getFinalQ() { return finalQ.get(); }
    public void setFinalQ(int value) { finalQ.set(value); }    
    
    public static String formatUang(double amount) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("id", "ID"));
        symbols.setCurrencySymbol("Rp");
        symbols.setMonetaryDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        
        DecimalFormat rupiahFormat = new DecimalFormat(" #,##0.00", symbols);
        return rupiahFormat.format(amount);
    }
}




/*    
    private Integer idM;
    private String namaM;
    private Integer kuantitasM;
    private Double hargaM;
    private Double subTotalM;

    public dataMenuOrder(Integer idM, String namaM, Integer kuantitasM, Double hargaM) {
        this.idM = idM;
        this.namaM = namaM;
        this.kuantitasM = kuantitasM;
        this.hargaM = hargaM;
        
        this.subTotalM=kuantitasM*hargaM;
    }

    public Integer getIdM() {
        return idM;
    }

    public void setIdM(Integer idM) {
        this.idM = idM;
    }

    public String getNamaM() {
        return namaM;
    }

    public void setNamaM(String namaM) {
        this.namaM = namaM;
    }

    public Integer getKuantitasM() {
        return kuantitasM;
    }

    public void setKuantitasM(Integer kuantitasM) {
        this.kuantitasM = kuantitasM;
    }

    public Double getHargaM() {
        return hargaM;
    }

    public void setHargaM(Double hargaM) {
        this.hargaM = hargaM;
    }

    public Double getSubTotalM() {
        return subTotalM;
    }

    public void setSubTotalM(Double subTotalM) {
        this.subTotalM = subTotalM;
    }

*/

/*
    private StringProperty nama;
    private IntegerProperty id, kuantitas;
    private DoubleProperty harga;

    public dataMenuOrder(StringProperty nama, IntegerProperty id, IntegerProperty kuantitas, DoubleProperty harga) {
        this.nama = nama;
        this.id = id;
        this.kuantitas = kuantitas;
        this.harga = harga;
    }

    public StringProperty getNama() {
        return nama;
    }

    public void setNama(StringProperty nama) {
        this.nama = nama;
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public IntegerProperty getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(IntegerProperty kuantitas) {
        this.kuantitas = kuantitas;
    }

    public DoubleProperty getHarga() {
        return harga;
    }

    public void setHarga(DoubleProperty harga) {
        this.harga = harga;
    }

 
    
    private static String idtrx;

    public dataMenuOrder() {
    }

    public static String getIdtrx() {
        return idtrx;
    }

    public static void setIdtrx(String idtrx) {
        dataMenuOrder.idtrx = idtrx;
    }
    */



/*

public dataMenuOrder(Integer idM, String namaM, Integer kuantitasM, Double hargaM) {
        this.idM = new SimpleIntegerProperty(idM);
        this.namaM = new SimpleStringProperty(namaM);
        this.kuantitasM = new SimpleIntegerProperty(kuantitasM);
        this.hargaM = new SimpleDoubleProperty(hargaM);
        
        this.subTotalM = new SimpleDoubleProperty(kuantitasM * hargaM);
        
        // Bind subTotal agar otomatis update saat kuantitas atau harga berubah
        this.subTotalM.bind(this.kuantitasM.multiply(this.hargaM));
    }
    



    // Property getters
    public StringProperty namaMProperty() {
        return namaM;
    }

    public IntegerProperty kuantitasMProperty() {
        return kuantitasM;
    }

    public DoubleProperty hargaMProperty() {
        return hargaM;
    }

    public DoubleProperty subTotalMProperty() {
        return subTotalM;
    }

    // Standard getters
    public String getNamaM() {
        return namaM.get();
    }

    public Integer getKuantitasM() {
        return kuantitasM.get();
    }

    public Double getHargaM() {
        return hargaM.get();
    }

    public Double getSubTotalM() {
        return subTotalM.get();
    }

    // Standard setters
    public void setNamaM(String nama) {
        this.namaM.set(nama);
    }

    public void setKuantitasM(Integer kuantitas) {
        this.kuantitasM.set(kuantitas);
    }

    public void setHargaM(Double harga) {
        this.hargaM.set(harga);
    }

    @Override
    public String toString() {
        return String.format("%s (%d x Rp%,.2f = Rp%,.2f)", 
            getNamaM(), getKuantitasM(), getHargaM(), getSubTotalM());
    }

*/