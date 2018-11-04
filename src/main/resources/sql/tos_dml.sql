INSERT INTO tos.cities (name) VALUES ('Yerevan'), ('Moscow'), ('Tbilisi'), ('Praha'), ('Paris');
INSERT INTO tos.companies (name)
VALUES ('IRAN ASEMAN AIRLINES'), ('AIR ARMENIA CJSC'), ('SIBERIA AIRLINES'), ('CZECH AIRLINES');
INSERT INTO tos.aircrafts (company_id, model, class1_count, class2_count) VALUES
  (1, 'Tu 154', 30, 60),
  (1, 'SuperJet-100', 40, 60),
  (2, 'Airbus A320', 40, 100),
  (3, 'Boeing-737', 20, 100),
  (3, 'Airbus A320', 30, 70),
  (3, 'Tu 154', 30, 60),
  (4, 'SuperJet-100', 40, 60),
  (4, 'Airbus A320', 50, 8);


INSERT INTO tos.flights (departure_date, arrival_date, departure_city_id, arrival_city_id, aircraft_id, 
	class1_price, class2_price, class1_tickets_available, class2_tickets_available)
VALUES
  ('2013-12-23 03:45:00', '2013-12-23 05:15:00', 1, 2, 1, 320, 280, 30, 60),
  ('2013-12-23 03:45:00', '2013-12-23 05:15:00', 1, 2, 1, 320, 280, 30, 60),
  ('2013-12-23 06:35:00', '2013-12-23 09:05:00', 1, 4, 3, 280, 240, 40, 100),
  ('2013-12-23 03:45:00', '2013-12-23 05:15:00', 1, 2, 4, 300, 260, 20, 100),
  ('2013-12-23 03:45:00', '2013-12-23 05:15:00', 5, 1, 1, 330, 280, 30, 60);

/*INSERT INTO tos.orders (flight_id, class1_tickets_Count, lass2_tickets_Count, credit_card) VALUES
  (1, 2, 0, 10001111),
  (1, 2, 2, 10001113),
  (1, 1, 2, 10001114),
  (2, 1, 2, 10001116),
  (3, 2, 2, 10001117),
  (3, 1, 2, 10001118),
  (5, 1, 1, 10001131),
  (5, 2, 1, 10001132),
  (5, 2, 4, 10001138);
*/

