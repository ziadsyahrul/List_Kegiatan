package com.ziadsyahrul.latihanroom.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.ziadsyahrul.latihanroom.db.Constant;

@Entity(tableName = "tb_list")
public class ListModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.id_list)
    private int id_list;

    @ColumnInfo(name = Constant.nama_kegiatan)
    private String nama_kegiatan;

    public int getId_list() {
        return id_list;
    }

    public void setId_list(int id_list) {
        this.id_list = id_list;
    }

    public String getNama_kegiatan() {
        return nama_kegiatan;
    }

    public void setNama_kegiatan(String nama_kegiatan) {
        this.nama_kegiatan = nama_kegiatan;
    }
}

