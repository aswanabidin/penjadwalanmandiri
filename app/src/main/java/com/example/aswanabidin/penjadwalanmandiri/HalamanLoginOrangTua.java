package com.example.aswanabidin.penjadwalanmandiri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HalamanLoginOrangTua extends AppCompatActivity implements View.OnClickListener {

    private TextView tvorangtua, tvLupaPass;
    private Button btnMasukSebagaiOrtu;
    private EditText etemail,etpass;
    private ProgressDialog progressDialog;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login_orang_tua);

        auth = FirebaseAuth.getInstance();
        FirebaseUser isLogin = auth.getCurrentUser();

        tvLupaPass = (TextView) findViewById(R.id.tvLupaPassOrtu);
        etemail = (EditText) findViewById(R.id.etEmailPengguna);
        btnMasukSebagaiOrtu = (Button) findViewById(R.id.btnMasukSebagaiOrtu);
        etpass = (EditText) findViewById(R.id.etKataSandi);
        tvLupaPass.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Masuk...");

        btnMasukSebagaiOrtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etemail.getText().toString().trim();
                final String pass = etpass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(HalamanLoginOrangTua.this, "Masukkan Email Orang Tua!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(HalamanLoginOrangTua.this, "Masukkan Kata Sandi!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.show();
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(HalamanLoginOrangTua.this, "Akun tidak terdaftar!", Toast.LENGTH_SHORT).show();
                        } else {
                            //pindahin ke halaman main
                            Intent intent = new Intent(HalamanLoginOrangTua.this, HalamanPenjadwalanMandiri.class);
                            startActivity(intent);
                        }
                    }
                });
                progressDialog.dismiss();
            }
        });
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
