package com.example.aswanabidin.penjadwalanmandiri;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aswanabidin.penjadwalanmandiri.HalamanLupaPassword;

public class HalamanLoginAnak extends AppCompatActivity implements View.OnClickListener {

    private TextView tvanak, tvLupaPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login_anak);

        tvLupaPass = (TextView) findViewById(R.id.tvLupaPassAnak);
        tvLupaPass.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLupaPassAnak:
                startActivity(new Intent(this, HalamanLupaPassword.class));
                break;
        }
    }
}
