package com.example.wahyu.androidresepjamu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.ViewGroup;

import com.example.wahyu.androidresepjamu.R;
import com.example.wahyu.androidresepjamu.model.Kategori;
import com.example.wahyu.androidresepjamu.model.Makanan;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by wahyu on 4/7/2017.
 */

public class MakananOpenHelper extends Database {
    private final Context context;
    private SQLiteDatabase mSQLLite;

    public MakananOpenHelper(Context context) {
        super(context);

        this.context = context;
    }

    @Override
    public void childOnCreate(SQLiteDatabase db) {
        db.execSQL("create table resep (" +
                "id integer primary key autoincrement," +
                "judul char(50) not null," +
                "gambar text," +
                "alat text not null," +
                "bahan text not null," +
                "langkah text not null," +
                "kategori char(15) not null," +
                "favorite int not null" +
                ")");
    }

    @Override
    public void childOnUpgrade(SQLiteDatabase db) {
        db.execSQL("drop table if exists resep");
        onCreate(db);
    }

    public boolean insert(Makanan makanan) {
        mSQLLite = this.getWritableDatabase();

        ContentValues mval = new ContentValues();
        mval.put(context.getString(R.string.database_resep_judul), makanan.getNama());
        mval.put(context.getString(R.string.database_resep_gambar), makanan.getGambar());
        mval.put(context.getString(R.string.database_resep_alat), makanan.getAlat());
        mval.put(context.getString(R.string.database_resep_bahan), makanan.getBahan());
        mval.put(context.getString(R.string.database_resep_langkah), makanan.getLangkah());
        mval.put(context.getString(R.string.database_resep_kategori), makanan.getKategori().toString());
        mval.put(context.getString(R.string.database_resep_favorite), String.valueOf(makanan.getFavorite()));

        Long i = mSQLLite.insert(context.getString(R.string.database_resep_table), null, mval);

        Log.d("Values of I = ", "******************* " + i + " ***************");

        mSQLLite.close();

        if (i == 1) {
            return true;
        }

        return false;
    }

    public boolean edit(Makanan makanan) {

        mSQLLite = this.getWritableDatabase();

        ContentValues mval = new ContentValues();
        mval.put(context.getString(R.string.database_resep_judul), makanan.getNama());
        mval.put(context.getString(R.string.database_resep_gambar), makanan.getGambar());
        mval.put(context.getString(R.string.database_resep_alat), makanan.getAlat());
        mval.put(context.getString(R.string.database_resep_bahan), makanan.getBahan());
        mval.put(context.getString(R.string.database_resep_langkah), makanan.getLangkah());
        mval.put(context.getString(R.string.database_resep_kategori), makanan.getKategori().toString());
        mval.put(context.getString(R.string.database_resep_favorite), String.valueOf(makanan.getFavorite()));

        return mSQLLite.update(context.getString(R.string.database_resep_table), mval, context.getString(R.string.database_resep_id) + " = " + String.valueOf(makanan.getId()), null) > 0;
    }

    public boolean delete(String id) {
        mSQLLite = this.getWritableDatabase();

        int returnDeleteInt = mSQLLite.delete(context.getString(R.string.database_resep_table), context.getString(R.string.database_resep_id) + " = " + id, null);

        mSQLLite.close();

        return returnDeleteInt > 0;
    }

    public Makanan select(String id) {
        mSQLLite = this.getWritableDatabase();
        Makanan makanan = null;

        Cursor alldata = mSQLLite.query(context.getString(R.string.database_resep_table),
                new String[]{
                        context.getString(R.string.database_resep_id),
                        context.getString(R.string.database_resep_judul),
                        context.getString(R.string.database_resep_gambar),
                        context.getString(R.string.database_resep_alat),
                        context.getString(R.string.database_resep_bahan),
                        context.getString(R.string.database_resep_langkah),
                        context.getString(R.string.database_resep_kategori),
                        context.getString(R.string.database_resep_favorite)
                }, context.getString(R.string.database_resep_id) + " = "+ id, null, null, null, null);

        if (alldata != null) {
            alldata.moveToFirst();
            makanan = new Makanan(
                    alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_id))),
                    alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_judul))),
                    alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_gambar))),
                    alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_alat))),
                    alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_bahan))),
                    alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_langkah))),
                    Kategori.valueOf(alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_kategori)))),
                    alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_favorite)))
            );
        }

        mSQLLite.close();

        return makanan;
    }

    public ArrayList<Makanan> selectAll() {
        mSQLLite = this.getWritableDatabase();
        ArrayList<Makanan> makanans = new ArrayList<>();

        Cursor alldata = mSQLLite.query(context.getString(R.string.database_resep_table),
                new String[]{
                        context.getString(R.string.database_resep_id),
                        context.getString(R.string.database_resep_judul),
                        context.getString(R.string.database_resep_gambar),
                        context.getString(R.string.database_resep_alat),
                        context.getString(R.string.database_resep_bahan),
                        context.getString(R.string.database_resep_langkah),
                        context.getString(R.string.database_resep_kategori),
                        context.getString(R.string.database_resep_favorite)
                }, null, null, null, null, null);

        if (alldata.moveToFirst()) {
            while (!alldata.isAfterLast()) {
                Log.d("Values of alldata = ", "******************* " + DatabaseUtils.dumpCursorToString(alldata) + " ***************");

                Makanan makanan = new Makanan(
                        alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_id))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_judul))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_gambar))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_alat))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_bahan))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_langkah))),
                        Kategori.valueOf(alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_kategori)))),
                        alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_favorite)))
                );

                makanans.add(makanan);

                alldata.moveToNext();
            }
        }

        mSQLLite.close();

        return makanans;
    }

    public ArrayList<Makanan> selectAllFromKategori(String kategori) {
        mSQLLite = this.getWritableDatabase();
        ArrayList<Makanan> makanans = new ArrayList<>();

        Cursor alldata = mSQLLite.query(context.getString(R.string.database_resep_table),
                new String[]{
                        context.getString(R.string.database_resep_id),
                        context.getString(R.string.database_resep_judul),
                        context.getString(R.string.database_resep_gambar),
                        context.getString(R.string.database_resep_alat),
                        context.getString(R.string.database_resep_bahan),
                        context.getString(R.string.database_resep_langkah),
                        context.getString(R.string.database_resep_kategori),
                        context.getString(R.string.database_resep_favorite)
                }, context.getString(R.string.database_resep_kategori) + "  = \"" + kategori + "\"", null, null, null, null);

        if (alldata.moveToFirst()) {
            while (!alldata.isAfterLast()) {
                Log.d("Values of alldata = ", "******************* " + DatabaseUtils.dumpCursorToString(alldata) + " ***************");

                Makanan makanan = new Makanan(
                        alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_id))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_judul))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_gambar))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_alat))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_bahan))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_langkah))),
                        Kategori.valueOf(alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_kategori)))),
                        alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_favorite)))
                );

                makanans.add(makanan);

                alldata.moveToNext();
            }
        }

        mSQLLite.close();

        return makanans;
    }

    public ArrayList<Makanan> selectAllFavorite() {
        mSQLLite = this.getWritableDatabase();
        ArrayList<Makanan> makanans = new ArrayList<>();

        Cursor alldata = mSQLLite.query(context.getString(R.string.database_resep_table),
                new String[]{
                        context.getString(R.string.database_resep_id),
                        context.getString(R.string.database_resep_judul),
                        context.getString(R.string.database_resep_gambar),
                        context.getString(R.string.database_resep_alat),
                        context.getString(R.string.database_resep_bahan),
                        context.getString(R.string.database_resep_langkah),
                        context.getString(R.string.database_resep_kategori),
                        context.getString(R.string.database_resep_favorite)
                }, context.getString(R.string.database_resep_favorite) + " = 1", null, null, null, null);

        if (alldata.moveToFirst()) {
            while (!alldata.isAfterLast()) {
                Log.d("Values of alldata = ", "******************* " + DatabaseUtils.dumpCursorToString(alldata) + " ***************");

                Makanan makanan = new Makanan(
                        alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_id))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_judul))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_gambar))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_alat))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_bahan))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_langkah))),
                        Kategori.valueOf(alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_kategori)))),
                        alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_favorite)))
                );

                makanans.add(makanan);

                alldata.moveToNext();
            }
        }

        mSQLLite.close();

        return makanans;
    }

}
