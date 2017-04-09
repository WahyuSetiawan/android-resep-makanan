package com.example.wahyu.androidresepjamu.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wahyu.androidresepjamu.R;

import java.util.ArrayList;

/**
 * Created by wahyu on 4/8/2017.
 */

public class AdapterJenisMakanan extends RecyclerView.Adapter<AdapterJenisMakanan.ViewHolder> {
    private ArrayList<JenisMakanan> jenisMakanans = new ArrayList<>();

    public AdapterJenisMakanan() {
        jenisMakanans.add(new JenisMakanan(0, "Minuman"));
        jenisMakanans.add(new JenisMakanan(0, "Makanan"));
        jenisMakanans.add(new JenisMakanan(0, "Kue"));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_jenis_makanan, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JenisMakanan jenisMakanan = jenisMakanans.get(position);

        holder.title.setText(jenisMakanan.getTitle());
    }

    @Override
    public int getItemCount() {
        return jenisMakanans.size();
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