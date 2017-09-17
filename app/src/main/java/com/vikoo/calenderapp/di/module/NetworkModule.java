package com.vikoo.calenderapp.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vikoo.calenderapp.BuildConfig;
import com.vikoo.calenderapp.di.scope.AppScope;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vikoo on 16/09/17.
 */

@Module
public class NetworkModule {

    private static final int CONNECT_TIME_OUT = 60;
    private static final int READ_TIME_OUT = 60;

    @Provides
    @AppScope
    public Gson gson(){
        return new GsonBuilder()
                .create();
    }

    @Provides
    @AppScope
    public Converter.Factory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @AppScope
    public OkHttpClient okHttpClient(OkHttpClient.Builder okHttpBuilder){
        return okHttpBuilder.build();
    }

    @Provides
    @AppScope
    public OkHttpClient.Builder okHttpBuilder(HttpLoggingInterceptor httpLoggingInterceptor){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        // interceptor for token and header
        // interceptor for logging
        if(BuildConfig.DEBUG){
            builder.addInterceptor(httpLoggingInterceptor);
        }
        return builder;
    }

    @Provides
    @AppScope
    public HttpLoggingInterceptor httpLoggingInterceptor(){
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}
