package com.atdev.controller;

import com.atdev.payload.response.WeatherResponse;
import com.atdev.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/temperature/{city}")
    public ResponseEntity getTemperature(@PathVariable("city") String city){
        WeatherResponse weatherResponse = weatherService.getWeather(city);
        if (weatherResponse!=null){
            return new ResponseEntity(weatherResponse, HttpStatus.OK);
        }
        return new ResponseEntity(weatherResponse, HttpStatus.BAD_REQUEST);
    }
}
