package com.vikoo.calenderapp.di.component;

import com.vikoo.calenderapp.screens.agendaview.AgendaActivity;
import com.vikoo.calenderapp.screens.calenderview.CalenderActivity;
import com.vikoo.calenderapp.di.module.ActivityContextModule;
import com.vikoo.calenderapp.di.module.PresentersModule;
import com.vikoo.calenderapp.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by vikoo on 16/09/17.
 */

@ActivityScope
@Component (modules = {ActivityContextModule.class, PresentersModule.class}, dependencies = {ApplicationComponent.class})
public interface ActivityComponent {

    void inject(CalenderActivity calenderActivity);
    void inject(AgendaActivity calenderActivity);
}

