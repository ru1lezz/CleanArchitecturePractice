package com.example.android.cleanarchitecturepractice.domain.interactor;

import com.example.android.cleanarchitecturepractice.domain.SharedPrefRepository;
import com.example.android.cleanarchitecturepractice.domain.model.SharedPref;

public class SetSharedPrefInteractor {
    private final SharedPrefRepository repository;
    private final SharedPref sharedPref;

    public SetSharedPrefInteractor(SharedPrefRepository repository, SharedPref sharedPref) {
        this.repository = repository;
        this.sharedPref = sharedPref;
    }

    public void execute() { repository.setSharedPref(sharedPref); }
}
