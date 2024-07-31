package com.atdev.service;

import com.atdev.payload.response.WeatherResponse;

public interface WeatherService {

    public WeatherResponse getWeather(String city);
}
