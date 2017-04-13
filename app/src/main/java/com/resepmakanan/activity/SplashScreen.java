package com.resepmakanan.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.resepmakanan.R;
import com.resepmakanan.database.MakananOpenHelper;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    private boolean handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new MakananOpenHelper(SplashScreen.this);
                startMenuUtama();
            }
        }, SPLASH_TIME_OUT);
    }

    private void startMenuUtama() {
        Intent i = new Intent(SplashScreen.this, MenuUtama.class);
        startActivity(i);

        finish();
    }

}
