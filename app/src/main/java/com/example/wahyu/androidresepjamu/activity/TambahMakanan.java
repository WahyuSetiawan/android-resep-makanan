package com.example.wahyu.androidresepjamu.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wahyu.androidresepjamu.R;
import com.example.wahyu.androidresepjamu.database.MakananOpenHelper;
import com.example.wahyu.androidresepjamu.model.Kategori;
import com.example.wahyu.androidresepjamu.model.Makanan;
import com.nekoloop.base64image.Base64Image;
import com.nekoloop.base64image.RequestEncode;
import com.squareup.picasso.Picasso;

import java.io.File;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

import static com.example.wahyu.androidresepjamu.R.array.spinner_kategori_tambah_resep;

public class TambahMakanan extends AppCompatActivity {
    private ImageView mImageResep;
    private EditText mTextJudulResep,
            mTextAlat,
            mTextBahan,
            mTextCara;
    private Spinner mSpinnerKategori;
    private Button mButtonSimpan;

    private ArrayAdapter<CharSequence> mAdapterSpinner;

    private View.OnClickListener mListenerSimpan;
    private View.OnClickListener mListenerAmbilGambar;

    private MakananOpenHelper mDatabaseMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_makanan);

        setupDatabase();

        setupComponent();

        setupToolbar();

        setupData();
    }

    private void setupData() {
        mAdapterSpinner = ArrayAdapter.createFromResource(this, spinner_kategori_tambah_resep, android.R.layout.simple_spinner_item);
        mAdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerKategori.setAdapter(mAdapterSpinner);
    }

    private void setupComponent() {
        mListenerAmbilGambar = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openChooserWithGallery(TambahMakanan.this, "Pilih Media", 10);
            }
        };

        mListenerSimpan = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageResep.getDrawable() != null) {

                    Base64Image.with(TambahMakanan.this)
                            .encode(((BitmapDrawable) mImageResep.getDrawable()).getBitmap())
                            .into(new RequestEncode.Encode() {
                                      @Override
                                      public void onSuccess(String returnbase64imagestring) {
                                          if (validation()) {
                                              if (mDatabaseMakanan.insert(
                                                      new Makanan(
                                                              0,
                                                              mTextJudulResep.getText().toString(),
                                                              returnbase64imagestring,
                                                              mTextAlat.getText().toString(),
                                                              mTextBahan.getText().toString(),
                                                              mTextCara.getText().toString(),
                                                              Kategori.valueOf(mSpinnerKategori.getSelectedItem().toString().toUpperCase()),
                                                              0
                                                      )
                                              )) {
                                                  Toast.makeText(TambahMakanan.this, "Data resep makanan telah tersimpan", Toast.LENGTH_SHORT).show();
                                              } else {
                                                  Toast.makeText(TambahMakanan.this, "Data resep gagal tersimpan", Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                      }

                                      @Override
                                      public void onFailure() {
                                          Toast.makeText(TambahMakanan.this, "Mengubah gambar gagal", Toast.LENGTH_SHORT).show();
                                      }
                                  }
                            );
                }
            }
        };

        mImageResep = (ImageView) findViewById(R.id.gambarresep);
        mTextJudulResep = (EditText) findViewById(R.id.judulresep);
        mTextAlat = (EditText) findViewById(R.id.alat);
        mTextBahan = (EditText) findViewById(R.id.bahan);
        mTextCara = (EditText) findViewById(R.id.langkah);
        mSpinnerKategori = (Spinner) findViewById(R.id.kategori);
        mButtonSimpan = (Button) findViewById(R.id.simpan);

        mImageResep.setOnClickListener(mListenerAmbilGambar);
        mButtonSimpan.setOnClickListener(mListenerSimpan);

        EasyImage.configuration(this).setImagesFolderName("ResepMakanan");
    }

    private boolean validation() {
        if (mTextJudulResep.length() == 0) {
            mTextJudulResep.setError("Judul harus disertakan");
            return false;
        }

        return true;
    }

    private void setupDatabase() {
        mDatabaseMakanan = new MakananOpenHelper(this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            EasyImage.handleActivityResult(requestCode, resultCode, data, TambahMakanan.this, new DefaultCallback() {
                @Override
                public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                    if (mImageResep instanceof ImageView) {
                        Picasso.with(TambahMakanan.this)
                                .load(imageFile)
                                .fit()
                                .centerCrop()
                                .into(mImageResep);
                    }
                }

                @Override
                public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                    super.onImagePickerError(e, source, type);
                    e.printStackTrace();
                }

                @Override
                public void onCanceled(EasyImage.ImageSource source, int type) {
                    super.onCanceled(source, type);
                    if (source == EasyImage.ImageSource.CAMERA) {
                        File photoFile = EasyImage.lastlyTakenButCanceledPhoto(TambahMakanan.this);
                        if (photoFile != null) photoFile.delete();
                    }
                }
            });
        }
    }
}
