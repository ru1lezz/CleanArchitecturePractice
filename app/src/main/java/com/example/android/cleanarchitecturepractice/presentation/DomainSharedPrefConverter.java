package com.example.android.cleanarchitecturepractice.presentation;

import com.example.android.cleanarchitecturepractice.domain.Converter;
import com.example.android.cleanarchitecturepractice.presentation.view.model.SharedPref;

public class DomainSharedPrefConverter extends Converter<SharedPref, com.example.android.cleanarchitecturepractice.domain.model.SharedPref> {

    @Override
    public com.example.android.cleanarchitecturepractice.domain.model.SharedPref convertTo(SharedPref sharedPref) {
        com.example.android.cleanarchitecturepractice.domain.model.SharedPref to = new com.example.android.cleanarchitecturepractice.domain.model.SharedPref();
        to.setDays(sharedPref.getDays());
        to.setCity(sharedPref.getCity());
        return to;
    }

    @Override
    public SharedPref convertFrom(com.example.android.cleanarchitecturepractice.domain.model.SharedPref sharedPref) {
        SharedPref from = new SharedPref();
        from.setCity(sharedPref.getCity());
        from.setDays(sharedPref.getDays());
        return from;
    }
}
