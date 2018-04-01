package com.land.gow.plantplanner.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.land.gow.plantplanner.dao.PlantDao;
import com.land.gow.plantplanner.model.Plant;

/**
 * Created by becky on 2018-03-22.
 */
@Database(entities = {Plant.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract PlantDao plantDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "plant-tracker-database")
                            // allow queries on the main thread.
                            // TODO: Don't do this on a real app
                            .fallbackToDestructiveMigration()

                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
