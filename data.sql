CREATE DATABASE IF NOT EXISTS `parking` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `parking`;


CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `neighborhood` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `employees` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `parking_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrpsvtwviij0r8g5kgwku1wvxl` (`parking_id`),
  KEY `FKqs505klecu2miswk3wr733m8v` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `parking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pkqnp9njgq9wvyttbc42bg2d7` (`cnpj`),
  KEY `FKnk2u42ivb9ftxuxxdgqag3e8f` (`address_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `phones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ddd` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `parking_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc50nfp82mjmkds6jn5leq2uq9` (`parking_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `types` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `vacancies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `quantity_ocuppied` int(11) DEFAULT NULL,
  `parking_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2jffoabpp2fcu3pbtxmarscgj` (`parking_id`),
  KEY `FK1dr2mh09qfbyd43xe6wvngyf3` (`type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `vehicles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) DEFAULT NULL,
  `license_plate` varchar(255) DEFAULT NULL,
  `parking_id` bigint(20) DEFAULT NULL,
  `vehicle_model_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9vovnbiegxevdhqfcwvp2g8pj` (`license_plate`),
  KEY `FK4si86ainsvxuhdtqpr7fshgni` (`parking_id`),
  KEY `FKkvwjes4u21x8el7qsg5pg63ug` (`vehicle_model_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `vehicles_reports` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `entry_date` datetime DEFAULT NULL,
  `exit_date` datetime DEFAULT NULL,
  `time_parked_in_minutes` bigint(20) DEFAULT NULL,
  `parking_id` bigint(20) DEFAULT NULL,
  `vehicle_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1d6uv8rxdtpfqt9xltfdeus13` (`parking_id`),
  KEY `FK2uar1joo3ieva4prpx5dj2mxt` (`vehicle_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `vehicle_brands` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `vehicle_models` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `vehicle_brand_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdtflqu4u50k0q3ay2ghh6fp5h` (`type_id`),
  KEY `FKb2g4ydys87fj4o5scgl60butv` (`vehicle_brand_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

insert into vehicle_brands(name) values('Chevrolet');
insert into vehicle_brands(name) values('Volkswagens');
insert into vehicle_brands(name) values('Yamaha');
insert into vehicle_brands(name) values('Honda');

insert into types(id, name) VALUES(1, 'CAR');
insert into types(id, name) VALUES(2, 'MOTORCYCLE');

insert into vehicle_models(name, type_id, vehicle_brand_id) values('CIVIC', 1, 4);
insert into vehicle_models(name, type_id, vehicle_brand_id) values('FIT', 1, 4);
insert into vehicle_models(name, type_id, vehicle_brand_id) values('MT-07', 2, 3);
insert into vehicle_models(name, type_id, vehicle_brand_id) values('XSR', 2, 3);

insert into role(name) values('ROLE_MANAGER');
insert into role(name) values('ROLE_ANALYST');



