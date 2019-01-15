package com.the.weather.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;

import javax.persistence.*;

@Data
@Entity
@Embeddable
@Table(name = "weather")
public class Weather {
    public Weather() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "base")
    private String base;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "pressure")
    private int pressure;

    @Column(name = "humidity")
    private int humidity;

    @Column(name = "temp_min")
    private double tempMin;

    @Column(name = "temp_max")
    private double tempMax;

    @Column(name = "visibility")
    private int visibility;

    @Column(name = "wind_speed")
    private double wind_speed;

    @Column(name = "wind_deg")
    private double wind_deg;

    @Column(name = "clouds_all")
    private int cloudsAll;

    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "sunrise")
    private long sunrise;

    @Column(name = "sunset")
    private long sunset;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private Point position;

    @JsonCreator
    public Weather(
            @JsonProperty("lat") double latitude,
            @JsonProperty("lng") double longitude) {

        position = Geometries.mkPoint(new G2D(longitude, latitude), CoordinateReferenceSystems.WGS84);
    }

    @JsonProperty("lat")
    public double getLat() {
        if(position == null) return 0;
        return ((G2D) position.getPosition()).getLat();
    }

    @JsonProperty("lng")
    public double getLng() {
        if(position == null) return 0;
        return ((G2D) position.getPosition()).getLon();
    }

    @JsonIgnore
    public Point getPosition() {
        return position;
    }

    public void setPositionByLatLng(double latitude, double longitude){
        position = Geometries.mkPoint(new G2D(longitude, latitude), CoordinateReferenceSystems.WGS84);
    }
}
