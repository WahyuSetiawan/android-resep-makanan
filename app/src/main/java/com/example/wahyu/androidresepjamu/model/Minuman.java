package com.example.wahyu.androidresepjamu.model;

/**
 * Created by wahyu on 4/7/2017.
 */

public class Minuman {
    private String nama;
    private String gambar;

    public Minuman(String nama, String gambar) {
        this.nama = nama;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public String getGambar() {
        return gambar;
    }
}