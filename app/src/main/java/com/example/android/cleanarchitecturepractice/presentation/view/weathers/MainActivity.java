package com.example.android.cleanarchitecturepractice.presentation.view.weathers;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.cleanarchitecturepractice.R;
import com.example.android.cleanarchitecturepractice.data.repository.WeatherRepositoryImpl;
import com.example.android.cleanarchitecturepractice.presentation.presenter.WeatherPresenter;
import com.example.android.cleanarchitecturepractice.presentation.view.model.WeatherUIModel;
import com.example.android.cleanarchitecturepractice.presentation.view.weathers.MainView;
import com.example.android.cleanarchitecturepractice.presentation.view.weathers.WeatherAdapter;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements MainView {

    private WeatherPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private WeatherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        initRecycler();
        mPresenter = new WeatherPresenter(Executors.newSingleThreadExecutor(),
                new Handler(Looper.getMainLooper()),
                this,
                new WeatherRepositoryImpl());
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mAdapter = new WeatherAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void displayWeatherList(List<WeatherUIModel> weatherList) {
        mAdapter.setmWeatherList(weatherList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }
}
