package com.vikoo.calenderapp.di.module;

import android.app.Activity;
import android.content.Context;

import com.vikoo.calenderapp.di.qualifier.ActivityContextQualifier;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vikoo on 16/09/17.
 */

@Module
public class ActivityContextModule {

    private final Activity activity;

    public ActivityContextModule(Activity context){
        activity = context;
    }

    @Provides
    @ActivityContextQualifier
    public Context appContext(){
        return activity;
    }

    @Provides
    @ActivityContextQualifier
    public Activity activity(){
        return activity;
    }
}
