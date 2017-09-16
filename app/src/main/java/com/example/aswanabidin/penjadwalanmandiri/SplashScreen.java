package com.example.aswanabidin.penjadwalanmandiri;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.Transaction;

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 2000;
    private TextView judul1,judul2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView myImageView = (ImageView) findViewById(R.id.ivlogo);
        myImageView.setImageResource(R.drawable.logo_penjadwalan);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashScreen.this, HalamanUtama.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
