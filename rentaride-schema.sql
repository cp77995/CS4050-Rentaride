/*
*rentaride-schema.sql
*/

DROP DATABASE IF EXISTS rentaride;
CREATE DATABASE rentaride;
USE rentaride;

DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	username VARCHAR(20),
	password VARCHAR(20),
	email VARCHAR(255),
	address VARCHAR(255),
	create_date DATETIME,
	member_until DATETIME,
	licanse_state VARCHAR(2),
	license_num VARCHAR(255),
	cc_number INT,
	cc_expiration DATETIME,
	status VARCHAR(255)
);

DROP TABLE IF EXISTS comment;
CREATE TABLE comment (
	id INT PRIMARY KEY AUTO_INCREMENT,
	customer_id INT,
		FOREIGN KEY (customer_id) REFERENCES customer(id),
	rental_id INT,
		FOREIGN KEY (rental_id) REFERENCES rental(id),
	text VARCHAR(1024),
	date DATETIME
);

DROP TABLE IF EXISTS admin;
CREATE TABLE admin (
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	username VARCHAR(20),
	password VARCHAR(20),
	email VARCHAR(255),
	address VARCHAR(255),
	create_date DATETIME,
);

DROP TABLE IF EXISTS hourly_price;
CREATE TABLE hourly_price (
	id PRIMARY KEY AUTO_INCREMENT,
	INT max_hours,
	INT price
);

DROP TABLE IF EXISTS vehicle_type;
CREATE TABLE vehicle_type (
	id INT PRIMARY KEY AUTO_INCREMENT,
	hourly_price_id INT,
		FOREIGN KEY (hourly_price_id) REFERENCES hourly_price(id),
	type VARCHAR(255)
);

DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle (
	id INT PRIMARY KEY AUTO_INCREMENT,
	type_id INT,
		FOREIGN KEY (type_id) REFERENCES vehicle_type(id),
	location_id INT,
		FOREIGN KEY (location_id) REFERENCES rental_location(id),
	make VARCHAR(255),
	model VARCHAR(255),
	year INT,
	mileage INT,
	tag VARCHAR(255),
	last_serviced DATETIME,
	status enum('in_location', 'in_rental') DEFAULT 'inlocation',
	condition enum('good', 'needs_mait') DEFAULT 'good'
);

DROP TABLE IF EXISTS rental_location;
CREATE TABLE rental_location (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255),
	address VARCHAR(255),
	capacity INT
);

DROP TABLE IF EXISTS membership;
CREATE TABLE membership (
	INT PRIMARY KEY AUTO_INCREMENT,
	price FLOAT(5,2)
);

DROP TABLE IF EXISTS reservation;
CREATE TABLE reservation (
	id INT PRIMARY KEY AUTO_INCREMENT,
	customer_id INT,
		FOREIGN KEY (customer_id) REFERENCES customer(id),
	rental_location_id INT,
		FOREIGN KEY (rental_location_id) REFERENCES customer(id),
	vehicle_type_id,
		FOREIGN KEY (vehicle_type_id) REFERENCES vehicle_type(id),
	pickup_time DATETIME,
	length INT,
	cancelled INT
);

DROP TABLE IF EXISTS rental;
CREATE TABLE rental (
	id INT PRIMARY KEY AUTO_INCREMENT,
	reservation_id INT,
		FOREIGN KEY (reservation_id) REFERENCES reservation(id),
	customer_id INT,
		FOREIGN KEY (customer_id) REFERENCES customer(id),
	vehicle_id INT,
		FOREIGN KEY (vehicle_id) REFERENCES customer(id),
	comment_id INT,
		FOREIGN KEY (comment_id) REFERENCES comment(id)
	pickup_time DATETIME,
	return_time TIMESTAMP,
	charges FLOAT(5,2),
	time_exceeded INT
);

