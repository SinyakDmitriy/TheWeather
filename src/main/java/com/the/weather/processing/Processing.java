package com.the.weather.processing;

import com.the.weather.model.Geo;
import com.the.weather.model.Weather;
import com.the.weather.repository.GeoRepository;
import com.the.weather.services.WeatherService;
import com.the.weather.util.TimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Objects.nonNull;

@Component
public class Processing implements TimeConstants {

    @Autowired private GeoRepository geoRepository;
    @Autowired private WeatherService weatherService;

    private List<Geo> townList = new CopyOnWriteArrayList<>();

    public void init(){
        townList.addAll((List<Geo>) geoRepository.findAll());
    }

    @Scheduled(fixedRate = 2000L)
    public void scheduler(){
        if(townList.isEmpty()) init();
        else {
            Geo town = townList.get(0);
            townList.remove(0);
            long currentTimestamp = System.currentTimeMillis();
            Weather prev = weatherService.findByPositionAndTimestampGreaterThanEqual(town.getPosition(), currentTimestamp - HOUR);

            if(nonNull(prev)) return;

            Weather weather = weatherService.getWeather(Double.parseDouble(town.getLat()), Double.parseDouble(town.getLng()));
            weatherService.saveWeather(weather);
        }
    }

}
