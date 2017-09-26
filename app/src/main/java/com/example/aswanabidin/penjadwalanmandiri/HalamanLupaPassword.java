package com.example.aswanabidin.penjadwalanmandiri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aswanabidin.penjadwalanmandiri.HalamanUtama;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class HalamanLupaPassword extends AppCompatActivity {

    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private Button btnkirim;
    private EditText emaillupapass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_lupa_password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnkirim = (Button) findViewById(R.id.btnLupaPassword);
        emaillupapass = (EditText) findViewById(R.id.etEmailLupaPass);
        auth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Reset Kata Sandi...");

        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emaillupapass.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(HalamanLupaPassword.this, "Masuukan Alamat Email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.show();
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(HalamanLupaPassword.this, "Reset kata sandi telah dikirim ke email anda!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(HalamanLupaPassword.this, "Gagal mengirim ke email!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        Intent intent = new Intent(HalamanLupaPassword.this, HalamanUtama.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    //kembali
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanUtama.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
