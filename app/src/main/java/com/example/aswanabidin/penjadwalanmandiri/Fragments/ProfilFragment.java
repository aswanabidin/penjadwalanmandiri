package com.example.aswanabidin.penjadwalanmandiri.Fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aswanabidin.penjadwalanmandiri.HalamanEditProfil;
import com.example.aswanabidin.penjadwalanmandiri.HalamanUtama;
import com.example.aswanabidin.penjadwalanmandiri.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    private Button btneditprofil;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private Button btnkeluarakun;

    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        btnkeluarakun = (Button) view.findViewById(R.id.btnKeluarAkun);
        btnkeluarakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Keluar Akun")
                        .setMessage("Apa anda yakin keluar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                auth.signOut();
//                        HalamanAccount.this.finish();

                                progressDialog.show();
                                Intent intent = new Intent(getActivity(), HalamanUtama.class);
                                startActivity(intent);
                                Toast.makeText(getActivity(), "Keluar Akun Sukses", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

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


        return view;
    }

    public interface OnFragmentInteractionListener {
    }

}
