package com.vikoo.calenderapp.network;


import com.vikoo.calenderapp.network.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.vikoo.calenderapp.network.RestApi.KEY;

/**
 * Created by vikoo on 16/09/17.
 */
public interface RestApi {

    String KEY = "d75ed95ce0e3fe07cf3e5d0b096eba22";

    @GET("/forecast/" + KEY +"/{latLng}")
    Call<WeatherResponse> getWeatherData(@Path("latLng") String latLng);
}
