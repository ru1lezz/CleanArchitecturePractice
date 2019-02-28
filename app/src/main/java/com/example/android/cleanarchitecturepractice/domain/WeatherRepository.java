package com.example.android.cleanarchitecturepractice.domain;

import com.example.android.cleanarchitecturepractice.domain.model.Weather;

import java.util.List;

public interface WeatherRepository {
    List<Weather> getRemoteWeatherList(String city, String days);
    Weather getWeatherLocal(String city, long epoch);
}
