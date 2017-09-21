package com.vikoo.calendar.di.module;

import com.vikoo.calendar.di.scope.AppScope;
import com.vikoo.calendar.network.RestApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by vikoo on 16/09/17.
 */

@Module (includes = NetworkModule.class)
public class NetworkServiceModule {

    public static final String BASE_URL = "https://api.darksky.net";

    @Provides
    @AppScope
    public RestApi restApi(Retrofit retrofit){
        return retrofit.create(RestApi.class);
    }

    @Provides
    @AppScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Converter.Factory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }
}
