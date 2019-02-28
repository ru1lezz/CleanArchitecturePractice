package com.example.android.cleanarchitecturepractice.data.repository;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.android.cleanarchitecturepractice.data.WeatherDto;
import com.example.android.cleanarchitecturepractice.data.db.DbDtoConverter;
import com.example.android.cleanarchitecturepractice.data.db.WeatherDatabase;
import com.example.android.cleanarchitecturepractice.data.db.model.WeatherLocal;
import com.example.android.cleanarchitecturepractice.data.network.ApiMapper;
import com.example.android.cleanarchitecturepractice.data.DomainWeatherConverter;
import com.example.android.cleanarchitecturepractice.data.network.RetrofitHelper;
import com.example.android.cleanarchitecturepractice.domain.WeatherRepository;
import com.example.android.cleanarchitecturepractice.domain.model.Weather;

import java.util.List;

public class WeatherRepositoryImpl implements WeatherRepository {

    private final ApiMapper apiMapper;
    private final WeatherDatabase db;

    public WeatherRepositoryImpl(Context context) {
        apiMapper = new ApiMapper(new RetrofitHelper());
        db = Room.databaseBuilder(context, WeatherDatabase.class, "weather_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Override
    public List<Weather> getRemoteWeatherList(String city, String days) {
        List<WeatherDto> weatherDto = apiMapper.getWeatherListSync(city, days);
        List<WeatherLocal> weatherLocalList = new DbDtoConverter(city).convertFromAll(weatherDto);
        insertToDb(weatherLocalList);
        return new DomainWeatherConverter().convertToAll(weatherDto);
    }

    @Override
    public Weather getWeatherLocal(String city, long epoch) {
        WeatherDto weatherDto = new DbDtoConverter(city).convertTo(db.getWeatherDao().getWeather(city, epoch));

        return new DomainWeatherConverter().convertTo(weatherDto);
    }

    public void insertToDb(List<WeatherLocal> weatherLocalList) {
        for(WeatherLocal weatherLocal : weatherLocalList) {
            db.getWeatherDao().insert(weatherLocal);
        }
    }
}