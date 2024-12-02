CREATE DATABASE user_db1;

USE user_db1;

CREATE TABLE users_info (
    id INT AUTO_INCREMENT primary key,
    phone_number VARCHAR(15),
    dob VARCHAR(100),
    name VARCHAR(100),
    platform VARCHAR(50),
    password VARCHAR(255)
);
