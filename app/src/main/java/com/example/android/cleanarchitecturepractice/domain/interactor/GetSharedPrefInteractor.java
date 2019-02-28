package com.example.android.cleanarchitecturepractice.domain.interactor;

import com.example.android.cleanarchitecturepractice.domain.SharedPrefRepository;
import com.example.android.cleanarchitecturepractice.domain.model.SharedPref;

public class GetSharedPrefInteractor {
    private final SharedPrefRepository repository;

    public GetSharedPrefInteractor(SharedPrefRepository repository) {
        this.repository = repository;
    }

    public SharedPref execute() { return repository.getSharedPref(); }
}
