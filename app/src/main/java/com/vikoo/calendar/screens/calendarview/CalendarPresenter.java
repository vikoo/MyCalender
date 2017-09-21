package com.vikoo.calendar.screens.calendarview;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.vikoo.calendar.db.entity.CalenderEvent;
import com.vikoo.calendar.di.component.ApplicationComponent;
import com.vikoo.calendar.utility.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by vikoo on 17/09/17.
 */

public class CalendarPresenter implements CalendarViewContract.Presenter {

    private CalendarViewContract.View mView;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void attach(CalendarViewContract.View view, ApplicationComponent applicationComponent) {
        mView = view;
        mApplicationComponent = applicationComponent;
        onMonthScroll(new Date());
    }

    @Override
    public void detach() {
        mView = null;
    }


    @Override
    public void fetchEvents() {
        mApplicationComponent.getDatabase().getCalenderEventDao().getAll().subscribe(new SingleObserver<List<CalenderEvent>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<CalenderEvent> calenderEvents) {
                List<Event> events = new ArrayList<>();
                for(CalenderEvent calenderEvent : calenderEvents){
                    Event event = new Event(calenderEvent.eventColor, calenderEvent.time, calenderEvent.eventTitle);
                    events.add(event);
                }
                mView.showEvents(events);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void onDayClick(Date dateClicked) {
        Timber.i("onDayClick: " + dateClicked);
    }

    @Override
    public void onMonthScroll(Date firstDayOfNewMonth) {
        mView.setMonthText(Utils.getCalenderMonth(firstDayOfNewMonth));
    }
}
