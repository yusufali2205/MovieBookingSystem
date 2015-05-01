/* Drop the tables if they are already present in database */
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS MOVIE;
DROP TABLE IF EXISTS ACTOR;
DROP TABLE IF EXISTS THEATRE;
DROP TABLE IF EXISTS PLAYED_IN;
DROP TABLE IF EXISTS SEATS;

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

create table IF NOT EXISTS MOVIE
(
	movie_id VARCHAR(50),
	movie_name VARCHAR(50),
	release_date DATE,
	genre VARCHAR(20),
	running	BOOLEAN,
	PRIMARY KEY (  movie_id )
) ENGINE = InnoDB;

create table IF NOT EXISTS REVIEWS
(
	movie_id VARCHAR(50) REFERENCES MOVIE(`movie_id`) ON DELETE CASCADE,
	username VARCHAR(50) REFERENCES USER(`username`) ON DELETE CASCADE,
	rating INT not null,
	comment VARCHAR(200),
	PRIMARY KEY (  movie_id, username )
) ENGINE = InnoDB;


create table IF NOT EXISTS ACTOR
(
	actor_id VARCHAR(10),
	actor_name VARCHAR(50) not null,
	movie_id VARCHAR(50) REFERENCES MOVIE(`movie_id`) ON DELETE CASCADE,
	PRIMARY KEY (actor_id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS THEATRE
(
	theatre_id VARCHAR(10),
	theatre_name VARCHAR(50),
	show_time VARCHAR(50),
	PRIMARY KEY (theatre_id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS PLAYED_IN
(
	theatre_id VARCHAR(10) REFERENCES THEATRE(`theatre_id`) ON DELETE CASCADE,
	movie_id VARCHAR(50) REFERENCES MOVIE(`movie_id`) ON DELETE CASCADE,
	show_time VARCHAR(10),
	date DATE,
	PRIMARY KEY (theatre_id, movie_id, show_time, date)
);

CREATE TABLE IF NOT EXISTS SEATS
(
	booking_id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(50) REFERENCES USER(`username`) ON DELETE CASCADE,
	movie_id VARCHAR(50) REFERENCES MOVIE(`movie_id`) ON DELETE CASCADE,
	theatre_id VARCHAR(10) REFERENCES THEATRE(`theatre_id`) ON DELETE CASCADE,
	show_time VARCHAR(10),
	seat_numbers VARCHAR(150),
	PRIMARY KEY (booking_id, username, movie_id, theatre_id, show_time, seat_numbers)
) ENGINE = InnoDB;

