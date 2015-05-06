/* Drop the tables if they are already present in database */
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS MOVIE;
DROP TABLE IF EXISTS ACTOR;
DROP TABLE IF EXISTS ACTOR_MOVIES;
DROP TABLE IF EXISTS THEATRE;
DROP TABLE IF EXISTS PLAYED_IN;
DROP TABLE IF EXISTS REVIEWS;
DROP TABLE IF EXISTS BOOKINGS;

create table IF NOT EXISTS USER
(
	username VARCHAR(50),
	firstname VARCHAR(50),
	lastname VARCHAR(50),
	type VARCHAR(10),
	password VARCHAR(50),
	email VARCHAR(50) unique,
	PRIMARY KEY (  username )
) ENGINE = InnoDB;

INSERT INTO USER VALUES("yusuf", "Yusuf", "Ali", "admin", "yusuf", "yusuf@ali.com");
INSERT INTO USER VALUES("abhishek", "Abhi", "Shek", "customer", "abhishek", "abhi@shek.com");

create table IF NOT EXISTS MOVIE
(
	movie_id INT,
	movie_name VARCHAR(50),
	release_date DATE,
	genre VARCHAR(20),
	running	BOOLEAN,
	PRIMARY KEY (  movie_id )
) ENGINE = InnoDB;

INSERT INTO MOVIE VALUES(1, "Kal ho na ho", "2015-05-01", "romance", true);
INSERT INTO MOVIE VALUES(2, "3 Idiots", "2015-05-01", "comedy", true);

create table IF NOT EXISTS REVIEWS
(
	movie_id INT REFERENCES MOVIE(`movie_id`) ON DELETE CASCADE,
	username VARCHAR(50) REFERENCES USER(`username`) ON DELETE CASCADE,
	rating INT not null,
	comment VARCHAR(200),
	PRIMARY KEY (  movie_id, username )
) ENGINE = InnoDB;

INSERT INTO REVIEWS VALUES(1, "abhishek", 5, "Loved this movie");

create table IF NOT EXISTS ACTOR
(
	actor_id INT NOT NULL AUTO_INCREMENT,
	actor_name VARCHAR(50) not null,
	PRIMARY KEY (actor_id)
) ENGINE = InnoDB;

INSERT INTO ACTOR VALUES(1, "Shahrukh Khan");
INSERT INTO ACTOR VALUES(2, "Preeti Zinta");

create table IF NOT EXISTS ACTOR_MOVIES
(
	actor_id INT NOT NULL REFERENCES ACTOR(`actor_id`) ON DELETE CASCADE,
	movie_id INT NOT NULL REFERENCES MOVIE(`movie_id`) ON DELETE CASCADE,
	PRIMARY KEY (actor_id, movie_id)
) ENGINE = InnoDB;

INSERT INTO ACTOR_MOVIES VALUES(1, 1);
INSERT INTO ACTOR_MOVIES VALUES(2, 1);

CREATE TABLE IF NOT EXISTS THEATRE
(
	theatre_id INT NOT NULL AUTO_INCREMENT,
	theatre_name VARCHAR(50),
	show_time VARCHAR(50),
	PRIMARY KEY (theatre_id)
) ENGINE = InnoDB;

INSERT INTO THEATRE VALUES(1, "PVR", "1200,1500,1800,2100");

CREATE TABLE IF NOT EXISTS PLAYED_IN
(
	theatre_id INT REFERENCES THEATRE(`theatre_id`) ON DELETE CASCADE,
	movie_id INT REFERENCES MOVIE(`movie_id`) ON DELETE CASCADE,
	show_time VARCHAR(10),
	last_date DATE,
	PRIMARY KEY (theatre_id, movie_id, show_time, last_date)
) ENGINE = InnoDB;

INSERT INTO PLAYED_IN VALUES(1, 1, "1200", "2015-05-15");
INSERT INTO PLAYED_IN VALUES(1, 1, "1500", "2015-05-15");
INSERT INTO PLAYED_IN VALUES(1, 2, "1200", "2015-05-15");
INSERT INTO PLAYED_IN VALUES(1, 2, "1500", "2015-05-15");

CREATE TABLE IF NOT EXISTS BOOKINGS
(
	booking_id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(50) REFERENCES USER(`username`) ON DELETE CASCADE,
	movie_id INT REFERENCES MOVIE(`movie_id`) ON DELETE CASCADE,
	theatre_id INT REFERENCES THEATRE(`theatre_id`) ON DELETE CASCADE,
	show_time VARCHAR(10),
	date DATE,
	seat_numbers VARCHAR(150),
	PRIMARY KEY (booking_id, username, movie_id, theatre_id, show_time, date, seat_numbers)
) ENGINE = InnoDB;

INSERT INTO BOOKINGS VALUES("", "abhishek", 1, 1, "1200", "2015-05-05", "A1,A2");
INSERT INTO BOOKINGS VALUES("", "abhishek", 1, 1, "1200", "2015-05-05", "B1,B2");
