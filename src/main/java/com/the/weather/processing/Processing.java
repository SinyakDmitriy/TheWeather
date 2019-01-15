package com.the.weather.processing;

import com.the.weather.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Processing {

    @Autowired private WeatherService weatherService;

    @Scheduled(fixedRate = 10000L)
    public void init(){

    }

}
