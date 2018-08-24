package com.resepmakanan.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.resepmakanan.R;
import com.resepmakanan.adapter.AdapterDaftarMakanan;
import com.resepmakanan.database.CostumDaoMaster;
import com.resepmakanan.model.DaoSession;
import com.resepmakanan.model.Kategori;
import com.resepmakanan.model.KategoriDao;
import com.resepmakanan.model.Makanan;
import com.resepmakanan.model.MakananDao;
import com.resepmakanan.other.DividerItemDecoration;
import com.resepmakanan.other.RecyclerTouchListener;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.resepmakanan.R.id.coordinator;
import static com.resepmakanan.R.id.recycler_daftar_makanan;
import static com.resepmakanan.R.id.text_notifikasi;

public class DaftarMakanan extends AppCompatActivity {
    @BindView(recycler_daftar_makanan)
    RecyclerView mRecyclerMakanan;
    @BindView(text_notifikasi)
    TextView mTextNotifikasi;
    @BindView(coordinator)
    CoordinatorLayout mCoordinatorLayout;

    private AdapterDaftarMakanan mAdapterRecycler;

    List<Makanan> mListMakanan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_makanan);
        ButterKnife.bind(this);

        setupToolbar();
        setupAdapter();
        setupData();
    }

    private void setupAdapter() {
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mAdapterRecycler = new AdapterDaftarMakanan(this);
        //mRecyclerMakanan.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerMakanan.setHasFixedSize(true);

        mRecyclerMakanan.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerMakanan, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int posititon) {
                Intent detailIntent = new Intent(DaftarMakanan.this, DetailMakanan.class);

                detailIntent.putExtra(getString(R.string.put_extra_detail), String.valueOf(mListMakanan.get(posititon).getId()));
                detailIntent.putExtra(getString(R.string.put_extra_detail_postition), posititon);

                startActivityForResult(detailIntent, getResources().getInteger(R.integer.detail_open));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mRecyclerMakanan.setLayoutManager(mLinearLayoutManager);
        mRecyclerMakanan.setAdapter(mAdapterRecycler);
    }

    private void setupData() {
        String keterangan = "";

        DaoSession daoSession = CostumDaoMaster.getSession(this);

        if (getIntent().getLongExtra(getString(R.string.put_extra_kategori), 0) != 0) {
            long kategori = getIntent().getLongExtra(getString(R.string.put_extra_kategori),0);
            Kategori kategori1 = daoSession.getKategoriDao().queryBuilder().where(KategoriDao.Properties.Id.eq(kategori)).unique();

            setTitle(StringUtils.capitalize(kategori1.getJudul()));

            mListMakanan = daoSession.getMakananDao().queryBuilder().where(MakananDao.Properties.Id_kategori.eq(kategori)).list();

            keterangan = "Tidak tedapat data " + kategori1.getJudul();
        }

        if (getIntent().getStringExtra(getString(R.string.put_extra_favorite)) != null) {
            setTitle(StringUtils.capitalize("Favorite"));

            mListMakanan = daoSession.getMakananDao().queryBuilder().where(MakananDao.Properties.Favorite.eq(1)).list();

            keterangan = "Tidak resep Favorite";
        }

        if (mListMakanan.size() == 0) {
            mTextNotifikasi.setVisibility(View.VISIBLE);
            mRecyclerMakanan.setVisibility(View.GONE);
            mTextNotifikasi.setText(keterangan);
        } else {
            mRecyclerMakanan.setVisibility(View.VISIBLE);
            mTextNotifikasi.setVisibility(View.GONE);
        }

        mAdapterRecycler.setMakanans(mListMakanan);
        mAdapterRecycler.notifyDataSetChanged();
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == getResources().getInteger(R.integer.detail_open)) {
            if (resultCode == getResources().getInteger(R.integer.return_delete_data)) {
                String id = data.getStringExtra(getString(R.string.put_extra_detail_postition));
                mAdapterRecycler.remove(Integer.valueOf(id));

                Snackbar.make(mCoordinatorLayout, "", Snackbar.LENGTH_SHORT).show();
            }

            if (resultCode == getResources().getInteger(R.integer.return_edit_data)) {
                String idData = data.getStringExtra(getString(R.string.put_extra_detail));
                String position = data.getStringExtra(getString(R.string.put_extra_detail_postition));

                Makanan makanan = CostumDaoMaster.getSession(this).getMakananDao().queryBuilder().where(MakananDao.Properties.Id.eq(idData)).unique();

                mAdapterRecycler.edit(Integer.parseInt(position), makanan);
            }
        }
    }
}
