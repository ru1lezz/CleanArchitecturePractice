package com.example.android.cleanarchitecturepractice.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Converter<From, To> {

    protected abstract To convert(From from);

    public final List<To> convertAll(List<From> from) {
        List<To> to = new ArrayList<>();
        for(From item : from) {
            to.add(convert(item));
        }
        return to;
    }
}