package com.example.aswanabidin.penjadwalanmandiri.OrangTua;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
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
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.aswanabidin.penjadwalanmandiri.Adapter.TambahJadwalAnakOrtuAdapeter;
import com.example.aswanabidin.penjadwalanmandiri.HalamanPenjadwalanMandiri;
import com.example.aswanabidin.penjadwalanmandiri.Model.TambahAnakOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.Model.TambahJadwalOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.R;
import com.example.aswanabidin.penjadwalanmandiri.Utility;
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
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class HalamanTambahJadwal extends AppCompatActivity implements View.OnClickListener {

    private EditText namakegiatanortu, poinortu, tglortu, waktuortu, catatanortu;
    int day, month, year, hour, minute;
    private DatabaseReference mDatabase;
    private StorageReference mStorageRef;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private Button btnsubmit, lampirfoto;
    private String userChoosenTask;
    private File files;
    private Uri foto;
    private String namafile;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ImageView imfoto;
    private String url, id;
    private ProgressBar progressBar;
    String stvdeskripsi,stvnmkegiatan,stvtanggal,stvwaktu,stvpoin,surl;
    private TambahJadwalAnakOrtuAdapeter mAdapter;
    private ArrayList list;

    private ArrayList<TambahAnakOrtuModel> tam = new ArrayList<>();
    private ArrayList<TambahJadwalOrtuModel> tambahJadwalOrtuModels = new ArrayList<>();
    public static final String FB_STORAGE_PATH = "image/";
    public static final String FB_DATABASE_PATH = "tambahjadwaldariortu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_tambah_jadwal);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lampirfoto = (Button) findViewById(R.id.btnPilihProfil);
        lampirfoto.setOnClickListener(this);
        imfoto = (ImageView) findViewById(R.id.imgprofil);
        imfoto.setOnClickListener(this);
        imfoto.setAdjustViewBounds(true);

        mDatabase = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        //progressbar
        progressBar = (ProgressBar) findViewById(R.id.progress_circle);

        btnsubmit = (Button) findViewById(R.id.btnSubmitOrtuTambahJadwal);
        namakegiatanortu = (EditText) findViewById(R.id.etNamaKegiatanTambahJadwal);
        poinortu = (EditText) findViewById(R.id.etPoinTambahJadwal);
        tglortu = (EditText) findViewById(R.id.etTanggalTambahJadwal);
        waktuortu = (EditText) findViewById(R.id.etWaktuTambahJadwal);
        catatanortu = (EditText) findViewById(R.id.etCatatanTambahJadwal);


        tglortu = (EditText) findViewById(R.id.etTanggalTambahJadwal);
        tglortu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentDate = Calendar.getInstance();
                year = currentDate.get(Calendar.YEAR);
                month = currentDate.get(Calendar.MONTH);
                day = currentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(HalamanTambahJadwal.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
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
                        if (tanggalpilih < System.currentTimeMillis()) {
                            Toast.makeText(HalamanTambahJadwal.this, "Tidak Ada Kegiatan", Toast.LENGTH_SHORT).show();
                        } else
                            tglortu.setText(tanggal);
                    }
                },year, month, day);
                mDatePicker.setTitle("Pilih Tanggal");
                mDatePicker.show();
            }
        });


        waktuortu = (EditText) findViewById(R.id.etWaktuTambahJadwal);
        waktuortu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentTime = Calendar.getInstance();
                hour = currentTime.get(Calendar.HOUR_OF_DAY);
                minute = currentTime.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(HalamanTambahJadwal.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        waktuortu.setText("" + hourOfDay + ":" + minute);
                        SimpleDateFormat sdt = new SimpleDateFormat("hh:mm:ss a");
                        sdt.getTimeInstance();
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        mDatabase.child("tambahanakdariortu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> areas = new ArrayList<String>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String areaName = areaSnapshot.child("namaanakortu").getValue(String.class);
                    areas.add(areaName);
                }

                Spinner areaSpinner = (Spinner) findViewById(R.id.spinanak);
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(HalamanTambahJadwal.this, android.R.layout.simple_spinner_item, areas);
                areaSpinner.setAdapter(areasAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void btnSubmitOrtuTambahJadwal (View v) {
        if (foto != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Unggah...");
            progressDialog.show();

            //get the storage reference
            StorageReference ref = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() +","+getImageExt(foto));

            //add file to reference
            ref.putFile(foto)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismiss dialog ketika sukses
                            progressDialog.dismiss();

                            //display toast msg ketika sukses
                            Toast.makeText(getApplicationContext(), "Upload Gambar Selesai", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), JadwalFragment.class));
                            Intent intent = new Intent(HalamanTambahJadwal.this, HalamanPenjadwalanMandiri.class);
                            startActivity(intent);

                            TambahJadwalOrtuModel tambahJadwalOrtuModel = new TambahJadwalOrtuModel(namakegiatanortu.getText().toString(),
                                    poinortu.getText().toString(), tglortu.getText().toString(), waktuortu.getText().toString(),
                                    catatanortu.getText().toString(),  taskSnapshot.getDownloadUrl().toString());

                            //save image info in firebase database
                            String uploadId = mDatabase.child("tambahjadwaldariortu").push().getKey();
                            mDatabase.child(uploadId).setValue(tambahJadwalOrtuModel);
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //dismiss dialog ketika terjadi error
                            progressDialog.dismiss();
                            //display toast msg ketika terjadi error
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //show upload progress
                            double progress = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Mengunggah " + (int)progress+"%");
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Pilih Gambar", Toast.LENGTH_SHORT).show();
        }
    }

    //kembali
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanPenjadwalanMandiri.class);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(HalamanTambahJadwal.this);
        builder.setTitle("Lampirkan Foto");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(HalamanTambahJadwal.this);
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
            Toast.makeText(HalamanTambahJadwal.this, "Foto gagal diambil, silahkan coba lagi", Toast.LENGTH_LONG).show();
        }
    }

    private void onCaptureImageResult(Intent data) {
        foto = Uri.fromFile(files);
        Picasso.with(HalamanTambahJadwal.this).load(files).resize(imfoto.getWidth(), 500).centerCrop().into(imfoto);
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = HalamanTambahJadwal.this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            File file = new File(filePath);
            foto = Uri.fromFile(file);
            Picasso.with(HalamanTambahJadwal.this).load(file).resize(imfoto.getWidth(), 500).centerCrop().into(imfoto);
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
