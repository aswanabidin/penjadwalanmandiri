package com.example.aswanabidin.penjadwalanmandiri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aswanabidin.penjadwalanmandiri.Fragments.HadiahFragment;
import com.example.aswanabidin.penjadwalanmandiri.Model.AkunModel;
import com.example.aswanabidin.penjadwalanmandiri.Model.HadiahModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class HalamanTambahHadiah extends AppCompatActivity {

    private EditText etnamahadiah,etpoinhadiah,etdeskripsihadiah;
    private Button btnpilihprofil;
    int day, month, year, hour, minute;
    private DatabaseReference mDatabase;
    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    public static final String FB_STORAGE_PATH = "hadiah/";
    public static final String FB_DATABASE_PATH = "hadiah";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_tambah_hadiah);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        etnamahadiah = (EditText) findViewById(R.id.etNamaHadiah);
        etpoinhadiah = (EditText) findViewById(R.id.etPoinHadiah);
        etdeskripsihadiah = (EditText) findViewById(R.id.etDeskripsiHadiah);
        btnpilihprofil = (Button) findViewById(R.id.btnSubmitHadiahOrtu);
        btnpilihprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahHadiah();
            }
        });
    }

    public void tambahHadiah(){
        //inisialisasi data untuk disimpan
        String mnamahadiah = etnamahadiah.getText().toString().trim();
        String mpoinhadiah = etpoinhadiah.getText().toString().trim();
        String mdeskripsihadiah = etdeskripsihadiah.getText().toString().trim();

        //mengecek data

        progressDialog.setMessage("Upload...");
        if (!TextUtils.isEmpty(mnamahadiah)){
            progressDialog.show();
            //buat objek akun
            String id = mDatabase.push().getKey();
            HadiahModel hadiahModel = new HadiahModel(mnamahadiah,mpoinhadiah,mdeskripsihadiah);
            mDatabase.child(id).setValue(hadiahModel);
            Toast.makeText(this, "Hadiah Telah Ditambahkan", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HalamanPenjadwalanMandiri.class));
            progressDialog.dismiss();
        } else {
            Toast.makeText(this, "Silahkan datanya dilengkapi", Toast.LENGTH_SHORT).show();
        }
    }

    //kembali
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HadiahFragment.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
