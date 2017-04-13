package com.resepmakanan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.resepmakanan.R;
import com.resepmakanan.adapter.AdapterJenisMakanan;
import com.resepmakanan.model.Kategori;
import com.resepmakanan.other.RecyclerTouchListener;

public class JenisMakanan extends AppCompatActivity {
    private AdapterJenisMakanan mAdapterJenisMakanan;

    private RecyclerView mRecyclerJenisMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_makanan);

        setupDatabase();

        setupComponent();

        setupToolbar();
    }

    private void setupDatabase() {

    }

    private void setupComponent() {
        mRecyclerJenisMakanan = (RecyclerView) findViewById(R.id.jenis_makanan_activity);

        LinearLayoutManager mLayourManagerRecycler = new LinearLayoutManager(this);
        mLayourManagerRecycler.setOrientation(LinearLayoutManager.VERTICAL);

        mAdapterJenisMakanan = new AdapterJenisMakanan();

        mRecyclerJenisMakanan.setLayoutManager(mLayourManagerRecycler);
        mRecyclerJenisMakanan.setAdapter(mAdapterJenisMakanan);
        mRecyclerJenisMakanan.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerJenisMakanan, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int posititon) {
                Intent intent = new Intent(JenisMakanan.this, DaftarMakanan.class);
                intent.putExtra(getString(R.string.put_extra_kategori), Kategori.values()[posititon].toString());
                JenisMakanan.this.startActivityForResult(intent, getResources().getInteger(R.integer.daftar_makanan));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void setupToolbar() {
        setTitle("Jenis Makanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
