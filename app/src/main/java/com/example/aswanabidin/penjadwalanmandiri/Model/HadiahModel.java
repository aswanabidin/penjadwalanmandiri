package com.example.aswanabidin.penjadwalanmandiri.Model;

/**
 * Created by aswanabidin on 9/20/17.
 */

public class HadiahModel {

    public String namahadiah;
    public String poinhadiah;
    public String deskripsihadiah;

    public HadiahModel(){

    }

    public HadiahModel(String namahadiah, String poinhadiah, String deskripsihadiah) {
        this.namahadiah = namahadiah;
        this.poinhadiah = poinhadiah;
        this.deskripsihadiah = deskripsihadiah;
    }

    public String getNamahadiah() {
        return namahadiah;
    }

    public void setNamahadiah(String namahadiah) {
        this.namahadiah = namahadiah;
    }

    public String getPoinhadiah() {
        return poinhadiah;
    }

    public void setPoinhadiah(String poinhadiah) {
        this.poinhadiah = poinhadiah;
    }

    public String getDeskripsihadiah() {
        return deskripsihadiah;
    }

    public void setDeskripsihadiah(String deskripsihadiah) {
        this.deskripsihadiah = deskripsihadiah;
    }
}
