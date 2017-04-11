package com.example.wahyu.androidresepjamu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by wahyu on 4/7/2017.
 */

public abstract class Database extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 3;
    static final String DATABASE_NAME = "databaseresepmakanan";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        childOnCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        childOnUpgrade(db);
    }

    public abstract void childOnCreate(SQLiteDatabase db);

    public abstract void childOnUpgrade(SQLiteDatabase db);
}
