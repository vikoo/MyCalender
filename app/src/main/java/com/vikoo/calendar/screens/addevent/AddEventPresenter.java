package com.vikoo.calendar.screens.addevent;

import android.text.TextUtils;

import com.vikoo.calendar.db.entity.CalenderEvent;
import com.vikoo.calendar.di.component.ApplicationComponent;
import com.vikoo.calendar.utility.Utils;

/**
 * Created by vikoo on 17/09/17.
 */

public class AddEventPresenter implements AddEventContract.Presenter {

    public static final int ERROR_NAME = 1;
    public static final int ERROR_DATE = 2;
    public static final int ERROR_DURATION = 3;
    public static final int ERROR_TIME = 4;


    private AddEventContract.View mView;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void attach(AddEventContract.View view, ApplicationComponent applicationComponent) {
        mView = view;
        mApplicationComponent = applicationComponent;
    }

    @Override
    public void detach() {
        mView = null;
    }


    @Override
    public void validate(String eventName, String date, String time, String duration, boolean isAllDay) {
        if(TextUtils.isEmpty(eventName)){
            mView.onValidationFailed(ERROR_NAME);
            return;
        }
        if(TextUtils.isEmpty(date)){
            mView.onValidationFailed(ERROR_DATE);
            return;
        }
        if(TextUtils.isEmpty(time)){
            mView.onValidationFailed(ERROR_TIME);
            return;
        }
        if(!isAllDay && TextUtils.isEmpty(duration)){
            mView.onValidationFailed(ERROR_DURATION);
            return;
        }
        addEvent(eventName, date, time, duration, isAllDay);

    }

    private void addEvent(String name, String date, String time, String duration, boolean isAllDay){
        CalenderEvent calenderEvent = new CalenderEvent();
        calenderEvent.eventTitle = name;
        calenderEvent.isAllDayEvent = isAllDay;
        calenderEvent.duration = isAllDay ? 0L : Utils.getMSForMin((int) (Double.parseDouble(duration) * 60));
        calenderEvent.eventDescription = "";
        calenderEvent.longitude = 0.0d;
        calenderEvent.latitude = 0.0d;
        calenderEvent.location = "";
        calenderEvent.eventColor = mView.getEventColor();
        calenderEvent.time = Utils.getTimeFromInput(date, time);
        mApplicationComponent.getDatabase().getCalenderEventDao().save(calenderEvent);
        mView.onEventAdded();
    }
}
