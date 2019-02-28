package com.example.android.cleanarchitecturepractice.data.sharedpref;

import com.example.android.cleanarchitecturepractice.data.SharedPrefDto;
import com.example.android.cleanarchitecturepractice.domain.Converter;

public class DtoConverter extends Converter<SharedPref, SharedPrefDto> {
    public SharedPrefDto convertTo(SharedPref from) {
        SharedPrefDto to = new SharedPrefDto();
        to.setCity(from.getCity());
        to.setDays(from.getDays());
        return to;
    }

    @Override
    public SharedPref convertFrom(SharedPrefDto sharedPrefDto) {
        SharedPref from = new SharedPref();
        from.setCity(sharedPrefDto.getCity());
        from.setDays(sharedPrefDto.getDays());
        return from;
    }
}
