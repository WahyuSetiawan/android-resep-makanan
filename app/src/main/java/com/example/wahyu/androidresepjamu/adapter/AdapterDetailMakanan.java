package com.example.wahyu.androidresepjamu.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wahyu.androidresepjamu.R;
import com.example.wahyu.androidresepjamu.model.Kue;
import com.example.wahyu.androidresepjamu.model.Makanan;
import com.example.wahyu.androidresepjamu.model.Minuman;

import java.util.ArrayList;
/**
 * Created by wahyu on 4/6/2017.
 */

public class AdapterDetailMakanan extends RecyclerView.Adapter<AdapterDetailMakanan.ViewAdapterMakanan> {
    ArrayList<Makanan> makanans = new ArrayList<>();
    ArrayList<Minuman> minumans = new ArrayList<>();
    ArrayList<Kue> kues = new ArrayList<>();


    @Override
    public ViewAdapterMakanan onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewAdapterMakanan(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_jenis_makanan, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewAdapterMakanan holder, int position) {
        if (makanans.size() > 0) {

        }

        if (minumans.size() > 0) {

        }

        if (kues.size() > 0) {

        }
    }

    @Override
    public int getItemCount() {
        if (makanans.size() > 0) return makanans.size();
        if (minumans.size() > 0) return minumans.size();
        if (kues.size() > 0) return kues.size();

        return 0;
    }

    public static class ViewAdapterMakanan extends RecyclerView.ViewHolder {

        public ViewAdapterMakanan(View itemView) {
            super(itemView);
        }
    }
}
