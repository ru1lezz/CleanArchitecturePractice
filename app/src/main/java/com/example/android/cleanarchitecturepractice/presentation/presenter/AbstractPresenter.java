package com.example.android.cleanarchitecturepractice.presentation.presenter;

import android.os.Handler;

import java.util.concurrent.ExecutorService;

public abstract class AbstractPresenter {
    protected ExecutorService mExecutorService;
    protected Handler mHandler;

    public AbstractPresenter(ExecutorService executorService, Handler handler) {
        this.mExecutorService = executorService;
        this.mHandler = handler;
    }
}
