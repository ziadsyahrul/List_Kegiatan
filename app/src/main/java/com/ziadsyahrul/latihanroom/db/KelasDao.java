package com.ziadsyahrul.latihanroom.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ziadsyahrul.latihanroom.model.KategoriModel;
import com.ziadsyahrul.latihanroom.model.ListModel;

import java.util.List;

@Dao
public interface KelasDao {

    // Mengambil data
    @Query("SELECT * FROM KATEGORI ORDER BY nama_hari DESC")
    List<KategoriModel> select();

    // Memasukkan data
    @Insert
    void insert(KategoriModel kategoriModel);

    // Menghapus data
    @Delete
    void delete(KategoriModel kategoriModel);

    // Mengupdate data
    @Update
    void update(KategoriModel kategoriModel);

    // Mengambil data list
    @Query("SELECT * FROM TB_LIST WHERE id_list = :id_list ORDER BY nama_kegiatan ASC")
    List<ListModel> selectList(int id_list);

    // Memasukkan data list
    @Insert
    void insertList(ListModel listModel);

    // Menghapus data list
    @Delete
    void deleteList(ListModel listModel);

    // Mengupdate data list
    @Update
    void updateList(ListModel listModel);
}
