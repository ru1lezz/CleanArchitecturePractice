package com.example.android.cleanarchitecturepractice.domain.interactor;

import android.util.Log;

import com.example.android.cleanarchitecturepractice.domain.WeatherRepository;
import com.example.android.cleanarchitecturepractice.domain.model.Weather;

import java.util.List;

public class GetWeatherListInteractor {
    private final WeatherRepository mRepository;

    public GetWeatherListInteractor(WeatherRepository mRepository) {
        this.mRepository = mRepository;
    }

    public List<Weather> execute() {
        Log.i(getClass().getSimpleName(), "++++++++++++++++++++++++++++ " + mRepository.getWeatherList().size());
        return mRepository.getWeatherList();
    }
}
