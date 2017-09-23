package com.example.aswanabidin.penjadwalanmandiri.Adapter;


import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aswanabidin.penjadwalanmandiri.Fragments.AnakFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.AnakHadiahFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.AnakJadwalFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.AnakProfilFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.HadiahFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.JadwalFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.ProfilFragment;

/**
 * Created by aswanabidin on 9/23/17.
 */

public class AnakPagerAdapter extends FragmentStatePagerAdapter {

    int notabs;

    public AnakPagerAdapter(FragmentManager fm, int nomortabs) {
        super(fm);
        this.notabs = nomortabs;
    }


    @Override
    public int getCount() {
        return notabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                AnakJadwalFragment anakJadwalFragment = new AnakJadwalFragment();
                return anakJadwalFragment;
            case 1:
                AnakHadiahFragment anakHadiahFragment = new AnakHadiahFragment();
                return anakHadiahFragment;
            case 2:
                AnakProfilFragment anakProfilFragment = new AnakProfilFragment();
                return anakProfilFragment;
            default:
                return null;
        }
    }
}
