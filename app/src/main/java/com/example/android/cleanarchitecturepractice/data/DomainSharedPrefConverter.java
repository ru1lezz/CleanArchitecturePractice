package com.example.android.cleanarchitecturepractice.data;

import com.example.android.cleanarchitecturepractice.data.SharedPrefDto;
import com.example.android.cleanarchitecturepractice.domain.Converter;
import com.example.android.cleanarchitecturepractice.domain.model.SharedPref;

public class DomainSharedPrefConverter extends Converter<SharedPrefDto, SharedPref> {
    @Override
    public SharedPref convertTo(SharedPrefDto sharedPrefDto) {
        SharedPref to = new SharedPref();
        to.setCity(sharedPrefDto.getCity());
        to.setDays(sharedPrefDto.getDays());
        return to;
    }

    @Override
    public SharedPrefDto convertFrom(SharedPref sharedPref) {
        SharedPrefDto from = new SharedPrefDto();
        from.setCity(sharedPref.getCity());
        from.setDays(sharedPref.getDays());
        return from;
    }
}
