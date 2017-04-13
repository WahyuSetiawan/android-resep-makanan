package com.resepmakanan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.resepmakanan.R;
import com.resepmakanan.database.MakananOpenHelper;
import com.resepmakanan.model.Kategori;
import com.resepmakanan.model.Makanan;
import com.nekoloop.base64image.Base64Image;
import com.nekoloop.base64image.RequestDecode;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;

public class MenuUtama extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private CarouselView mCarouselView;
    private Button mButtonMenu, mButtonInfo;
    private CoordinatorLayout mCoordinatorLayout;

    private MakananOpenHelper mMakananOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        setupToolbar();

        setupFloating();

        setupNavigation();

        setupComponent();

        setupDatabase();

        setupData();
    }

    private void setupComponent() {
        mCarouselView = (CarouselView) findViewById(R.id.carousel_menu_utama);

        mButtonMenu = (Button) findViewById(R.id.menu_utama_menu);
        mButtonInfo = (Button) findViewById(R.id.menu_utama_info);
    }

    private void setupData() {
        mButtonInfo.setOnClickListener(mListenerInfo);
        mButtonMenu.setOnClickListener(mListenerMenu);

        final ArrayList<Makanan> mMakanans = mMakananOpenHelper.selectAllFavorite();

        mCarouselView.setPageCount(mMakanans.size());
        mCarouselView.setViewListener(new ViewListener() {
            @Override
            public View setViewForPosition(final int position) {
                final View costumer = getLayoutInflater().inflate(R.layout.carousel_favorite, null);

                Base64Image.with(MenuUtama.this)
                        .decode(mMakanans.get(position).getGambar())
                        .into(new RequestDecode.Decode() {
                            @Override
                            public void onSuccess(Bitmap bitmap) {
                                ((TextView) costumer.findViewById(R.id.judul)).setText(mMakanans.get(position).getNama());
                                ((ImageView) costumer.findViewById(R.id.gambar)).setImageBitmap(bitmap);
                            }

                            @Override
                            public void onFailure() {

                            }
                        });


                return costumer;
            }
        });

       /* mCarouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, final ImageView imageView) {
                Base64Image
                        .with(MenuUtama.this)
                        .decode(mMakanans.get(position).getGambar())
                        .into(new RequestDecode.Decode() {
                            @Override
                            public void onSuccess(Bitmap bitmap) {
                                imageView.setImageBitmap(bitmap);
                            }

                            @Override
                            public void onFailure() {

                            }
                        });
            }*/


        mCarouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Intent intDetailOpen = new Intent(MenuUtama.this, DetailMakanan.class);
                intDetailOpen.putExtra(getString(R.string.put_extra_detail), String.valueOf(mMakanans.get(position).getId()));
                MenuUtama.this.startActivityForResult(intDetailOpen, getResources().getInteger(R.integer.detail_open));
            }
        });
    }

    private void setupFloating() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inttambahmakanan = new Intent(MenuUtama.this, TambahMakanan.class);
                MenuUtama.this.startActivity(inttambahmakanan);
            }
        });
    }

    private void setupNavigation() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupDatabase() {
        mMakananOpenHelper = new MakananOpenHelper(this);
    }

    private void setupToolbar() {
        setTitle("Menu Utama");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private View.OnClickListener mListenerMenu = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MenuUtama.this, JenisMakanan.class);
            startActivityForResult(intent, getResources().getInteger(R.integer.jenis_makanan));
        }
    };
    private View.OnClickListener mListenerInfo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MenuUtama.this, Info.class);
            startActivityForResult(intent, getResources().getInteger(R.integer.info));
        }
    };

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_halaman_utama) {

        } else if (id == R.id.nav_makanan) {
            Intent intMakanan = new Intent(this, DaftarMakanan.class);

            intMakanan.putExtra(getString(R.string.put_extra_kategori), Kategori.MAKANAN.toString());
            this.startActivityForResult(intMakanan, getResources().getInteger(R.integer.return_menu_utama_data));
        } else if (id == R.id.nav_minuman) {
            Intent intMinuman = new Intent(this, DaftarMakanan.class);

            intMinuman.putExtra(getString(R.string.put_extra_kategori), Kategori.MINUMAN.toString());
            this.startActivityForResult(intMinuman, getResources().getInteger(R.integer.return_menu_utama_data));
        } else if (id == R.id.nav_kue) {
            Intent intKue = new Intent(this, DaftarMakanan.class);

            intKue.putExtra(getString(R.string.put_extra_kategori), Kategori.KUE.toString());
            this.startActivityForResult(intKue, getResources().getInteger(R.integer.return_menu_utama_data));
        } else if (id == R.id.nav_favorite) {
            Intent intent = new Intent(this, DaftarMakanan.class);

            intent.putExtra(getString(R.string.put_extra_favorite), getString(R.string.put_extra_favorite));
            this.startActivityForResult(intent, getResources().getInteger(R.integer.return_menu_utama_data));
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
