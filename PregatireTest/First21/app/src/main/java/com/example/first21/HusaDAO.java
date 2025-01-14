package com.example.first21;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HusaDAO {
    @Insert
    void insert (HusaTelefon h);
    @Query("SELECT * FROM Huse")
    List<HusaTelefon> getAll();
}
