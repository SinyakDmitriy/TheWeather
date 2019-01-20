package com.the.weather.repository;

import com.the.weather.model.Geo;
import org.springframework.data.repository.CrudRepository;

public interface GeoRepository extends CrudRepository<Geo, Long> {
}
