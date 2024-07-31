package com.atdev.service.impl;

import com.atdev.payload.response.WeatherResponse;
import com.atdev.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private RestTemplate restTemplate;

    private static final String KEY = "da6599250b1d483facb103758243107";
    private static final String API = "http://api.weatherapi.com/v1/current.json?key=KEY&q=CITY";

    public WeatherResponse getWeather(String city){
        String finalAPI = API.replace("CITY", city).replace("KEY", KEY);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse weatherResponse = response.getBody();
        return weatherResponse;
    }
}
