package com.example.android.cleanarchitecturepractice.data.network;

import com.example.android.cleanarchitecturepractice.data.WeatherDto;
import com.example.android.cleanarchitecturepractice.data.network.model.Weather;
import com.example.android.cleanarchitecturepractice.domain.Converter;

public class DtoConverter extends Converter<Weather, WeatherDto> {
    @Override
    public WeatherDto convertTo(Weather from) {
        WeatherDto to = new WeatherDto();
        to.setEpoch(from.getEpoch());
        to.setDate(from.getDate());
        to.setAvgHumidity(from.getDay().getAvgHumidity());
        to.setIconUrl(from.getDay().getCondition().getIconUrl());
        to.setMaxTemp(from.getDay().getMaxTemp());
        to.setMinTemp(from.getDay().getMinTemp());
        to.setMaxWind(from.getDay().getMaxWind());
        to.setText(from.getDay().getCondition().getText());
        return to;
    }

    @Override
    public Weather convertFrom(WeatherDto to) {
        return null;
    }
}
