package com.example.aswanabidin.penjadwalanmandiri;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aswanabidin.penjadwalanmandiri.Adapter.SpinnerAdapter;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.AnakFragment;
import com.example.aswanabidin.penjadwalanmandiri.Model.AkunModel;
import com.example.aswanabidin.penjadwalanmandiri.Model.ItemOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.Model.userOrtuModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HalamanDaftar extends AppCompatActivity {

    private Spinner kategoripengguna;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private FirebaseDatabase database;
    private FirebaseUser user;
    private StorageReference mStorageRef;
    private ProgressDialog progressDialog;
    EditText namalengkap, emailuser, tgllahir, username, katasandi;
    private Button btnsubmitdaftar;
    private Uri akun;
    int day, month, year, hour, minute;


    public static final String FB_DATABASE_PATH = "user";
    public static final String FB_STORAGE_PATH = "daftarakun/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_daftar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        //list dialog user
//        ArrayList<ItemOrtuModel> listuser = new ArrayList<>();
//        listuser.add(new ItemOrtuModel("Ayah"));
//        listuser.add(new ItemOrtuModel("Ibu"));
//        listuser.add(new ItemOrtuModel("Anak"));
//        listuser.add(new ItemOrtuModel(""));

        //list spinner dialog user
        kategoripengguna = (Spinner) findViewById(R.id.spinortu);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SpinnerOrtu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kategoripengguna.setAdapter(adapter);
        kategoripengguna.setPrompt("Kategori");


        mDatabase = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
        firebaseAuth = FirebaseAuth.getInstance();

        //inisialisasi editext dan button
        progressDialog = new ProgressDialog(this);
        namalengkap = (EditText) findViewById(R.id.etNamaDaftar);
        emailuser = (EditText) findViewById(R.id.etEmailDaftar);
        tgllahir = (EditText) findViewById(R.id.etTglLahir);
        username = (EditText) findViewById(R.id.etUsernameDaftar);
        katasandi = (EditText) findViewById(R.id.etKataSandiDaftar);
        btnsubmitdaftar = (Button) findViewById(R.id.btnDaftarAkun);
        btnsubmitdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inisialisasi data untuk disimpan
                final String nama = namalengkap.getText().toString().trim();
                final String email = emailuser.getText().toString().trim();
                final String tglahir = tgllahir.getText().toString().trim();
                final String pengguna = kategoripengguna.getSelectedItem().toString();
                final String user = username.getText().toString().trim();
                final String pass = katasandi.getText().toString().trim();

                if (TextUtils.isEmpty(nama)) {
                    Toast.makeText(getApplicationContext(), "Masukkan Nama", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Masukkan Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pengguna)) {
                    Toast.makeText(getApplicationContext(), "Masukkan Kategori Pengguna", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(getApplicationContext(), "Masukkan Nama Pengguna", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Masukkan Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("Buat Akun...");
                progressDialog.show();



                //Autentifikasi firebase
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(HalamanDaftar.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(HalamanDaftar.this, "Pendaftaran Selesai, cek email untuk verifikasi akun", Toast.LENGTH_SHORT).show();

                                if(task.isSuccessful()){
                                    firebaseAuth.signInWithEmailAndPassword(email, pass);

                                    userOrtuModel userOrtuModel = new userOrtuModel();
                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
                                    DatabaseReference db = ref.child(firebaseAuth.getCurrentUser().getUid());
//                                    userOrtuModel.setNamalengkap(nama);
//                                    userOrtuModel.setEmail(email);
//                                    userOrtuModel.setTgllahir(tglahir);
//                                    userOrtuModel.setKategori(pengguna);
//                                    userOrtuModel.setUsername(user);
//                                    userOrtuModel.setKatasandi(pass);
                                    db.child("namalengkap").setValue(nama);
                                    db.child("emailuser").setValue(email);
                                    db.child("tglahir").setValue(tglahir);
                                    db.child("kategori").setValue(pengguna);
                                    db.child("username").setValue(user);
                                    db.child("katasandi").setValue(pass);
                                } else {
                                    Toast.makeText(HalamanDaftar.this, "Daftar Akun Gagal", Toast.LENGTH_SHORT).show();
                                }

                                if(!task.isSuccessful()){
                                    progressDialog.dismiss();
                                    Toast.makeText(HalamanDaftar.this, "Pendaftaran Gagal." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                }else{
                                    emailVerification();
                                }
                            }
                        });
                    }
                });

        tgllahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentDate = Calendar.getInstance();
                year = currentDate.get(Calendar.YEAR);
                month = currentDate.get(Calendar.MONTH);
                day = currentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(HalamanDaftar.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedyear, int selectedmonth, int selectedday) {
                        String tanggal;
                        long tanggalpilih = 0;
                        if (selectedmonth < 10) {
                            tanggal = String.valueOf(selectedday + "-" + (++selectedmonth) + "-" + year);
                        } else {
                            tanggal = String.valueOf(selectedday + "-" + (++selectedmonth) + "-" + year);
                        }
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = sdf.parse(tanggal);
                            tanggalpilih = date.getTime();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        tgllahir.setText(tanggal);
                    }
                },year, month, day);
                mDatePicker.setTitle("Pilih Tanggal");
                mDatePicker.show();
            }
        });
    }


    public void emailVerification(){
        user = firebaseAuth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Intent intent = new Intent(HalamanDaftar.this, HalamanUtama.class);
                    startActivity(intent);
                    finish();
                }else {
                    Log.e("onCreate : ", " sendEmailVerification", task.getException());
                    Toast.makeText(HalamanDaftar.this,
                            "Gagal mengirimkan verifikasi ke email.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
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
