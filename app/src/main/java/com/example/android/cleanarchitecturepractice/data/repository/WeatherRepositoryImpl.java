package com.example.android.cleanarchitecturepractice.data.repository;

import com.example.android.cleanarchitecturepractice.data.network.ApiMapper;
import com.example.android.cleanarchitecturepractice.data.network.DomainWeatherConverter;
import com.example.android.cleanarchitecturepractice.data.network.RetrofitHelper;
import com.example.android.cleanarchitecturepractice.domain.WeatherRepository;
import com.example.android.cleanarchitecturepractice.domain.model.Weather;

import java.util.List;

public class WeatherRepositoryImpl implements WeatherRepository {

    private final ApiMapper apiMapper;

    public WeatherRepositoryImpl() {
        apiMapper = new ApiMapper(new RetrofitHelper());
    }

    @Override
    public List<Weather> getWeatherList() {
        return new DomainWeatherConverter().convertAll(apiMapper.getWeatherListSync());
    }
}