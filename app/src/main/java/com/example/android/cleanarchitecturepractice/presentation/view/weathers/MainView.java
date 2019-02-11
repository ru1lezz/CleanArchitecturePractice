package com.example.android.cleanarchitecturepractice.presentation.view.weathers;

import com.example.android.cleanarchitecturepractice.presentation.view.model.WeatherUIModel;

import java.util.List;

public interface MainView {
    void displayWeatherList(List<WeatherUIModel> weatherList);
}
