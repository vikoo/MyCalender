package com.vikoo.calendar.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.vikoo.calendar.db.dao.CalenderEventDao;
import com.vikoo.calendar.db.entity.CalenderEvent;

/**
 * Created by vikoo on 16/09/17.
 */

@Database(
        entities = {
                CalenderEvent.class
        },
        version = 1,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "calender_app_db";

    public abstract CalenderEventDao getCalenderEventDao();
}
