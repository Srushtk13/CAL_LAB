CREATE DATABASE IF NOT EXISTS vsms;
USE vsms;

CREATE TABLE IF NOT EXISTS vehicle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    owner_name VARCHAR(100),
    vehicle_number VARCHAR(50),
    model VARCHAR(50)
);