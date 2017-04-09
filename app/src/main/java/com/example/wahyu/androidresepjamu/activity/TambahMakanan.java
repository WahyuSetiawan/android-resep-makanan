package com.example.wahyu.androidresepjamu.activity;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.wahyu.androidresepjamu.R;
import com.example.wahyu.androidresepjamu.database.Database;
import com.example.wahyu.androidresepjamu.database.MakananOpenHelper;
import com.example.wahyu.androidresepjamu.model.Kategori;
import com.example.wahyu.androidresepjamu.model.Makanan;

public class TambahMakanan extends AppCompatActivity {
    private ImageView mImageResep;
    private EditText mTextJudulResep,
            mTextAlat,
            mTextBahan,
            mTextCara;
    private Spinner mSpinnerKategori;
    private Button mButtonSimpan;

    View.OnClickListener mListenerSimpan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("debug_resep_makanan", mTextJudulResep.getText().toString());

            mDatabaseMakanan.insert(
                    new Makanan(
                            mTextJudulResep.getText().toString(),
                            "",
                            mTextAlat.getText().toString(),
                            mTextBahan.getText().toString(),
                            mTextCara.getText().toString(),
                            Kategori.MAKANAN
                    )
            );
        }
    };

    private MakananOpenHelper mDatabaseMakanan;
    private Makanan mMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_makanan);

        setupDatabse();

        setupComponent();

        setupToolbar();

        setupData();
    }

    private void setupData() {

    }

    private void setupComponent() {
        mImageResep = (ImageView) findViewById(R.id.gambarresep);
        mTextJudulResep = (EditText) findViewById(R.id.judulresep);
        mTextAlat = (EditText) findViewById(R.id.alat);
        mTextBahan = (EditText) findViewById(R.id.bahan);
        mTextCara = (EditText) findViewById(R.id.langkah);
        mSpinnerKategori = (Spinner) findViewById(R.id.kategori);
        mButtonSimpan = (Button) findViewById(R.id.simpan);

        mButtonSimpan.setOnClickListener(mListenerSimpan);

        mDatabaseMakanan = new MakananOpenHelper(this);
    }

    private void setupDatabse() {

    }

    private void setupToolbar() {
        setTitle("Tambah");
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
