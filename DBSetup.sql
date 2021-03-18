--Create Database for Food Delivery Service
CREATE DATABASE glints_db;

--Create Required Tables
CREATE TABLE restaurant_details (restaurant_id BIGINT (20) NOT NULL AUTO_INCREMENT,
cash_balance DECIMAL(10,2),
restaurant_name VARCHAR(500),
PRIMARY KEY(restaurant_id));

CREATE TABLE menu_details (menu_id BIGINT (20) NOT NULL AUTO_INCREMENT,
price DECIMAL(10,2),
dish_name VARCHAR(500),
restaurant_id BIGINT (20), 
FOREIGN KEY (restaurant_id)
REFERENCES restaurant_details(restaurant_id),
PRIMARY KEY(menu_id));

CREATE TABLE user_details (user_id BIGINT (20) NOT NULL,
cash_balance DECIMAL(10,2),
user_name VARCHAR(500),
PRIMARY KEY(user_id));

CREATE TABLE purchase_history (transaction_id BIGINT (20) NOT NULL AUTO_INCREMENT,
transaction_amount DECIMAL(10,2),
dish_name VARCHAR(500),
restaurant_name VARCHAR(500), 
user_id BIGINT (20) NOT NULL,
transaction_date DATETIME,
FOREIGN KEY (user_id)
REFERENCES user_details(user_id),
PRIMARY KEY(transaction_id));

CREATE TABLE opening_hours (id BIGINT (20) NOT NULL AUTO_INCREMENT,
day_of_week VARCHAR(10),
open_time VARCHAR(50),
close_time VARCHAR(50),
restaurant_id BIGINT (20), 
FOREIGN KEY (restaurant_id)
REFERENCES restaurant_details(restaurant_id),
PRIMARY KEY(id));
