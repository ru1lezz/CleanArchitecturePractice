package com.example.android.cleanarchitecturepractice.presentation;

import com.example.android.cleanarchitecturepractice.domain.Converter;
import com.example.android.cleanarchitecturepractice.domain.model.Weather;
import com.example.android.cleanarchitecturepractice.presentation.view.model.WeatherUIModel;

public class WeatherUIModelConverter extends Converter<Weather, WeatherUIModel> {

    @Override
    public WeatherUIModel convertTo(Weather from) {
        WeatherUIModel to = new WeatherUIModel();
        to.setCity(from.getCity());
        to.setDate(from.getDate());
        to.setEpoch(from.getEpoch());
        to.setMaxTemp(from.getMaxTemp());
        to.setMinTemp(from.getMinTemp());
        to.setText(from.getText());
        to.setUrlIcon(from.getIconUrl());
        to.setAvgHumidity(from.getAvgHumidity());
        to.setMaxWind(from.getMaxWind());
        return to;
    }

    @Override
    public Weather convertFrom(WeatherUIModel weatherUIModel) {
        return null;
    }
}
