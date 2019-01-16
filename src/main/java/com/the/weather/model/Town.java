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
@Table(name = "town")
public class Town {
    public Town() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @Column(name = "city_name")
    private String city;

    @Column(name = "position")
    private Point position;

    @JsonCreator
    public Town(
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
