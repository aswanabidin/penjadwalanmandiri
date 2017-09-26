package com.example.aswanabidin.penjadwalanmandiri;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.penjadwalanmandiri.Fragments.AnakFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.HadiahFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.JadwalFragment;
import com.example.aswanabidin.penjadwalanmandiri.Fragments.ProfilFragment;


public class HalamanPenjadwalanMandiri extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static TabLayout tabLayout;
    private FragmentManager fm;
    private FragmentTransaction tukar;
    private JadwalFragment jadwalFragment;
    private HadiahFragment hadiahFragment;
    private AnakFragment anakFragment;
    private ProfilFragment profilFragment;
    private TextView judul;
    private ViewPager viewPager;
    private ImageView imgnotifikasi;
    private boolean jadwal, hadiah, anak, profil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_penjadwalan_mandiri);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Jadwal"));
        tabLayout.addTab(tabLayout.newTab().setText("Hadiah"));
        tabLayout.addTab(tabLayout.newTab().setText("Anak"));
        tabLayout.addTab(tabLayout.newTab().setText("Profil"));

        imgnotifikasi = (ImageView) findViewById(R.id.imgnotif);
        imgnotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanPenjadwalanMandiri.this, HalamanNotifikasiOrangTua.class);
                startActivity(intent);
            }
        });


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new com.example.aswanabidin.penjadwalanmandiri.Adapter.PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        judul = (TextView) findViewById(R.id.toolbarTitle);
        judul.setText("Penjadwalan Mandiri");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Cabin-Regular.otf");
        judul.setTypeface(typeface);


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.histori) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
