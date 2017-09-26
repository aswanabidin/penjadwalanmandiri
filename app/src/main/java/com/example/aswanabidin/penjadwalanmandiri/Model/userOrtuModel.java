package com.example.aswanabidin.penjadwalanmandiri.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aswanabidin on 9/25/17.
 */

public class userOrtuModel {

    private String namalengkap, email, tgllahir, kategori, username, katasandi;

    public userOrtuModel(String namalengkap, String email, String tgllahir, String kategori, String username, String katasandi) {
        this.namalengkap = namalengkap;
        this.email = email;
        this.tgllahir = tgllahir;
        this.kategori = kategori;
        this.username = username;
        this.katasandi = katasandi;
    }

    public userOrtuModel(){

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

    public String getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(String tgllahir) {
        this.tgllahir = tgllahir;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
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
