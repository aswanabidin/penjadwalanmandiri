package com.example.aswanabidin.penjadwalanmandiri.OrangTua;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.penjadwalanmandiri.HalamanPenjadwalanMandiri;
import com.example.aswanabidin.penjadwalanmandiri.Model.TambahJadwalOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class HalamanDetailJadwalOrtu extends AppCompatActivity implements View.OnClickListener {

    private TextView tvdeskripsi, tvnmkegiatan, tvtanggal, tvwaktu, tvpoin;
    private ImageView imageView;
    String stvdeskripsi,stvnmkegiatan,stvtanggal,stvwaktu,stvpoin,surl;
    private StorageReference mSotrageRef;
    Context context;
    private DatabaseReference myRef;
    FirebaseDatabase database;
    private Button btneditjadwal, btnhapusjadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_detail_jadwal_ortu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvdeskripsi = (TextView) findViewById(R.id.tvcatatankegiatan);
        tvnmkegiatan = (TextView) findViewById(R.id.tvnamakegiatan);
        tvtanggal = (TextView) findViewById(R.id.tvtanggalkegiatan);
        tvwaktu = (TextView) findViewById(R.id.tvwaktukegiatan);
        tvpoin = (TextView) findViewById(R.id.tvpoinkegiatan);
        imageView = (ImageView) findViewById(R.id.imgprofilanakJadwal);
        btnhapusjadwal = (Button) findViewById(R.id.btnHapusJadwal);
        btneditjadwal = (Button) findViewById(R.id.btnEditJadwal);
        btnhapusjadwal.setOnClickListener(this);

        btnhapusjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HalamanDetailJadwalOrtu.this);
                builder.setTitle("Hapus Jadwal")
                        .setMessage("Apa anda yakin menghapus jadwal ini?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance().getReference().child("tambahjadwaldariortu").child("catatanortu").getDatabase();
//                                database.getReference().removeValue();
                                startActivity(new Intent(HalamanDetailJadwalOrtu.this, HalamanPenjadwalanMandiri.class));

                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btneditjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanDetailJadwalOrtu.this, HalamanEditJadwalOrtu.class);
                startActivity(intent);
            }
        });

        //untuk mengambil data dari holder recylceradapter dan menampilkan di textview
        Bundle bundle;
        bundle = getIntent().getExtras();
        if (bundle != null) {
            TambahJadwalOrtuModel tambahJadwalOrtuModel = bundle.getParcelable("tambahjadwaldariortu");
            stvdeskripsi = tambahJadwalOrtuModel.getCatatanortu();
            stvnmkegiatan = tambahJadwalOrtuModel.getNamakegiatanortu();
            stvtanggal = tambahJadwalOrtuModel.getTglortu();
            stvwaktu = tambahJadwalOrtuModel.getWaktuortu();
            stvpoin = tambahJadwalOrtuModel.getPoinortu();
            surl = tambahJadwalOrtuModel.getUrl();

            tvdeskripsi.setText(tambahJadwalOrtuModel.getCatatanortu());
            tvnmkegiatan.setText(tambahJadwalOrtuModel.getNamakegiatanortu());
            tvtanggal.setText(tambahJadwalOrtuModel.getTglortu());
            tvwaktu.setText(tambahJadwalOrtuModel.getWaktuortu());
            tvpoin.setText(tambahJadwalOrtuModel.getPoinortu());
            Picasso.with(getApplication()).load(surl).fit().centerCrop().into(imageView);
        }

    }

//    public void btnHapusJadwal(View v){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//        builder.setTitle("Hapus Data")
//                .setMessage("Apa anda yakin menghapus data ini?")
//                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
////                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
////                        Query query = ref.child("tambahjadwaldariortu").orderByChild("catatanortu").orderByChild("namakegiatanortu")
////                                .orderByChild("poinortu").orderByChild("tglortu").orderByChild("waktuortu").equalTo("url");
////                        query.addListenerForSingleValueEvent(new ValueEventListener() {
////                            @Override
////                            public void onDataChange(DataSnapshot dataSnapshot) {
////                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
////                                    dataSnapshot1.getRef().removeValue();
////                                }
////                            }
////
////                            @Override
////                            public void onCancelled(DatabaseError databaseError) {
////                            }
////                        });
//                        startActivity(new Intent(HalamanDetailJadwalOrtu.this, HalamanPenjadwalanMandiri.class));
//                    }
//                })
//                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }

    // kodingan tombol back
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


    }
}
