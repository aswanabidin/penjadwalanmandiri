package com.example.aswanabidin.penjadwalanmandiri;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HalamanUtama extends AppCompatActivity implements View.OnClickListener{

    private TextView daftar;
    private Button btnMasukOrtu, btnMasukAnak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        btnMasukOrtu = (Button) findViewById(R.id.btnMasukSebagaiOrangTua);
        btnMasukAnak = (Button) findViewById(R.id.btnMasukSebagaiAnak);
        daftar = (TextView) findViewById(R.id.tvBuatAkun);
        daftar.setOnClickListener(this);

        btnMasukOrtu.setOnClickListener(this);
        btnMasukAnak.setOnClickListener(this);
    }


    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMasukSebagaiOrangTua:
                startActivity(new Intent(this, HalamanLoginOrangTua.class));
                break;

            case R.id.btnMasukSebagaiAnak:
                startActivity(new Intent(this, HalamanLoginAnak.class));
                break;

            case R.id.tvBuatAkun:
                startActivity(new Intent(this, HalamanDaftar.class));
                break;

        }
    }
}
