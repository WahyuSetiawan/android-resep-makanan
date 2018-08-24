package com.resepmakanan.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.resepmakanan.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Info extends AppCompatActivity {
    @BindView(R.id.nama_profile) TextView namaProfile;
    @BindView(R.id.nim_profile) TextView nimProfile;
    @BindView(R.id.image_app)
    ImageView imageApp;
    @BindView(R.id.nama)
    TextView nama;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.version)
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

        setupComponent();
    }

    private void setupComponent() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0) ;

            nama.setText(getString(R.string.app_name));
            description.setText(getString(R.string.app_description));
            version.setText("Version " + info.versionName);

            namaProfile.setText("NAMA: WAHYU SETYAWAN");
            nimProfile.setText("NPM.  : 1171065082");

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.back)
    public void backClick(){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
