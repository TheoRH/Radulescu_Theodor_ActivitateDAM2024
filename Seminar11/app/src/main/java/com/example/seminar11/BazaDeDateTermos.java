package com.example.seminar11;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Termos.class},version=1)
public abstract class BazaDeDateTermos extends RoomDatabase {


    private static BazaDeDateTermos INSTANCE;

    public abstract TermosDAO termosDao();

    public static synchronized BazaDeDateTermos getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BazaDeDateTermos.class,
                    "termos_database"
            ).fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
