package com.example.aswanabidin.penjadwalanmandiri;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class HalamanDaftar extends AppCompatActivity implements View.OnClickListener {

    private Spinner kategoripengguna;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private FirebaseDatabase database;
    private FirebaseUser user;
    private StorageReference mStorageRef;
    private ProgressDialog progressDialog;
    EditText namalengkap, tmplahir, tgllahirs, status, emailuser, username, katasandi;
    private Button btnsubmitdaftar, btnsubmit, lampirfoto;
    int day, month, year, hour, minute, id;
    private String userChoosenTask;
    private File files;
    private Uri foto;
    private String namafile;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ImageView imfoto, img;
    private String url;
    private ImageView imageView;


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

        lampirfoto = (Button) findViewById(R.id.btnProfilDaftar);
        lampirfoto.setOnClickListener(this);
        imfoto = (ImageView) findViewById(R.id.imgprofildaftar);
        imfoto.setOnClickListener(this);
        imfoto.setAdjustViewBounds(true);

        namalengkap = (EditText) findViewById(R.id.etNamaDaftar);
        tmplahir = (EditText) findViewById(R.id.etTempatLahir);
        tgllahirs = (EditText) findViewById(R.id.etTglLahir);
        emailuser = (EditText) findViewById(R.id.etEmailDaftar);
        username = (EditText) findViewById(R.id.etUsernameDaftar);
        katasandi = (EditText) findViewById(R.id.etKataSandiDaftar);

        btnsubmitdaftar = (Button) findViewById(R.id.btnDaftarAkun);
        btnsubmitdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inisialisasi data untuk disimpan
                final String nama = namalengkap.getText().toString().trim();
                final String tempatlahir = tmplahir.getText().toString().trim();
                final String tgllahir = tgllahirs.getText().toString().trim();
                final String pengguna = kategoripengguna.getSelectedItem().toString();
                final String email = emailuser.getText().toString().trim();
                final String user = username.getText().toString().trim();
                final String pass = katasandi.getText().toString().trim();


                if (TextUtils.isEmpty(nama)) {
                    Toast.makeText(getApplicationContext(), "Masukkan Nama", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tempatlahir)) {
                    Toast.makeText(getApplicationContext(), "Masukkan Tempat Lahir", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tgllahir)) {
                    Toast.makeText(getApplicationContext(), "Masukkan Tanggal lahir", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pengguna)) {
                    Toast.makeText(getApplicationContext(), "Masukkan Status Pengguna", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Masukkan Email", Toast.LENGTH_SHORT).show();
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
                                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                                    userOrtuModel.setNamalengkap(nama);
//                                    userOrtuModel.setEmail(email);
//                                    userOrtuModel.setTgllahir(tglahir);
//                                    userOrtuModel.setKategori(pengguna);
//                                    userOrtuModel.setUsername(user);
//                                    userOrtuModel.setKatasandi(pass);
                                    db.child("id").setValue(id);
                                    db.child("namalengkap").setValue(nama);
                                    db.child("tempatlahir").setValue(tempatlahir);
                                    db.child("tgllahir").setValue(tgllahir);
                                    db.child("kategori").setValue(pengguna);
                                    db.child("email").setValue(email);
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

        tgllahirs.setOnClickListener(new View.OnClickListener() {
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
                        tgllahirs.setText(tanggal);
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

//    public void btnDaftarAkun(View v) {
//        if (foto != null) {
//            final ProgressDialog progressDialog = new ProgressDialog(this);
//            progressDialog.setTitle("Uploading...");
//            progressDialog.show();
//
//            //get the storage reference
//            StorageReference ref = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() +","+getImageExt(foto));
//
//            //add file to reference
//            ref.putFile(foto)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            //dismiss dialog ketika sukses
//                            progressDialog.dismiss();
//
//                            //display toast msg ketika sukses
//                            Toast.makeText(getApplicationContext(), "Upload Gambar Selesai", Toast.LENGTH_SHORT).show();
////                            startActivity(new Intent(getApplicationContext(), JadwalFragment.class));
//                            Intent intent = new Intent(HalamanDaftar.this, HalamanUtama.class);
//                            startActivity(intent);
//
//                            AkunModel akunModel = new AkunModel(namalengkap.getText().toString(),
//                                    tmplahir.getText().toString(), tgllahirs.getText().toString(),
//                                    kategoripengguna.getSelectedItem().toString(), emailuser.getText().toString(),
//                                    username.getText().toString(), katasandi.getText().toString(),
//                                    taskSnapshot.getDownloadUrl().toString());
//
//                            //save image info in firebase database
//                            String uploadId = mDatabase.push().getKey();
//                            mDatabase.child(uploadId).setValue(akunModel);
//                        }
//                    })
//
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            //dismiss dialog ketika terjadi error
//                            progressDialog.dismiss();
//                            //display toast msg ketika terjadi error
//                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            //show upload progress
//                            double progress = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                            progressDialog.setMessage("Uploaded " + (int)progress+"%");
//                        }
//                    });
//        } else {
//            Toast.makeText(getApplicationContext(), "Pilih Gambar", Toast.LENGTH_SHORT).show();
//        }
//
//    }


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

    @Override
    public void onClick(View view) {
        if (view == lampirfoto) {
            photoBuilder();
        } else if (view==imfoto){

        }

    }

    @NonNull
    private Boolean isValid() {
        if (imfoto.getDrawable() == null) {
            return false;
        }
        return true;
    }

    private void photoBuilder() {
        final CharSequence[] options = {"Ambil Foto", "Pilih dari Galeri", "Batal"};
        AlertDialog.Builder builder = new AlertDialog.Builder(HalamanDaftar.this);
        builder.setTitle("Lampirkan Foto");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(HalamanDaftar.this);
                if (options[item].equals("Ambil Foto")) {
                    if (result) {
                        userChoosenTask = "Take Photo";
                        if (result)
                            cameraIntent();
                    }
                } else if (options[item].equals("Pilih dari Galeri")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();
                } else if (options[item].equals("Batal")) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //pilih lokasi penyimpanan file
        File file = new File(Environment.getExternalStorageDirectory(), UUID.randomUUID().toString() + ".jpg");
        files = file;
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        namafile = Uri.fromFile(file).getLastPathSegment();
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        } else {
            Toast.makeText(HalamanDaftar.this, "Foto gagal diambil, silahkan coba lagi", Toast.LENGTH_LONG).show();
        }
    }

    private void onCaptureImageResult(Intent data) {
        foto = Uri.fromFile(files);
        Picasso.with(HalamanDaftar.this).load(files).resize(imfoto.getWidth(), 500).centerCrop().into(imfoto);
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = HalamanDaftar.this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            File file = new File(filePath);
            foto = Uri.fromFile(file);
            Picasso.with(HalamanDaftar.this).load(file).resize(imfoto.getWidth(), 500).centerCrop().into(imfoto);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Ambil Foto"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Pilih Dari Galeri"))
                        galleryIntent();
                } else {
                    Toast.makeText(this, "Akses Tidak Diizinkan", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public String getImageExt(Uri foto){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(foto));
    }
}
