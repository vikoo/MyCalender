package com.vikoo.calendar.screens.agendaview;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.vikoo.calendar.db.AppDatabase;
import com.vikoo.calendar.db.entity.CalenderEvent;
import com.vikoo.calendar.di.component.ApplicationComponent;
import com.vikoo.calendar.di.component.DaggerApplicationComponent;
import com.vikoo.calendar.di.module.ContextModule;
import com.vikoo.calendar.network.RestApi;
import com.vikoo.calendar.screens.agendaview.models.Agenda;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vikoo on 22/09/17.
 */
@RunWith(AndroidJUnit4.class)
public class AgendaPresenterTest {

    private AppDatabase mDb;
    private RestApi mRestApi;
    private AgendaPresenter mAgendaPresenter;
    @Mock
    ApplicationComponent mApplicationComponent;
    @Mock
    AgendaViewContract.View mView;


    @Before
    public void initDb() throws Exception {
        MockitoAnnotations.initMocks(this);
        mAgendaPresenter = new AgendaPresenter();
        mDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), AppDatabase.class)
                .build();

        mRestApi = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(InstrumentationRegistry.getContext()))
                .build().getRestApi();

        when(mApplicationComponent.getDatabase()).thenReturn(mDb);
        when(mApplicationComponent.getRestApi()).thenReturn(mRestApi);
        mAgendaPresenter.attach(mView, mApplicationComponent);
    }

    @After
    public void closeDb(){
        mDb.close();
    }

    @Test
    public void fetchEvents() throws Exception {
        List<CalenderEvent> list = mDb.getCalenderEventDao().getAllEvents();
        mAgendaPresenter.fetchEvents(new Date());
        verify(mView).showEvents(ArgumentMatchers.<Agenda>anyList());
    }

}