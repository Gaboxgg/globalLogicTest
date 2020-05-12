package com.example.globallogictest.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.globallogictest.interfaces.InfoDao;

@Database(entities = {Info.class}, version = 1)
public abstract class InfoDatabase extends RoomDatabase {

    public abstract InfoDao infoDao();
    private static InfoDatabase INSTANCE;

    static InfoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InfoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InfoDatabase.class, "info_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}