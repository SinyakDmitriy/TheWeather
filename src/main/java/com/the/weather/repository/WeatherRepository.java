package com.the.weather.repository;

import com.the.weather.model.Weather;
import org.geolatte.geom.Point;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<Weather, Long> {
    Weather findByPositionAndTimestampGreaterThanEqual(Point position, long timestamp);
}
