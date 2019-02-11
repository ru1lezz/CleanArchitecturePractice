package com.example.android.cleanarchitecturepractice.presentation.view.model;

import java.util.Objects;

public class WeatherUIModel {
    private long epoch;
    private String date;
    private double minTemp;
    private double maxTemp;
    private String urlIcon;
    private String text;

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherUIModel that = (WeatherUIModel) o;
        return epoch == that.epoch &&
                Double.compare(that.minTemp, minTemp) == 0 &&
                Double.compare(that.maxTemp, maxTemp) == 0 &&
                Objects.equals(date, that.date) &&
                Objects.equals(urlIcon, that.urlIcon) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(epoch, date, minTemp, maxTemp, urlIcon, text);
    }
}
