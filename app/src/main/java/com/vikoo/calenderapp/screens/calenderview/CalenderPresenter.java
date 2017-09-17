package com.vikoo.calenderapp.screens.calenderview;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.vikoo.calenderapp.db.entity.CalenderEvent;
import com.vikoo.calenderapp.di.component.ApplicationComponent;
import com.vikoo.calenderapp.utility.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by vikoo on 17/09/17.
 */

public class CalenderPresenter implements CalenderViewContract.Presenter {

    private CalenderViewContract.View mView;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void attach(CalenderViewContract.View view) {
        mView = view;
        mApplicationComponent = mView.getApplicationComponent();
        onMonthScroll(new Date());
        fetchEvents();
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
