INSERT INTO person(email,  password, role)
VALUES ('admin@gmail.com', '123','ADMIN');
INSERT INTO person(email,  password, role)
VALUES ('user_1@gmail.com', '123','BLOCKED');
INSERT INTO person(email,  password, role)
VALUES ('user_2@gmail.com', '123','USER');
INSERT INTO person(email,  password, role)
VALUES ('user_3@gmail.com', '123','BLOCKED');
INSERT INTO person(email,  password, role)
VALUES ('user_4@gmail.com', '123','USER');
INSERT INTO person(email,  password, role)
VALUES ('user@gmail.com', '123','USER');
INSERT INTO person(email,  password, role)
VALUES ('user_5@gmail.com', '123','USER');
INSERT INTO person(email,  password, role)
VALUES ('user_6@gmail.com', '123','USER');
INSERT INTO person(email,  password, role)
VALUES ('user_7@gmail.com', '123','USER');
INSERT INTO person(email,  password, role)
VALUES ('user_8@gmail.com', '123','USER');
INSERT INTO person(email,  password, role)
VALUES ('user_9@gmail.com', '123','USER');
INSERT INTO discount(discount)
VALUES ('50');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Luxury Hotel', 'Moscow', 'Russia', '5');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('AxeWood Hotel', 'Florida', 'USA', '4');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('SvenMom Hotel', 'Stambul', 'Turkey', '3');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount_id)
VALUES ('Active','2019-03-05', '2019-03-10', '100000', '1', 'text', '1');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount_id)
VALUES ('Exchange','2019-03-06', '2019-03-19', '200000', '2', 'text', '1');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount_id)
VALUES ('Active','2019-03-07', '2019-03-13', '300000', '3', 'text', '1');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount_id)
VALUES ('Entertaiment','2019-03-08', '2019-03-15', '400000', '2', 'text', '1');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount_id)
VALUES ('Shopping','2019-03-09', '2019-03-14', '500000', '1', 'text', '1');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount_id, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount_id, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount_id, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount_id, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount_id, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount_id, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount_id, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, status, discount_id, total_price)
VALUES ('1','1', '1', 'PAID', '1', '500000');
