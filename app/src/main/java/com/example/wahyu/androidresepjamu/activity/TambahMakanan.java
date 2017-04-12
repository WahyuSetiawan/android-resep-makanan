package com.example.wahyu.androidresepjamu.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.wahyu.androidresepjamu.R;
import com.example.wahyu.androidresepjamu.database.MakananOpenHelper;
import com.example.wahyu.androidresepjamu.model.Kategori;
import com.example.wahyu.androidresepjamu.model.Makanan;
import com.nekoloop.base64image.Base64Image;
import com.nekoloop.base64image.RequestDecode;
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
    private ConstraintLayout mConstarintLayout;

    private ArrayAdapter<CharSequence> mAdapterSpinner;

    private MakananOpenHelper mDatabaseMakanan;
    private Makanan makanan;


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
        mAdapterSpinner = ArrayAdapter.createFromResource(this, spinner_kategori_tambah_resep, R.layout.spinner_item);
        mAdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerKategori.setAdapter(mAdapterSpinner);

        if (getIntent().getStringExtra(getString(R.string.put_extra_tambah_makanan)) != null) {
            String id = getIntent().getStringExtra(getString(R.string.put_extra_tambah_makanan_id));

            makanan = mDatabaseMakanan.select(id);

            Base64Image.with(this)
                    .decode(makanan.getGambar())
                    .into(new RequestDecode.Decode() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            mTextJudulResep.setText(makanan.getNama());
                            mTextAlat.setText(makanan.getAlat());
                            mTextCara.setText(makanan.getLangkah());
                            mTextBahan.setText(makanan.getBahan());
                            mImageResep.setImageBitmap(bitmap);

                            int selectionSpinner = java.util.Arrays.asList(getResources().getStringArray(spinner_kategori_tambah_resep)).indexOf(makanan.getKategori());

                            mSpinnerKategori.setSelection(selectionSpinner);
                        }

                        @Override
                        public void onFailure() {

                        }
                    });
        }
    }

    private void setupComponent() {
        mConstarintLayout = (ConstraintLayout) findViewById(R.id.coordinator);

        mImageResep = (ImageView) findViewById(R.id.gambarresep);
        mTextJudulResep = (EditText) findViewById(R.id.judulresep);
        mTextAlat = (EditText) findViewById(R.id.alat);
        mTextBahan = (EditText) findViewById(R.id.bahan);
        mTextCara = (EditText) findViewById(R.id.langkah);
        mSpinnerKategori = (Spinner) findViewById(R.id.kategori);
        mButtonSimpan = (Button) findViewById(R.id.simpan);

        mImageResep.setOnClickListener(mListenerAmbilGambar);

        if (getIntent().getStringExtra(getString(R.string.put_extra_tambah_makanan)) != null) {
            setTitle("Edit");
            mButtonSimpan.setOnClickListener(mListenerEdit);
        } else {
            setTitle("Tambah");
            mButtonSimpan.setOnClickListener(mListenerSimpan);
        }

        EasyImage.configuration(this).setImagesFolderName("ResepMakanan");
    }

    private boolean validation() {
        if (mTextJudulResep.length() == 0) {
            mTextJudulResep.setError("Judul harus disertakan");
            return false;
        }

        if (mImageResep.getDrawable() == null) {
            Snackbar.make(getWindow().getDecorView().getRootView(), "Tidak Ada Gambar yang disertakan", Snackbar.LENGTH_SHORT)
                    .show();
            return false;
        }

        return true;
    }

    private void setupDatabase() {
        mDatabaseMakanan = new MakananOpenHelper(this);
    }

    private void setupToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private View.OnClickListener mListenerSimpan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (validation()) {
                Base64Image.with(TambahMakanan.this).encode(((BitmapDrawable) mImageResep.getDrawable()).getBitmap()).into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String returnbase64imagestring) {
                        if (mDatabaseMakanan.insert(
                                new Makanan(
                                        mTextJudulResep.getText().toString(),
                                        returnbase64imagestring,
                                        mTextAlat.getText().toString(),
                                        mTextBahan.getText().toString(),
                                        mTextCara.getText().toString(),
                                        Kategori.valueOf(mSpinnerKategori.getSelectedItem().toString().toUpperCase())
                                )
                        )) {
                            Snackbar.make(mConstarintLayout, "Penyimpanan Berhasil", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Snackbar.make(mConstarintLayout, "Gagal Pemnyimpanan", Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure() {
                        Snackbar.make(mConstarintLayout, "Gagal Pemnyimpanan", Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        }
    };

    private View.OnClickListener mListenerEdit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (validation()) {
                Base64Image.with(TambahMakanan.this).encode(((BitmapDrawable) mImageResep.getDrawable()).getBitmap()).into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String returnimagebase64) {
                        if (mDatabaseMakanan.edit(
                                new Makanan(
                                        makanan.getId(),
                                        mTextJudulResep.getText().toString(),
                                        returnimagebase64,
                                        mTextAlat.getText().toString(),
                                        mTextBahan.getText().toString(),
                                        mTextCara.getText().toString(),
                                        Kategori.valueOf(mSpinnerKategori.getSelectedItem().toString().toUpperCase()),
                                        makanan.getFavorite()
                                )
                        )) {
                            Intent data = new Intent();
                            data.putExtra(getString(R.string.put_extra_status), getString(android.R.string.ok));
                            setResult(RESULT_OK, data);
                            finish();
                        } else {
                            Snackbar.make(mConstarintLayout, "Gagal melakukan perubahan data", Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }
        }
    };

    private View.OnClickListener mListenerAmbilGambar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EasyImage.openChooserWithGallery(TambahMakanan.this, "Pilih Media", 10);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent data = new Intent();
                setResult(getResources().getInteger(R.integer.form_makanan), data);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
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
