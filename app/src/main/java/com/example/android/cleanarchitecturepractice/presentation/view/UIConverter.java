package com.example.android.cleanarchitecturepractice.presentation.view;

import com.example.android.cleanarchitecturepractice.domain.Converter;
import com.example.android.cleanarchitecturepractice.domain.model.Weather;
import com.example.android.cleanarchitecturepractice.presentation.view.model.WeatherUIModel;

public class UIConverter extends Converter<Weather, WeatherUIModel> {

    @Override
    protected WeatherUIModel convert(Weather from) {
        WeatherUIModel to = new WeatherUIModel();
        to.setDate(from.getDate());
        to.setEpoch(from.getEpoch());
        to.setMaxTemp(from.getMaxTemp());
        to.setMinTemp(from.getMinTemp());
        to.setText(from.getText());
        to.setUrlIcon(from.getIconUrl());
        return to;
    }
}
