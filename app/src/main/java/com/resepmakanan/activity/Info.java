package com.resepmakanan.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.resepmakanan.R;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        setupComponent();
    }

    private void setupComponent() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0) ;

            ((TextView) findViewById(R.id.nama)).setText(getString(R.string.app_name));
            ((TextView) findViewById(R.id.description)).setText(getString(R.string.app_description));
            ((TextView) findViewById(R.id.version)).setText("Version " + info.versionName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
