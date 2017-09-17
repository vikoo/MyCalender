package com.vikoo.calenderapp.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.vikoo.calenderapp.R;
import com.vikoo.calenderapp.db.dao.CalenderEventDao;
import com.vikoo.calenderapp.utility.Utils;

import timber.log.Timber;

/**
 * Created by vikoo on 16/09/17.
 */

public class DbCallback extends RoomDatabase.Callback {

    private Context mContext;
    public DbCallback(Context context){
        mContext = context;
    }
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        Timber.i("db onCreate");
        ContentValues contentValues = new ContentValues();

        int currentDay = Utils.getCurrentDay();
        int currentMonth = Utils.getCurrentMonth();
        int currentYear = Utils.getCurrentYear();

        contentValues.put("eventColor", ContextCompat.getColor(mContext, R.color.colorAccent));
        contentValues.put("time", Utils.Day2MS(currentDay, currentMonth, currentYear, 20, 0, 0));
        contentValues.put("eventTitle", "Team Dinner");
        contentValues.put("eventDescription", "Team Dinner @ Palace Hotel");
        contentValues.put("duration", Utils.getMSForMin(120));
        contentValues.put("latitude", 12.960146);
        contentValues.put("longitude", 77.648496);
        contentValues.put("location", "Palace Hotel");
        contentValues.put("isAllDayEvent", false);
        db.insert(CalenderEventDao.TABLE_NAME, OnConflictStrategy.REPLACE, contentValues);

        contentValues = new ContentValues();
        contentValues.put("eventColor", ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        contentValues.put("time", Utils.Day2MS(currentDay + 1, currentMonth, currentYear, 8, 0, 0));
        contentValues.put("eventTitle", "DIA foundation with Marthe");
        contentValues.put("eventDescription", "DIA foundation with Marthe");
        contentValues.put("duration", 0);
        contentValues.put("latitude", 12.960146);
        contentValues.put("longitude", 77.648496);
        contentValues.put("location", "");
        contentValues.put("isAllDayEvent", true);
        db.insert(CalenderEventDao.TABLE_NAME, OnConflictStrategy.REPLACE, contentValues);

        contentValues = new ContentValues();
        contentValues.put("eventColor", ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        contentValues.put("time", Utils.Day2MS(currentDay + 1, currentMonth, currentYear, 19, 0, 0));
        contentValues.put("eventTitle", "Drinks with ADA");
        contentValues.put("eventDescription", "Drinks with ADA");
        contentValues.put("duration", Utils.getMSForMin(150));
        contentValues.put("latitude", 12.960146);
        contentValues.put("longitude", 77.648496);
        contentValues.put("location", "");
        contentValues.put("isAllDayEvent", false);
        db.insert(CalenderEventDao.TABLE_NAME, OnConflictStrategy.REPLACE, contentValues);

        contentValues = new ContentValues();
        contentValues.put("eventColor", ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        contentValues.put("time", Utils.Day2MS(currentDay + 2, currentMonth, currentYear, 9, 0, 0));
        contentValues.put("eventTitle", "Meeting with brsawler");
        contentValues.put("eventDescription", "calender app discussion");
        contentValues.put("duration", Utils.getMSForMin(90));
        contentValues.put("latitude", 12.960146);
        contentValues.put("longitude", 77.648496);
        contentValues.put("location", "");
        contentValues.put("isAllDayEvent", false);
        db.insert(CalenderEventDao.TABLE_NAME, OnConflictStrategy.REPLACE, contentValues);

        contentValues = new ContentValues();
        contentValues.put("eventColor", ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        contentValues.put("time", Utils.Day2MS(currentDay + 3, currentMonth, currentYear, 9, 0, 0));
        contentValues.put("eventTitle", "Meeting with rno - Calender app ");
        contentValues.put("eventDescription", "calender app discussion");
        contentValues.put("duration", Utils.getMSForMin(90));
        contentValues.put("latitude", 12.960146);
        contentValues.put("longitude", 77.648496);
        contentValues.put("location", "");
        contentValues.put("isAllDayEvent", false);
        db.insert(CalenderEventDao.TABLE_NAME, OnConflictStrategy.REPLACE, contentValues);

        contentValues = new ContentValues();
        contentValues.put("eventColor", ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        contentValues.put("time", Utils.Day2MS(currentDay + 3, currentMonth, currentYear, 9, 0, 0));
        contentValues.put("eventTitle", "Calender app war-room");
        contentValues.put("eventDescription", "calender app discussion");
        contentValues.put("duration", Utils.getMSForMin(90));
        contentValues.put("latitude", 12.960146);
        contentValues.put("longitude", 77.648496);
        contentValues.put("location", "");
        contentValues.put("isAllDayEvent", true);
        db.insert(CalenderEventDao.TABLE_NAME, OnConflictStrategy.REPLACE, contentValues);

        Timber.i("db onCreate added default events");

    }
}
