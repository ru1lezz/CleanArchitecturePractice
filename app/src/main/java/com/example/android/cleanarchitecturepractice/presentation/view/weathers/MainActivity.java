package com.example.android.cleanarchitecturepractice.presentation.view.weathers;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.cleanarchitecturepractice.R;
import com.example.android.cleanarchitecturepractice.data.repository.SharedPrefRepositoryImpl;
import com.example.android.cleanarchitecturepractice.data.repository.WeatherRepositoryImpl;
import com.example.android.cleanarchitecturepractice.presentation.presenter.weatherlist.WeatherListPresenter;
import com.example.android.cleanarchitecturepractice.presentation.view.model.WeatherUIModel;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements MainView {

    private WeatherListPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private WeatherAdapter mAdapter;
    private EditText mCityEditText;
    private Spinner mSpinner;
    private Button mLoadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initRecycler();
        mPresenter = new WeatherListPresenter(Executors.newSingleThreadExecutor(),
                new Handler(Looper.getMainLooper()),
                this,
                new WeatherRepositoryImpl(MainActivity.this),
                new SharedPrefRepositoryImpl(MainActivity.this),
                getResources());
        initListeners();
        initSpinner();
        mPresenter.onCreate();
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mCityEditText = findViewById(R.id.city_edit_text);
        mSpinner = findViewById(R.id.spinner);
        mLoadButton = findViewById(R.id.load_button);
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mAdapter = new WeatherAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListeners() {
        mLoadButton.setOnClickListener(view -> mPresenter.onLoad());
        mCityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.onCityEditTextChanged(s.toString());
            }
        });
    }

    private void initSpinner() {
        String[] options = getResources().getStringArray(R.array.days_array_options);
        int[] values = getResources().getIntArray(R.array.days_array_values);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.onSpinnerItemSelected(values[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void displayWeatherList(List<WeatherUIModel> weatherList) {
        mAdapter.setmWeatherList(weatherList);
    }

    @Override
    public void setCity(String city) {
        mCityEditText.setText(city);
    }

    @Override
    public void setDays(int position) {
        mSpinner.setSelection(position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }
}
