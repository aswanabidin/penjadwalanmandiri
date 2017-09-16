package com.example.aswanabidin.penjadwalanmandiri.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.aswanabidin.penjadwalanmandiri.HalamanTambahJadwal;
import com.example.aswanabidin.penjadwalanmandiri.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class JadwalFragment extends Fragment {

    private Button btntambahjadwal;

    public JadwalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jadwal, container, false);

        btntambahjadwal = (Button) view.findViewById(R.id.btnTambahJadwal);
        btntambahjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HalamanTambahJadwal.class);
                startActivity(intent);
            }
        });

        return view;

    }

    public interface OnFragmentInteractionListener {
    }
}
