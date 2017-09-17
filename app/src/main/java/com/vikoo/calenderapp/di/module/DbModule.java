package com.vikoo.calenderapp.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.vikoo.calenderapp.db.AppDatabase;
import com.vikoo.calenderapp.db.DbCallback;
import com.vikoo.calenderapp.di.qualifier.AppContextQualifier;
import com.vikoo.calenderapp.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vikoo on 16/09/17.
 */

@Module (includes = ContextModule.class)
public class DbModule {

    @Provides
    @AppScope
    public AppDatabase db(@AppContextQualifier Context context, DbCallback dbCallback){
        return  Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DB_NAME)
                .addCallback(dbCallback)
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @AppScope
    public DbCallback dbCallback(@AppContextQualifier Context context){
        return new DbCallback(context);
    }

}
