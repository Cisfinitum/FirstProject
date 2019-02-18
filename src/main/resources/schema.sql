DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS discount;
DROP TABLE IF EXISTS hotel;
DROP TABLE IF EXISTS tourOffer;
DROP TABLE IF EXISTS reservation;

CREATE TABLE person
(
  id       NUMERIC IDENTITY PRIMARY KEY,
  email VARCHAR(200) NOT NULL,
  password VARCHAR(512) NOT NULL,
  role     VARCHAR(200) NOT NULL
);
CREATE TABLE discount
(
  id       NUMERIC IDENTITY PRIMARY KEY,
  discount INT
);
CREATE TABLE hotel
(
  id            NUMERIC IDENTITY PRIMARY KEY,
  name          VARCHAR(50) NOT NULL,
  city          VARCHAR(50) NOT NULL,
  country       VARCHAR(50) NOT NULL,
  numberOfStars INT
);
CREATE TABLE tourOffer
(
  id           NUMERIC IDENTITY PRIMARY KEY,
  tourType     VARCHAR(300) NOT NULL,
  startDate    DATE,
  endDate      DATE,
  pricePerUnit INT          NOT NULL,
  hotel_id     INT          NOT NULL,
  description  VARCHAR(512),
  discount_id  INT,
  FOREIGN KEY (hotel_id) REFERENCES hotel (id),
  FOREIGN KEY (discount_id) references discount (id)
);
CREATE TABLE reservation
(
  id             NUMERIC IDENTITY PRIMARY KEY,
  client_id      INT          NOT NULL,
  tourOffer_id   INT          NOT NULL,
  numberOfPeople INT          NOT NULL,
  status         VARCHAR(100) NOT NULL,
  discount_id    INT,
  totalPrice     INT,
  FOREIGN KEY (tourOffer_id) REFERENCES tourOffer (id),
  FOREIGN KEY (client_id) REFERENCES person (id),
);