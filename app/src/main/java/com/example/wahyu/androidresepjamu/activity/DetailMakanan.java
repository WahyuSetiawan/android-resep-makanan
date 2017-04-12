package com.example.wahyu.androidresepjamu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private int position;
    private CoordinatorLayout mCoordinatorLayout;


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
        position = getIntent().getIntExtra(getString(R.string.put_extra_detail_postition), 0);

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
                mMakanan.changeFavorite();

                if (mMakananOpenHelper.edit(mMakanan)) {
                    mMakanan = mMakananOpenHelper.select(String.valueOf(mMakanan.getId()));

                    if (mMakanan.getFavorite() > 0) {
                        Snackbar.make(view, "Resep Favorite", Snackbar.LENGTH_LONG)
                                .setAction("Ok", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        returnEdit();
                                    }
                                })
                                .show();
                    } else {
                        Snackbar.make(view, "Menghilangkan favorite dari resep", Snackbar.LENGTH_SHORT)
                                .show();
                    }
                } else {
                    Snackbar.make(view, "Gagal mengubah favorite", Snackbar.LENGTH_SHORT)
                            .setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    returnEdit();
                                }
                            })
                            .show();
                }
            }
        });
    }

    private void setupComponent() {
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        mTextNamaResep = (TextView) findViewById(R.id.text_nama_resep);
        mTextAlat = (TextView) findViewById(R.id.text_alat);
        mTextBahan = (TextView) findViewById(R.id.text_bahan_makanan);
        mTextLangkah = (TextView) findViewById(R.id.text_langkah_memasak);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pemberitahuan");
        builder.setMessage("Yakin hapus resep ini ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mMakananOpenHelper.delete(String.valueOf(mMakanan.getId()))) {
                    Snackbar.make(mImageTitle, "Resep Telah Terhapus", Snackbar.LENGTH_LONG)
                            .setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            })
                            .show();
                    returnDelete();
                } else {
                    Snackbar.make(mImageTitle, "Resep Gagal Terhapus", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void edit() {
        Intent mIntentEdit = new Intent(DetailMakanan.this, TambahMakanan.class);
        mIntentEdit.putExtra(getString(R.string.put_extra_tambah_makanan), getString(R.string.put_extra_tambah_makanan_edit));
        mIntentEdit.putExtra(getString(R.string.put_extra_tambah_makanan_id), String.valueOf(mMakanan.getId()));
        startActivityForResult(mIntentEdit, getResources().getInteger(R.integer.form_makanan));
    }

    private void returnEdit() {
        Intent data = new Intent();
        data.putExtra(getString(R.string.put_extra_detail_postition), String.valueOf(DetailMakanan.this.position));
        data.putExtra(getString(R.string.put_extra_detail), String.valueOf(mMakanan.getId()));
        setResult(getResources().getInteger(R.integer.return_edit_data), data);
        finish();
    }

    private void returnDelete() {
        Intent data = new Intent();
        data.putExtra(getString(R.string.put_extra_detail_postition), String.valueOf(DetailMakanan.this.position));
        setResult(getResources().getInteger(R.integer.return_delete_data), data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_detail_makanan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                returnEdit();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == getResources().getInteger(R.integer.form_makanan)) {
            if (resultCode == RESULT_OK) {
                Snackbar.make(mCoordinatorLayout, "Data berhasil terubah", Snackbar.LENGTH_SHORT).show();
                setupData();
            }
        }

    }
}
