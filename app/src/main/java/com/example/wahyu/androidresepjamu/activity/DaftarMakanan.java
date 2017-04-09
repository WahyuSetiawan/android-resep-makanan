package com.example.wahyu.androidresepjamu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wahyu.androidresepjamu.R;
import com.example.wahyu.androidresepjamu.adapter.AdapterDetailMakanan;
import com.example.wahyu.androidresepjamu.database.Database;
import com.example.wahyu.androidresepjamu.database.MakananOpenHelper;

import org.w3c.dom.Text;

import static com.example.wahyu.androidresepjamu.R.id.recycler_daftar_makanan;
import static com.example.wahyu.androidresepjamu.R.id.text_notifikasi;

public class DaftarMakanan extends AppCompatActivity {
    private RecyclerView mRecyclerMakanan;
    private TextView mTextNotifikasi;

    private Database database;

    private AdapterDetailMakanan mAdapterRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_makanan);

        setupDatabase();

        setupToolbar();

        setupComponent();

        setupData();
    }

    private void setupData() {
        if (getIntent().getStringExtra(getString(R.string.put_extra_kategori)).equals(getString(R.string.put_extra_makanan_data))) {
            setTitle(getString(R.string.makanan));
            mTextNotifikasi.setText("Tidak tedapat data makanan");
        }

        if (getIntent().getStringExtra(getString(R.string.put_extra_kategori)).equals(getString(R.string.put_extra_minuman_data))) {
            setTitle(getString(R.string.minuman));
            mTextNotifikasi.setText("Tidak terdapat data minuman");
        }

        if (getIntent().getStringExtra(getString(R.string.put_extra_kategori)).equals(getString(R.string.put_extra_kue_data))) {
            setTitle(getString(R.string.kue));
            mTextNotifikasi.setText("Tidak terdapat data kue");
        }
    }

    private void setupComponent() {
        mRecyclerMakanan = (RecyclerView) findViewById(recycler_daftar_makanan);

        mTextNotifikasi = (TextView) findViewById(text_notifikasi);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mAdapterRecycler = new AdapterDetailMakanan();

        mRecyclerMakanan.setLayoutManager(mLinearLayoutManager);
        mRecyclerMakanan.setAdapter(mAdapterRecycler);
    }

    private void setupDatabase() {
        database = new MakananOpenHelper(this);

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
