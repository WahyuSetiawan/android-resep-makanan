package com.resepmakanan.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.resepmakanan.R;
import com.resepmakanan.model.Makanan;
import com.nekoloop.base64image.Base64Image;
import com.nekoloop.base64image.RequestDecode;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * Created by wahyu on 4/6/2017.
 */

public class AdapterDaftarMakanan extends RecyclerView.Adapter<AdapterDaftarMakanan.ViewAdapterMakanan> {
    public ArrayList<Makanan> makanans = new ArrayList<>();
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
        System.out.println("asdfasdf");

        final Makanan makanan = makanans.get(position);

        Base64Image.with(context).decode(makanan.getGambar()).into(new RequestDecode.Decode() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                holder.image.setImageBitmap(bitmap);
                holder.title.setText(StringUtils.capitalize(makanan.getNama()));
                holder.description.setText(StringUtils.capitalize(makanan.getKategori().toString()));

                if (makanan.getFavorite() > 0) {
                    holder.favorite.setVisibility(View.VISIBLE);
                } else {
                    holder.favorite.setVisibility(View.GONE);
                }
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
        public ImageView image;
        public ImageView favorite;
        public TextView title;
        public TextView description;
        public View view;

        public ViewAdapterMakanan(View itemView) {
            super(itemView);

            view = itemView;
            image = (ImageView) itemView.findViewById(R.id.imageresep);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            favorite = (ImageView) itemView.findViewById(R.id.favorite);
        }
    }
}
