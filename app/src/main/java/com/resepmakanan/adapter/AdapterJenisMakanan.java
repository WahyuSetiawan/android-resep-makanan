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
import android.widget.Toast;

import com.nekoloop.base64image.Base64Image;
import com.nekoloop.base64image.RequestDecode;
import com.resepmakanan.R;
import com.resepmakanan.activity.JenisMakanan;
import com.resepmakanan.database.CostumDaoMaster;
import com.resepmakanan.global.GambarKategori;
import com.resepmakanan.model.Kategori;
import com.resepmakanan.model.KategoriDao;
import com.resepmakanan.model.Makanan;
import com.resepmakanan.model.MakananDao;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.resepmakanan.global.GambarKategori.makanan;

/**
 * Created by wahyu on 4/8/2017.
 */

public class AdapterJenisMakanan extends RecyclerView.Adapter<AdapterJenisMakanan.ViewHolder> {
    public List<Kategori> kategoris = new ArrayList<>();
    private Context context;

    public AdapterJenisMakanan(List<Kategori> kategoris) {
        this.kategoris = kategoris;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_jenis_makanan, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Kategori kategori = this.kategoris.get(position);
        File img = new File(kategori.getGambar());
        if (img.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
            holder.gambar.setImageBitmap(bitmap);
        }
        holder.title.setText(kategori.getJudul());
        long count = 0;
        if (kategori.getJenis() > 0) {
            count = CostumDaoMaster.getSession(this.context).getMakananDao().queryBuilder().where(MakananDao.Properties.Id_kategori.eq(kategori.getId())).count();
        } else {
            QueryBuilder queryBuilder = CostumDaoMaster.getSession(this.context).getKategoriDao().queryBuilder();
            queryBuilder.join(KategoriDao.Properties.Id, Makanan.class, MakananDao.Properties.Id_kategori);
            count = queryBuilder.where(KategoriDao.Properties.Jenis.eq(kategori.getId())).count();
        }
        holder.count.setText(count + " Items");
    }

    @Override
    public int getItemCount() {
        return kategoris.size();
    }

    public Kategori getItem(int position) {
        return kategoris.get(position);
    }

    public void setKategoris(List<Kategori> kategoris) {
        this.kategoris = kategoris;

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.jenis_makanan)
        TextView title;
        @BindView(R.id.count_makanan)
        TextView count;
        @BindView(R.id.gambar)
        ImageView gambar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
