package com.kedai.temeji;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
public class dataUser {
    private Integer id_user;
    private String username;
    private String password;
    private String level;
    private String nama_asli;  

    public dataUser(Integer id_user, String username, String password, String level, String nama_asli ) {
        this.id_user = id_user;
        this.username = username;
        this.password= password;
        this.level = level;
        this.nama_asli = nama_asli;
    }

    public Integer getId_user() {
        return id_user;
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public String getLevel(){
        return level;
    }

    public String getNama_asli() {
        return nama_asli;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setNama_asli(String nama_asli) {
        this.nama_asli = nama_asli;
    }
    
    
}
