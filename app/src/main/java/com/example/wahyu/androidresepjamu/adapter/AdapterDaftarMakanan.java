package com.example.wahyu.androidresepjamu.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wahyu.androidresepjamu.R;
import com.example.wahyu.androidresepjamu.activity.DetailMakanan;
import com.example.wahyu.androidresepjamu.model.Makanan;
import com.nekoloop.base64image.Base64Image;
import com.nekoloop.base64image.RequestDecode;

import java.util.ArrayList;

/**
 * Created by wahyu on 4/6/2017.
 */

public class AdapterDaftarMakanan extends RecyclerView.Adapter<AdapterDaftarMakanan.ViewAdapterMakanan> {
    ArrayList<Makanan> makanans = new ArrayList<>();
    Context context;

    public AdapterDaftarMakanan(Context context) {
        this.context = context;
    }

    public void setMakanans(ArrayList<Makanan> makanans) {
        this.makanans = makanans;
    }

    @Override
    public ViewAdapterMakanan onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewAdapterMakanan(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_daftar_makanan, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewAdapterMakanan holder, int position) {
        final Makanan makanan = makanans.get(position);

        Base64Image.with(context).decode(makanan.getGambar()).into(new RequestDecode.Decode() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                holder.image.setImageBitmap(bitmap);
                holder.title.setText(makanan.getNama());

                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mIntentDetailMakanan = new Intent(context, DetailMakanan.class);
                        mIntentDetailMakanan.putExtra(context.getString(R.string.put_extra_detail), makanan.getId());
                        context.startActivity(mIntentDetailMakanan);
                    }
                });
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return makanans.size();
    }

    public static class ViewAdapterMakanan extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public View view;

        public ViewAdapterMakanan(View itemView) {
            super(itemView);

            view = itemView;
            image = (ImageView) itemView.findViewById(R.id.imageresep);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
