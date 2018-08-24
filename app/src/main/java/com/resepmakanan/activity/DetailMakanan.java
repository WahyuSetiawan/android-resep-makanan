package com.resepmakanan.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.resepmakanan.R;
import com.resepmakanan.database.CostumDaoMaster;
import com.resepmakanan.model.Makanan;
import com.resepmakanan.model.MakananDao;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailMakanan extends AppCompatActivity {
    @BindView(R.id.coordinator)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.text_nama_resep)
    TextView mTextNamaResep;

    @BindView(R.id.fab)
    FloatingActionButton mFloathingButton;

    @BindView(R.id.text_bahan_makanan)
    TextView mTextBahan;
    @BindView(R.id.text_langkah_memasak)
    TextView mTextLangkah;
    @BindView(R.id.image_detail)
    ImageView mImageTitle;
    @BindView(R.id.fotoresep)
    ImageView mImageResep;

    private Makanan mMakanan;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_makanan);
        ButterKnife.bind(this);
        setupData();
        setupToolbar();
    }

    private void setupData() {
        mMakanan = CostumDaoMaster.getSession(this)
                .getMakananDao()
                .queryBuilder()
                .where(MakananDao.Properties.Id.eq(getIntent().getStringExtra(getString(R.string.put_extra_detail)))).unique();

        position = getIntent().getIntExtra(getString(R.string.put_extra_detail_postition), 0);

        setTitle(mMakanan.getJudul());

        mTextNamaResep.setText(mMakanan.getJudul());
        mTextBahan.setText(mMakanan.getBahan());
        mTextLangkah.setText(mMakanan.getLangkah());

        File file = new File(mMakanan.getGambar());

        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

            mImageTitle.setImageBitmap(bitmap);
            mImageResep.setImageBitmap(bitmap);
        }
    }

    @OnClick(R.id.fab)
    void setupFloating() {
        mMakanan.changeFavorite();
        //if (mMakananOpenHelper.edit(mMakanan)) {
        CostumDaoMaster.getSession(this).getMakananDao().update(mMakanan);

        if (mMakanan.getFavorite() > 0) {
            Snackbar.make(mCoordinatorLayout, "Resep Favorite", Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            returnEdit();
                        }
                    })
                    .show();
        } else {
            Snackbar.make(mCoordinatorLayout, "Menghilangkan ic_favorite dari resep", Snackbar.LENGTH_SHORT).show();
        }
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
                try {
                    CostumDaoMaster.getSession(DetailMakanan.this).getMakananDao().delete(mMakanan);

                    Snackbar.make(mImageTitle, "Resep Telah Terhapus", Snackbar.LENGTH_LONG)
                            .setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            })
                            .show();
                    returnDelete();
                } catch (SQLiteConstraintException e) {
                    Log.e(TambahMakanan.class.getSimpleName(), "onClick: " + e.getMessage());
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
