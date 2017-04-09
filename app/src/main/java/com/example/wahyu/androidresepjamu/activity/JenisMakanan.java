package com.example.wahyu.androidresepjamu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.wahyu.androidresepjamu.R;
import com.example.wahyu.androidresepjamu.adapter.AdapterJenisMakanan;

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
