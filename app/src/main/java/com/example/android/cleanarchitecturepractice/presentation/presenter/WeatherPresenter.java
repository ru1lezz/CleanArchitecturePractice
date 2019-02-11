package com.example.android.cleanarchitecturepractice.presentation.presenter;

import android.os.Handler;

import com.example.android.cleanarchitecturepractice.domain.WeatherRepository;
import com.example.android.cleanarchitecturepractice.domain.interactor.GetWeatherListInteractor;
import com.example.android.cleanarchitecturepractice.domain.model.Weather;
import com.example.android.cleanarchitecturepractice.presentation.view.UIConverter;
import com.example.android.cleanarchitecturepractice.presentation.view.model.WeatherUIModel;
import com.example.android.cleanarchitecturepractice.presentation.view.weathers.MainView;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class WeatherPresenter extends AbstractPresenter implements BasePresenter{

    private MainView mView;
    private WeatherRepository mWeatherRepository;

    public WeatherPresenter(ExecutorService executorService, Handler handler, MainView view, WeatherRepository weatherRepository) {
        super(executorService, handler);
        this.mView = view;
        this.mWeatherRepository = weatherRepository;
    }

    @Override
    public void onResume() {
        mExecutorService.execute(() -> {
            GetWeatherListInteractor getWeatherListInteractor = new GetWeatherListInteractor(mWeatherRepository);
            List<Weather> weatherList = getWeatherListInteractor.execute();
            List<WeatherUIModel> newList = new UIConverter().convertAll(weatherList);
            mHandler.post(() -> {
                mView.displayWeatherList(newList);
            });
        });
    }
}
