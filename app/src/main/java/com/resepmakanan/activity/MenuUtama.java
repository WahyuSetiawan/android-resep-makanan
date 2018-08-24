package com.resepmakanan.activity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.resepmakanan.R;
import com.resepmakanan.database.CostumDaoMaster;
import com.resepmakanan.global.KategoriEnum;
import com.resepmakanan.model.Kategori;
import com.resepmakanan.model.KategoriDao;
import com.resepmakanan.model.Makanan;
import com.resepmakanan.model.MakananDao;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ViewListener;

import org.greenrobot.greendao.database.Database;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuUtama extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar mToolbar;
    @BindView(R.id.carousel_menu_utama)
    CarouselView mCarouselView;
    CoordinatorLayout mCoordinatorLayout;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_utama);
        ButterKnife.bind(this);

        setupToolbar();
        setupNavigation();
        setupData();
    }

    private void setupData() {
        final List<Makanan> makanans = CostumDaoMaster.getSession(this).getMakananDao().queryBuilder()
                .where(MakananDao.Properties.Favorite.eq(1))
                .list();

        mCarouselView.setPageCount(makanans.size());
        mCarouselView.setViewListener(new ViewListener() {
            @Override
            public View setViewForPosition(final int position) {
                final View costumer = getLayoutInflater().inflate(R.layout.carousel_favorite, null);

                File file = new File(makanans.get(position).getGambar());
                if (file.exists()) {
                    ((TextView) costumer.findViewById(R.id.judul)).setText(makanans.get(position).getJudul());
                    ((ImageView) costumer.findViewById(R.id.gambar)).setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                }

                return costumer;
            }
        });

        mCarouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Intent intDetailOpen = new Intent(MenuUtama.this, DetailMakanan.class);
                intDetailOpen.putExtra(getString(R.string.put_extra_detail), String.valueOf(makanans.get(position).getId()));
                MenuUtama.this.startActivityForResult(intDetailOpen, getResources().getInteger(R.integer.detail_open));
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void setupNavigation() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SubMenu subMenu = navigationView.getMenu().findItem(R.id.group_jenis_makanan).getSubMenu();

        List<Kategori> kategoris = CostumDaoMaster.getSession(this).getKategoriDao().queryBuilder().where(KategoriDao.Properties.Jenis.eq(0)).list();

        for (Kategori kategori : kategoris) {
            File file = new File(kategori.getIcon());

            if (file.exists()) {
                Drawable d = new BitmapDrawable(BitmapFactory.decodeFile(kategori.getIcon()));
                subMenu.add(Menu.CATEGORY_SECONDARY, (int) kategori.getId(), (int) kategori.getId(), kategori.getJudul()).setIcon(d);
            }

        }
    }

    private void setupToolbar() {
        setTitle("Menu Utama");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.menu_utama_menu)
    void clickMenuUtama() {
        Intent intent = new Intent(MenuUtama.this, JenisMakanan.class);
        startActivityForResult(intent, getResources().getInteger(R.integer.jenis_makanan));
    }

    @OnClick(R.id.menu_utama_keluar)
    void clickKeluar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuUtama.this);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Anda yakin keluar?");
        builder.setNegativeButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    @OnClick(R.id.fab)
    void clickFloating() {
        Intent inttambahmakanan = new Intent(MenuUtama.this, TambahMakanan.class);
        MenuUtama.this.startActivity(inttambahmakanan);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.nav_favorite:
                intent = new Intent(this, DaftarMakanan.class);

                intent.putExtra(getString(R.string.put_extra_favorite), getString(R.string.put_extra_favorite));
                this.startActivityForResult(intent, getResources().getInteger(R.integer.return_menu_utama_data));
                break;
            case R.id.nav_info:
                intent = new Intent(this, Info.class);
                startActivity(intent);
                break;
        }

        if (item.getGroupId() == Menu.CATEGORY_SECONDARY) {
            Intent intKue = new Intent(this, JenisMakanan.class)
                    .putExtra(getString(R.string.put_extra_kategori), item.getItemId());

            this.startActivity(intKue);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == getResources().getInteger(R.integer.return_menu_utama_data)) {
            setupData();
        }
    }
}
