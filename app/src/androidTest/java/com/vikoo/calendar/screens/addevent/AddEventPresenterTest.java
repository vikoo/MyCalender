package com.vikoo.calendar.screens.addevent;

import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.vikoo.calendar.db.AppDatabase;
import com.vikoo.calendar.db.dao.CalenderEventDao;
import com.vikoo.calendar.db.entity.CalenderEvent;
import com.vikoo.calendar.di.component.ApplicationComponent;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by vikoo on 22/09/17.
 */
@RunWith(AndroidJUnit4.class)
public class AddEventPresenterTest {

    private AppDatabase mDb;
    private CalenderEventDao mEventDao;
    private AddEventPresenter mAddEventPresenter;

    @Mock
    ApplicationComponent mApplicationComponent;
    @Mock
    AddEventContract.View mView;

    @Before
    public void initDb() throws Exception {
        MockitoAnnotations.initMocks(this);
        mAddEventPresenter = new AddEventPresenter();
        mDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), AppDatabase.class)
                .build();

        when(mApplicationComponent.getDatabase()).thenReturn(mDb);
        mAddEventPresenter.attach(mView, mApplicationComponent);

        mEventDao = mDb.getCalenderEventDao();
    }

    @After
    public void closeDb(){
        mDb.close();
    }

    @Test
    public void addEvent_validArgs() throws Exception {
        List<CalenderEvent> list = mDb.getCalenderEventDao().getAllEvents();
        int before = list.size();
        mAddEventPresenter.validate("event", "28/09/2017", "02:00", "1", false, Color.argb(0,0,0,0));
        list = mDb.getCalenderEventDao().getAllEvents();
        int after = list.size();
        Assert.assertTrue(after > before);
    }

    @Test
    public void addEvent_invalidArgsName() throws Exception {
        List<CalenderEvent> list = mDb.getCalenderEventDao().getAllEvents();
        int before = list.size();
        mAddEventPresenter.validate("", "28/09/2017", "02:00", "1", false, Color.argb(0,0,0,0));
        list = mDb.getCalenderEventDao().getAllEvents();
        int after = list.size();
        Assert.assertTrue(after == before);
    }

    @Test
    public void addEvent_invalidArgsDate() throws Exception {
        List<CalenderEvent> list = mDb.getCalenderEventDao().getAllEvents();
        int before = list.size();
        mAddEventPresenter.validate("Test", "", "02:00", "1", false, Color.argb(0,0,0,0));
        list = mDb.getCalenderEventDao().getAllEvents();
        int after = list.size();
        Assert.assertTrue(after == before);
    }

    @Test
    public void addEvent_invalidArgsTime() throws Exception {
        List<CalenderEvent> list = mDb.getCalenderEventDao().getAllEvents();
        int before = list.size();
        mAddEventPresenter.validate("TEst", "28/09/2017", "", "1", false, Color.argb(0,0,0,0));
        list = mDb.getCalenderEventDao().getAllEvents();
        int after = list.size();
        Assert.assertTrue(after == before);
    }

    @Test
    public void addEvent_invalidArgsDuration() throws Exception {
        List<CalenderEvent> list = mDb.getCalenderEventDao().getAllEvents();
        int before = list.size();
        mAddEventPresenter.validate("TEst", "28/09/2017", "02:00", "", false, Color.argb(0,0,0,0));
        list = mDb.getCalenderEventDao().getAllEvents();
        int after = list.size();
        Assert.assertTrue(after == before);
    }

    @Test
    public void addEvent_invalidArgsAllDay() throws Exception {
        List<CalenderEvent> list = mDb.getCalenderEventDao().getAllEvents();
        int before = list.size();
        mAddEventPresenter.validate("TEst", "28/09/2017", "02:00", "", true, Color.argb(0,0,0,0));
        list = mDb.getCalenderEventDao().getAllEvents();
        int after = list.size();
        Assert.assertTrue(after > before);
    }

}