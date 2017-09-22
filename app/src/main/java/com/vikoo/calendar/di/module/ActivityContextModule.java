package com.vikoo.calendar.di.module;

import android.app.Activity;
import android.content.Context;

import com.vikoo.calendar.di.qualifier.ActivityContextQualifier;
import com.vikoo.calendar.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vikoo on 16/09/17.
 */

@Module
public class ActivityContextModule {

    private final Activity mActivity;

    public ActivityContextModule(Activity context){
        mActivity = context;
    }

    @Provides
    @ActivityContextQualifier
    @ActivityScope
    public Context appContext(){
        return mActivity;
    }

    @Provides
    @ActivityContextQualifier
    @ActivityScope
    public Activity activity(){
        return mActivity;
    }
}
