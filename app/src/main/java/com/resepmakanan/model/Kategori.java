package com.resepmakanan.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Kategori {
    @Id(autoincrement = true)
    private long id;
    @Unique
    private String judul;
    private String gambar;
    private String icon;
    private int jenis;

    @Keep
    public Kategori(long id, String judul, String gambar, String icon, int jenis) {
        this.id = id;
        this.judul = judul;
        this.gambar = gambar;
        this.icon = icon;
        this.jenis = jenis;
    }

    @Generated(hash = 1434439002)
    public Kategori() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getJenis() {
        return jenis;
    }

    public void setJenis(int jenis) {
        this.jenis = jenis;
    }
}
