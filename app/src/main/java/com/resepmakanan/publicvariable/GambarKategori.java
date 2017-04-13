package com.resepmakanan.publicvariable;

import com.resepmakanan.R;

/**
 * Created by wahyu on 4/13/2017.
 */

public enum GambarKategori {
    makanan("Makanan", R.drawable.makanan),
    minuman("Minuman", R.drawable.minuman),
    kue("Kue", R.drawable.kue);

    private String nama;
    private int image;

    private GambarKategori(String nama, int drawable) {
        this.nama = nama;
        this.image = drawable;
    }

    public int getImage() {
        return this.image;
    }

    public String getNama(){
        return this.nama;
    }
}
