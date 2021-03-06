package com.vikoo.calendar;

import android.app.Application;
import android.content.Context;

import com.vikoo.calendar.di.component.ApplicationComponent;
import com.vikoo.calendar.di.component.DaggerApplicationComponent;
import com.vikoo.calendar.di.module.ContextModule;

/**
 * Created by vikoo on 16/09/17.
 */

public class CalendarApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }


    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }

    public static CalendarApplication get(Context context){
        return (CalendarApplication) context.getApplicationContext();
    }

}
