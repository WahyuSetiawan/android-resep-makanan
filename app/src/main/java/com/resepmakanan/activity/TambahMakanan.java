package com.resepmakanan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.resepmakanan.R;
import com.resepmakanan.database.CostumDaoMaster;
import com.resepmakanan.model.Kategori;
import com.resepmakanan.model.KategoriDao;
import com.resepmakanan.model.Makanan;
import com.resepmakanan.model.MakananDao;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

import static com.resepmakanan.R.array.spinner_kategori_tambah_resep;

public class TambahMakanan extends AppCompatActivity {
    @BindView(R.id.gambarresep)
    ImageView mImageResep;
    @BindView(R.id.judulresep)
    EditText mTextJudulResep;
    @BindView(R.id.bahan)
    EditText mTextBahan;
    @BindView(R.id.langkah)
    EditText mTextCara;
    @BindView(R.id.kategori)
    Spinner mSpinnerKategori;
    @BindView(R.id.simpan)
    Button mButtonSimpan;
    @BindView(R.id.coordinator)
    ConstraintLayout mConstarintLayout;

    private Makanan makanan;
    private int requestCodeCamera = 20;

    private boolean edit = false;
    private ApaterSpinner mAdapterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_makanan);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit = getIntent().getStringExtra(getString(R.string.put_extra_tambah_makanan)) != null;

        setupComponent();
        setupData();
    }

    private void setupData() {
        List<Kategori> kategoris = CostumDaoMaster.getSession(this).getKategoriDao().queryBuilder().where(KategoriDao.Properties.Jenis.notEq(0)).list();

        mAdapterSpinner = new ApaterSpinner(this, android.R.layout.simple_spinner_item, kategoris);
        mAdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerKategori.setAdapter(mAdapterSpinner);
        makanan = new Makanan();

        if (getIntent().getStringExtra(getString(R.string.put_extra_tambah_makanan)) != null) {
            String id = getIntent().getStringExtra(getString(R.string.put_extra_tambah_makanan_id));

            makanan = CostumDaoMaster.getSession(this).getMakananDao().queryBuilder().where(MakananDao.Properties.Id.eq(id)).unique();

            File file = new File(makanan.getGambar());

            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                mImageResep.setImageBitmap(bitmap);
            }

            mTextJudulResep.setText(makanan.getJudul());
            mTextCara.setText(makanan.getLangkah());
            mTextBahan.setText(makanan.getBahan());

            int selectionSpinner = java.util.Arrays.asList(getResources().getStringArray(spinner_kategori_tambah_resep)).indexOf(makanan.getKategori());

            mSpinnerKategori.setSelection(selectionSpinner);
        }
    }

    private void setupComponent() {
        setTitle((edit) ? "Edit" : "Tambah");

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

    @OnClick(R.id.simpan)
    public void onClick(View v) {
        if (validation()) {
            String filename = storeCameraPhotoInSDCard(((BitmapDrawable) mImageResep.getDrawable()).getBitmap(), currentDateFormat());

            makanan.setJudul(mTextJudulResep.getText().toString());
            makanan.setGambar(filename);
            makanan.setBahan(mTextBahan.getText().toString());
            makanan.setLangkah(mTextCara.getText().toString());
            makanan.setKategori(mAdapterSpinner.getItem(mSpinnerKategori.getSelectedItemPosition()));

            try {
                if (!edit) {
                    long id = CostumDaoMaster.getSession(TambahMakanan.this).getMakananDao().insert(makanan);
                } else {
                    CostumDaoMaster.getSession(TambahMakanan.this).getMakananDao().update(makanan);
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(TambahMakanan.this);
                builder.setTitle("Notification");
                builder.setMessage("Penyimpanan anda berhasil");

                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        onBackPressed();
                    }
                });

                String positiveText = getString(android.R.string.ok);
                builder.setPositiveButton(positiveText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onBackPressed();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            } catch (SQLiteConstraintException e) {
                Log.e(TambahMakanan.class.getSimpleName(), "onClick: " + e.getMessage());
                Snackbar.make(mConstarintLayout, "Gagal menyimpan data makanan", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.gambarresep)
    public void onClickAmbilGambar(View v) {
        EasyImage.openChooserWithGallery(TambahMakanan.this, "Pilih Media", 10);
    }

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

                    Picasso.with(TambahMakanan.this)
                            .load(imageFile)
                            .fit()
                            .centerCrop()
                            .into(mImageResep);
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

    private String currentDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String currentTimeStamp = simpleDateFormat.format(new Date());
        return currentTimeStamp;
    }

    private String storeCameraPhotoInSDCard(Bitmap bitmap, String cuurentDate) {
        File outputFile = new File(Environment.getExternalStorageDirectory(), "photo_" + cuurentDate + ".jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            return outputFile.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public class ApaterSpinner extends ArrayAdapter<Kategori> {
        private Context context;
        private List<Kategori> kategoris;
        private LayoutInflater flater;

        public ApaterSpinner(@NonNull Activity context, int resource, List<Kategori> kategoris) {
            super(context, resource);
            this.context = context;
            this.kategoris = kategoris;
            this.flater = context.getLayoutInflater();
        }

        @Override
        public int getCount() {
            return kategoris.size();
        }

        @Nullable
        @Override
        public Kategori getItem(int position) {
            return kategoris.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = flater.inflate(R.layout.spinner_layout, null, true);
            ((TextView)view.findViewById(R.id.kategori)).setText(kategoris.get(position).getJudul());
            ((ImageView) view.findViewById(R.id.icon)).setImageBitmap(BitmapFactory.decodeFile(CostumDaoMaster.getSession(context).getKategoriDao().queryBuilder().where(KategoriDao.Properties.Id.eq(kategoris.get(position).getJenis())).unique().getIcon()));
           /* TextView label = (TextView) super.getView(position, convertView, parent);

            label.setText(kategoris.get(position).getJudul());*/
            return view;
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = flater.inflate(R.layout.spinner_layout, null, true);
            ((TextView)view.findViewById(R.id.kategori)).setText(kategoris.get(position).getJudul());
            ((ImageView) view.findViewById(R.id.icon)).setImageBitmap(BitmapFactory.decodeFile(CostumDaoMaster.getSession(context).getKategoriDao().queryBuilder().where(KategoriDao.Properties.Id.eq(kategoris.get(position).getJenis())).unique().getIcon()));
           /* TextView label = (TextView) super.getView(position, convertView, parent);

            label.setText(kategoris.get(position).getJudul());*/
            return view;
        }
    }

}
