package com.example.android.cleanarchitecturepractice.data.network;

import com.example.android.cleanarchitecturepractice.data.WeatherDto;
import com.example.android.cleanarchitecturepractice.domain.Converter;
import com.example.android.cleanarchitecturepractice.domain.model.Weather;

public class DomainWeatherConverter extends Converter<WeatherDto, Weather> {
    @Override
    protected Weather convert(WeatherDto from) {
        Weather to = new Weather();
        to.setEpoch(from.getEpoch());
        to.setDate(from.getDate());
        to.setAvgHumidity(from.getAvgHumidity());
        to.setIconUrl(from.getIconUrl());
        to.setMaxTemp(from.getMaxTemp());
        to.setMinTemp(from.getMinTemp());
        to.setMaxWind(from.getMaxWind());
        to.setText(from.getText());
        return to;
    }
}
