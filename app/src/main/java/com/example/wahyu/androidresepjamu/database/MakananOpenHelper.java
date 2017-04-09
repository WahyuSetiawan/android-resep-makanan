package com.example.wahyu.androidresepjamu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.wahyu.androidresepjamu.model.Makanan;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by wahyu on 4/7/2017.
 */

public class MakananOpenHelper extends Database {

    public MakananOpenHelper(Context context) {
        super(context);
    }

    @Override
    public void childOnCreate(SQLiteDatabase db) {

    }

    @Override
    public void childOnUpgrade(SQLiteDatabase db) {

    }

    public boolean insert(Makanan makanan) {
        System.out.println(makanan.getNama());

        return false;
    }

    public boolean edit(Objects objects, String id) {
        return false;
    }

    public boolean delete(String id) {
        return false;
    }

    public Objects select(String id) {
        return null;
    }

    public ArrayList selectAll() {
        return null;
    }

    public void coba() {

    }


}
