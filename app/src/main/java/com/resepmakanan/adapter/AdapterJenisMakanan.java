package com.resepmakanan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.resepmakanan.R;
import com.resepmakanan.publicvariable.GambarKategori;
import com.resepmakanan.publicvariable.Kategori;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by wahyu on 4/8/2017.
 */

public class AdapterJenisMakanan extends RecyclerView.Adapter<AdapterJenisMakanan.ViewHolder> {
    public AdapterJenisMakanan() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_jenis_makanan, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(StringUtils.capitalize(GambarKategori.values()[position].getNama()));
        holder.gambar.setImageResource(GambarKategori.values()[position].getImage());
    }

    @Override
    public int getItemCount() {
        return Kategori.values().length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView gambar;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.jenis_makanan);
            gambar = (ImageView) itemView.findViewById(R.id.gambar);
        }
    }

    public static class JenisMakanan {
        int gambar;
        String title;

        public JenisMakanan(int gambar, String title) {
            this.gambar = gambar;
            this.title = title;
        }

        public int getGambar() {
            return gambar;
        }

        public String getTitle() {
            return title;
        }
    }
}
