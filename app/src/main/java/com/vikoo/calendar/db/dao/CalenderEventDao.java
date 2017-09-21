package com.vikoo.calendar.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.vikoo.calendar.db.entity.CalenderEvent;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by vikoo on 16/09/17.
 */

@Dao
public interface CalenderEventDao {

    String TABLE_NAME = "CalenderEvent";

    @Query("DELETE FROM " + TABLE_NAME)
    void deleteTable();

    @Query("SELECT * FROM " + TABLE_NAME)
    Single<List<CalenderEvent>> getAll();

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE time >= :startTime AND time < :endTime ORDER BY time ASC")
    Single<List<CalenderEvent>> getEntriesBetweenDate(long startTime, long endTime);

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE eventId = :eventId")
    CalenderEvent getEventById(String eventId);

    @Query("DELETE FROM " + TABLE_NAME + " WHERE eventId = :eventId")
    void deleteByEventById(String eventId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(CalenderEvent... entities);
}
