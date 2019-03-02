package com.example.android.cleanarchitecturepractice.presentation.presenter.weather;

import android.os.Handler;

import com.example.android.cleanarchitecturepractice.domain.WeatherRepository;
import com.example.android.cleanarchitecturepractice.domain.interactor.GetWeatherInteractor;
import com.example.android.cleanarchitecturepractice.domain.model.Weather;
import com.example.android.cleanarchitecturepractice.presentation.DetailedWeatherConverter;
import com.example.android.cleanarchitecturepractice.presentation.presenter.AbstractPresenter;
import com.example.android.cleanarchitecturepractice.presentation.presenter.BasePresenter;
import com.example.android.cleanarchitecturepractice.presentation.view.detailedweather.DetailedWeatherView;
import com.example.android.cleanarchitecturepractice.presentation.view.model.DetailedWeatherUIModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;

public class WeatherPresenter extends AbstractPresenter implements BasePresenter {
    private DetailedWeatherView mView;
    private WeatherRepository repository;

    private String city;
    private long epoch;

    public WeatherPresenter(ExecutorService executorService, Handler handler, DetailedWeatherView mView, WeatherRepository repository, String city, long epoch) {
        super(executorService, handler);
        this.mView = mView;
        this.repository = repository;
        this.city = city;
        this.epoch = epoch;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {
        mExecutorService.execute(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM");
            Date date = new Date(epoch * 1000);
            GetWeatherInteractor interactor = new GetWeatherInteractor(city, epoch, repository);
            Weather weather = interactor.execute();
            DetailedWeatherUIModel weatherUIModel = new DetailedWeatherConverter().convertTo(weather);
            mHandler.post(() -> {
                mView.setCity(city);
                mView.setDate(sdf.format(date));
                mView.setHighTemperature(String.valueOf(weatherUIModel.getMaxTemp()));
                mView.setLowTemperature(String.valueOf(weatherUIModel.getMinTemp()));
                mView.setHumidity(String.valueOf(weatherUIModel.getAvgHumidity()));
                mView.setWeatherDescription(weatherUIModel.getText());
                mView.setWind(String.valueOf(weatherUIModel.getMaxWind()));
                mView.setIconUrl("http:" + weatherUIModel.getUrlIcon());
            });
        });
    }

    @Override
    public void onLoad() {

    }
}
