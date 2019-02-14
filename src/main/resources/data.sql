INSERT INTO person(email,  password, role)
VALUES ('user@gmail.com', '123','ADMIN');
INSERT INTO discount(discount)
VALUES ('50');
INSERT INTO hotel(name, city, country, numberOfStars)
VALUES ('Luxury Hotel', 'Moscow', 'Russia', '5');
INSERT INTO tourOffer(tourType, startDate, endDate, pricePerUnit, hotel_id, description, discount_id)
VALUES ('Shopping',CURRENT_DATE, CURRENT_DATE, '500000', '1', 'best tour ever', '1');
INSERT INTO reservation(client_id, tourOffer_id, numberOfPersons, status, discount_id, totalPrice)
VALUES ('1','1', '1', 'paid', '1', '500000');