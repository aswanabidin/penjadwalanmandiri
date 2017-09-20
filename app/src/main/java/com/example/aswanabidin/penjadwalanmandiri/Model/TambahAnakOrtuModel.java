package com.example.aswanabidin.penjadwalanmandiri.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aswanabidin on 9/18/17.
 */

public class TambahAnakOrtuModel implements Parcelable {

    public String namaanakortu;
    public String tempatlahiranakortu;
    public String tgllahiranakortu;
    public String emailanakortu;
    public String namapenggunaanakortu;
    public String katasandianakortu;

    public String getNamaanakortu() {
        return namaanakortu;
    }

    public void setNamaanakortu(String namaanakortu) {
        this.namaanakortu = namaanakortu;
    }

    public String getTempatlahiranakortu() {
        return tempatlahiranakortu;
    }

    public void setTempatlahiranakortu(String tempatlahiranakortu) {
        this.tempatlahiranakortu = tempatlahiranakortu;
    }

    public String getTgllahiranakortu() {
        return tgllahiranakortu;
    }

    public void setTgllahiranakortu(String tgllahiranakortu) {
        this.tgllahiranakortu = tgllahiranakortu;
    }

    public String getEmailanakortu() {
        return emailanakortu;
    }

    public void setEmailanakortu(String emailanakortu) {
        this.emailanakortu = emailanakortu;
    }

    public String getNamapenggunaanakortu() {
        return namapenggunaanakortu;
    }

    public void setNamapenggunaanakortu(String namapenggunaanakortu) {
        this.namapenggunaanakortu = namapenggunaanakortu;
    }

    public String getKatasandianakortu() {
        return katasandianakortu;
    }

    public void setKatasandianakortu(String katasandianakortu) {
        this.katasandianakortu = katasandianakortu;
    }

    public static Creator<TambahAnakOrtuModel> getCREATOR() {
        return CREATOR;
    }

    public TambahAnakOrtuModel(String namaanakortu, String tempatlahiranakortu, String tgllahiranakortu, String emailanakortu, String namapenggunaanakortu, String katasandianakortu) {
        this.namaanakortu = namaanakortu;
        this.tempatlahiranakortu = tempatlahiranakortu;
        this.tgllahiranakortu = tgllahiranakortu;
        this.emailanakortu = emailanakortu;
        this.namapenggunaanakortu = namapenggunaanakortu;
        this.katasandianakortu = katasandianakortu;
    }

    protected TambahAnakOrtuModel(Parcel in) {
        namaanakortu = in.readString();
        tempatlahiranakortu = in.readString();
        tgllahiranakortu = in.readString();
        emailanakortu = in.readString();
        namapenggunaanakortu = in.readString();
        katasandianakortu = in.readString();
    }

    public static final Creator<TambahAnakOrtuModel> CREATOR = new Creator<TambahAnakOrtuModel>() {
        @Override
        public TambahAnakOrtuModel createFromParcel(Parcel in) {
            return new TambahAnakOrtuModel(in);
        }

        @Override
        public TambahAnakOrtuModel[] newArray(int size) {
            return new TambahAnakOrtuModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(namaanakortu);
        parcel.writeString(tempatlahiranakortu);
        parcel.writeString(tgllahiranakortu);
        parcel.writeString(emailanakortu);
        parcel.writeString(namapenggunaanakortu);
        parcel.writeString(katasandianakortu);
    }
}
