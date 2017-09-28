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

import com.example.aswanabidin.penjadwalanmandiri.Adapter.TambahAnakAnakOrtuAdapter;
import com.example.aswanabidin.penjadwalanmandiri.OrangTua.HalamanTambahAnak;
import com.example.aswanabidin.penjadwalanmandiri.Model.TambahAnakOrtuModel;
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
public class AnakFragment extends Fragment {

    private Button btntambahanak;

    private ArrayList<TambahAnakOrtuModel> tambahAnakOrtuModel;
    private String TAG = "AnakFragment";

    private ArrayList<TambahAnakOrtuModel> tambahAnakOrtuModels = new ArrayList<>();
    private Context context;
    private TambahAnakAnakOrtuAdapter mAdapter;
    private RecyclerView recyclerView;
    private CardView cardView;

    private DatabaseReference myRef;
    private FirebaseDatabase database;

    public static final String FB_STORAGE_PATH = "imgAnak/";
    public static final String FB_DATABASE_PATH = "tambahanakdariortu";

    private ProgressBar progressBar;


    public AnakFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anak, container, false);

        ButterKnife.bind(view);

        mAdapter = new TambahAnakAnakOrtuAdapter(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.listAnakAnakOrtu);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cardView = (CardView) view.findViewById(R.id.cardview_tambahanakanakortu);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_circle);

        btntambahanak = (Button) view.findViewById(R.id.btnTambahAkunAnak);
        btntambahanak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HalamanTambahAnak.class);
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

                tambahAnakOrtuModel = new ArrayList<TambahAnakOrtuModel>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    TambahAnakOrtuModel value = dataSnapshot1.getValue(TambahAnakOrtuModel.class);
                    TambahAnakOrtuModel anak = new TambahAnakOrtuModel();
                    String mnamaanak = value.getNamaanakortu();
                    String mtmplahir = value.getTempatlahiranakortu();
                    String mtgllahir = value.getTgllahiranakortu();
                    String memail = value.getEmailanakortu();
                    String musername = value.getNamapenggunaanakortu();
//                    String mpass = value.getKatasandianakortu();
                    String url = value.getUrl();
                    anak.setNamaanakortu(mnamaanak);
                    anak.setTempatlahiranakortu(mtmplahir);
                    anak.setTgllahiranakortu(mtgllahir);
                    anak.setEmailanakortu(memail);
                    anak.setNamapenggunaanakortu(musername);
//                    anak.setKatasandianakortu(mpass);
                    anak.setUrl(url);
                    mAdapter.addData(anak);
                }
                progressBar.setVisibility(View.GONE); //progress bar berhenti ketika cardview muncul
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //failed to read value
                Log.w("Hello", "Failed to read value.", databaseError.toException());
            }
        });
//
        return view;

    }

}
