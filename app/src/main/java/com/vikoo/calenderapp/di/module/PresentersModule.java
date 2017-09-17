package com.vikoo.calenderapp.di.module;

import com.vikoo.calenderapp.screens.agendaview.AgendaViewContract;
import com.vikoo.calenderapp.screens.agendaview.AgentaPresenter;
import com.vikoo.calenderapp.screens.calenderview.CalenderPresenter;
import com.vikoo.calenderapp.screens.calenderview.CalenderViewContract;
import com.vikoo.calenderapp.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vikoo on 16/09/17.
 */

@Module ()
public class PresentersModule {

    @Provides
    @ActivityScope
    public CalenderViewContract.Presenter calenderPresenter(){
        return new CalenderPresenter();
    }

    @Provides
    @ActivityScope
    public AgendaViewContract.Presenter agendaPresenter(){
        return new AgentaPresenter();
    }

}
