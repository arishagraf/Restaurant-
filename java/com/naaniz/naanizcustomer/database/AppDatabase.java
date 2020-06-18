package com.naaniz.naanizcustomer.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.naaniz.naanizcustomer.database.daos.DataDao;
import com.naaniz.naanizcustomer.database.domainobjects.Profile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Profile.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataDao dataDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


