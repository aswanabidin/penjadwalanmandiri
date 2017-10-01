package com.example.aswanabidin.penjadwalanmandiri.OrangTua;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.penjadwalanmandiri.HalamanPenjadwalanMandiri;
import com.example.aswanabidin.penjadwalanmandiri.Model.TambahHadiahOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class HalamanDetailHadiahOrtu extends AppCompatActivity {

    private TextView tvhadiah, tvpoinhadiah, tvdeskripsihadiah;
    private ImageView imageView;
    String stvhadiah, stvpoinhadiah, stvdeskripsihadiah, surl;
    private StorageReference mStorageRef;
    Context context;
    private DatabaseReference myRef;
    FirebaseDatabase database;
    private Button btnedithadiah, btnhapushadiah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_detail_hadiah_ortu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvhadiah = (TextView) findViewById(R.id.tvnamahadiah);
        tvpoinhadiah = (TextView) findViewById(R.id.tvpoinhadiah);
        tvdeskripsihadiah = (TextView) findViewById(R.id.tvdeskripsihadiah);
        imageView = (ImageView) findViewById(R.id.imghadiah);
        btnedithadiah = (Button) findViewById(R.id.btnEditHadiah);
        btnedithadiah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanDetailHadiahOrtu.this, HalamanEditHadiahOrtu.class);
                startActivity(intent);
            }
        });

        btnhapushadiah = (Button) findViewById(R.id.btnHapusHadiah);
        btnhapushadiah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HalamanDetailHadiahOrtu.this);
                builder.setTitle("Hapus Hadiah")
                        .setMessage("Apa anda yakin menghapus hadiah ini?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance().getReference().child("tambahjadwaldariortu").child("catatanortu").getDatabase();
//                                database.getReference().removeValue();
                                startActivity(new Intent(HalamanDetailHadiahOrtu.this, HalamanPenjadwalanMandiri.class));
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

        //untuk mengambil data dari tambahhadiahanakortuadapter dan menampilkan textview
        Bundle bundle;
        bundle = getIntent().getExtras();
        if (bundle != null) {
            TambahHadiahOrtuModel tambahHadiahOrtuModel = bundle.getParcelable("tambahhadiahdariortu");

            stvhadiah = tambahHadiahOrtuModel.getNamahadiahortu();
            stvpoinhadiah = tambahHadiahOrtuModel.getPoinhadiahortu();
            stvdeskripsihadiah = tambahHadiahOrtuModel.getDeskripsihadiahortu();
            surl = tambahHadiahOrtuModel.getUrl();

            tvhadiah.setText(tambahHadiahOrtuModel.getNamahadiahortu());
            tvpoinhadiah.setText(tambahHadiahOrtuModel.getPoinhadiahortu());
            tvdeskripsihadiah.setText(tambahHadiahOrtuModel.getDeskripsihadiahortu());
            Picasso.with(getApplication()).load(surl).fit().centerCrop().into(imageView);
        }
    }

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
}
