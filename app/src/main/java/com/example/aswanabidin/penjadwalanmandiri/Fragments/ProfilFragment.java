package com.example.aswanabidin.penjadwalanmandiri.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.aswanabidin.penjadwalanmandiri.HalamanEditProfil;
import com.example.aswanabidin.penjadwalanmandiri.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    private Button btneditprofil;

    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

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
