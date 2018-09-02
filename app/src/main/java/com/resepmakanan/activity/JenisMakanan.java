package com.resepmakanan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.resepmakanan.R;
import com.resepmakanan.adapter.AdapterJenisMakanan;
import com.resepmakanan.database.CostumDaoMaster;
import com.resepmakanan.global.KategoriEnum;
import com.resepmakanan.model.Kategori;
import com.resepmakanan.model.KategoriDao;
import com.resepmakanan.other.RecyclerTouchListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JenisMakanan extends AppCompatActivity {
    private AdapterJenisMakanan mAdapterJenisMakanan;
    private List<Kategori> kategoris;

    @BindView(R.id.jenis_makanan_activity)
    RecyclerView mRecyclerJenisMakanan;

    private boolean root = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_makanan);
        ButterKnife.bind(this);

        setTitle("Jenis Makanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupComponent();
    }

    private void setupComponent() {
        LinearLayoutManager mLayourManagerRecycler = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        root = this.getIntent().getIntExtra(getString(R.string.put_extra_kategori),-1)<=0;
        kategoris = CostumDaoMaster.getSession(this).getKategoriDao().queryBuilder()
                .where(KategoriDao.Properties.Jenis.eq(this.getIntent().getIntExtra(getString(R.string.put_extra_kategori), 0)))
                .list();

        mAdapterJenisMakanan = new AdapterJenisMakanan(kategoris);

        mRecyclerJenisMakanan.setLayoutManager(mLayourManagerRecycler);
        mRecyclerJenisMakanan.setAdapter(mAdapterJenisMakanan);

        mRecyclerJenisMakanan.addOnItemTouchListener(
                new RecyclerTouchListener(getApplicationContext(),
                        mRecyclerJenisMakanan,
                        new RecyclerTouchListener.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                if (root) {
                                    kategoris =  CostumDaoMaster.getSession(JenisMakanan.this).getKategoriDao().queryBuilder()
                                            .where(KategoriDao.Properties.Jenis.eq(kategoris.get(position).getId()))
                                            .list();

                                    mAdapterJenisMakanan.setKategoris(kategoris);

                                    root = !root;
                                } else {
                                    Intent intent = new Intent(JenisMakanan.this, DaftarMakanan.class);
                                    intent.putExtra(getString(R.string.put_extra_kategori), kategoris.get(position).getId());
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onLongClick(View view, int position) {

                            }
                        }));
    }

    @Override
    public void onBackPressed() {
        if (!root) {
            kategoris =  CostumDaoMaster.getSession(JenisMakanan.this).getKategoriDao().queryBuilder()
                    .where(KategoriDao.Properties.Jenis.eq(0))
                    .list();

            mAdapterJenisMakanan.setKategoris(kategoris);

            root = !root;
        }else{
            super.onBackPressed();
        }
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
