DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS hotel;
DROP TABLE IF EXISTS tourOffer;
DROP TABLE IF EXISTS reservation;

CREATE TABLE person
(
  id       NUMERIC IDENTITY PRIMARY KEY,
  email    VARCHAR(200) NOT NULL,
  password VARCHAR(512) NOT NULL,
  role     VARCHAR(200) NOT NULL
);
CREATE TABLE hotel
(
  id            NUMERIC IDENTITY PRIMARY KEY,
  name          VARCHAR(50) NOT NULL,
  city          VARCHAR(50) NOT NULL,
  country       VARCHAR(50) NOT NULL,
  number_of_stars INT
);
CREATE TABLE tour_offer
(
  id           NUMERIC IDENTITY PRIMARY KEY,
  tour_type     VARCHAR(300) NOT NULL,
  start_date    DATE,
  end_date      DATE,
  price_per_unit INT          NOT NULL,
  hotel_id     INT          NOT NULL,
  description  VARCHAR(512),
  discount_id  INT,
  FOREIGN KEY (hotel_id) REFERENCES hotel (id)
);
CREATE TABLE reservation
(
  id              NUMERIC IDENTITY PRIMARY KEY,
  client_id       INT          NOT NULL,
  tour_offer_id    INT          NOT NULL,
  number_of_people INT          NOT NULL,
  status          VARCHAR(100) NOT NULL,
  discount_id     INT,
  total_price      INT,
  FOREIGN KEY (tour_offer_id) REFERENCES tour_offer (id),
  FOREIGN KEY (client_id) REFERENCES person (id),
);