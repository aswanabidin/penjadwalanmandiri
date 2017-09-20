package com.example.aswanabidin.penjadwalanmandiri.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aswanabidin on 9/20/17.
 */

public class AkunModel {

    public String namalengkap;
    public String email;
    public String kategoripengguna;
    public String username;
    public String katasandi;

    public AkunModel(){

    }

    public AkunModel(String namalengkap, String email, String kategoripengguna, String username, String katasandi) {
        this.namalengkap = namalengkap;
        this.email = email;
        this.kategoripengguna = kategoripengguna;
        this.username = username;
        this.katasandi = katasandi;
    }

    public String getNamalengkap() {
        return namalengkap;
    }

    public void setNamalengkap(String namalengkap) {
        this.namalengkap = namalengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKategoripengguna() {
        return kategoripengguna;
    }

    public void setKategoripengguna(String kategoripengguna) {
        this.kategoripengguna = kategoripengguna;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKatasandi() {
        return katasandi;
    }

    public void setKatasandi(String katasandi) {
        this.katasandi = katasandi;
    }
}
