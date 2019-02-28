package com.example.android.cleanarchitecturepractice.domain;

import com.example.android.cleanarchitecturepractice.domain.model.SharedPref;

public interface SharedPrefRepository {
    SharedPref getSharedPref();

    void setSharedPref(SharedPref sharedPref);
}
