package com.ziadsyahrul.latihanroom.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ziadsyahrul.latihanroom.model.KategoriModel;
import com.ziadsyahrul.latihanroom.model.ListModel;

@Database(entities = {KategoriModel.class, ListModel.class}, version = 1)
public abstract class ListDatabase extends RoomDatabase {

    public abstract KelasDao kelasDao();

    private static ListDatabase INSTANCE;

    // Method return untuk bikin database nya
    public static ListDatabase createDatabase(Context context){
        synchronized (ListDatabase.class){
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, ListDatabase.class, "db_list").allowMainThreadQueries().build();
            }
        }
        return INSTANCE;
    }
}
