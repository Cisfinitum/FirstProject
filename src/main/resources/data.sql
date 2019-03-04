INSERT INTO person(email,  password, role, phoneNumber, firstName, lastName)
VALUES ('admin@gmail.com', '123','ADMIN', '+79993337821', 'Frodo', 'Baggins');
INSERT INTO person(email,  password, role, phoneNumber, firstName, lastName)
VALUES ('user_1@gmail.com', '123','BLOCKED', '+79993337821', 'Aragorn II', 'Elessar Telcontar');
INSERT INTO person(email,  password, role, phoneNumber, firstName, lastName)
VALUES ('user_2@gmail.com', '123','USER', '+79993337821', 'Gollum', 'ex Smeagol ');
INSERT INTO person(email,  password, role, phoneNumber, firstName, lastName)
VALUES ('user_3@gmail.com', '123','BLOCKED', '+79993337821', 'Gimli', 'son of Gloin');
INSERT INTO person(email,  password, role, phoneNumber, firstName, lastName)
VALUES ('user_4@gmail.com', '123','USER', '+79993337821', 'Thorin ', 'Oakenshield');
INSERT INTO person(email,  password, role, phoneNumber, firstName, lastName)
VALUES ('user@gmail.com', '123','USER', '+79993337821', 'Samwise ', 'Gamgee');
INSERT INTO person(email,  password, role, phoneNumber, firstName, lastName)
VALUES ('user_5@gmail.com', '123','USER', '+79993337821', 'Legolas ', 'son of Thranduil');
INSERT INTO person(email,  password, role, phoneNumber, firstName, lastName)
VALUES ('user_6@gmail.com', '123','USER', '+79993337821', 'Peregrin ', 'Took');
INSERT INTO person(email,  password, role, phoneNumber, firstName, lastName)
VALUES ('user_7@gmail.com', '123','USER', '+79993337821', 'Saruman', 'the White');
INSERT INTO person(email,  password, role, phoneNumber, firstName, lastName)
VALUES ('user_8@gmail.com', '123','USER', '+79993337821', 'Gandalf', 'the Gray');
INSERT INTO person(email,  password, role, phoneNumber, firstName, lastName)
VALUES ('user_9@gmail.com', '123','USER', '+79993337821', 'Gandalf', 'the White');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Luxury Hotel', 'Moscow', 'Russia', '5');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('AxeWood Hotel', 'Florida', 'USA', '4');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('SvenMom Hotel', 'Stambul', 'Turkey', '3');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount)
VALUES ('Active','2019-03-05', '2019-03-10', '100000', '1', 'text', '0');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount)
VALUES ('Exchange','2019-03-06', '2019-03-19', '200000', '2', 'text', '0');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount)
VALUES ('Active','2019-03-07', '2019-03-13', '300000', '3', 'text', '0');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount)
VALUES ('Entertaiment','2019-03-08', '2019-03-15', '400000', '2', 'text', '50');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount)
VALUES ('Shopping','2019-03-09', '2019-03-14', '500000', '1', 'text', '50');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
