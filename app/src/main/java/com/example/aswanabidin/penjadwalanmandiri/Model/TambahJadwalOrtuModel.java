package com.example.aswanabidin.penjadwalanmandiri.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aswanabidin on 9/18/17.
 */

public class TambahJadwalOrtuModel implements Parcelable{

    public String namakegiatanortu;
    public String poinortu;
    public String tglortu;
    public String waktuortu;
    public String catatanortu;
    public String url;

    public TambahJadwalOrtuModel(String namakegiatanortu, String poinortu, String tglortu, String waktuortu, String catatanortu, String url) {
        this.namakegiatanortu = namakegiatanortu;
        this.poinortu = poinortu;
        this.tglortu = tglortu;
        this.waktuortu = waktuortu;
        this.catatanortu = catatanortu;
        this.url = url;
    }

    public TambahJadwalOrtuModel(){

    }

    protected TambahJadwalOrtuModel(Parcel in) {
        namakegiatanortu = in.readString();
        poinortu = in.readString();
        tglortu = in.readString();
        waktuortu = in.readString();
        catatanortu = in.readString();
        url = in.readString();
    }


    public static final Creator<TambahJadwalOrtuModel> CREATOR = new Creator<TambahJadwalOrtuModel>() {
        @Override
        public TambahJadwalOrtuModel createFromParcel(Parcel in) {
            return new TambahJadwalOrtuModel(in);
        }

        @Override
        public TambahJadwalOrtuModel[] newArray(int size) {
            return new TambahJadwalOrtuModel[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNamakegiatanortu() {
        return namakegiatanortu;
    }

    public void setNamakegiatanortu(String namakegiatanortu) {
        this.namakegiatanortu = namakegiatanortu;
    }

    public String getPoinortu() {
        return poinortu;
    }

    public void setPoinortu(String poinortu) {
        this.poinortu = poinortu;
    }

    public String getTglortu() {
        return tglortu;
    }

    public void setTglortu(String tglortu) {
        this.tglortu = tglortu;
    }

    public String getWaktuortu() {
        return waktuortu;
    }

    public void setWaktuortu(String waktuortu) {
        this.waktuortu = waktuortu;
    }

    public String getCatatanortu() {
        return catatanortu;
    }

    public void setCatatanortu(String catatanortu) {
        this.catatanortu = catatanortu;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(namakegiatanortu);
        parcel.writeString(poinortu);
        parcel.writeString(tglortu);
        parcel.writeString(waktuortu);
        parcel.writeString(catatanortu);
        parcel.writeString(url);
    }
}
