package com.vikoo.calenderapp;

import android.app.Application;
import android.content.Context;

import com.vikoo.calenderapp.di.component.ApplicationComponent;
import com.vikoo.calenderapp.di.component.DaggerApplicationComponent;
import com.vikoo.calenderapp.di.module.ContextModule;

/**
 * Created by vikoo on 16/09/17.
 */

public class CalenderApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }


    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

    public static CalenderApplication get(Context context){
        return (CalenderApplication) context.getApplicationContext();
    }

}
