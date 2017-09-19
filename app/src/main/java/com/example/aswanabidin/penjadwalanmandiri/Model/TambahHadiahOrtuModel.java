package com.example.aswanabidin.penjadwalanmandiri.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aswanabidin on 9/18/17.
 */

public class TambahHadiahOrtuModel implements Parcelable {

    public String namahadiahortu;
    public String poinhadiahortu;
    public String deskripsihadiahortu;

    public String getNamahadiahortu() {
        return namahadiahortu;
    }

    public void setNamahadiahortu(String namahadiahortu) {
        this.namahadiahortu = namahadiahortu;
    }

    public String getPoinhadiahortu() {
        return poinhadiahortu;
    }

    public void setPoinhadiahortu(String poinhadiahortu) {
        this.poinhadiahortu = poinhadiahortu;
    }

    public String getDeskripsihadiahortu() {
        return deskripsihadiahortu;
    }

    public void setDeskripsihadiahortu(String deskripsihadiahortu) {
        this.deskripsihadiahortu = deskripsihadiahortu;
    }

    public static Creator<TambahHadiahOrtuModel> getCREATOR() {
        return CREATOR;
    }

    public TambahHadiahOrtuModel(String namahadiahortu, String poinhadiahortu, String deskripsihadiahortu) {
        this.namahadiahortu = namahadiahortu;
        this.poinhadiahortu = poinhadiahortu;
        this.deskripsihadiahortu = deskripsihadiahortu;
    }

    protected TambahHadiahOrtuModel(Parcel in) {
    }

    public static final Creator<TambahHadiahOrtuModel> CREATOR = new Creator<TambahHadiahOrtuModel>() {
        @Override
        public TambahHadiahOrtuModel createFromParcel(Parcel in) {
            return new TambahHadiahOrtuModel(in);
        }

        @Override
        public TambahHadiahOrtuModel[] newArray(int size) {
            return new TambahHadiahOrtuModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
