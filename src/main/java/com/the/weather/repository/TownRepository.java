package com.the.weather.repository;

import com.the.weather.model.Town;
import org.springframework.data.repository.CrudRepository;

public interface TownRepository extends CrudRepository<Town, Long> {
}
