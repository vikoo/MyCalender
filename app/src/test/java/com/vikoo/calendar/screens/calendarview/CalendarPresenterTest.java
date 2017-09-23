package com.vikoo.calendar.screens.calendarview;

import com.vikoo.calendar.di.component.ApplicationComponent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by vikoo on 22/09/17.
 */
public class CalendarPresenterTest {

    @Mock
    ApplicationComponent mApplicationComponent;
    @Mock
    CalendarViewContract.View mView;

    private CalendarViewContract.Presenter mCalendarPresenter;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mCalendarPresenter = new CalendarPresenter();
    }
    @Test
    public void attach() throws Exception {
        mCalendarPresenter.attach(mView, mApplicationComponent);
        verify(mView).setMonthText(anyString());
    }

}