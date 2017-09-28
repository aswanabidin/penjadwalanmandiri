package com.example.aswanabidin.penjadwalanmandiri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aswanabidin.penjadwalanmandiri.HalamanLupaPassword;
import com.google.firebase.auth.FirebaseAuth;

public class HalamanLoginAnak extends AppCompatActivity implements View.OnClickListener {

    private TextView tvanak, tvLupaPass;
    private Button btnmasuksebagaianak;
    private EditText etemail,etpass;
    private ProgressDialog progressDialog;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login_anak);

        tvLupaPass = (TextView) findViewById(R.id.tvLupaPassAnak);
        tvLupaPass.setOnClickListener(this);
        etemail = (EditText) findViewById(R.id.etEmailPenggunaAnak);
        etpass = (EditText) findViewById(R.id.etKataSandiAnak);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Masuk...");

        btnmasuksebagaianak = (Button) findViewById(R.id.btnMasukSebagaiAnak);
//        btnmasuksebagaianak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLupaPassAnak:
                startActivity(new Intent(this, HalamanLupaPassword.class));
                break;
            case R.id.btnMasukSebagaiAnak:
                startActivity(new Intent(this, HalamanPenjadwalanMandiriAnak.class));
                break;
        }
    }
}
