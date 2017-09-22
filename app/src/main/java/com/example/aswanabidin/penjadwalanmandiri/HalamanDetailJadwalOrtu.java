package com.example.aswanabidin.penjadwalanmandiri;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.penjadwalanmandiri.Model.TambahJadwalOrtuModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class HalamanDetailJadwalOrtu extends AppCompatActivity {

    private TextView tvdeskripsi, tvnmkegiatan, tvtanggal, tvwaktu, tvpoin;
    private ImageView imageView;
    String stvdeskripsi,stvnmkegiatan,stvtanggal,stvwaktu,stvpoin,surl;
    private StorageReference mSotrageRef;
    Context context;
    private DatabaseReference myRef;
    FirebaseDatabase database;
    private Button btneditjadwal;

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
        imageView = (ImageView) findViewById(R.id.imgjadwal);

        btneditjadwal = (Button) findViewById(R.id.btnEditJadwal);
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
