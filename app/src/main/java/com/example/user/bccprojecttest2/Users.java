package com.example.user.bccprojecttest2;

public class Users {
    private String nama;
    private int umur;
    private String jenisKelamin;

    public Users(){

    }
    public Users(String nama,int umur, String jenisKelamin){
        this.nama = nama;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
    }

    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }
}
