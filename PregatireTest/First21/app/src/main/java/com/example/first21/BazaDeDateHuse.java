package com.example.first21;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {HusaTelefon.class},version=1)
public abstract class BazaDeDateHuse extends RoomDatabase {

    private static BazaDeDateHuse INSTANCE;

    public abstract HusaDAO husaDAO();

    public static synchronized BazaDeDateHuse getInstance(Context ctx){
        if(INSTANCE==null)
        {
            INSTANCE= Room.databaseBuilder(
                    ctx.getApplicationContext(),
                    BazaDeDateHuse.class,
                    "huse_database"
            ).fallbackToDestructiveMigration().build();
        }
        return  INSTANCE;
    }

}
