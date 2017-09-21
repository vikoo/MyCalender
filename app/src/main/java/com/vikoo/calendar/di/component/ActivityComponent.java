package com.vikoo.calendar.di.component;

import com.vikoo.calendar.screens.addevent.AddEventActivity;
import com.vikoo.calendar.screens.agendaview.AgendaActivity;
import com.vikoo.calendar.screens.calendarview.CalendarActivity;
import com.vikoo.calendar.di.module.ActivityContextModule;
import com.vikoo.calendar.di.module.PresentersModule;
import com.vikoo.calendar.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by vikoo on 16/09/17.
 */

@ActivityScope
@Component (modules = {ActivityContextModule.class, PresentersModule.class}, dependencies = {ApplicationComponent.class})
public interface ActivityComponent {

    void inject(CalendarActivity calendarActivity);
    void inject(AgendaActivity calenderActivity);
    void inject(AddEventActivity calenderActivity);
}

