package com.example.aswanabidin.penjadwalanmandiri.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aswanabidin on 9/20/17.
 */

public class AkunModel {

    public String namalengkap;
    public String tmplahir;
    public String tgllahir;
    public String kategoripengguna;
    public String email;
    public String username;
    public String katasandi;
    public String url;

    public AkunModel(){

    }

    public AkunModel(String namalengkap, String tmplahir, String tgllahir, String kategoripengguna, String email, String username, String katasandi, String url) {
        this.namalengkap = namalengkap;
        this.tmplahir = tmplahir;
        this.tgllahir = tgllahir;
        this.kategoripengguna = kategoripengguna;
        this.email = email;
        this.username = username;
        this.katasandi = katasandi;
        this.url = url;
    }

    public String getNamalengkap() {
        return namalengkap;
    }

    public void setNamalengkap(String namalengkap) {
        this.namalengkap = namalengkap;
    }

    public String getTmplahir() {
        return tmplahir;
    }

    public void setTmplahir(String tmplahir) {
        this.tmplahir = tmplahir;
    }

    public String getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(String tgllahir) {
        this.tgllahir = tgllahir;
    }

    public String getKategoripengguna() {
        return kategoripengguna;
    }

    public void setKategoripengguna(String kategoripengguna) {
        this.kategoripengguna = kategoripengguna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
