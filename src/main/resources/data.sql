INSERT INTO person(email,  password, role)
VALUES ('user@gmail.com', '123','ADMIN');
INSERT INTO discount(discount)
VALUES ('50');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Luxury Hotel', 'Moscow', 'Russia', '5');
INSERT INTO tourOffer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount_id)
VALUES ('Shopping',CURRENT_DATE, CURRENT_DATE, '500000', '1', 'best tour ever', '1');
INSERT INTO reservation(client_id, tour_offer_id, number_of_persons, status, discount_id, total_price)
VALUES ('1','1', '1', 'paid', '1', '500000');