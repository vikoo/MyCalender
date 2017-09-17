package com.vikoo.calenderapp.di.module;

import android.content.Context;

import com.vikoo.calenderapp.di.qualifier.AppContextQualifier;
import com.vikoo.calenderapp.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vikoo on 16/09/17.
 */

@Module
public class ContextModule {

    private final Context appContext;

    public ContextModule(Context context){
        appContext = context;
    }

    @Provides
    @AppScope
    public @AppContextQualifier Context context(){
        return appContext;
    }
}
