package com.vikoo.calenderapp.screens.calenderview;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.vikoo.calenderapp.base.BasePresenter;
import com.vikoo.calenderapp.base.BaseView;

import java.util.Date;
import java.util.List;

/**
 * Created by vikoo on 17/09/17.
 */

public class CalenderViewContract {

    public interface View extends BaseView<Presenter>{

        void showEvents(List<Event> events);
        void setMonthText(String text);
    }

    public interface Presenter extends BasePresenter<View>{

        void fetchEvents();
        void onDayClick(Date dateClicked);
        void onMonthScroll(Date firstDayOfNewMonth);
    }
}
