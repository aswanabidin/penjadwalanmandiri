package com.example.aswanabidin.penjadwalanmandiri.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aswanabidin.penjadwalanmandiri.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {


    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return view;
    }

    public interface OnFragmentInteractionListener {
    }

}
