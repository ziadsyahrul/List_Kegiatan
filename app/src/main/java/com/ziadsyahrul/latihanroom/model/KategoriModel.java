package com.ziadsyahrul.latihanroom.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.ziadsyahrul.latihanroom.db.Constant;

@Entity(tableName = Constant.nama_table)
public class KategoriModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.id_kategori)
    private int id_kelas;

    @ColumnInfo(name = Constant.nama_hari)
    private String nama_hari;

    @ColumnInfo(name = Constant.tanggal)
    private String tanggal;

    public int getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(int id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getNama_hari() {
        return nama_hari;
    }

    public void setNama_hari(String nama_hari) {
        this.nama_hari = nama_hari;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
