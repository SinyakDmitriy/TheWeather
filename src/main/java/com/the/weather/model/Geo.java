package com.the.weather.model;

import lombok.Data;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.geolatte.geom.Point;
import javax.persistence.*;

@Data
@Entity
@Embeddable
@Table(name = "geo")
public class Geo {
    public Geo() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "country_en")
    private String countryEN;

    @Column(name = "region_en")
    private String regionEN;

    @Column(name = "city_en")
    private String cityEN;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    @Column(name = "population")
    private long population;

    public Point getPosition(){
        return Geometries.mkPoint(new G2D(Double.parseDouble(lng), Double.parseDouble(lat)), CoordinateReferenceSystems.WGS84);
    }
}
