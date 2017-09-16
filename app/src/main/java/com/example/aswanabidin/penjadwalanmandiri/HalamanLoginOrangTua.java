package com.example.aswanabidin.penjadwalanmandiri;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HalamanLoginOrangTua extends AppCompatActivity implements View.OnClickListener {

    private TextView tvorangtua, tvLupaPass;
    private Button btnMasukSebagaiOrtu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login_orang_tua);

        btnMasukSebagaiOrtu = (Button) findViewById(R.id.btnMasukSebagaiOrtu);
        btnMasukSebagaiOrtu.setOnClickListener(this);

        tvLupaPass = (TextView) findViewById(R.id.tvLupaPassOrtu);
        tvLupaPass.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLupaPassOrtu:
                startActivity(new Intent(this, HalamanLupaPassword.class));
                break;

            case R.id.btnMasukSebagaiOrtu:
                startActivity(new Intent(this, HalamanPenjadwalanMandiri.class));
                break;
        }
    }
}
