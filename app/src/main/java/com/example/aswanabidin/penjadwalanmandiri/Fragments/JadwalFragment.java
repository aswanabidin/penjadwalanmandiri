package com.example.aswanabidin.penjadwalanmandiri.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aswanabidin.penjadwalanmandiri.Adapter.TambahJadwalAnakOrtuAdapeter;
import com.example.aswanabidin.penjadwalanmandiri.HalamanTambahJadwal;
import com.example.aswanabidin.penjadwalanmandiri.Model.TambahJadwalOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class JadwalFragment extends Fragment {

    private Button btntambahjadwal;
    private TambahJadwalOrtuModel tambahJadwalOrtuModel;
    private String TAG = "JadwalFragment";

    private ArrayList<TambahJadwalOrtuModel> tambahJadwalOrtuModels = new ArrayList<>();
    private Context context;
    private TambahJadwalAnakOrtuAdapeter mAdapter;
    private RecyclerView recyclerView;
    private CardView cardView;

    private DatabaseReference myRef;
    private FirebaseDatabase database;

    public static final String FB_STORAGE_PATH = "image/";
    public static final String FB_DATABASE_PATH = "tambahjadwaldariortu";

    private ProgressBar progressBar;

    public JadwalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jadwal, container, false);

        ButterKnife.bind(view);

        mAdapter = new TambahJadwalAnakOrtuAdapeter(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.listJadwalAnakOrtu);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cardView = (CardView) view.findViewById(R.id.cardview_tambahjadwalanakortu);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_circle);

        btntambahjadwal = (Button) view.findViewById(R.id.btnTambahJadwal);
        btntambahjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HalamanTambahJadwal.class);
                startActivity(intent);
            }
        });

        //instansiasi firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(FB_DATABASE_PATH);
        myRef.keepSynced(true);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                progressBar.setVisibility(View.VISIBLE); //progress bar mulai

                tambahJadwalOrtuModels = new ArrayList<TambahJadwalOrtuModel>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    TambahJadwalOrtuModel value = dataSnapshot1.getValue(TambahJadwalOrtuModel.class);
                    TambahJadwalOrtuModel jadwal = new TambahJadwalOrtuModel();
                    String namakegiatan = value.getNamakegiatanortu();
                    String poinkegiatan = value.getPoinortu();
                    String tglkegiatan = value.getTglortu();
                    String waktukegiatan = value.getWaktuortu();
                    String catatankegiatan = value.getCatatanortu();
                    String url = value.getUrl();
                    jadwal.setNamakegiatanortu(namakegiatan);
                    jadwal.setPoinortu(poinkegiatan);
                    jadwal.setTglortu(tglkegiatan);
                    jadwal.setWaktuortu(waktukegiatan);
                    jadwal.setCatatanortu(catatankegiatan);
                    jadwal.setUrl(url);
//                    Log.i("infodata",url);
                    mAdapter.addData(jadwal);
                }
                progressBar.setVisibility(View.GONE); //progress bar berhenti ketika cardview muncul
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //failed to read value
                Log.w("Hello", "Failed to read value.", databaseError.toException());
            }
        });

        return view;

    }
}
