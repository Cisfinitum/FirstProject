INSERT INTO person(email, password, role)
VALUES ('user@gmail.com', '123','ADMIN');
INSERT INTO discount(discount)
VALUES ('50');
INSERT INTO hotel(name, city, country, numberOfStars)
VALUES ('Luxury Hotel', 'Moscow', 'Russia', '5');
INSERT INTO hotel(name, city, country, numberOfStars)
VALUES ('Court Yard Marriott', 'Moscow', 'Turkey', '4');
INSERT INTO hotel(name, city, country, numberOfStars)
VALUES ('5 seasons', 'Moscow', 'Thailand', '3');
INSERT INTO hotel(name, city, country, numberOfStars)
VALUES ('Motel', 'Moscow', 'Indonesia', '2');

INSERT INTO tourOffer(tourType, startDate, endDate, pricePerUnit, hotel_id, description, discount_id)
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
INSERT INTO tourOffer(tourType, startDate, endDate, pricePerUnit, hotel_id, description, discount_id)
VALUES ('Aaaa',CURRENT_DATE, CURRENT_DATE, '100000', '1', 'Yyyy', '1');
INSERT INTO tourOffer(tourType, startDate, endDate, pricePerUnit, hotel_id, description, discount_id)
VALUES ('Bbbb',CURRENT_DATE, CURRENT_DATE, '200000', '1', 'Hhhh', '1');
INSERT INTO tourOffer(tourType, startDate, endDate, pricePerUnit, hotel_id, description, discount_id)
VALUES ('Cccc',CURRENT_DATE, CURRENT_DATE, '300000', '1', 'Nnnn', '1');
INSERT INTO tourOffer(tourType, startDate, endDate, pricePerUnit, hotel_id, description, discount_id)
VALUES ('Dddd',CURRENT_DATE, CURRENT_DATE, '400000', '1', 'Vvvv', '1');
