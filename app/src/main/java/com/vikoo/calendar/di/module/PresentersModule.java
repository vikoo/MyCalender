package com.vikoo.calendar.di.module;

import com.vikoo.calendar.screens.addevent.AddEventContract;
import com.vikoo.calendar.screens.addevent.AddEventPresenter;
import com.vikoo.calendar.screens.agendaview.AgendaViewContract;
import com.vikoo.calendar.screens.agendaview.AgendaPresenter;
import com.vikoo.calendar.screens.calendarview.CalendarPresenter;
import com.vikoo.calendar.screens.calendarview.CalendarViewContract;
import com.vikoo.calendar.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vikoo on 16/09/17.
 */

@Module(includes = {DbModule.class})
public class PresentersModule {

    @Provides
    @ActivityScope
    public CalendarViewContract.Presenter calenderPresenter(){
        return new CalendarPresenter();
    }

    @Provides
    @ActivityScope
    public AgendaViewContract.Presenter agendaPresenter(){
        return new AgendaPresenter();
    }

    @Provides
    @ActivityScope
    public AddEventContract.Presenter addEventPresenter(){
        return new AddEventPresenter();
    }

}
