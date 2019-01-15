package com.the.weather.api;

import com.the.weather.model.Weather;
import com.the.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Test {

    @Autowired private WeatherRepository weatherRepository;

    public void init(){

        Weather weather = new Weather();
        weather.setBase("");
        weather.setCloudsAll(0);
        weather.setHumidity(0);
        weather.setName("");
        weather.setPositionByLatLng(50, 36);
        weather.setPressure(0);
        weather.setSunrise(0);
        weather.setSunset(0);
        weather.setTemperature(0);
        weather.setTempMax(0);
        weather.setTempMin(0);
        weather.setTimestamp(10);
        weather.setVisibility(0);
        weather.setWind_deg(0);
        weather.setWind_speed(0);

        weatherRepository.save(weather);

    }
}
