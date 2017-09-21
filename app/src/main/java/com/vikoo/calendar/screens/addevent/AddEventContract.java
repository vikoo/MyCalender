package com.vikoo.calendar.screens.addevent;

import com.vikoo.calendar.base.BasePresenter;
import com.vikoo.calendar.base.BaseView;

/**
 * Created by vikoo on 17/09/17.
 */

public class AddEventContract {

    public interface View extends BaseView<Presenter>{
        void onValidationFailed(int errorCode);
        void onEventAdded();
        int getEventColor();
    }

    public interface Presenter extends BasePresenter<View>{
        void validate(String eventName, String date, String time, String duration, boolean isAllDay);
    }
}
