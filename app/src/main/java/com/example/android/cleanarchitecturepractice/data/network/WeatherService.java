package com.example.android.cleanarchitecturepractice.data.network;

import com.example.android.cleanarchitecturepractice.data.network.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET(value = "forecast.json")
    Call<WeatherResponse> getWeatherList(@Query("key") String key, @Query("q") String name, @Query("days") String days, @Query("lang") String lang);
}
