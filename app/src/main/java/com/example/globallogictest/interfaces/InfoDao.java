package com.example.globallogictest.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.globallogictest.data.Info;

import java.util.List;

@Dao
public interface InfoDao {

    @Insert
    void insert(Info info);

    @Query("DELETE FROM info_table")
    void delete();

    @Query("DELETE FROM info_table")
    void deleteAll();

    @Query("SELECT * from info_table ORDER BY title ASC")
    LiveData<List<Info>> getAllInfo();
}
