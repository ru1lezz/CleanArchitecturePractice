package com.example.android.cleanarchitecturepractice.data.repository;

import android.content.Context;

import com.example.android.cleanarchitecturepractice.data.DomainSharedPrefConverter;
import com.example.android.cleanarchitecturepractice.data.sharedpref.SharedPrefDataStorage;
import com.example.android.cleanarchitecturepractice.domain.SharedPrefRepository;
import com.example.android.cleanarchitecturepractice.domain.model.SharedPref;

public class SharedPrefRepositoryImpl implements SharedPrefRepository {

    private SharedPrefDataStorage sharedPrefDataStorage;

    public SharedPrefRepositoryImpl(Context context) {
        sharedPrefDataStorage = new SharedPrefDataStorage(context);
    }

    @Override
    public SharedPref getSharedPref() {
        return new DomainSharedPrefConverter().convertTo(sharedPrefDataStorage.getPreferences());
    }

    @Override
    public void setSharedPref(SharedPref sharedPref) {
        sharedPrefDataStorage.setPreferences(new DomainSharedPrefConverter().convertFrom(sharedPref));
    }
}
