INSERT INTO person(email,  password, role, phone_number, first_name, last_name)
VALUES ('admin@gmail.com', '123','ADMIN', '+79993337821', 'Frodo', 'Baggins');
INSERT INTO person(email,  password, role, phone_number, first_name, last_name)
VALUES ('user_1@gmail.com', '123','BLOCKED', '+79993337821', 'Aragorn II', 'Elessar Telcontar');
INSERT INTO person(email,  password, role, phone_number, first_name, last_name)
VALUES ('user_2@gmail.com', '123','USER', '+79993337821', 'Gollum', 'ex Smeagol ');
INSERT INTO person(email,  password, role, phone_number, first_name, last_name)
VALUES ('user_3@gmail.com', '123','BLOCKED', '+79993337821', 'Gimli', 'son of Gloin');
INSERT INTO person(email,  password, role, phone_number, first_name, last_name)
VALUES ('user_4@gmail.com', '123','USER', '+79993337821', 'Thorin ', 'Oakenshield');
INSERT INTO person(email,  password, role, phone_number, first_name, last_name)
VALUES ('user@gmail.com', '123','USER', '+79993337821', 'Samwise ', 'Gamgee');
INSERT INTO person(email,  password, role, phone_number, first_name, last_name)
VALUES ('user_5@gmail.com', '123','USER', '+79993337821', 'Legolas ', 'son of Thranduil');
INSERT INTO person(email,  password, role, phone_number, first_name, last_name)
VALUES ('user_6@gmail.com', '123','USER', '+79993337821', 'Peregrin ', 'Took');
INSERT INTO person(email,  password, role, phone_number, first_name, last_name)
VALUES ('user_7@gmail.com', '123','USER', '+79993337821', 'Saruman', 'the White');
INSERT INTO person(email,  password, role, phone_number, first_name, last_name)
VALUES ('user_8@gmail.com', '123','USER', '+79993337821', 'Gandalf', 'the Gray');
INSERT INTO person(email,  password, role, phone_number, first_name, last_name)
VALUES ('user_9@gmail.com', '123','USER', '+79993337821', 'Gandalf', 'the White');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Ugly goblin', 'Hobbiton', 'Shire', '5');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Iron axe', 'Minas Tirith', 'Gondor', '4');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('White walker', 'Edoras', 'Rohan', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Leaky pot', 'Aldburg', 'Rohan', '5');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Three pigs', 'Dunharrow', 'Rohan', '4');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Cheshire cat', 'Hornburg', 'Rohan', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Old brewer', 'Osgiliath', 'Gondor', '5');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Fairy and ale', 'Minas Ithil', 'Gondor', '4');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Muddy eye', 'Pelargir', 'Gondor', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Perforated barrel', 'Dol Amroth', 'Gondor', '5');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Gnarled hooks', 'Cair Andros', 'Gondor', '4');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('MO', 'Barad-dur', 'Mordor', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Middle-earth booze', 'Durthang', 'Mordor', '5');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Pitched troll', 'Towers of the Teet', 'Mordor', '4');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Lame pegasus', 'Cirith Ungol', 'Mordor', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Crown', 'Lorien', 'The Misty Mountains', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Divine rapier', 'Mirkwood', 'Mirkwood', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Hidden in leaf', 'Fangorn', 'The Misty Mountains', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Enchanted mango', 'Isengard', 'Gondor', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Fairy fire', 'Moria', 'The Misty Mountains', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Magic stick', 'Esgaroth', 'Esgaroth', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Honningbrew meadery', 'Rivendell', 'Rivendell', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Black-briar meadery', 'Mountains of Lune', 'Mountains of Lune', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Silvermoon', 'Lonely Mountain', 'Lonely Mountain', '3');
INSERT INTO hotel(name, city, country, number_of_stars)
VALUES ('Prancing pony', 'Bree', 'Bree', '3');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount)
VALUES ('Hunter','2019-03-05', '2019-03-10', '100000', '2', 'Gondor tour', '0');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount)
VALUES ('Active','2019-03-06', '2019-03-19', '200000', '1', 'text', '0');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount)
VALUES ('Active','2019-03-07', '2019-03-13', '300000', '3', 'text', '0');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount)
VALUES ('Entertaiment','2019-03-08', '2019-03-15', '400000', '5', 'text', '50');
INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount)
VALUES ('Shopping','2019-03-09', '2019-03-14', '500000', '1', 'text', '50');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people, archive_status,status, discount, total_price)
VALUES ('1','1', '1', 'NEW','PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people,archive_status, status, discount, total_price)
VALUES ('1','1', '1', 'NEW','PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people,archive_status, status, discount, total_price)
VALUES ('1','1', '1', 'ARCHIVED','PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people,archive_status, status, discount, total_price)
VALUES ('1','1', '1', 'NEW','PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people,archive_status, status, discount, total_price)
VALUES ('1','1', '1', 'NEW','PAID', '1', '500000');
INSERT INTO reservation(client_id, tour_offer_id, number_of_people,archive_status, status, discount, total_price)
VALUES ('1','1', '1', 'ARCHIVED','PAID', '1', '500000');

