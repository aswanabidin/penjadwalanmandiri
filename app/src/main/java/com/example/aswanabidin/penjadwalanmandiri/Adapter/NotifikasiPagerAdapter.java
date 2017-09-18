package com.example.aswanabidin.penjadwalanmandiri.Adapter;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aswanabidin.penjadwalanmandiri.Fragments.AnakFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.HadiahFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.JadwalFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.NotifikasiHadiahFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.NotifikasiJadwalFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.ProfilFragment;

/**
 * Created by aswanabidin on 9/17/17.
 */

public class NotifikasiPagerAdapter extends FragmentStatePagerAdapter {

    int notabs;

    public NotifikasiPagerAdapter(android.support.v4.app.FragmentManager fm, int nomortabs) {
        super(fm);
        this.notabs = nomortabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NotifikasiJadwalFragment notifikasiJadwalFragment = new NotifikasiJadwalFragment();
                return notifikasiJadwalFragment;
            case 1:
                NotifikasiHadiahFragment notifikasiHadiahFragment = new NotifikasiHadiahFragment();
                return notifikasiHadiahFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return notabs;
    }
}
