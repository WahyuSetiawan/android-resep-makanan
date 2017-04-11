package com.example.wahyu.androidresepjamu.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wahyu.androidresepjamu.R;
import com.example.wahyu.androidresepjamu.database.MakananOpenHelper;
import com.example.wahyu.androidresepjamu.model.Makanan;
import com.nekoloop.base64image.Base64Image;
import com.nekoloop.base64image.RequestDecode;

public class DetailMakanan extends AppCompatActivity {
    private FloatingActionButton mFloathingButton;
    private TextView mTextNamaResep,
            mTextAlat,
            mTextBahan,
            mTextLangkah;
    private ImageView mImageTitle;

    private MakananOpenHelper mMakananOpenHelper;
    private Makanan mMakanan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_makanan);

        setupDatabase();

        setupToolbar();

        setupComponent();

        setupFloating();

        setupData();

    }

    private void setupData() {
        mMakanan = mMakananOpenHelper.select(getIntent().getStringExtra(getString(R.string.put_extra_detail)));

        setTitle(mMakanan.getNama());

        mTextNamaResep.setText(mMakanan.getNama());
        mTextBahan.setText(mMakanan.getBahan());
        mTextAlat.setText(mMakanan.getAlat());
        mTextLangkah.setText(mMakanan.getLangkah());

        Base64Image.with(this)
                .decode(mMakanan.getGambar())
                .into(new RequestDecode.Decode() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        mImageTitle.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure() {

                    }
                });
    }

    private void setupFloating() {
        mFloathingButton = (FloatingActionButton) findViewById(R.id.fab);

        mFloathingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setupComponent() {
        mTextNamaResep = (TextView) findViewById(R.id.text_nama_resep);
        mTextAlat = (TextView) findViewById(R.id.text_alat);
        mTextBahan = (TextView) findViewById(R.id.text_bahan_makanan);
        mTextLangkah = (TextView) findViewById(R.id.text_bahan_makanan);
        mImageTitle = (ImageView) findViewById(R.id.image_detail);
    }

    private void setupDatabase() {
        mMakananOpenHelper = new MakananOpenHelper(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void hapus() {
        Snackbar.make(this.mFloathingButton, "", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    private void edit() {
        Snackbar.make(this.mFloathingButton, "", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_detail_makanan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_edit:
                edit();
                break;
            case R.id.action_delete:
                hapus();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
