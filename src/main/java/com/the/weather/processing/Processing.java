package com.the.weather.processing;

import com.the.weather.model.Town;
import com.the.weather.model.Weather;
import com.the.weather.repository.TownRepository;
import com.the.weather.services.WeatherService;
import com.the.weather.util.TimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Objects.nonNull;

@Component
public class Processing implements TimeConstants {

    @Autowired private TownRepository townRepository;
    @Autowired private WeatherService weatherService;

    private List<Town> townList = new CopyOnWriteArrayList<>();

    public void init(){
        townList.addAll((List<Town>) townRepository.findAll());
    }

    @Scheduled(fixedRate = 2000L)
    public void scheduler(){
        if(townList.isEmpty()) init();
        else {
            Town town = townList.get(0);
            townList.remove(0);
            long currentTimestamp = System.currentTimeMillis();
            Weather prev = weatherService.findByPositionAndTimestampGreaterThanEqual(town.getPosition(), currentTimestamp - HOUR);

            if(nonNull(prev)) return;

            Weather weather = weatherService.getWeather(town.getLat(), town.getLng());
            weatherService.saveWeather(weather);
        }
    }

}
