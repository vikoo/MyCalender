package com.vikoo.calendar.di.component;

import com.vikoo.calendar.db.AppDatabase;
import com.vikoo.calendar.di.module.ContextModule;
import com.vikoo.calendar.di.module.DbModule;
import com.vikoo.calendar.di.module.NetworkServiceModule;
import com.vikoo.calendar.di.scope.AppScope;
import com.vikoo.calendar.network.RestApi;

import dagger.Component;

/**
 * Created by vikoo on 16/09/17.
 */

@AppScope
@Component (modules = {NetworkServiceModule.class, DbModule.class, ContextModule.class})
public interface ApplicationComponent {

    AppDatabase getDatabase();

    RestApi getRestApi();

}

