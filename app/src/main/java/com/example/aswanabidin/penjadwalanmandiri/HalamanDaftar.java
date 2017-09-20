package com.example.aswanabidin.penjadwalanmandiri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aswanabidin.penjadwalanmandiri.Adapter.SpinnerAdapter;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.AnakFragment;
import com.example.aswanabidin.penjadwalanmandiri.Model.AkunModel;
import com.example.aswanabidin.penjadwalanmandiri.Model.ItemOrtuModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class HalamanDaftar extends AppCompatActivity {

    private Spinner kategoripengguna;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private StorageReference mStorageRef;
    private ProgressDialog progressDialog;
    private EditText namalengkap, emailuser, username, katasandi;
    private Button btnsubmitdaftar;
    private Uri akun;

    public static final String FB_DATABASE_PATH = "daftarakun";
    public static final String FB_STORAGE_PATH = "daftarakun/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_daftar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //list dialog user
        ArrayList<ItemOrtuModel> listuser = new ArrayList<>();
        listuser.add(new ItemOrtuModel("Ayah"));
        listuser.add(new ItemOrtuModel("Ibu"));
        listuser.add(new ItemOrtuModel("Anak"));
        listuser.add(new ItemOrtuModel(""));

        //list spinner dialog user
        kategoripengguna = (Spinner) findViewById(R.id.spinortu);
        final SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.spinner_layout, R.id.txtspinner, listuser) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemView = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) itemView.findViewById(R.id.txtspinner)).setText("");
                    ((TextView) itemView.findViewById(R.id.txtspinner)).setHint("Pengguna");
                }
                return itemView;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };
        kategoripengguna.setAdapter(adapter);
        kategoripengguna.setSelection(adapter.getCount());
        kategoripengguna.setPrompt("Pengguna");

        mDatabase = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        //inisialisasi editext dan button
        progressDialog = new ProgressDialog(this);
        namalengkap = (EditText) findViewById(R.id.etNamaDaftar);
        emailuser = (EditText) findViewById(R.id.etEmailDaftar);
        username = (EditText) findViewById(R.id.etUsernameDaftar);
        katasandi = (EditText) findViewById(R.id.etKataSandiDaftar);
        btnsubmitdaftar = (Button) findViewById(R.id.btnDaftarAkun);
        btnsubmitdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buatAkun();
            }
        });
    }

    private void buatAkun(){
        //inisialisasi data untuk disimpan
        String nama = namalengkap.getText().toString().trim();
        String email = emailuser.getText().toString().trim();
        String pengguna = kategoripengguna.getSelectedItem().toString();
        String user = username.getText().toString().trim();
        String pass = katasandi.getText().toString().trim();

        //mengecek data

        progressDialog.setMessage("Upload...");
        if (!TextUtils.isEmpty(nama)){
            progressDialog.show();
            //buat objek akun
            String id = mDatabase.push().getKey();
            AkunModel akunModel = new AkunModel(nama,email,pengguna,user,pass);
            mDatabase.child(id).setValue(akunModel);
            Toast.makeText(this, "Akun Ditambahkan", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HalamanUtama.class));
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
            Intent intent = new Intent(this, AnakFragment.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
