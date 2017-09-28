package com.example.aswanabidin.penjadwalanmandiri.Fragments;


import android.content.Context;
import android.content.Intent;
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

import com.example.aswanabidin.penjadwalanmandiri.Adapter.TambahHadiahAnakOrtuAdapter;
import com.example.aswanabidin.penjadwalanmandiri.OrangTua.HalamanTambahHadiah;
import com.example.aswanabidin.penjadwalanmandiri.Model.TambahHadiahOrtuModel;
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
 */
public class HadiahFragment extends Fragment {

    private Button btntambahhadiah;
    private TambahHadiahOrtuModel tambahHadiahOrtuModel;
    private String TAG = "HadiahFragment";

    private ArrayList<TambahHadiahOrtuModel> tambahHadiahOrtuModels = new ArrayList<>();
    private Context context;
    private TambahHadiahAnakOrtuAdapter mAdapter;
    private RecyclerView recyclerView;
    private CardView cardView;

    private DatabaseReference myRef;
    private FirebaseDatabase database;

    public static final String FB_STORAGE_PATH = "imgHadiah/";
    public static final String FB_DATABASE_PATH = "tambahhadiahdariortu";

    private ProgressBar progressBar;


    public HadiahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_hadiah, container, false);

        ButterKnife.bind(view);

        mAdapter = new TambahHadiahAnakOrtuAdapter(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.listHadiahAnakOrtu);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cardView = (CardView) view.findViewById(R.id.cardview_tambahhadiahanakortu);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_circle);

        btntambahhadiah = (Button) view.findViewById(R.id.btnTambahHadiah);
        btntambahhadiah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HalamanTambahHadiah.class);
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

                tambahHadiahOrtuModels = new ArrayList<TambahHadiahOrtuModel>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    TambahHadiahOrtuModel value = dataSnapshot1.getValue(TambahHadiahOrtuModel.class);

                    TambahHadiahOrtuModel hadiah = new TambahHadiahOrtuModel();
                    String mnamahadiah = value.getNamahadiahortu();
                    String mpoinhadiah = value.getPoinhadiahortu();
                    String mdeskripsi = value.getDeskripsihadiahortu();
                    String url = value.getUrl();
                    hadiah.setNamahadiahortu(mnamahadiah);
                    hadiah.setPoinhadiahortu(mpoinhadiah);
                    hadiah.setDeskripsihadiahortu(mdeskripsi);
                    hadiah.setUrl(url);
//                    Log.i("infodata",url);
                    mAdapter.addData(hadiah);
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
