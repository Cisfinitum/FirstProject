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
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount_id)
VALUES ('Shopping',CURRENT_DATE, CURRENT_DATE, '500000', '1', 'best tour ever', '1');
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
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount_id)
VALUES ('Aaaa',CURRENT_DATE, CURRENT_DATE, '100000', '1', 'Yyyy', '1');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount_id)
VALUES ('Bbbb',CURRENT_DATE, CURRENT_DATE, '200000', '1', 'Hhhh', '1');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount_id)
VALUES ('Cccc',CURRENT_DATE, CURRENT_DATE, '300000', '1', 'Nnnn', '1');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount_id)
VALUES ('Dddd',CURRENT_DATE, CURRENT_DATE, '400000', '1', 'Vvvv', '1');
