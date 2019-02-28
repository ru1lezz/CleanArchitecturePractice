package com.example.android.cleanarchitecturepractice.presentation.presenter.weatherlist;

import android.content.res.Resources;
import android.os.Handler;

import com.example.android.cleanarchitecturepractice.R;
import com.example.android.cleanarchitecturepractice.domain.SharedPrefRepository;
import com.example.android.cleanarchitecturepractice.domain.WeatherRepository;
import com.example.android.cleanarchitecturepractice.domain.interactor.GetSharedPrefInteractor;
import com.example.android.cleanarchitecturepractice.domain.interactor.GetWeatherListInteractor;
import com.example.android.cleanarchitecturepractice.domain.model.Weather;
import com.example.android.cleanarchitecturepractice.presentation.DomainSharedPrefConverter;
import com.example.android.cleanarchitecturepractice.presentation.UIConverter;
import com.example.android.cleanarchitecturepractice.presentation.presenter.AbstractPresenter;
import com.example.android.cleanarchitecturepractice.presentation.presenter.BasePresenter;
import com.example.android.cleanarchitecturepractice.presentation.view.model.SharedPref;
import com.example.android.cleanarchitecturepractice.presentation.view.model.WeatherUIModel;
import com.example.android.cleanarchitecturepractice.presentation.view.weathers.MainView;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class WeatherListPresenter extends AbstractPresenter implements BasePresenter {

    private Resources resources;
    private MainView mView;
    private WeatherRepository mWeatherRepository;
    private SharedPrefRepository sharedPrefRepository;
    private SharedPref sharedPref;

    public WeatherListPresenter(ExecutorService executorService, Handler handler, MainView view, WeatherRepository weatherRepository, SharedPrefRepository sharedPrefRepository, Resources resources) {
        super(executorService, handler);
        this.mView = view;
        this.mWeatherRepository = weatherRepository;
        this.sharedPrefRepository = sharedPrefRepository;
        this.resources = resources;
    }

    @Override
    public void onCreate() {
        sharedPref = new DomainSharedPrefConverter().convertFrom(new GetSharedPrefInteractor(sharedPrefRepository).execute());
        mView.setCity(sharedPref.getCity());
        mView.setDays(sharedPref.getDays() - 1);
    }

    @Override
    public void onResume() {
        mExecutorService.execute(() -> {
            GetWeatherListInteractor getWeatherListInteractor = new GetWeatherListInteractor(sharedPref.getCity(), String.valueOf(sharedPref.getDays() + 1), mWeatherRepository);
            List<Weather> weatherList = getWeatherListInteractor.execute();
            List<WeatherUIModel> newList = new UIConverter().convertToAll(weatherList);
            mHandler.post(() -> {
                mView.displayWeatherList(newList);
                mView.setCity(sharedPref.getCity());
                setSpinnerPosition();
            });
        });
    }

    private void setSpinnerPosition() {
        int[] days = resources.getIntArray(R.array.days_array_values);
        for (int i = 0; i < days.length; i++) {
            if(days[i] == sharedPref.getDays()) {
                mView.setDays(i);
                return;
            }
        }
    }

    @Override
    public void onLoad() {
        mExecutorService.execute(() -> {
            GetWeatherListInteractor getWeatherListInteractor = new GetWeatherListInteractor(sharedPref.getCity(), String.valueOf(sharedPref.getDays()), mWeatherRepository);
            List<Weather> weatherList = getWeatherListInteractor.execute();
            List<WeatherUIModel> newList = new UIConverter().convertToAll(weatherList);
            mHandler.post(() -> mView.displayWeatherList(newList));
        });
    }

    public void onSpinnerItemSelected(int days) {
        sharedPref.setDays(days);
    }

    public void onCityEditTextChanged(String city) {
        sharedPref.setCity(city);
    }
}