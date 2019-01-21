package com.the.weather.services.impl;

import com.the.weather.model.Weather;
import com.the.weather.repository.WeatherRepository;
import com.the.weather.services.WeatherService;
import org.geolatte.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.http.HttpMethod.GET;

@Component
public class WeatherServiceImpl implements WeatherService {

    @Autowired private RestTemplate restTemplate;
    @Autowired private WeatherRepository weatherRepository;

    @Override
    public Weather getWeather(double lat, double lng) {
        Map<String, Object> map = restTemplate.exchange(
                "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lng}&APPID=e7b083307cdcb84f7256bba93a255920",
                GET,
                null,
                Map.class,
                lat,
                lng
        ).getBody();

        Weather weather = new Weather();
        weather.setBase((String) map.get("base"));

        weather.setPositionByLatLng(lat, lng);

        Map<String, Object> main = (Map<String, Object>) map.get("main");
        weather.setTemperature(getDouble(main.get("temp")));
        weather.setPressure(getInteger(main.get("pressure")));
        weather.setHumidity(getInteger(main.get("humidity")));
        weather.setTempMax(getDouble(main.get("temp_max")));
        weather.setTempMin(getDouble(main.get("temp_min")));

        weather.setVisibility(getInteger(map.get("visibility")));

        Map<String, Object> wind = (Map<String, Object>) map.get("wind");
        weather.setWind_speed(getInteger(wind.get("speed")));
        weather.setWind_deg(getInteger(wind.get("deg")));

        Map<String, Object> clouds = (Map<String, Object>) map.get("clouds");
        weather.setCloudsAll(getInteger(clouds.get("all")));
        weather.setTimestamp(getInteger(map.get("dt")));

        Map<String, Object> sys = (Map<String, Object>) map.get("sys");
        weather.setSunrise(getInteger(sys.get("sunrise")));
        weather.setSunset(getInteger(sys.get("sunset")));

        weather.setName((String) map.get("name"));

        return weather;
    }

    private Double getDouble(Object obj){

        if(obj instanceof Double) return (Double) obj;
        if(obj instanceof Long) return ((Long) obj).doubleValue();
        if(obj instanceof Integer) return ((Integer) obj).doubleValue();
        if(obj instanceof Short) return ((Short) obj).doubleValue();
        if(obj instanceof Byte) return ((Byte) obj).doubleValue();

        return 0.0d;
    }

    private Integer getInteger(Object obj){

        if(obj instanceof Integer) return (Integer) obj;
        if(obj instanceof Long) return ((Long) obj).intValue();
        if(obj instanceof Double) return ((Double) obj).intValue();
        if(obj instanceof Short) return ((Short) obj).intValue();
        if(obj instanceof Byte) return ((Byte) obj).intValue();

        return 0;
    }

    @Override
    public Weather findByPositionAndTimestampGreaterThanEqual(Point position, long timestamp) {
        return weatherRepository.findByPositionAndTimestampGreaterThanEqual(position, timestamp);
    }

    @Override
    public void saveWeather(Weather weather) {
        weatherRepository.save(weather);
    }
}
