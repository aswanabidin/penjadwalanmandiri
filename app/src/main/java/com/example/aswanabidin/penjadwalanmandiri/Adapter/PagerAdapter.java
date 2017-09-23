package com.example.aswanabidin.penjadwalanmandiri.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aswanabidin.penjadwalanmandiri.Fragments.AnakFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.AnakJadwalFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.HadiahFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.JadwalFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.ProfilFragment;

/**
 * Created by aswanabidin on 9/15/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int notabs;

    public PagerAdapter(FragmentManager fm, int nomortabs) {
        super(fm);
        this.notabs = nomortabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                JadwalFragment jadwalFragment = new JadwalFragment();
                return jadwalFragment;
            case 1:
                HadiahFragment hadiahFragment = new HadiahFragment();
                return hadiahFragment;
            case 2:
                AnakFragment anakFragment = new AnakFragment();
                return anakFragment;
            case 3:
                ProfilFragment profilFragment = new ProfilFragment();
                return profilFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return notabs;
    }
}
