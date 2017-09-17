package com.vikoo.calenderapp.di.component;

import com.vikoo.calenderapp.db.AppDatabase;
import com.vikoo.calenderapp.di.module.ContextModule;
import com.vikoo.calenderapp.di.module.DbModule;
import com.vikoo.calenderapp.di.module.NetworkServiceModule;
import com.vikoo.calenderapp.di.scope.AppScope;
import com.vikoo.calenderapp.network.RestApi;

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

