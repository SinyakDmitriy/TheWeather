CREATE TABLE weather (
    id INT(11) NOT NULL AUTO_INCREMENT,
    base varchar(512) NOT NULL,
    temperature float NOT NULL,
    pressure INT(11) NOT NULL,
    humidity INT(11) NOT NULL,
    temp_min float NOT NULL,
    temp_max float NOT NULL,
    visibility INT(11) NOT NULL,
    wind_speed INT(11) NOT NULL,
    wind_deg INT(11) NOT NULL,
    clouds_all int(11) NOT NULL,
    timestamp bigint NOT NULL,
    sunrise bigint NOT NULL,
    sunset bigint NOT NULL,
    name varchar(512) NOT NULL,
    position POINT NOT NULL,
    PRIMARY KEY (id),
    spatial key k_position (position),
    KEY POINT_ID_FK (id),
    UNIQUE KEY timestamp_position_uniq (timestamp , position)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

CREATE TABLE town (
    id INT(11) NOT NULL AUTO_INCREMENT,
    country varchar(256) not null,
    region varchar(256) not null,
    city_name varchar(256) NOT NULL,
    position POINT NOT NULL,
    PRIMARY KEY (id),
    spatial key k_position (position),
    KEY POINT_ID_FK (id),
    UNIQUE KEY country_region_city_name_uniq (country, region, city_name)
)  ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;