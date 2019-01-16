package com.the.weather.services;

import com.the.weather.model.Weather;
import org.geolatte.geom.Point;

public interface WeatherService {
    Weather getWeather(double lat, double lng);
    Weather findByPositionAndTimestampGreaterThanEqual(Point position, long timestamp);
    void saveWeather(Weather weather);
}
