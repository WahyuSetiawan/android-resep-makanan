package com.example.wahyu.androidresepjamu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.wahyu.androidresepjamu.R;
import com.example.wahyu.androidresepjamu.adapter.AdapterDaftarMakanan;
import com.example.wahyu.androidresepjamu.database.MakananOpenHelper;
import com.example.wahyu.androidresepjamu.model.Kategori;
import com.example.wahyu.androidresepjamu.model.Makanan;

import java.util.ArrayList;

import static com.example.wahyu.androidresepjamu.R.id.recycler_daftar_makanan;
import static com.example.wahyu.androidresepjamu.R.id.text_notifikasi;

public class DaftarMakanan extends AppCompatActivity {
    private RecyclerView mRecyclerMakanan;
    private TextView mTextNotifikasi;

    private AdapterDaftarMakanan mAdapterRecycler;

    private MakananOpenHelper mMakananOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_makanan);

        setupDatabase();

        setupToolbar();

        setupComponent();

        setupAdapter();

        setupData();
    }

    private void setupAdapter() {
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mAdapterRecycler = new AdapterDaftarMakanan(this);

        mRecyclerMakanan.setLayoutManager(mLinearLayoutManager);
        mRecyclerMakanan.setAdapter(mAdapterRecycler);
    }

    private void setupData() {
        ArrayList<Makanan> mListMakanan = null;
        String keterangan = "";
        if (getIntent().getStringExtra(getString(R.string.put_extra_kategori)).equals(Kategori.MAKANAN.toString())) {
            setTitle(getString(R.string.makanan));

            mListMakanan = mMakananOpenHelper.selectAllFromKategori(Kategori.MAKANAN.toString());

            keterangan = "Tidak tedapat data makanan";
        }

        if (getIntent().getStringExtra(getString(R.string.put_extra_kategori)).equals(Kategori.MINUMAN.toString())) {
            setTitle(getString(R.string.minuman));
            keterangan = "Tidak terdapat data minuman";

            mListMakanan = mMakananOpenHelper.selectAllFromKategori(Kategori.MINUMAN.toString());
        }

        if (getIntent().getStringExtra(getString(R.string.put_extra_kategori)).equals(Kategori.KUE.toString())) {
            setTitle(getString(R.string.kue));
            keterangan = "Tidak terdapat data kue";

            mListMakanan = mMakananOpenHelper.selectAllFromKategori(Kategori.KUE.toString());
        }

        if (mListMakanan.size() == 0) {
            mTextNotifikasi.setVisibility(View.VISIBLE);
            mRecyclerMakanan.setVisibility(View.GONE);
            mTextNotifikasi.setText(keterangan);
        } else {
            mRecyclerMakanan.setVisibility(View.VISIBLE);
            mTextNotifikasi.setVisibility(View.GONE);
        }

        mAdapterRecycler.setMakanans(mListMakanan);
        mAdapterRecycler.notifyDataSetChanged();
    }

    private void setupComponent() {
        mRecyclerMakanan = (RecyclerView) findViewById(recycler_daftar_makanan);
        mTextNotifikasi = (TextView) findViewById(text_notifikasi);
    }

    private void setupDatabase() {
        mMakananOpenHelper = new MakananOpenHelper(this);

    }

    private void setupToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
