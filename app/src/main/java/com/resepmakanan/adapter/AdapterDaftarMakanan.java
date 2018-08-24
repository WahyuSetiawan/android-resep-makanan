package com.resepmakanan.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.resepmakanan.R;
import com.resepmakanan.database.CostumDaoMaster;
import com.resepmakanan.model.Kategori;
import com.resepmakanan.model.KategoriDao;
import com.resepmakanan.model.Makanan;
import com.nekoloop.base64image.Base64Image;
import com.nekoloop.base64image.RequestDecode;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wahyu on 4/6/2017.
 */

public class AdapterDaftarMakanan extends RecyclerView.Adapter<AdapterDaftarMakanan.ViewAdapterMakanan> {
    public List<Makanan> makanans = new ArrayList<>();
    Context context;

    public AdapterDaftarMakanan(Context context) {
        this.context = context;
    }

    public void setMakanans(List<Makanan> makanans) {
        this.makanans = makanans;
    }

    @Override
    public ViewAdapterMakanan onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewAdapterMakanan(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_daftar_makanan, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewAdapterMakanan holder, int position) {
        final Makanan makanan = makanans.get(position);

        File img = new File(makanan.getGambar());
        if (img.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
            holder.image.setImageBitmap(bitmap);
        }

        holder.title.setText(StringUtils.capitalize(makanan.getJudul()));
        holder.description.setText(StringUtils.capitalize(makanan.getKategori().getJudul()));

        Kategori kategori = CostumDaoMaster.getSession(this.context).getKategoriDao().queryBuilder().where(KategoriDao.Properties.Id.eq(makanan.getId_kategori())).unique();
        Kategori kategori1 = CostumDaoMaster.getSession(this.context).getKategoriDao().queryBuilder().where(KategoriDao.Properties.Id.eq(kategori.getJenis())).unique();

        holder.icon.setImageBitmap(BitmapFactory.decodeFile(kategori1.getIcon()));

        if (makanan.getFavorite() > 0) {
            holder.favorite.setVisibility(View.VISIBLE);
        } else {
            holder.favorite.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return makanans.size();
    }

    public void remove(int position) {
        makanans.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, makanans.size());
    }

    public void edit(int i, Makanan makanan) {
        makanans.set(i, makanan);
        notifyItemChanged(i);
    }

    public static class ViewAdapterMakanan extends RecyclerView.ViewHolder {
        @BindView(R.id.imageresep)
        public ImageView image;
        @BindView(R.id.favorite)
        public ImageView favorite;
        @BindView(R.id.title)
        public TextView title;
        @BindView(R.id.description)
        public TextView description;
        @BindView(R.id.icon)
        ImageView icon;
        public View view;

        public ViewAdapterMakanan(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }
}
