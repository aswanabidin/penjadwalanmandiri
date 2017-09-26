package com.example.aswanabidin.penjadwalanmandiri.Fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aswanabidin.penjadwalanmandiri.HalamanEditProfil;
import com.example.aswanabidin.penjadwalanmandiri.HalamanUtama;
import com.example.aswanabidin.penjadwalanmandiri.Model.TambahJadwalOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.Model.userOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    private Button btneditprofil;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private Button btnkeluarakun;
    private TextView tvnamalengkap, tvemail, tvtgllahir, tvkategori, tvusername;

    private ArrayList<userOrtuModel> userOrtuModels = new ArrayList<>();

    private DatabaseReference myRef;
    private FirebaseDatabase database;

    public static final String FB_DATABASE_PATH = "users";


    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        tvnamalengkap = (TextView) view.findViewById(R.id.tvnamapenggunaprofil);
        tvemail = (TextView) view.findViewById(R.id.tvemailprofil);
        tvtgllahir = (TextView) view.findViewById(R.id.tvtanggallahirprofil);
        tvkategori = (TextView) view.findViewById(R.id.tvorangtuaprofil);
        tvusername = (TextView) view.findViewById(R.id.tvusernameprofil);

        btnkeluarakun = (Button) view.findViewById(R.id.btnKeluarAkun);
        btnkeluarakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Keluar Akun")
                        .setMessage("Apa anda yakin keluar?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getActivity(), HalamanUtama.class));

                            }

                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btneditprofil = (Button) view.findViewById(R.id.btnEditProfil);
        btneditprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HalamanEditProfil.class);
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

                userOrtuModels = new ArrayList<userOrtuModel>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    userOrtuModel value = dataSnapshot1.getValue(userOrtuModel.class);

                    String nama = value.getNamalengkap();
                    String email = value.getEmail();
                    String tgllahir = value.getTgllahir();
                    String kategori = value.getKategori();
                    String username = value.getUsername();
                    tvnamalengkap.setText(nama);
                    tvemail.setText(email);
                    tvtgllahir.setText(tgllahir);
                    tvkategori.setText(kategori);
                    tvusername.setText(username);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //failed to read value
                Log.w("Hello", "Failed to read value.", databaseError.toException());
            }
        });


        return view;
    }

    public interface OnFragmentInteractionListener {
    }

}
