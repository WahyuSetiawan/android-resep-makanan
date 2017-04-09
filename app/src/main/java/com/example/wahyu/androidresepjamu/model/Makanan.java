package com.example.wahyu.androidresepjamu.model;

/**
 * Created by wahyu on 4/7/2017.
 */

public class Makanan {
    private String nama;
    private String gambar;
    private String langkah;
    private String alat;
    private String bahan;
    private Kategori kategori;

    public Makanan(String nama, String gambar, String alat, String bahan, String langkah, Kategori kategori) {
        this.nama = nama;
        this.gambar = gambar;
        this.langkah = langkah;
        this.alat = alat;
        this.bahan = bahan;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public String getGambar() {
        return gambar;
    }

    public String getLangkah() {
        return langkah;
    }

    public String getAlat() {
        return alat;
    }

    public String getBahan() {
        return bahan;
    }

    public Kategori getKategori() {
        return kategori;
    }
}
