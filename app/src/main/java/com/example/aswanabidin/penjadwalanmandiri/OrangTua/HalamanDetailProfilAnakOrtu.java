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
import com.example.aswanabidin.penjadwalanmandiri.Model.TambahAnakOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class HalamanDetailProfilAnakOrtu extends AppCompatActivity {

    private TextView tvemailanak, tvnmanak, tvtmplahir, tvtgllahir, tvusernm;
    private ImageView imageView;
    String stvnmanak, stvemailanak, stvtmplahir, stvtgllahir, stvusernm, surl;
    private StorageReference mStorageRef;
    Context context;
    private DatabaseReference myRef;
    FirebaseDatabase database;
    private Button btneditprofilanak, btnhapusanak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_detail_profil_anak_ortu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvnmanak = (TextView) findViewById(R.id.tvnamaanakprofil);
        tvemailanak = (TextView) findViewById(R.id.tvalamatemail);
        tvtmplahir = (TextView) findViewById(R.id.tvtempatlahiranakprofil);
        tvtgllahir = (TextView) findViewById(R.id.tvtgllahiranakprofil);
        tvusernm = (TextView) findViewById(R.id.tvusernameanakprofil);
        imageView = (ImageView) findViewById(R.id.imganakprofil);
        btneditprofilanak = (Button) findViewById(R.id.btnEditAkunAnak);
        btneditprofilanak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanDetailProfilAnakOrtu.this, HalamanEditProfilAnakOrtu.class);
                startActivity(intent);
            }
        });

        btnhapusanak = (Button) findViewById(R.id.btnHapusAkunAnak);
        btnhapusanak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HalamanDetailProfilAnakOrtu.this);
                builder.setTitle("Hapus Akun Anak")
                        .setMessage("Apa anda yakin menghapus akun anak ini?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance().getReference().child("tambahjadwaldariortu").child("catatanortu").getDatabase();
//                                database.getReference().removeValue();
                                startActivity(new Intent(HalamanDetailProfilAnakOrtu.this, HalamanPenjadwalanMandiri.class));

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

        //untuk mengambil data dari holder tambahanakanakortuadapter dan menampilkan textview
        Bundle bundle;
        bundle = getIntent().getExtras();
        if (bundle != null) {
            TambahAnakOrtuModel tambahAnakOrtuModel = bundle.getParcelable("tambahanakdariortu");
            stvnmanak = tambahAnakOrtuModel.getNamaanakortu();
            stvtmplahir = tambahAnakOrtuModel.getTempatlahiranakortu();
            stvtgllahir = tambahAnakOrtuModel.getTgllahiranakortu();
            stvemailanak = tambahAnakOrtuModel.getEmailanakortu();
            stvusernm = tambahAnakOrtuModel.getNamapenggunaanakortu();
            surl = tambahAnakOrtuModel.getUrl();

            tvnmanak.setText(tambahAnakOrtuModel.getNamaanakortu());
            tvtmplahir.setText(tambahAnakOrtuModel.getTempatlahiranakortu());
            tvtgllahir.setText(tambahAnakOrtuModel.getTgllahiranakortu());
            tvemailanak.setText(tambahAnakOrtuModel.getEmailanakortu());
            tvusernm.setText(tambahAnakOrtuModel.getNamapenggunaanakortu());
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
