package com.vikoo.calendar.network;


import com.vikoo.calendar.network.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by vikoo on 16/09/17.
 */
public interface RestApi {

    String KEY = "d75ed95ce0e3fe07cf3e5d0b096eba22";

    @GET("/forecast/" + KEY +"/{latLng}")
    Call<WeatherResponse> getWeatherData(@Path("latLng") String latLng);
}
