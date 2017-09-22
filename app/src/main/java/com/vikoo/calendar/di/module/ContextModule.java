package com.vikoo.calendar.di.module;

import android.content.Context;

import com.vikoo.calendar.di.qualifier.AppContextQualifier;
import com.vikoo.calendar.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vikoo on 16/09/17.
 */

@Module
public class ContextModule {

    private final Context mAppContext;

    public ContextModule(Context context){
        mAppContext = context;
    }

    @Provides
    @AppScope
    public @AppContextQualifier Context context(){
        return mAppContext;
    }
}
