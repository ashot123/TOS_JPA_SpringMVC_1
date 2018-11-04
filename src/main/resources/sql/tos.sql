DROP TABLE IF EXISTS tos.orders;
DROP TABLE IF EXISTS tos.flights;
DROP TABLE IF EXISTS tos.aircrafts;
DROP TABLE IF EXISTS tos.companies;
DROP TABLE IF EXISTS tos.cities;

CREATE TABLE tos.cities (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) UNIQUE,
  PRIMARY KEY(id)
);


CREATE TABLE tos.companies (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20),
	PRIMARY KEY(id)
);

CREATE TABLE tos.aircrafts (
	id INT NOT NULL AUTO_INCREMENT,
	company_id INT NOT NULL,
  FOREIGN KEY (company_id) REFERENCES tos.companies(id) ON DELETE CASCADE,
	model VARCHAR(20),
	class1_count INT NOT NULL,
	class2_count INT NOT NULL,
	PRIMARY KEY(id),
	INDEX company_id_index (company_id ASC)

);

CREATE TABLE tos.flights (
  id INT NOT NULL AUTO_INCREMENT,
  departure_date DATETIME NOT NULL,
  arrival_date DATETIME NOT NULL,
  departure_city_id INT NOT NULL,
  FOREIGN KEY (departure_city_id) REFERENCES tos.cities(id) ON DELETE CASCADE,
  arrival_city_id INT NOT NULL,
  FOREIGN KEY (arrival_city_id) REFERENCES tos.cities(id) ON DELETE CASCADE,
  aircraft_id INT NOT NULL,
  FOREIGN KEY (aircraft_id) REFERENCES tos.aircrafts(id) ON DELETE CASCADE,
  class1_price INT NOT NULL,
  class2_price INT NOT NULL,
  class1_tickets_available INT UNSIGNED NOT NULL,
  class2_tickets_available INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  INDEX departure_city_id_index (departure_city_id ASC),
  INDEX arrival_city_id_index (arrival_city_id ASC),
  INDEX aircraft_id_index (aircraft_id ASC),
  CHECK (class1_tickets_available >= 0),
  CHECK (class2_tickets_available >= 0));

CREATE TABLE tos.orders (
  id INT NOT NULL AUTO_INCREMENT,
  flight_id INT NOT NULL,
  FOREIGN KEY (flight_id) REFERENCES tos.flights(id) ON DELETE CASCADE,
  class1_tickets_Count INT NOT NULL,
  class2_tickets_Count INT NOT NULL,
  credit_card VARCHAR(20) NOT NULL,
  PRIMARY KEY (id),
  INDEX flight_id_index (flight_id ASC));


