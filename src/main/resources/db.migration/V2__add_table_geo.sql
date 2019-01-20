CREATE TABLE IF NOT EXISTS geo (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  country_en varchar(255) NOT NULL,
  region_en varchar(255) NOT NULL,
  city_en varchar(255) NOT NULL,
  country varchar(255) NOT NULL,
  region varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  lat varchar(255) NOT NULL,
  lng varchar(255) NOT NULL,
  population int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=8450 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;