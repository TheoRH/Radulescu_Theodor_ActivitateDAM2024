package com.example.seminar11;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TermosDAO {
    @Insert
    void insert(Termos t);
    @Query("SELECT * FROM Termosuri")
    List<Termos> getAll();

    @Delete
    void delete(Termos t);

}
